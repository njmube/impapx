package com.luxsoft.impapx.contabilidad


import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.luxsoft.cfd.ComprobanteFiscal;
import com.luxsoft.impapx.FacturaDeGastos;
import com.luxsoft.impapx.FacturaDeImportacion;
import com.luxsoft.impapx.Pedimento;
import com.luxsoft.impapx.TipoDeCambio;
import com.luxsoft.impapx.Venta;
import com.luxsoft.impapx.VentaDet;
import com.luxsoft.impapx.cxc.CXCAplicacion;
import com.luxsoft.impapx.cxc.CXCNota;
import com.luxsoft.impapx.cxp.Aplicacion;
import com.luxsoft.impapx.cxp.NotaDeCredito;
import com.luxsoft.impapx.tesoreria.Comision;
import com.luxsoft.impapx.tesoreria.CompraDeMoneda;
import com.luxsoft.impapx.tesoreria.Inversion;
import com.luxsoft.impapx.tesoreria.MovimientoDeCuenta;
import com.luxsoft.impapx.tesoreria.PagoProveedor;
import com.luxsoft.impapx.tesoreria.SaldoDeCuenta;
import com.luxsoft.impapx.tesoreria.Traspaso;

class PolizaDeDiarioController {

	def polizaService
	
     def index() {
	   redirect action: 'list', params: params
    }
	 
	def mostrarPoliza(long id){
		def poliza=Poliza.findById(id,[fetch:[partidas:'eager']])
		render (view:'poliza' ,model:[poliza:poliza,partidas:poliza.partidas])
	}
	 
	def list() {
		if(!session.periodoContable){
			PeriodoContable periodo=new PeriodoContable()
			periodo.actualizarConFecha()
			session.periodoContable=periodo
		}
		PeriodoContable periodo=session.periodoContable
		def sort=params.sort?:'fecha'
		def order=params.order?:'desc'
		
		def polizas=Poliza.findAllByTipoAndFechaBetween('DIARIO',periodo.inicio,periodo.fin,[sort:sort,order:order])
		[polizaInstanceList: polizas, polizaInstanceTotal: polizas.size()]
	}
	
	def generarPoliza(String fecha){
		Date dia=Date.parse("dd/MM/yyyy",fecha)
		
		params.dia=dia
		
		//println 'Generando poliza: '+params
		
		//Prepara la poliza
		Poliza poliza=new Poliza(tipo:'DIARIO',folio:1, fecha:dia,descripcion:'Poliza '+dia.text(),partidas:[])
		
		//Collecciones usadas mas de una vez
		def facturas=Venta.findAll("from Venta v  where date(v.cfd.fecha)=? and v.tipo=?",[dia,'VENTA'])
		
		// Procesadores
		
		procesarFacturacion(poliza, dia, facturas)
		procesarCostoDeVentas(poliza, dia, facturas)		
		//procesarAltaDeAnticipos(poliza, dia)
		procesarCobroSaldoDeudor(poliza, dia)
		procesarCompraDeMonedaExtranjera(poliza, dia)
		procesarComisionesBancarias(poliza, dia)
		procesarTraspasosBancarios(poliza, dia)
		
		procesarInversionesAlta(poliza, dia)
		procesarInversionesRetorno(poliza, dia)
		procesarVariacionCambiariaBancos(poliza, dia)
		procesarVariacionCambiariaProveedores(poliza, dia)
		
		procesarDescuentosCxP(poliza, dia)
		procesarNotasDeCreditoCxC(poliza, dia)
		procesarNotasDeCargoCxC(poliza, dia)
		procesarProvisionDeGastos(poliza, dia)
		
		//Salvar la poliza
		poliza.debe=poliza.partidas.sum (0.0,{it.debe})
		poliza.haber=poliza.partidas.sum(0.0,{it.haber})
		poliza=polizaService.salvarPoliza(poliza)
		//poliza.folio=polizaService.nextFolio(poliza)
		//poliza.save(failOnError:true)
		redirect action: 'mostrarPoliza', params: [id:poliza.id]
	}
	
	private procesarFacturacion(def poliza,def dia,def facturas){
		
		def asiento="FACTURACION"
		facturas.each{ fac->
			
			//Cargo a clientes
			def clave="106-$fac.cliente.subCuentaOperativa"
			def cuenta=CuentaContable.findByClave(clave)
			//println 'Cuenta localizada: '+cuenta
			if(!cuenta) throw new RuntimeException("No existe la cuenta para el cliente: "+fac.cliente+ 'Clave: '+clave)
			poliza.addToPartidas(
					cuenta:cuenta,
					debe:fac.total,
					haber:0.0,
					asiento:asiento,
					descripcion:"Fecha:$fac.cfd.fecha $fac.cliente.nombre",
					referencia:"$fac.cfd.folio"
					,fecha:poliza.fecha
					,tipo:poliza.tipo
					,entidad:'Venta'
					,origen:fac.id)
			
			//Abono a ventas
			clave="401-$fac.cliente.subCuentaOperativa"
			cuenta=CuentaContable.findByClave(clave)
			if(!cuenta) throw new RuntimeException("No existe la cuenta para el cliente: "+fac.cliente+ 'Clave: '+clave)
			poliza.addToPartidas(
				cuenta:cuenta,
				debe:0.0,
				haber:fac.importe,
				asiento:asiento,
				descripcion:"Fecha:$fac.cfd.fecha $fac.cliente.nombre",
				referencia:"$fac.cfd.folio"
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Venta'
				,origen:fac.id)
			
			//Abono a iva por trasladar
			poliza.addToPartidas(
				cuenta:CuentaContable.findByClave('206-0002'),
				debe:0.0,
				haber:fac.impuestos,
				asiento:asiento,
				descripcion:"Fecha:$fac.cfd.fecha $fac.cliente.nombre",
				referencia:"$fac.cfd.folio"
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Venta'
				,origen:fac.id)
				
				
		}
	}
	
	private procesarCostoDeVentas(def poliza,def dia,def facturas){
		// Asiento COSTO DE VENTA
		def asiento='COSTO DE VENTA'
		facturas.each{fac->
			def costoNeto=fac.partidas.sum(0.0,{it.embarque.costoBruto})
			def embarque=fac.partidas.embarque.embarque.id			
			
			System.out.println("******** embarque_id:"+embarque+" **********");
			
			//Abono al inventario
			poliza.addToPartidas(
				cuenta:CuentaContable.findByClave('119-0001'),
				debe:0.0,
				haber:costoNeto,
				asiento:asiento,
				descripcion:"Fecha:$fac.cfd.fecha $fac.cliente.nombre Pedimento:$fac.cfd.pedimento",
				referencia:"$fac.cfd.folio"
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Venta'
				,origen:fac.id)
			
			//Cargo a costo de venta
			poliza.addToPartidas(
				cuenta:CuentaContable.findByClave('501-0001'),
				debe:costoNeto,
				haber:0.0,
				asiento:asiento,
				descripcion:"Fecha:$fac.cfd.fecha $fac.cliente.nombre Pedimento:$fac.cfd.pedimento",
				referencia:"$fac.cfd.folio"
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Venta'
				,origen:fac.id)
			
		}
	}
	
	//Genera el alta de anticipos 
	
	
	
	private procesarCobroSaldoDeudor(def poliza,def dia){
		//Asiento: 
		def asiento='SALDO DEUDOR'
		def cobros=MovimientoDeCuenta.findAll("from MovimientoDeCuenta m where m.concepto=? and date(m.fecha)=?",['SALDO_DEUDOR',dia])
		cobros.each{ cobro ->
			
			//Cargo a bancos
			def cuentaDeBanco=cobro.cuenta
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:cobro.importe.abs()*cobro.tc,
				haber:0.0,
				asiento:asiento,
				descripcion:"$cobro.cuenta ",
				referencia:"$cobro.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'MovimientoDeCuenta'
				,origen:cobro.id)
			
			//Abono a deudor
			
			def cuenta=cobro.cuentaDeudora
			if(!cuenta) throw new RuntimeException("No existe la cuenta deudora para el cobro : $cobro ")
			poliza.addToPartidas(
				cuenta:cuenta,
				debe:0.0,
				haber:cobro.importe.abs()*cobro.tc,
				asiento:asiento,
				descripcion:"$cobro.cuenta ",
				referencia:"$cobro.referenciaBancaria"
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'MovimientoDeCuenta'
				,origen:cobro.id)
		}
	}
	
	private procesarCompraDeMonedaExtranjera(def poliza,def dia){
		def asiento='COMPRA MONEDA'
		
		def compras=CompraDeMoneda.findAll("from CompraDeMoneda c where date(c.fecha)=?",[dia])
		
		compras.each{ compra ->
			
			
			
			//Cargo a la cuenta destino
			def cuentaDeBanco=compra.cuentaDestino
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:compra.ingreso.importe.abs()*compra.tipoDeCambioCompra,
				haber:0.0,
				asiento:asiento,
				descripcion:"T.C: $compra.tipoDeCambioCompra $compra.ingreso.importe ($compra.cuentaDestino.moneda )",
				referencia:"$compra.pagoProveedor.egreso.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'CompraDeMoneda'
				,origen:compra.id)
			
			//Abono a la cuenta origen
			cuentaDeBanco=compra.cuentaOrigen
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:0.0,
				haber:compra.pagoProveedor.egreso.importe.abs(),
				asiento:asiento,
				descripcion:"T.C: $compra.tipoDeCambioCompra $compra.ingreso.importe ($compra.cuentaDestino.moneda )",
				referencia:"$compra.pagoProveedor.egreso.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'CompraDeMoneda'
				,origen:compra.id)
			
		}
	}
	
	private procesarComisionesBancarias(def poliza,def dia){
		def asiento="COMISIONES BANCARIAS"
		
		def comisiones=Comision.findAll("from Comision m where date(m.fecha)=?",[dia])
		
		comisiones.each{comision ->
			
			// 1. Cargo a comisiones bancarias
			def cuenta=CuentaContable.findByClave('600-0013')
			if(cuenta==null)
				throw new RuntimeException("No existe la cuenta contable 600-0013")
			poliza.addToPartidas(
				cuenta:cuenta,
				debe:comision.comision.abs()*comision.tc,
				haber:0.0,
				asiento:asiento,
				descripcion:"Comision $comision.cuenta ",
				referencia:"$comision.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Comision'
				,origen:comision.id)
			
			// 2. Cargo a IVA acreditable
			cuenta=CuentaContable.findByClave('117-0001')
			if(cuenta==null)
				throw new RuntimeException("No existe la cuenta contable 117-0001")
			poliza.addToPartidas(
				cuenta:cuenta,
				debe:comision.impuesto.abs()*comision.tc,
				haber:0.0,
				asiento:asiento,
				descripcion:" IVA comision $comision.cuenta ",
				referencia:"$comision.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Comision'
				,origen:comision.id)
			
			
			// 3. Abono al banco (Por el importe de la comision)
			def cuentaDeBanco=comision.cuenta
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:0.0,
				haber:comision.comision.abs()*comision.tc,
				asiento:asiento,
				descripcion:"Comision $comision.cuenta ",
				referencia:"$comision.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Comision'
				,origen:comision.id)
			
			// 4. Abono al IVA (Por la comision)
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:0.0,
				haber:comision.impuesto.abs()*comision.tc,
				asiento:asiento,
				descripcion:"IVA comision $comision.cuenta ",
				referencia:"$comision.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Comision'
				,origen:comision.id)
			
			// 5. Cargo al IETU Deducible
			cuenta=CuentaContable.findByClave('900-0003')
			if(cuenta==null)
				throw new RuntimeException("No existe la cuenta contable 900-0003")
			poliza.addToPartidas(
				cuenta:cuenta,
				debe:comision.comision.abs()*comision.tc,
				haber:0.0,
				asiento:asiento,
				descripcion:"IETU comisiones bancarias $comision.cuenta ",
				referencia:"$comision.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Comision'
				,origen:comision.id)
			
			// 6. Abono al Deducible IETU
			cuenta=CuentaContable.findByClave('901-0003')
			if(cuenta==null)
				throw new RuntimeException("No existe la cuenta contable 901-0003")
			poliza.addToPartidas(
				cuenta:cuenta,
				debe:0.0,
				haber:comision.comision.abs()*comision.tc,
				asiento:asiento,
				descripcion:"IETU comisiones bancarias $comision.cuenta ",
				referencia:"$comision.referenciaBancaria",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Comision'
				,origen:comision.id)
		
		}
		
		
	}
	
	private procesarTraspasosBancarios(def poliza,def dia){
		def asiento='TRASPASO BANCARIO'
		
		def traspasos=Traspaso.findAll("from Traspaso c where date(c.fecha)=?",[dia])
		
		traspasos.each{ traspaso ->
			
			
			
			//Cargo a la cuenta destino
			def cuentaDeBanco=traspaso.cuentaDestino
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:traspaso.importe.abs(),
				haber:0.0,
				asiento:asiento,
				descripcion:"$traspaso.cuentaDestino ",
				referencia:traspaso.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Traspaso'
				,origen:traspaso.id)
			
			//Abono a la cuenta origen
			cuentaDeBanco=traspaso.cuentaOrigen
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:0.0,
				haber:traspaso.importe.abs(),
				asiento:asiento,
				descripcion:"$traspaso.cuentaOrigen  ",
				referencia:traspaso.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Traspaso'
				,origen:traspaso.id)
			
		}
	}
	
	
	private procesarInversionesAlta(def poliza,def dia){
		def asiento='INVERSION ENTRADA'
		
		def inversiones=Inversion.findAll("from Inversion c where date(c.fecha)=?",[dia])
		
		inversiones.each{ inversion ->
			
			//Abono a Inversion
			def cuentaDeBanco=inversion.cuentaDestino
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:0.0,
				haber:inversion.importe.abs(),
				asiento:asiento,
				descripcion:"$inversion.cuentaOrigen  ",
				referencia:inversion.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Inversion'
				,origen:inversion.id)
			
			
			//Cargo a Disponible en inversion
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave('103-0003'),
				debe:inversion.importe.abs(),
				haber:0.0,
				asiento:asiento,
				descripcion:"$inversion.cuentaOrigen  ",
				referencia:inversion.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Inversion'
				,origen:inversion.id)
			
		}
	}
	
	private procesarInversionesRetorno(def poliza,def dia){
		def asiento='INVERSION RETORNO'
		
		def inversiones=Inversion.findAll("from Inversion c where date(c.vencimiento)=?",[dia])
		
		inversiones.each{ inversion ->
			
			
			
			//Cargo a inversion
			def cuentaDeBanco=inversion.cuentaDestino
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:inversion.importe.abs(),
				haber:0.0,
				asiento:asiento,
				descripcion:"$inversion.cuentaOrigen  ",
				referencia:inversion.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Inversion'
				,origen:inversion.id)
			
			//Cargo a inversion
			cuentaDeBanco=inversion.cuentaDestino
			if(cuentaDeBanco.cuentaContable==null)
				throw new RuntimeException("Cuenta de banco sin cuenta contable asignada: $cuentaDeBanco")
			
			// Cargo al banco (rendimiento)
			poliza.addToPartidas(
				cuenta:cuentaDeBanco.cuentaContable,
				debe:inversion.rendimientoReal.abs(),
				haber:0.0,
				asiento:asiento,
				descripcion:"$inversion.cuentaOrigen ",
				referencia:inversion.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Inversion'
				,origen:inversion.id)
			//Cargo al ISR Bancario (por rendimiento)
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave('750-0003'),
				debe:inversion.importeIsr,
				haber:0.0,
				asiento:asiento,
				descripcion:"$inversion.cuentaOrigen  ",
				referencia:inversion.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Inversion'
				,origen:inversion.id)
			
			//Abono a Disponible en inversion
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave('103-0003'),
				debe:0.0,
				haber:inversion.importe.abs(),
				asiento:asiento,
				descripcion:"$inversion.cuentaOrigen ",
				referencia:inversion.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Inversion'
				,origen:inversion.id)
			
			//Abono a Intereses bancarios
			def res=inversion.importeIsr+inversion.rendimientoReal
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave('701-0001'),
				debe:0.0,
				haber:res,
				asiento:asiento,
				descripcion:"$inversion.cuentaOrigen  ",
				referencia:inversion.comentario,
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Inversion'
				,origen:inversion.id)
			
			
		}
	}
	
	
	private procesarDescuentosCxP(def poliza,def dia){
		def asiento='DESCUENTOS_CXP'
		
		def notas=NotaDeCredito.findAll ("from NotaDeCredito a where date(a.fecha)=?",[dia])
		notas.each{nota ->
			
			
			def importe=nota.total*nota.tc
			def clave="701-$nota.proveedor.subCuentaOperativa"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:0.0,
				haber:importe,
				asiento:asiento,
				descripcion:"NC: $nota.documento $nota.concepto  $nota.total * $nota.tc",
				referencia:"$nota.documento",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'NotaDeCredito'
				,origen:nota.id)
			
			clave="201-$nota.proveedor.subCuentaOperativa"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:importe,
				haber:0.0,
				asiento:asiento,
				descripcion:"NC: $nota.documento $nota.concepto  $nota.total * $nota.tc",
				referencia:"$nota.documento",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'NotaDeCredito'
				,origen:nota.id)
			
		}
	}
	
	
	
	private procesarNotasDeCreditoCxC(def poliza,def dia){
		def asiento='NOTAS DE CREDITO CXC'
		
		def notas=CXCNota.findAll ("from CXCNota a where date(a.fecha)=?",[dia])
		notas.each{nota ->
			
			//Abono a cliente
			//def importe=nota.total
			def clave="106-$nota.cliente.subCuentaOperativa"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:0.0,
				haber:nota.total,
				asiento:asiento,
				descripcion:"NC: $nota.tipo $nota.cfd.folio  ",
				referencia:"$nota.cfd.folio",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'CXCNota'
				,origen:nota.id)
			//Cargo a descuentos y rebajas
			clave="406-$nota.cliente.subCuentaOperativa"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:nota.importe,
				haber:0.0,
				asiento:asiento,
				descripcion:"NC: $nota.tipo $nota.cfd.folio  ",
				referencia:"$nota.cfd.folio",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'NotaDeCredito'
				,origen:nota.id)
			//Cargo a IVA Pendiente por trasladar (descuentos y rebajas)
			clave="206-0002"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:nota.impuesto,
				haber:0.0,
				asiento:asiento,
				descripcion:"NC: $nota.tipo $nota.cfd.folio  ",
				referencia:"$nota.cfd.folio",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'NotaDeCredito'
				,origen:nota.id)
			
		}
	}
	
	private procesarVariacionCambiariaBancos(def poliza,def dia){
		
		def finDeMes=dia.finDeMes().clearTime()
		if(dia.clearTime()!=finDeMes)
			return
		def asiento="VARIACION CAMBIARIA BANCOS"
		
		def saldos=SaldoDeCuenta.executeQuery("from SaldoDeCuenta s where s.year=? and s.mes=? and s.cuenta.moneda!='MXN'",[dia.toYear(),dia.toMonth()])
		
		saldos.each{ saldo->
			def fini=dia.inicioDeMes()-2
			def tipoDeCambioIni=TipoDeCambio.findByFecha(fini)
			if(!tipoDeCambioIni){
				throw new RuntimeException("No existe el tipo de cambio registrado para el $fini")
			}
			def saldoInicialMN=saldo.saldoInicial*tipoDeCambioIni.factor
			def movimientos=MovimientoDeCuenta.executeQuery(
				"select sum(m.importe*m.tc) ,sum(m.importe) from MovimientoDeCuenta m where m.cuenta=? and  date(m.fecha) between ? and ? "
				,[saldo.cuenta,dia.inicioDeMes(),dia.finDeMes()] )
			
			def movMN=movimientos[0][0]?:0.0
			def mov=movimientos[0][1]?:0.0
			
			def saldoFinal=saldo.saldoInicial+mov
			
			def tipoDeCambio=TipoDeCambio.findByFecha(dia-1)
			if(!tipoDeCambio){
				throw new RuntimeException("No existe el tipo de cambio registrado para el $dia")
			}
			def saldoFinalMNActualizado=saldoFinal*tipoDeCambio.factor
			def saldoFinalMN=saldoInicialMN+movMN
			
			def diferencia=saldoFinalMNActualizado-saldoFinalMN
			
			//println "Saldo inicial $saldo.saldoInicial Mov:$mov  SaldoFinal: $saldoFinal Tc: $tipoDeCambio.factor SaldoFinal ActMN: $saldoFinalMNActualizado"
			//println "Saldo Inicial MN: $saldoInicialMN $fini $tipoDeCambioIni Mov:$movMN SaldoFinal: $saldoFinalMN"
			
			def periodo=dia.asPeriodoText()
			
			///
			//
			poliza.addToPartidas(
				cuenta:saldo.cuenta.cuentaContable,
				debe:diferencia>0?diferencia.abs():0.0,
				haber:diferencia<0?diferencia.abs():0.0,
				asiento:asiento,
				descripcion:"Variacion cambiaria $periodo ",
				referencia:"$saldo.cuenta.id",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'NA'
				,origen:0)
			
			def clave=diferencia>0?"701-0002":"705-0002"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:diferencia<0?diferencia.abs():0.0,
				haber:diferencia>0?diferencia.abs():0.0,
				asiento:asiento,
				descripcion:"Variacion cambiaria $periodo ",
				referencia:"$saldo.cuenta.id",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'NA'
				,origen:0)
			
		}
		
	}
	
	private procesarVariacionCambiariaProveedores(def poliza,def dia){
		
		def finDeMes=dia.finDeMes().clearTime()
		if(dia.clearTime()!=finDeMes)
			return
		def asiento="VARIACION CAMBIARIA PROVEEDORES"
		
		def facturasList=FacturaDeImportacion
	//		.executeQuery("from FacturaDeImportacion f where date(f.fecha)<=? and f.total-(select sum(x.total) from Aplicacion x where x.factura=f and date(x.fecha)<=?) >0 order by f.fecha",
			.executeQuery("from FacturaDeImportacion f where date(f.fecha)<=? order by f.fecha",
			[dia])
		
		def facturas=facturasList.findAll{ fac->
			def pagosAplic=Aplicacion.executeQuery("select sum(a.total) from Aplicacion a where a.factura=? and date(a.fecha)<=?",[fac,dia])//[0][0]?:0.0
			//println 'Pagos aplicados: '+pagosAplic
			def pagos=pagosAplic[0]?:0.0
			def saldo=fac.total-pagos
			fac.saldoAlCorte=saldo
			return saldo>0
		}
		
		Map facturasPorProveedor=facturas.groupBy({
			//println it
			it.proveedor
			})
		
		facturasPorProveedor.entrySet().each{
			def proveedor=it.key
			//println 'Procesando diferencia cambiara para proveedor: '+proveedor
			def facs=it.value
			
			def saldo=0.0
			def saldoActualizado=0.0
			def tcProvAnterior=TipoDeCambio.findByFecha(dia.inicioDeMes()-2)
			def tcCorte=TipoDeCambio.findByFecha(dia.finDeMes()-1)
			
			facs.each{ fac->
				
				
				
				def pedimentos=Pedimento.executeQuery("select det.pedimento from EmbarqueDet det where det.factura=?",[fac])
				if(!pedimentos.isEmpty()){
					def pedimento=pedimentos[0]
					def fecha=pedimento.fecha
					def tc=pedimento.tipoDeCambio
					if(fecha.toMonth()!=dia.toMonth() ){
						tc=tcProvAnterior.factor
					}
					saldo+=fac.saldoAlCorte*tc
					saldoActualizado+=fac.saldoAlCorte*tcCorte.factor
					//println " $proveedor, $fac.documento, $fac.saldoAlCorte, $tc, $tcCorte.factor "
				}
				
				
				
				
			}
			
			def diferencia=saldo-saldoActualizado
			def periodo=dia.asPeriodoText()
			
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave("201-$proveedor.subCuentaOperativa"),
				debe:diferencia>0?diferencia.abs():0.0,
				haber:diferencia<0?diferencia.abs():0.0,
				asiento:asiento,
				descripcion:"Variacion cambiaria $periodo ",
				referencia:"$proveedor.id",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'NA'
				,origen:0)
			
			def clave=diferencia>0?"701-0002":"705-0002"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:diferencia<0?diferencia.abs():0.0,
				haber:diferencia>0?diferencia.abs():0.0,
				asiento:asiento,
				descripcion:"Variacion cambiaria $periodo ",
				referencia:"$proveedor.id",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'NA'
				,origen:0)
			
		}
		
	}
	
	private procesarNotasDeCargoCxC(def poliza,def dia){
		def asiento='NOTAS DE CARGO CXC'
		
		def notas=Venta.findAll ("from Venta v where v.tipo='NOTA_DE_CARGO' and date(v.fecha)=?",[dia])
		
		notas.each{ nota ->
			
			//Cargo a cliente
			
			def clave="106-$nota.cliente.subCuentaOperativa"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:nota.total,
				haber:0.0,
				asiento:asiento,
				descripcion:"N.Cargo:$nota.cfd.folio  $nota.cliente ",
				referencia:"$nota.cfd.folio",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Venta'
				,origen:nota.id)
			
			//Abono a productos financieros
			clave="701-0003"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:0.0,
				haber:nota.importe,
				asiento:asiento,
				descripcion:"N.Cargo:$nota.cfd.folio  $nota.cliente ",
				referencia:"$nota.cfd.folio",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Venta'
				,origen:nota.id)
			
			//Abono a IVA Pendiente por trasladar (productos financieros)
			clave="206-0002"
			poliza.addToPartidas(
				cuenta:CuentaContable.buscarPorClave(clave),
				debe:0.0,
				haber:nota.impuestos,
				asiento:asiento,
				descripcion:"N.Cargo:$nota.cfd.folio  $nota.cliente ",
				referencia:"$nota.cfd.folio",
				,fecha:poliza.fecha
				,tipo:poliza.tipo
				,entidad:'Venta'
				,origen:nota.id)
			
		}
	}
	
	private procesarProvisionDeGastos(Poliza poliza ,Date dia){
		/*
		Calendar cal=Calendar.instance
		cal.setTime(dia);
		println 'Calendar: '+cal.getTime()
		println 'Find de mes: '+cal.getActualMaximum(Calendar.DATE)
		
		
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE))
		
		def finDeMes=cal.getTime().clearTime()
		println 'Registrando provision de gastos: '+dia+ " Fin de mes: "+finDeMes
		*/
		def finDeMes=dia.finDeMes().clearTime()
		if(dia.clearTime()!=finDeMes)
			return
			
		def asiento="PROVISION DE GASTO"
		
		def facturasList=FacturaDeGastos.executeQuery("from FacturaDeGastos f where year(f.fecha)=? and month(f.fecha)=?",[dia.toYear(),dia.toMonth()])
		
		println 'Factuars de gastos en el periodo: '+facturasList.size()
		
		def facturas=facturasList.findAll { fac->
			
			def aplicaciones=Aplicacion.executeQuery("from Aplicacion a where a.factura=? and date(a.fecha)<=?",[fac,dia])
			
			println "Gasto fac: $fac.documento por $fac.total pagos: $aplicaciones.size"
			
			return !aplicaciones
		}
		
		println 'Facturas de gastos con saldo al fin de mes: '+facturas.size()
		
		facturas.each{ fac->
			fac.conceptos.each{c->
				//Cargo a gasto concepto
				poliza.addToPartidas(
					cuenta:c.concepto,
					debe:c.importe,
					haber:0.0,
					asiento:asiento,
					descripcion:"$c.descripcion",
					referencia:"$fac.documento"
					,fecha:poliza.fecha
					,tipo:poliza.tipo
					,entidad:'FacturaDeGasto'
					,origen:fac.id)
				
				//Cargo al iva de gasto
				poliza.addToPartidas(
					cuenta:CuentaContable.buscarPorClave("117-0004"),
					debe:c.impuesto,
					haber:0.0,
					asiento:asiento,
					descripcion:"$c.descripcion",
					referencia:"$fac.documento"
					,fecha:poliza.fecha
					,tipo:poliza.tipo
					,entidad:'FacturaDeGasto'
					,origen:fac.id)
				//Abono a acredores diversos
				def cuenta=CuentaContable.buscarPorClave("203-V001")
				if(c.tipo=='SEGUROS Y FIANZAS'){
					cuenta=CuentaContable.buscarPorClave("203-$fac.proveedor.subCuentaOperativa")
				}
				poliza.addToPartidas(
					cuenta:cuenta,
					debe:0.0,
					haber:c.total,
					asiento:asiento,
					descripcion:"$c.descripcion",
					referencia:"$fac.documento"
					,fecha:poliza.fecha
					,tipo:poliza.tipo
					,entidad:'FacturaDeGasto'
					,origen:fac.id)
				
				if(c.retension>0){
					poliza.addToPartidas(
						cuenta:CuentaContable.buscarPorClave("117-0008"),
						debe:c.retension,
						haber:0.0,
						asiento:asiento,
						descripcion:"$c.descripcion",
						referencia:"$fac.documento"
						,fecha:poliza.fecha
						,tipo:poliza.tipo
						,entidad:'FacturaDeGasto'
						,origen:fac.id)
				}
				
				if(c.retensionIsr){
					poliza.addToPartidas(
						cuenta:CuentaContable.buscarPorClave("205-0006"),
						debe:c.retensionIsr,
						haber:0.0,
						asiento:asiento,
						descripcion:"$c.descripcion",
						referencia:"$fac.documento"
						,fecha:poliza.fecha
						,tipo:poliza.tipo
						,entidad:'FacturaDeGasto'
						,origen:fac.id)
				}
			}
		}
	}
	
}

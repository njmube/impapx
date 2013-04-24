package com.luxsoft.impapx

import grails.validation.Validateable;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException

class FacturaDeImportacionController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list(FacturasPorPeriodoCommand cmd) {
		println 'Lista de facturas params: '+params
		params.max = Math.min(params.max ? params.int('max') : 500, 1000)
		params.sort='id'
		params.order= "desc"
		if(!params.fechaInicial)
			params.fechaInicial=new Date()-90
		if(!params.fechaFinal)
			params.fechaFinal=new Date()
		cmd.properties=params
		println 'Periodo: '+cmd
		//def periodo=new FacturasPorPeriodoCommand(params)
		//def rango="${params.fechaInicial.text()} al ${params.fechaFinal.text()}"
		def facturas=FacturaDeImportacion.findAllByFechaBetween(cmd.fechaInicial,cmd.fechaFinal,params)
		//println 'Facturas :'+facturas.size()
		def total=FacturaDeImportacion.countByFechaBetween(cmd.fechaInicial,cmd.fechaFinal)
		
        [facturaDeImportacionInstanceList: facturas
			, facturaDeImportacionInstanceTotal: total
			,periodo:cmd
			,fechaInicial:params.fechaInicial
			,fechaFinal:params.fechaFinal]
    }
	
	

    def create() {
		
		switch (request.method) {
		case 'GET':
			
			def fac=new FacturaDeImportacion(params)
			fac.tc=13.50
			fac.moneda=Currency.getInstance('USD');
        	[cuentaPorPagarInstance: fac]
			break
		case 'POST':
			
			params.proveedor=Proveedor.get(params.long('proveedorId'))
			params.vencimiento=new Date()
	        def facturaDeImportacionInstance = new FacturaDeImportacion(params)
			
	        if (!facturaDeImportacionInstance.save(flush: true)) {
	            render view: 'create', model: [facturaDeImportacionInstance: facturaDeImportacionInstance]
	            return
	        }

			flash.message = message(code: 'cuentaPorPagar.created.message', args: [facturaDeImportacionInstance.id])
	        redirect action: 'list'
			break
		}
    }

    def show() {
        def facturaDeImportacionInstance = FacturaDeImportacion.get(params.id)
        if (!facturaDeImportacionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaDeImportacion.label', default: 'FacturaDeImportacion'), params.id])
            redirect action: 'list'
            return
        }

        [facturaDeImportacionInstance: facturaDeImportacionInstance]
    }

    def edit() {
		switch (request.method) {
			case 'GET':
				def cuentaPorPagarInstance = FacturaDeImportacion.get(params.id)
				
				if (!cuentaPorPagarInstance) {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaPorPagar.label', default: 'CuentaPorPagar'), params.id])
					redirect action: 'list'
					return
				}
	
				[cuentaPorPagarInstance: cuentaPorPagarInstance]
				break
			case 'POST':
				
				def cuentaPorPagarInstance = FacturaDeImportacion.get(params.id)
				
				if (!cuentaPorPagarInstance) {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaPorPagar.label', default: 'Factura de importaci—n'), params.id])
					redirect action: 'list'
					return
				}
	
				if (params.version) {
					def version = params.version.toLong()
					if (cuentaPorPagarInstance.version > version) {
						cuentaPorPagarInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
								  [message(code: 'cuentaPorPagar.label', default: 'Factura de importaci—n')] as Object[],
								  "Another user has updated this CuentaPorPagar while you were editing")
						render view: 'edit', model: [cuentaPorPagarInstance: cuentaPorPagarInstance]
						return
					}
				}
				def proveedor=cuentaPorPagarInstance.proveedor
				params.proveedor=proveedor
				cuentaPorPagarInstance.properties = params
				
				if (!cuentaPorPagarInstance.save(failOnError: true)) {
					render view: 'edit', model: [cuentaPorPagarInstance: cuentaPorPagarInstance]
					return
				}
	
				flash.message = message(code: 'default.updated.message', args: [message(code: 'facturaDeImportacion.label', default: 'Factura'), cuentaPorPagarInstance.id])
				redirect action: 'list'
				break
			}
    }

    def delete() {
		
        def facturaDeImportacionInstance = FacturaDeImportacion.get(params.id)
        if (!facturaDeImportacionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaDeImportacion.label', default: 'FacturaDeImportacion'), params.id])
            redirect action: 'list'
            return
        }

        try {
            facturaDeImportacionInstance.delete(flush: true)
			flash.message = message(code: 'cuentaPorPagar.deleted.message', args: [ params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'facturaDeImportacion.label', default: 'FacturaDeImportacion'), params.id])
            redirect action: 'show', id: params.id
        }
    }
	
	
	def programacionDePagos(){
		println 'Programacion de pagos params: '+params
		params.max = Math.min(params.max ? params.int('max') : 200, 1000)
		params.sort='id'
		params.order= "desc"
		if(!params.fechaInicial)
			params.fechaInicial=new Date()
		if(!params.fechaFinal)
			params.fechaFinal=new Date()+7
		//cmd.properties=params
		FacturasPorPeriodoCommand cmd=new FacturasPorPeriodoCommand()
		cmd.properties=params
		println 'Periodo: '+cmd+' Proveedor: '+cmd?.proveedor?.id
		def facturas=[]
		if(StringUtils.isNotBlank(params.proveedor)){
			Proveedor p=Proveedor.get(params.long('proveedor.id'))
			cmd.proveedor=p
			facturas=FacturaDeImportacion.findAllByProveedorAndVencimientoBetween(p,cmd.fechaInicial,cmd.fechaFinal,params)
		}
		else{
			facturas=FacturaDeImportacion.findAllByVencimientoBetween(cmd.fechaInicial,cmd.fechaFinal,params)
		}
		//def total=FacturaDeImportacion.countByVencimientoBetween(cmd.fechaInicial,cmd.fechaFinal)
		
		[facturaDeImportacionInstanceList: facturas 
			, facturaDeImportacionInstanceTotal: facturas.size()
			,periodo:cmd
			,fechaInicial:params.fechaInicial
			,fechaFinal:params.fechaFinal]
	}
	
	def detalleDeFacturas(FacturasPorPeriodoCommand cmd){
		switch (request.method) {
			case 'GET':
				//println 'Detalle  GET'
				[facturasCommand:new FacturasPorPeriodoCommand(fechaInicial:new Date()-30,fechaFinal:new Date()),embarques: []]
				break
			case 'POST':
				println 'Detalle  POST'+params	
				def rows=EmbarqueDet.executeQuery("from EmbarqueDet d where  d.embarque.proveedor=? and d.factura.fecha between ? and ? order by d.factura.documento desc"
					,[cmd.proveedor,cmd.fechaInicial,cmd.fechaFinal])			
				[facturasCommand:cmd,embarques: rows]
				break
		}
	}
	/*
	def reporteDeFacturaDetImportacion(){
		
		def rows=EmbarqueDet.executeQuery("from EmbarqueDet d where  d.embarque.proveedor=? and d.factura.fecha between ? and ? order by d.factura.documento desc"
			,[cmd.proveedor,cmd.fechaInicial,cmd.fechaFinal])
		
		def cuenta=CuentaBancaria.get(params.cuentaId)
		
		if(!cuenta)
			throw new RuntimeException("No existe la cuenta: "+id)
		def periodo=Date.parse("dd/MM/yyyy", params.periodo)
		
		println "Generando estado de cuenta para:$cuenta  al:$periodo"
		
		def fechaIni=periodo.inicioDeMes()
		def fechaFin=periodo.finDeMes()
		
		def saldoInicial=MovimientoDeCuenta.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(fecha) < ?"
			,[cuenta,fechaIni])[0]?:00
		//Calculando los movimientos del mes
		def hql="select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and date(x.fecha) between ? and ? and ingreso=?  "
		def ingresos=MovimientoDeCuenta.executeQuery(hql,[cuenta,fechaIni,fechaFin,true])[0]?:00
		def egresos=MovimientoDeCuenta.executeQuery(hql,[cuenta,fechaIni,fechaFin,false])[0]?:00
		def saldoFinal=saldoInicial+(ingresos+egresos)
		
		def movimientos=MovimientoDeCuenta.findAllByCuentaAndFechaBetween(cuenta,fechaIni,fechaFin,[sort:'fecha'])
		def modelData=movimientos.collect { mov ->
			
			def res=[
			//'FOLIO':mov.id
			'FECHA':mov.fecha.format("dd"),
			 'CONCEPTO':mov.concepto
			 ,'TIPO':mov.tipo
			,'INGRESO':mov.ingreso?mov.importe.abs():0.0
			,'EGRESO':!mov.ingreso?mov.importe.abs():0.0
			 ,'COMENTARIO':mov.comentario
			 ,'REFERENCIA':mov.referenciaBancaria
			 ,INI:saldoInicial
			 ]
			return res
		}
		NumberFormat nf=NumberFormat.getNumberInstance()
		def repParams=[CUENTA:cuenta.toString()
			,FECHA_INI:fechaIni.text()
			,FECHA_FIN:fechaFin.text()
			,SALDO_INI:nf.format(saldoInicial)
			,SALDO_FIN:nf.format(saldoFinal)
			,INGRESOS:nf.format(ingresos.abs())
			,EGRESOS:nf.format(egresos.abs())
			]
		//params<<repParams
		repParams<<params
		println 'Ejecutando reporte params:'+repParams+'\n Registros: '+modelData
		chain(controller:'jasper',action:'index',model:[data:modelData],params:repParams)
		
	}*/
	
}

@Validateable
class FacturasPorPeriodoCommand{
	
	Date fechaInicial
	Date fechaFinal
	Proveedor proveedor
	
	String toString(){
		return fechaInicial.text() +' al '+fechaFinal.text() 
	}
	
}


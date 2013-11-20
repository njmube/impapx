package com.luxsoft.impapx.contabilidad

class SaldoPorCuentaContableService {

    def actualizarSaldos(int year,int mes){
		
		def cuentas=CuentaContable.findAllByDetalle('false')
		//println 'cuentas registradas: '+cuentas
		println 'Actualizando saldos para cuentas en el periodo: '+year+'/'+mes
		cuentas.each{ c->
			
			c.subCuentas.each{
				//println 'Procesando cuenta: '+it.clave
				actualizarSaldo(year,mes,it)
			}
			//println 'Actualizando saldo para la cuenta de mayor: '+c.clave
			actualizarSaldo(year,mes,c)
			
		}
	}
	
	def actualizarSaldo(int year,int mes, def cuenta){
		println 'Mes :'+mes+ 'Year: '+year
		def calendar=Calendar.getInstance()
		calendar.set(Calendar.MONTH,mes-1)
		calendar.set(Calendar.YEAR, year)
		calendar.set(Calendar.DATE,1)
		//calendar.setTime(new Date(2013,8,1))
		
		
		
		
		def fecha=calendar.getTime().inicioDeMes()
		//def fecha=new Date(2013,8,1)
		println 'Actualizando saldo para cuenta '+cuenta+' Per:'+mes+' /'+year+ 'icio de mes: '+fecha
		if(cuenta.detalle){
			
			//println 'Actualizando saldo para cuenta: '+cuenta
			def saldoInicial=PolizaDet.executeQuery("select sum(d.debe-d.haber) from PolizaDet d where d.cuenta=? and date(d.poliza.fecha)<?",[cuenta,fecha])
			
			def row=PolizaDet.executeQuery("select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta=? and year(d.poliza.fecha)=? and month(d.poliza.fecha)=?"
				,[cuenta,year,mes])
			
			println 'Saldo inicial: '+saldoInicial.get(0)
			def debe=row.get(0)[0]?:0.0
			
			def haber=row.get(0)[1]?:0.0
			def saldo=SaldoPorCuentaContable.findOrCreateWhere([cuenta:cuenta,year:year,mes:mes])
			saldo.fecha=fecha
			saldo.cierre=fecha
			saldo.saldoInicial=saldoInicial.get(0)?:0.0
			saldo.debe=debe
			saldo.haber=haber
			saldo.saldoFinal=saldo.saldoInicial+debe-haber
			def res=saldo.save(failOnError:true)
			println res
		}else{
			//println 'Actualizando saldo para cuenta de mayor: '+cuenta
			def saldoInicial=PolizaDet.executeQuery("select sum(d.debe-d.haber) from PolizaDet d where d.cuenta.padre=? and date(d.poliza.fecha)<?",[cuenta,fecha])
		
			def row=PolizaDet.executeQuery("select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta.padre=? and year(d.poliza.fecha)=? and month(d.poliza.fecha)=?"
				,[cuenta,year,mes])
		
			//println 'Saldo inicial: '+saldoInicial.get(0)
			def debe=row.get(0)[0]?:0.0
		
			def haber=row.get(0)[1]?:0.0
			def saldo=SaldoPorCuentaContable.findOrCreateWhere([cuenta:cuenta,year:year,mes:mes])
			
			saldo.fecha=fecha
			saldo.cierre=fecha
			saldo.saldoInicial=saldoInicial.get(0)?:0.0
			saldo.debe=debe
			saldo.haber=haber
			saldo.saldoFinal=saldo.saldoInicial+debe-haber
			saldo.save(failOnError:true)
		}
		
	}
	
	def reclasificarMovimientos(SaldoPorCuentaContable saldo,CuentaContable destino,def partidas){
		Set cuentas=[]
		partidas.each{
			
			def polizaDet=PolizaDet.get(it.toLong())
			cuentas.add(polizaDet.cuenta)
			println 'Reclasificando: '+it
			polizaDet.cuenta=destino
			polizaDet.descripcion+="(Rec)"
			polizaDet.save()
		}
		cuentas.add(destino)
		
		//Actualizamos los saldos
		cuentas.each{
			if(it.padre)
				actualizarSaldo(saldo.year,saldo.mes,it.padre)
		}
	}
}

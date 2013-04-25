package com.luxsoft.impapx

import org.spockframework.util.Assert;

import com.luxsoft.impapx.cxp.CuentaDeGastosGenerica;

class CuentaDeGastosGenericaService {

	def agregarFactura(long cuentaDeGastosId,long facturaId) {
		
		def cuenta=CuentaDeGastosGenerica.get(cuentaDeGastosId)
		Assert.notNull(cuenta,"No existe la cuenta de gastos genérica: "+cuentaDeGastosId)
		def factura=FacturaDeGastos.get(facturaId)
		Assert.notNull(factura,"No existe la factura: "+facturaId)
		
		cuenta.addToFacturas(factura)
		cuenta.actualizarImportes()
		cuenta=cuenta.save(failOnError:true)
		return [cuentaDeGastosGenerica:cuenta,factura:factura]
		
	}
	
	def eliminarFacturas(def cuentaDeGastosId,def facturas){
		def cuenta=CuentaDeGastosGenerica.findById(cuentaDeGastosId,[fetch:[facturas:'eager']])
		println 'Facturas asociadas: '+cuenta.facturas.size()
		
		facturas.each{
			def facturaId=it.toLong()
			//def factura=FacturaDeGastos.get(facturaId)
			def factura=cuenta.facturas.find{
				it.id==facturaId
			}
			//println 'Factura a eliminar: '+factura
			if(factura){
				def res=cuenta.removeFromFacturas(factura)
				//println 'Eliminacion: '+res.facturas.size()
				factura.cuentaGenerica=null
				factura.save()
				//factura.cuentaDeGastos=null
			}
		}
		cuenta.actualizarImportes()
		cuenta=cuenta.save(failOnError:true)
		println 'Facturas de cuenta actualizada: '+cuenta.facturas.size()
		println 'Nuevo importe actualizado: '+cuenta.importe
		return cuenta
		
	}
}

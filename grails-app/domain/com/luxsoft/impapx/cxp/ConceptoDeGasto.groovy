package com.luxsoft.impapx.cxp

import com.luxsoft.impapx.FacturaDeGastos;
import com.luxsoft.impapx.contabilidad.CuentaContable;
import com.luxsoft.impapx.tesoreria.MovimientoDeCuenta;
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class ConceptoDeGasto {
	
	CuentaContable concepto	//600-xx
	String descripcion
	BigDecimal importe
	BigDecimal impuestoTasa=16
	BigDecimal impuesto
	BigDecimal retensionTasa=0
	BigDecimal retension=0
	BigDecimal retensionIsrTasa=0
	BigDecimal retensionIsr=0
	BigDecimal ietu
	BigDecimal total
	MovimientoDeCuenta egreso
	String tipo
	
	static belongsTo =[factura:FacturaDeGastos]

    static constraints = {
		concepto(nullable:false)
		descripcion(blank:false,maxSize:300)
		egreso(nullable:true)
		tipo(nullable:true,inList:['GASTOS'
			,'ACTIVO FIJO'
			,'GASTOS DE IMPORTACION'
			,'OTRAS CONTRIBUCIONES Y DERECHO'
			,'SERVICIOS INDEPENDIENTES'
			,'SEGUROS Y FIANZAS'
			,'HONORARIOS AL CONSEJO ADMON'])
    }
	
	static mapping = {
		concepto fetch:'join'
	}
	
	boolean equals(Object obj){
		if(!obj.instanceOf(ConceptoDeGasto))
			return false
		if(this.is(obj))
			return true
		def eb=new EqualsBuilder()
		eb.append(concepto, obj.concepto)
		eb.append(id, obj.id)
		return eb.isEquals()
	}
	
	int hashCode(){
		def hb=new HashCodeBuilder(17,35)
		hb.append(concepto)
		hb.append(id)
		return hb.toHashCode()
	}
}

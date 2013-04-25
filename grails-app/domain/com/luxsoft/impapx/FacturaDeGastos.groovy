package com.luxsoft.impapx



import com.luxsoft.impapx.cxp.ConceptoDeGasto;
import com.luxsoft.impapx.cxp.CuentaDeGastosGenerica;

class FacturaDeGastos extends CuentaPorPagar{
	
	BigDecimal retensionIsr=0
	
	
	static hasMany =[conceptos:ConceptoDeGasto]
	
	static belongsTo=[cuentaGenerica:CuentaDeGastosGenerica]

    static constraints = {
		retensionIsr(nullable:true)
    }
	
	static mapping = {
		conceptos cascade: "all-delete-orphan"
	}
}

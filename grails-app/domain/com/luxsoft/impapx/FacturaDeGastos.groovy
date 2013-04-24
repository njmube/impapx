package com.luxsoft.impapx



import com.luxsoft.impapx.cxp.ConceptoDeGasto;

class FacturaDeGastos extends CuentaPorPagar{
	
	BigDecimal retensionIsr=0
	
	
	static hasMany =[conceptos:ConceptoDeGasto]

    static constraints = {
		retensionIsr(nullable:true)
    }
	
	static mapping = {
		conceptos cascade: "all-delete-orphan"
	}
}

package com.luxsoft.impapx.tesoreria

import com.luxsoft.impapx.CuentaBancaria;

class Cheque {
	
	CuentaBancaria cuenta
	int folio
	Date fechaImpresion
	MovimientoDeCuenta egreso

    static constraints = {
		fechaImpresion(nullable:true)
    }
	
	static mapping ={
		egreso fetch:'join'
		//id name:'folio', generator:'assigned'
	}
}

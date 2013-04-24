package com.luxsoft.impapx.cxc

import com.luxsoft.impapx.CuentaBancaria;

class CXCPago extends CXCAbono{
	
	String formaDePago
	String referenciaBancaria
	Date fechaBancaria
	CuentaBancaria cuenta

    static constraints = {
		formaDePago(inList:['TRANSFERENCIA','CHEQUE','EFECTIVO','DEPOSITO','TARJETA'])
		referenciaBancaria(nullable:true,maxSize:100)
		cuenta(nullable:true)
    }
}

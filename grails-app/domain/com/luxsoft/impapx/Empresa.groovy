package com.luxsoft.impapx

class Empresa {
	
	String nombre
	Direccion direccion
	String rfc
	String regimen
	
	static embedded = ['direccion']

    static constraints = {
		nombre(blank:false,maxSize:200,unique:true)
		rfc()
		regimen()
		direccion(nullable:true)
		
    }
}

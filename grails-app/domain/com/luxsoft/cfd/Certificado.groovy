package com.luxsoft.cfd



class Certificado {
	
	String numeroDeCertificado
	Date expedicion
	Date vencimiento
	String certificadoPath
	String privateKeyPath
	String algoritmo

    static constraints = {
		numeroDeCertificado(blank:false)
		expedicion()
		vencimiento()
		certificadoPath()
		privateKeyPath()
		algoritmo(maxSize:40)
    }
	
	static transients = ['certificado']
	
	
}

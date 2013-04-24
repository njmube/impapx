package com.luxsoft.cfd

import com.luxsoft.impapx.Aduana
import mx.gob.sat.cfd.x2.ComprobanteDocument;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante

class ComprobanteFiscal {
	
	String tipo
	Long origen
	String comentario
	String numeroDeCertificado
	Long folio
	String xmlPath
	ComprobanteDocument document
	Comprobante comprobante
	
	Date fecha
	String rfc
	String serie
	Integer numeroDeAprobacion
	Integer anoAprobacion
	
	BigDecimal total
	BigDecimal impuesto
	String estado
	String tipoCfd
	
	String pedimento
	Date fechaPedimento
	String aduana
	

    static constraints = {
		tipo(blank:false,inList:['FACTURA','NOTA_DE_CREDITO','NOTA_DE_CARGO'])
		comentario(nullable:true)
		
		fecha(nullable:true)
		rfc(nullable:true,maxSize:13)
		serie(nullable:true,maxSize:50)
		numeroDeAprobacion(nullable:true)
		anoAprobacion(nullable:true)
		total(nullable:true)
		impuesto(nullable:true)
		estado(nullable:true,maxSize:1)
		tipoCfd(nullable:true,maxSize:1)
		
		pedimento(nullable:true)
		fechaPedimento(nullable:true)
		aduana(nullable:true)
		
    }
	
	static transients =['document','comprobante']
	
	def loadComprobante(){
		
		URL url=new URL(xmlPath)
		this.document=ComprobanteDocument.Factory.parse(url)
	}
	
	Comprobante getComprobante(){
		if(comprobante==null){
			loadComprobante()
			comprobante=document.getComprobante()
		}
		return comprobante
	}
}

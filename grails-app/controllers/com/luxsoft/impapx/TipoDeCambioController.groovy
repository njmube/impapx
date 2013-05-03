package com.luxsoft.impapx

import grails.converters.JSON

class TipoDeCambioController {
	static scaffold = true
	
	def ajaxTipoDeCambioDiaAnterior(String fecha){
		println 'Calculando el tipo de cambio: '+params
		def dia=new Date().parse("dd/MM/yyyy",fecha)-1;
		
		def tc=TipoDeCambio.findByFecha(dia)
		println 'Tipo: '+tc
		def res=[:]
		if(tc)
			res.factor=tc.factor
		else
			res.error="NO EXISTE T.C REGISTRADO EN EL SISTEMA PARA EL :"+dia.text() 
		render res as JSON
	}
}

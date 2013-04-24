package com.luxsoft.impapx

import org.codehaus.groovy.grails.web.json.JSONArray
import grails.converters.JSON

class EmbarqueService {

	def eliminarPartidas(def params){
		println 'Eliminando partidas: '+params
		def embarque=Embarque.findById(params.embarqueId,[fetch:[partidas:'eager']])
		println 'Partidas antes: '+embarque.partidas.size()
		
		JSONArray jsonArray=JSON.parse(params.partidas);
		jsonArray.each{
			def embarqueDet=embarque.partidas.find{ ee-> ee.id==it.toLong()}//EmbarqueDet.get(it.toLong())
			/*
			def found=DistribucionDet.findByEmbarqueDet(embarqueDet)
			if(found) 
				throw new RuntimeException("Embarque utilizado en la lista de distribcionDet : "+found.id)
				*/
			def res=embarque.removeFromPartidas(embarqueDet)
			/*
			if(embarqueDet){
				embarque.partidas.remove(embarqueDet)
				embarqueDet.emarque=null
			}*/
			
			println 'EmbarqueDet removido: '+res+ " Det: "+embarqueDet
			embarqueDet.compraDet.entregado-=embarqueDet.cantidad;
			
		}
		def target=embarque.save(failOnError:true)
		println 'After remove: '+target.partidas.size()
		def res=[res:target.partidas.size()]
		return res
	}
	
}

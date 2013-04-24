package com.luxsoft.impapx

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

import util.MonedaUtils;

class RequisicionDetController {

    static allowedMethods = [create: ['GET', 'POST'],  delete: 'POST']
	
	def requisicionService

    def index() {
        redirect action: 'list', params: params
    }

   

    def create() {
		switch (request.method) {
		case 'GET':
			def requisicion=Requisicion.get(params.requisicionId)
        	[requisicionDetInstance: new RequisicionDet(params),requisicionInstance:requisicion]
			break
		case 'POST':
			/*
			def requisicion=Requisicion.get(params.requisicionId)
	        
			requisicionDetInstance.importe=requisicionDetInstance.total
			requisicion.addToPartidas(requisicionDetInstance)
			requisicion.actualizarImportes()
			*/
			def requisicionDetInstance = new RequisicionDet(params)
			def requisicion=requisicionService.agregarPartida(params.long('requisicionId'), requisicionDetInstance)
	        if (requisicion) {
	            //render view: 'create', model: [requisicionDetInstance: requisicionDetInstance]
				redirect controller:'requisicion', action: 'edit', id: requisicion.id
	            return
	        }
			flash.message = message(code: 'default.created.message', args: [message(code: 'requisicionDet.label', default: 'RequisicionDet'), requisicionDetInstance.id])
	        redirect controller:'requisicion', action: 'edit', id: requisicion.id
			break
		}
    }


	def embarquesDisponiblesJSONList(){
		println 'Embarques disponibles para anticipo: '+params
		def embarques=Embarque.findAll(
			"from Embarque e where e.id=? "
			,[params.long('term')],[max:10])
		def embarquesList=embarques.collect { row ->
			def label=row.toString()
			[id:row.id,label:label,value:label]
		}
		println 'Embarques registrados: '+embarquesList.size()
		render embarquesList as JSON
	}

    
}

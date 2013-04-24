package com.luxsoft.impapx

import com.luxsoft.impapx.cxp.ConceptoDeGasto
import grails.converters.JSON

import org.apache.commons.lang.exception.ExceptionUtils;
import org.codehaus.groovy.grails.web.json.JSONArray
import org.springframework.dao.DataIntegrityViolationException

class FacturaDeGastosController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
	def facturaDeGastosService

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = 500
        [facturaDeGastosInstanceList: FacturaDeGastos.list(params), facturaDeGastosInstanceTotal: FacturaDeGastos.count()]
		//[gastosDeImportacionInstanceList: GastosDeImportacion.list(params), gastosDeImportacionInstanceTotal: GastosDeImportacion.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[facturaDeGastosInstance: new FacturaDeGastos(params)]
			break
		case 'POST':
		println 'Salvando gasto: '+params
			params.proveedor=Proveedor.get(params.long('proveedorId'))
	        def facturaDeGastosInstance = new FacturaDeGastos(params)
	        if (!facturaDeGastosInstance.save(flush: true)) {
	            render view: 'create', model: [facturaDeGastosInstance: facturaDeGastosInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos'), facturaDeGastosInstance.id])
	        redirect action: 'show', id: facturaDeGastosInstance.id
			break
		}
    }

    def show() {
        def facturaDeGastosInstance = FacturaDeGastos.get(params.id)
        if (!facturaDeGastosInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos'), params.id])
            redirect action: 'list'
            return
        }

        [facturaDeGastosInstance: facturaDeGastosInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def facturaDeGastosInstance = FacturaDeGastos.findById(params.id,[fetch:[conceptos:'eager']])
	        if (!facturaDeGastosInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [facturaDeGastosInstance: facturaDeGastosInstance,conceptos:facturaDeGastosInstance.conceptos]
			break
		case 'POST':
		//println 'Actualizando gasto: '+params
	        def facturaDeGastosInstance = FacturaDeGastos.get(params.id)
	        if (!facturaDeGastosInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (facturaDeGastosInstance.version > version) {
	                facturaDeGastosInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos')] as Object[],
	                          "Another user has updated this FacturaDeGastos while you were editing")
	                render view: 'edit', model: [facturaDeGastosInstance: facturaDeGastosInstance]
	                return
	            }
	        }
			
			def proveedor=facturaDeGastosInstance.proveedor
			params.proveedor=proveedor
	        facturaDeGastosInstance.properties = params

	        if (!facturaDeGastosInstance.save(flush: true)) {
	            render view: 'edit', model: [facturaDeGastosInstance: facturaDeGastosInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos'), facturaDeGastosInstance.id])
	        redirect action: 'show', id: facturaDeGastosInstance.id
			break
		}
    }

    def delete() {
        def facturaDeGastosInstance = FacturaDeGastos.get(params.id)
        if (!facturaDeGastosInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos'), params.id])
            redirect action: 'list'
            return
        }

        try {
            facturaDeGastosInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos'), params.id])
            redirect action: 'show', id: params.id
        }
    }
	
	def agregarPartida(long id){
		println 'Agregando concepto: '+params
		def concepto=new ConceptoDeGasto()
		bindData(concepto,params,[exclude:['id']])
		def fac=facturaDeGastosService.agregarPartida(id, concepto)
		render view:'edit', model: [facturaDeGastosInstance: fac,conceptos:fac.conceptos]
		
	}
	
	def eliminarConceptos(){
		println 'Eliminando conceptos de gasto: '+params
		def data=[:]
		def fac = FacturaDeGastos.findById(params.facturaId,[fetch:[conceptos:'eager']])
		JSONArray jsonArray=JSON.parse(params.partidas);
		try {
			facturaDeGastosService.eliminarConceptos(fac,jsonArray)
			data.res='CONCEPTOS_ELIMINADOS'
		}
		catch (RuntimeException e) {
			
			println 'Error eliminando conceptos: '+ExceptionUtils.getRootCauseMessage(e)
			data.res="ERROR"
			data.error=ExceptionUtils.getRootCauseMessage(e)
		}
		render data as JSON
	}
}

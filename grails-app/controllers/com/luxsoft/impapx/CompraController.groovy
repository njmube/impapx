package com.luxsoft.impapx

import grails.converters.JSON

import org.apache.commons.lang.exception.ExceptionUtils;
import org.codehaus.groovy.grails.web.json.JSONArray
import org.springframework.dao.DataIntegrityViolationException

class CompraController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
	

   def filterPaneService
	
    def index() {
        redirect action: 'list', params: params
    }
	
	def filter(){
		if(!params.max) params.max = 20
		println filterPaneService?'OK':'ERROR'
		render( view:'list',
			model:[ compraInstanceList: filterPaneService.filter( params, Compra.class),
			compraInstanceTotal: filterPaneService.count( params, Compra.class ),
			filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
			params:params ] )
	}

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 100, 100)
		params.sort='id'
		params.order= "desc"
		//println 'Lista de compras: '+params
        [compraInstanceList: Compra.list(params), compraInstanceTotal: Compra.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[compraInstance: new Compra(params)]
			break
		case 'POST':
			//println 'Alta de compra: '+params
	        def compraInstance = new Compra(params)
	        if (!compraInstance.save(flush: true)) {
	            render view: 'create', model: [compraInstance: compraInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'compra.label', default: 'Compra'), compraInstance.id])
	        redirect action: 'edit', id: compraInstance.id
			break
		}
    }

    def show() {
        def compraInstance = Compra.get(params.id)
        if (!compraInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect action: 'list'
            return
        }

        [compraInstance: compraInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def compraInstance = Compra.findById(params.id,[fetch:[partidas:'join']])
	        if (!compraInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [compraInstance: compraInstance,partidas:compraInstance.partidas]
			break
		case 'POST':
		//println 'Actualizando compra: '+params
	        def compraInstance = Compra.get(params.id)
	        if (!compraInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (compraInstance.version > version) {
	                compraInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'compra.label', default: 'Compra')] as Object[],
	                          "Another user has updated this Compra while you were editing")
	                render view: 'edit', model: [compraInstance: compraInstance]
	                return
	            }
	        }
			//params.proveedor=Proveedor.findByNombre(params.proveedor)
	        compraInstance.properties = params

	        if (!compraInstance.save(flush: true)) {
	            render view: 'edit', model: [compraInstance: compraInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'compra.label', default: 'Compra'), compraInstance.id])
	        redirect action: 'show', id: compraInstance.id
			break
		}
    }
	
	def agregarPartida(){
		//println 'Agregando partida '+params
		
		def compra=Compra.findById(params.id,[fetch:[partidas:'eager']])
		def producto=Producto.get(params.producto.id)
		CompraDet det=new CompraDet(producto:producto,solicitado:params.cantidad,precio:0.0)
		compra.addToPartidas(det)
		compra.save(flush:true)
		redirect action: 'edit', id: compra.id
		
		
	}

    def delete() {
        def compraInstance = Compra.get(params.id)
        if (!compraInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect action: 'list'
            return
        }

        try {
            compraInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
            redirect action: 'show', id: params.id
        }
    }
	
	def eliminarPartida(){
		def data=[:]
		def compraInstance = Compra.findById(params.compraId,[fetch:[partidas:'eager']])
		JSONArray jsonArray=JSON.parse(params.partidas);
		//println 'Partidas a eliminar: '+jsonArray
		try {
			jsonArray.each {
				def det=CompraDet.get(it.toLong())
				
				if(det){
					
					compraInstance.removeFromPartidas(det)
					//println 'Eliminando : '+det+ 'res: '+compraInstance.partidas.size()
				}
			}
			compraInstance.save(failOnError:true)
			data.res='PARTIDAS_ELIMINADAS'
		}
		catch (RuntimeException e) {
			e.printStackTrace()
			data.res="ERROR"
			data.error=ExceptionUtils.getRootCauseMessage(e)
		}
		render data as JSON
	}
}

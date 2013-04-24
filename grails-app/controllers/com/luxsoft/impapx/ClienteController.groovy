package com.luxsoft.impapx

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class ClienteController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [clienteInstanceList: Cliente.list(params), clienteInstanceTotal: Cliente.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[clienteInstance: new Cliente(params)]
			break
		case 'POST':
	        def clienteInstance = new Cliente(params)
	        if (!clienteInstance.save(flush: true)) {
	            render view: 'create', model: [clienteInstance: clienteInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'cliente.label', default: 'Cliente'), clienteInstance.id])
	        redirect action: 'show', id: clienteInstance.id
			break
		}
    }

    def show() {
        def clienteInstance = Cliente.get(params.id)
        if (!clienteInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
            redirect action: 'list'
            return
        }

        [clienteInstance: clienteInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def clienteInstance = Cliente.get(params.id)
	        if (!clienteInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [clienteInstance: clienteInstance]
			break
		case 'POST':
	        def clienteInstance = Cliente.get(params.id)
	        if (!clienteInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (clienteInstance.version > version) {
	                clienteInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'cliente.label', default: 'Cliente')] as Object[],
	                          "Another user has updated this Cliente while you were editing")
	                render view: 'edit', model: [clienteInstance: clienteInstance]
	                return
	            }
	        }

	        clienteInstance.properties = params

	        if (!clienteInstance.save(flush: true)) {
	            render view: 'edit', model: [clienteInstance: clienteInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'cliente.label', default: 'Cliente'), clienteInstance.id])
	        redirect action: 'show', id: clienteInstance.id
			break
		}
    }

    def delete() {
        def clienteInstance = Cliente.get(params.id)
        if (!clienteInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
            redirect action: 'list'
            return
        }

        try {
            clienteInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
            redirect action: 'show', id: params.id
        }
    }
	
	def clientesJSONList(){
		def clientes=Cliente.findAllByNombreIlike(params.term+"%",[max:10,sort:"nombre",order:"desc"])
		def clientesList=clientes.collect { prov ->
			[id:prov.id,label:prov.nombre,value:prov.nombre]
		}
		render clientesList as JSON
	}
}

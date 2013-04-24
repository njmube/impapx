package com.luxsoft.impapx.cxp

import org.springframework.dao.DataIntegrityViolationException

class PagoController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pagoInstanceList: Pago.list(params), pagoInstanceTotal: Pago.count()]
    }

    

    def show() {
        def pagoInstance = Pago.get(params.id)
        if (!pagoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pago.label', default: 'Pago'), params.id])
            redirect action: 'list'
            return
        }

        [pagoInstance: pagoInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def pagoInstance = Pago.get(params.id)
	        if (!pagoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pago.label', default: 'Pago'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [pagoInstance: pagoInstance]
			break
		case 'POST':
	        def pagoInstance = Pago.get(params.id)
	        if (!pagoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pago.label', default: 'Pago'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (pagoInstance.version > version) {
	                pagoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'pago.label', default: 'Pago')] as Object[],
	                          "Another user has updated this Pago while you were editing")
	                render view: 'edit', model: [pagoInstance: pagoInstance]
	                return
	            }
	        }

	        pagoInstance.properties = params

	        if (!pagoInstance.save(flush: true)) {
	            render view: 'edit', model: [pagoInstance: pagoInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'pago.label', default: 'Pago'), pagoInstance.id])
	        redirect action: 'show', id: pagoInstance.id
			break
		}
    }

    def delete() {
        def pagoInstance = Pago.get(params.id)
        if (!pagoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'pago.label', default: 'Pago'), params.id])
            redirect action: 'list'
            return
        }

        try {
            pagoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'pago.label', default: 'Pago'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pago.label', default: 'Pago'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}

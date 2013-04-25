package com.luxsoft.impapx.cxp

import org.springframework.dao.DataIntegrityViolationException

class CuentaDeGastosGenericaController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
		params.sort='id'
		params.order='desc'
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [rows: CuentaDeGastosGenerica.list(params)]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[cuentaDeGastosGenericaInstance: new CuentaDeGastosGenerica(params)]
			break
		case 'POST':
	        def cuentaDeGastosGenericaInstance = new CuentaDeGastosGenerica(params)
	        if (!cuentaDeGastosGenericaInstance.save(flush: true)) {
	            render view: 'create', model: [cuentaDeGastosGenericaInstance: cuentaDeGastosGenericaInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica'), cuentaDeGastosGenericaInstance.id])
	        redirect action: 'show', id: cuentaDeGastosGenericaInstance.id
			break
		}
    }

    def show() {
        def cuentaDeGastosGenericaInstance = CuentaDeGastosGenerica.get(params.id)
        if (!cuentaDeGastosGenericaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica'), params.id])
            redirect action: 'list'
            return
        }

        [cuentaDeGastosGenericaInstance: cuentaDeGastosGenericaInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def cuentaDeGastosGenericaInstance = CuentaDeGastosGenerica.get(params.id)
	        if (!cuentaDeGastosGenericaInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [cuentaDeGastosGenericaInstance: cuentaDeGastosGenericaInstance]
			break
		case 'POST':
	        def cuentaDeGastosGenericaInstance = CuentaDeGastosGenerica.get(params.id)
	        if (!cuentaDeGastosGenericaInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (cuentaDeGastosGenericaInstance.version > version) {
	                cuentaDeGastosGenericaInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica')] as Object[],
	                          "Another user has updated this CuentaDeGastosGenerica while you were editing")
	                render view: 'edit', model: [cuentaDeGastosGenericaInstance: cuentaDeGastosGenericaInstance]
	                return
	            }
	        }

	        cuentaDeGastosGenericaInstance.properties = params

	        if (!cuentaDeGastosGenericaInstance.save(flush: true)) {
	            render view: 'edit', model: [cuentaDeGastosGenericaInstance: cuentaDeGastosGenericaInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica'), cuentaDeGastosGenericaInstance.id])
	        redirect action: 'show', id: cuentaDeGastosGenericaInstance.id
			break
		}
    }

    def delete() {
        def cuentaDeGastosGenericaInstance = CuentaDeGastosGenerica.get(params.id)
        if (!cuentaDeGastosGenericaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica'), params.id])
            redirect action: 'list'
            return
        }

        try {
            cuentaDeGastosGenericaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cuentaDeGastosGenerica.label', default: 'CuentaDeGastosGenerica'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}

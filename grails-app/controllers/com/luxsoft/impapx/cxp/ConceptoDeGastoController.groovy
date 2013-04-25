package com.luxsoft.impapx.cxp

import org.springframework.dao.DataIntegrityViolationException

class ConceptoDeGastoController {

    static allowedMethods = [create: ['GET', 'POST'], editConcepto: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [conceptoDeGastoInstanceList: ConceptoDeGasto.list(params), conceptoDeGastoInstanceTotal: ConceptoDeGasto.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[conceptoDeGastoInstance: new ConceptoDeGasto(params)]
			break
		case 'POST':
	        def conceptoDeGastoInstance = new ConceptoDeGasto(params)
	        if (!conceptoDeGastoInstance.save(flush: true)) {
	            render view: 'create', model: [conceptoDeGastoInstance: conceptoDeGastoInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto'), conceptoDeGastoInstance.id])
	        redirect action: 'show', id: conceptoDeGastoInstance.id
			break
		}
    }

    def show() {
        def conceptoDeGastoInstance = ConceptoDeGasto.get(params.id)
        if (!conceptoDeGastoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto'), params.id])
            redirect action: 'list'
            return
        }

        [conceptoDeGastoInstance: conceptoDeGastoInstance]
    }

    def edit() {
		//println 'Editando concepto de gasto: '+params
		switch (request.method) {
		case 'GET':
	        def conceptoDeGastoInstance = ConceptoDeGasto.get(params.id)
	        if (!conceptoDeGastoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [conceptoDeGastoInstance: conceptoDeGastoInstance]
			break
		case 'POST':
	        def conceptoDeGastoInstance = ConceptoDeGasto.get(params.id)
	        if (!conceptoDeGastoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (conceptoDeGastoInstance.version > version) {
	                conceptoDeGastoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto')] as Object[],
	                          "Another user has updated this ConceptoDeGasto while you were editing")
	                render view: 'editConcepto', model: [conceptoDeGastoInstance: conceptoDeGastoInstance]
	                return
	            }
	        }

	        conceptoDeGastoInstance.properties = params

	        if (!conceptoDeGastoInstance.save(flush: true)) {
	            render view: 'editConcepto', model: [conceptoDeGastoInstance: conceptoDeGastoInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto'), conceptoDeGastoInstance.id])
	        redirect controller:'facturaDeGastos',action: 'edit', id: conceptoDeGastoInstance.factura.id
			break
		}
    }

    def delete() {
        def conceptoDeGastoInstance = ConceptoDeGasto.get(params.id)
        if (!conceptoDeGastoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto'), params.id])
            redirect action: 'list'
            return
        }

        try {
            conceptoDeGastoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'conceptoDeGasto.label', default: 'ConceptoDeGasto'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}

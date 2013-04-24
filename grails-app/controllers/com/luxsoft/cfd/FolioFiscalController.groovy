package com.luxsoft.cfd

import org.springframework.dao.DataIntegrityViolationException

class FolioFiscalController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [folioFiscalInstanceList: FolioFiscal.list(params), folioFiscalInstanceTotal: FolioFiscal.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[folioFiscalInstance: new FolioFiscal(params)]
			break
		case 'POST':
	        def folioFiscalInstance = new FolioFiscal(params)
	        if (!folioFiscalInstance.save(flush: true)) {
	            render view: 'create', model: [folioFiscalInstance: folioFiscalInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'folioFiscal.label', default: 'FolioFiscal'), folioFiscalInstance.id])
	        redirect action: 'show', id: folioFiscalInstance.id
			break
		}
    }

    def show() {
        def folioFiscalInstance = FolioFiscal.get(params.id)
        if (!folioFiscalInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioFiscal.label', default: 'FolioFiscal'), params.id])
            redirect action: 'list'
            return
        }

        [folioFiscalInstance: folioFiscalInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def folioFiscalInstance = FolioFiscal.get(params.id)
	        if (!folioFiscalInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioFiscal.label', default: 'FolioFiscal'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [folioFiscalInstance: folioFiscalInstance]
			break
		case 'POST':
	        def folioFiscalInstance = FolioFiscal.get(params.id)
	        if (!folioFiscalInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioFiscal.label', default: 'FolioFiscal'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (folioFiscalInstance.version > version) {
	                folioFiscalInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'folioFiscal.label', default: 'FolioFiscal')] as Object[],
	                          "Another user has updated this FolioFiscal while you were editing")
	                render view: 'edit', model: [folioFiscalInstance: folioFiscalInstance]
	                return
	            }
	        }

	        folioFiscalInstance.properties = params

	        if (!folioFiscalInstance.save(flush: true)) {
	            render view: 'edit', model: [folioFiscalInstance: folioFiscalInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'folioFiscal.label', default: 'FolioFiscal'), folioFiscalInstance.id])
	        redirect action: 'show', id: folioFiscalInstance.id
			break
		}
    }

    def delete() {
        def folioFiscalInstance = FolioFiscal.get(params.id)
        if (!folioFiscalInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioFiscal.label', default: 'FolioFiscal'), params.id])
            redirect action: 'list'
            return
        }

        try {
            folioFiscalInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'folioFiscal.label', default: 'FolioFiscal'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'folioFiscal.label', default: 'FolioFiscal'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}

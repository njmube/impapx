package com.luxsoft.cfd

import org.springframework.dao.DataIntegrityViolationException

class CertificadoController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [certificadoInstanceList: Certificado.list(params), certificadoInstanceTotal: Certificado.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[certificadoInstance: new Certificado(params)]
			break
		case 'POST':
	        def certificadoInstance = new Certificado(params)
	        if (!certificadoInstance.save(flush: true)) {
	            render view: 'create', model: [certificadoInstance: certificadoInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'certificado.label', default: 'Certificado'), certificadoInstance.id])
	        redirect action: 'show', id: certificadoInstance.id
			break
		}
    }

    def show() {
        def certificadoInstance = Certificado.get(params.id)
        if (!certificadoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'certificado.label', default: 'Certificado'), params.id])
            redirect action: 'list'
            return
        }

        [certificadoInstance: certificadoInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def certificadoInstance = Certificado.get(params.id)
	        if (!certificadoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'certificado.label', default: 'Certificado'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [certificadoInstance: certificadoInstance]
			break
		case 'POST':
	        def certificadoInstance = Certificado.get(params.id)
	        if (!certificadoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'certificado.label', default: 'Certificado'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (certificadoInstance.version > version) {
	                certificadoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'certificado.label', default: 'Certificado')] as Object[],
	                          "Another user has updated this Certificado while you were editing")
	                render view: 'edit', model: [certificadoInstance: certificadoInstance]
	                return
	            }
	        }

	        certificadoInstance.properties = params

	        if (!certificadoInstance.save(flush: true)) {
	            render view: 'edit', model: [certificadoInstance: certificadoInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'certificado.label', default: 'Certificado'), certificadoInstance.id])
	        redirect action: 'show', id: certificadoInstance.id
			break
		}
    }

    def delete() {
        def certificadoInstance = Certificado.get(params.id)
        if (!certificadoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'certificado.label', default: 'Certificado'), params.id])
            redirect action: 'list'
            return
        }

        try {
            certificadoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'certificado.label', default: 'Certificado'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'certificado.label', default: 'Certificado'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}

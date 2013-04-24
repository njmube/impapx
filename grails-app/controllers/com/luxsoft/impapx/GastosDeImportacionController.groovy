package com.luxsoft.impapx

import org.springframework.dao.DataIntegrityViolationException

class GastosDeImportacionController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 50, 1000)
		params.sort='id'
		params.order='desc'
		
		def list=GastosDeImportacion.list(params)
        [gastosDeImportacionInstanceList: list, gastosDeImportacionInstanceTotal: GastosDeImportacion.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
			def fac=new GastosDeImportacion(params)
			fac.tasaDeImpuesto=16
        	[cuentaPorPagarInstance:fac]
			break
		case 'POST':
		
			params.proveedor=Proveedor.get(params.long('proveedorId'))
	        def gastosDeImportacionInstance = new GastosDeImportacion(params)
			
	        if (!gastosDeImportacionInstance.save(failOnError: true)) {
	            render view: 'create', model: [cuentaPorPagarInstance: gastosDeImportacionInstance]
	            return
	        }

			//flash.message = message(code: 'cuentaPorPagar.created.message', args: [gastosDeImportacionInstance.id])
	        redirect action: 'edit', id: gastosDeImportacionInstance.id
			break
		}
    }

    def show() {
        def fac = GastosDeImportacion.get(params.id)
        if (!fac) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'gastosDeImportacion.label', default: 'GastosDeImportacion'), params.id])
            redirect action: 'list'
            return
        }

        [gastosDeImportacionInstance: fac]
    }

	def edit() {
		switch (request.method) {
			case 'GET':
				def cuentaPorPagarInstance = GastosDeImportacion.get(params.id)
				
				if (!cuentaPorPagarInstance) {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaPorPagar.label', default: 'CuentaPorPagar'), params.id])
					redirect action: 'list'
					return
				}
	
				[cuentaPorPagarInstance: cuentaPorPagarInstance]
				break
			case 'POST':
				
				def cuentaPorPagarInstance = GastosDeImportacion.get(params.id)
				
				if (!cuentaPorPagarInstance) {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaPorPagar.label', default: 'Factura de importaci—n'), params.id])
					redirect action: 'list'
					return
				}
	
				if (params.version) {
					def version = params.version.toLong()
					if (cuentaPorPagarInstance.version > version) {
						cuentaPorPagarInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
								  [message(code: 'cuentaPorPagar.label', default: 'Factura de importaci—n')] as Object[],
								  "Another user has updated this CuentaPorPagar while you were editing")
						render view: 'edit', model: [cuentaPorPagarInstance: cuentaPorPagarInstance]
						return
					}
				}
				def proveedor=cuentaPorPagarInstance.proveedor
				params.proveedor=proveedor
				cuentaPorPagarInstance.properties = params
				
				if (!cuentaPorPagarInstance.save(failOnError: true)) {
					render view: 'edit', model: [cuentaPorPagarInstance: cuentaPorPagarInstance]
					return
				}
	
				flash.message = message(code: 'cuentaPorPagar.updated.message', args: [cuentaPorPagarInstance.id])
				redirect action: 'list'
				break
			}
	}

    def delete() {
		
        def fac = GastosDeImportacion.get(params.id)
        if (!fac) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaDeImportacion.label', default: 'FacturaDeImportacion'), params.id])
            redirect action: 'list'
            return
        }

        try {
            fac.delete(flush: true)
			flash.message = message(code: 'cuentaPorPagar.deleted.message', args: [ params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'facturaDeImportacion.label', default: 'FacturaDeImportacion'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}

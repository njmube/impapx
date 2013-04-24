package com.luxsoft.impapx

import grails.converters.JSON;

import org.springframework.dao.DataIntegrityViolationException

class CompraControllerBak {
	
	static scaffold=true

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { 
        redirect(action: "list", params: params)
		//render(view:"pruebas")
		
    }
	
	def proveedoresList(){
		println params
		def proveedores=Proveedor.findAllByNombreIlike(params.term+"%",[max:10,sort:"nombre",order:"desc"])
		def proveedoresList=proveedores.collect { prov ->
			[id:prov.id,label:prov.nombre,value:prov.nombre]
		}
		//def jsonResult=[provs:proveedoresList]
		render proveedoresList as JSON
	}

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [compraInstanceList: Compra.list(params), compraInstanceTotal: Compra.count()]
    }

    def create() {
		def compra=new Compra(params);
		compra.fecha=new Date()
		compra.moneda=Currency.getInstance("USD")
        [compraInstance: compra]
    }

    def save() {
		println 'Parametros para la genracion de compra: '+params
		Proveedor p=Proveedor.get(params.proveedorId)
		params.proveedor=p
		
        def compraInstance = new Compra(params)
        if (!compraInstance.save(flush: true)) {
            render(view: "create", model: [compraInstance: compraInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'compra.label', default: 'Compra'), compraInstance.id])
        redirect(action: "show", id: compraInstance.id)
    }

    def show(Long id) {
        def compraInstance = Compra.get(id)
        if (!compraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), id])
            redirect(action: "list")
            return
        }

        [compraInstance: compraInstance]
    }

    def edit(Long id) {
        def compraInstance = Compra.get(id)
        if (!compraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), id])
            redirect(action: "list")
            return
        }

        [compraInstance: compraInstance]
    }

    def update(Long id, Long version) {
        def compraInstance = Compra.get(id)
        if (!compraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (compraInstance.version > version) {
                compraInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'compra.label', default: 'Compra')] as Object[],
                          "Another user has updated this Compra while you were editing")
                render(view: "edit", model: [compraInstance: compraInstance])
                return
            }
        }

        compraInstance.properties = params

        if (!compraInstance.save(flush: true)) {
            render(view: "edit", model: [compraInstance: compraInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'compra.label', default: 'Compra'), compraInstance.id])
        redirect(action: "show", id: compraInstance.id)
    }

    def delete(Long id) {
        def compraInstance = Compra.get(id)
        if (!compraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), id])
            redirect(action: "list")
            return
        }

        try {
            compraInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), id])
            redirect(action: "show", id: id)
        }
    }
}

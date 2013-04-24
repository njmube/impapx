package com.luxsoft.impapx

import org.springframework.dao.DataIntegrityViolationException

class LineaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        [lineaInstanceList: Linea.list(params), lineaInstanceTotal: Linea.count()]
    }

    def create() {
        [lineaInstance: new Linea(params)]
    }

    def save() {
        def lineaInstance = new Linea(params)
        if (!lineaInstance.save(flush: true)) {
            render(view: "create", model: [lineaInstance: lineaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'linea.label', default: 'Linea'), lineaInstance.id])
        redirect(action: "show", id: lineaInstance.id)
    }

    def show(Long id) {
        def lineaInstance = Linea.get(id)
        if (!lineaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'linea.label', default: 'Linea'), id])
            redirect(action: "list")
            return
        }

        [lineaInstance: lineaInstance]
    }

    def edit(Long id) {
        def lineaInstance = Linea.get(id)
        if (!lineaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'linea.label', default: 'Linea'), id])
            redirect(action: "list")
            return
        }

        [lineaInstance: lineaInstance]
    }

    def update(Long id, Long version) {
        def lineaInstance = Linea.get(id)
        if (!lineaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'linea.label', default: 'Linea'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (lineaInstance.version > version) {
                lineaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'linea.label', default: 'Linea')] as Object[],
                          "Another user has updated this Linea while you were editing")
                render(view: "edit", model: [lineaInstance: lineaInstance])
                return
            }
        }

        lineaInstance.properties = params

        if (!lineaInstance.save(flush: true)) {
            render(view: "edit", model: [lineaInstance: lineaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'linea.label', default: 'Linea'), lineaInstance.id])
        redirect(action: "show", id: lineaInstance.id)
    }

    def delete(Long id) {
        def lineaInstance = Linea.get(id)
        if (!lineaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'linea.label', default: 'Linea'), id])
            redirect(action: "list")
            return
        }

        try {
            lineaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'linea.label', default: 'Linea'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'linea.label', default: 'Linea'), id])
            redirect(action: "show", id: id)
        }
    }
}

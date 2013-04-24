package com.luxsoft.impapx

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONArray
import org.hibernate.criterion.MatchMode;
import org.springframework.dao.DataIntegrityViolationException

class ProveedorController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 500, 550)
		params.sort='nombre'
		params.order='asc'
        [proveedorInstanceList: Proveedor.list(params), proveedorInstanceTotal: Proveedor.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[proveedorInstance: new Proveedor(params)]
			break
		case 'POST':
	        def proveedorInstance = new Proveedor(params)
	        if (!proveedorInstance.save(flush: true)) {
	            render view: 'create', model: [proveedorInstance: proveedorInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedorInstance.id])
	        redirect action: 'show', id: proveedorInstance.id
			break
		}
    }

    def show() {
        def proveedorInstance = Proveedor.get(params.id)
        if (!proveedorInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect action: 'list'
            return
        }

        [proveedorInstance: proveedorInstance]
    }

    def edit() {
    	println 'Editando proveedor: '+params
		switch (request.method) {
		case 'GET':
	        def proveedorInstance = Proveedor.findById(params.id, [fetch: [productos: 'select']])
			def productos=ProveedorProducto.findAll(sort:"codigo") {
				proveedor.id==params.long('id')
			}
	        
	        println 'Productos registrados: '+productos
	        if (!proveedorInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [proveedorInstance: proveedorInstance]
			break
		case 'POST':
	        def proveedorInstance = Proveedor.get(params.id)
	        if (!proveedorInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (proveedorInstance.version > version) {
	                proveedorInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'proveedor.label', default: 'Proveedor')] as Object[],
	                          "Another user has updated this Proveedor while you were editing")
	                render view: 'edit', model: [proveedorInstance: proveedorInstance]
	                return
	            }
	        }

	        proveedorInstance.properties = params

	        if (!proveedorInstance.save(flush: true)) {
	            render view: 'edit', model: [proveedorInstance: proveedorInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedorInstance.id])
	        redirect action: 'edit', id: proveedorInstance.id
			break
		}
    }

    def delete() {
        def proveedorInstance = Proveedor.get(params.id)
        if (!proveedorInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect action: 'list'
            return
        }

        try {
            proveedorInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect action: 'show', id: params.id
        }
    }
	
	def proveedoresJSONList(){
		def proveedores=Proveedor.findAllByNombreIlike(params.term+"%",[max:10,sort:"nombre",order:"desc"])
		def proveedoresList=proveedores.collect { prov ->
			[id:prov.id,label:prov.nombre,value:prov.nombre]
		}
		//proveedoresList.add([id:null,label:"TODOS",value:"TODOS"])
		//def jsonResult=[provs:proveedoresList]
		render proveedoresList as JSON
	}
	
	def proveedoresGastosJSONList(){
		def proveedores=Proveedor.findAllByNombreIlikeAndNacional(params.term+"%",true,[max:10,sort:"nombre",order:"desc"])
		def proveedoresList=proveedores.collect { prov ->
			[id:prov.id,label:prov.nombre,value:prov.nombre]
		}
		//def jsonResult=[provs:proveedoresList]
		render proveedoresList as JSON
	}

	def productosAsignablesJSONList(){
		def c=Producto.createCriteria()
		def productos=c.list{
			or{
				ilike("clave",params.term+'%')
				ilike("descripcion",'%'+params.term+'%')
			}
			order 'clave','asc'
			maxResults(15)
		}
		def productosList=productos.collect {
			[id:it.clave,label:it.toString(),value:it.toString(),descripcion:it.descripcion]
		}
		render productosList as JSON
	}

	def localisarPrecio(long proveedorId,long productoId){
		println 'Localizando precio: '+params
		def provProducto=ProveedorProducto.find{
			proveedor.id==proveedorId && producto.id==productoId
		}
		if(provProducto==null)
			provProducto=new ProveedorProducto(costoUnitario:0)
		render provProducto as JSON
	}
	
	def actualizarCostoUnitarioEnProductos(){
		println 'Actualizar costo unitario de productos params:'+params
		JSONArray jsonArray=JSON.parse(params.partidas);
		jsonArray.each{
			def det=ProveedorProducto.get(it.toLong())
			det.costoUnitario=params.costoUnitario.toBigDecimal()
		}
		def res=[costoUnitario:params.costoUnitario]
		render res as JSON
	}
	
	def eliminarProductos(){
		println 'Eliminando proveedorProductos params:'+params
		JSONArray jsonArray=JSON.parse(params.partidas);
		jsonArray.each{
			def det=ProveedorProducto.get(it.toLong())
			//def proveedor=det.proveedor
			//proveedor.removeFromProductos(det)
			det.delete(flush:true)
			
		}
		def res=[eliminados:jsonArray.length()]
		render res as JSON
	}
	
	def selectorDeProductos(long id){
		Proveedor p=Proveedor.get(id)
		def hql="from Producto p where p not in (select x.producto from ProveedorProducto x where x.proveedor=?) order by p.linea.nombre,p.marca.nombre,p.clave"
		def res=Producto.findAll(hql,[p])
		[productos:res,productosTotal:res.size(),proveedor:p]
	}

	def registrarProductos(){
		def data=[:]
		def proveedor = Proveedor.findById(params.proveedorId,[fetch:[productos:'eager']])
		JSONArray jsonArray=JSON.parse(params.partidas);
		jsonArray.each{
			def prod=Producto.get(it.toLong())
			proveedor.addToProductos(producto:prod,costoUnitario:0.0,gramos:prod.gramos)
		}
		proveedor.save(failOnError:true)
		render data as JSON
	}
}

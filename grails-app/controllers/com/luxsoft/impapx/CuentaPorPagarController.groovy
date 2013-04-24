package com.luxsoft.impapx



class CuentaPorPagarController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        
    }

    def list() {
		println 'List: '+params
        params.max = Math.min(params.max ? params.int('max') : 50, 100)
        def facturas=CuentaPorPagar.findAll(params)
        [cuentaPorPagarInstanceList:facturas, cuentaPorPagarInstanceTotal: CuentaPorPagar.count()]
    }

    /*	
	def proveedoresList(){
		def proveedores=Proveedor.findAllByNombreIlike(params.term+"%",[max:10,sort:"nombre",order:"desc"])
		def proveedoresList=proveedores.collect { prov ->
			[id:prov.id,label:prov.nombre,value:prov.nombre]
		}
		render proveedoresList as JSON
	}
	*/
	
	
	
}

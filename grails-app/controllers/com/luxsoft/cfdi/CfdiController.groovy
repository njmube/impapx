package com.luxsoft.cfdi



import static org.springframework.http.HttpStatus.*

import com.luxsoft.impapx.Venta;

import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;




class CfdiController {

    
	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
	def cfdiTimbrador
	
	def cfdiService

    def index() {
		
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[cfdiInstanceList:Cfdi.list(params),cfdiInstanceCount: Cfdi.count()]
        
    }
	
	def list(){
		redirect action: 'index', params: params
	}

    def show() {
        def cfdi=Cfdi.findById(params.id)
		if(cfdi==null){
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cfdiInstance.label', default: 'Cfdi'), params.id])
            redirect action: "index", method: "GET"
		}
		[cfdiInstance:cfdi]
    }
	
	
	
    
	
	def mostrarXml(){
		def cfdi=Cfdi.findById(params.id)
		if(cfdi==null){
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cfdiInstance.label', default: 'Cfdi'), params.id])
            redirect action: "index", method: "GET"
		}
		render view:'cfdiXml',model:[cfdi:cfdi,xml:cfdi.getComprobanteDocument().xmlText()]
	}
	
	
	def delete() {
		//println 'Eliminando CFDI: '+params.id
		def cfdi=Cfdi.findById(params.id)
		if(cfdi==null){
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cfdiInstance.label', default: 'Cfdi'), params.id])
			redirect action: "index", method: "GET"
		}
		cfdi.delete flush:true
		flash.message="CFDI: $params.id eliminado exitosamente"
		redirect action:'index'
	}
	
	
	
	def facturar(){
		println 'Facturando venta: '+params.id
		def venta=Venta.findById(params.id)
		if(venta==null){
			flash.message="No existe la venta: "+params.id
			redirect controller:'venta',action:'edit',params:params
		}
		def cfdi=cfdiService.generarCfdi(venta)
		render view:'/cfdi/show',model:[cfdiInstance:cfdi]
	}
	
	def timbrar(){
		println 'Tratando de timbrar: '+params.id
		def cfdi=Cfdi.findById(params.id)
		
		if(cfdi==null){
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cfdiInstance.label', default: 'Cfdi'), params.id])
			redirect action: "index", method: "GET"
		}
		println "Timbrando en ${cfdiTimbrador.timbradoDePrueba? 'En modo de prueba':'En modo real'} el cfdi: $cfdi "
		
		cfdi=cfdiTimbrador.timbrar(cfdi,"PAP830101CR3", "yqjvqfofb")
		render view:'/cfdi/show',model:[cfdiInstance:cfdi]
	}
	
	def imprimirCfdi(){
		
		def cfdi=Cfdi.findById(params.id)
		if(cfdi==null){
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cfdiInstance.label', default: 'Cfdi'), params.id])
            redirect action: "show", params:[id:id]
		}
		Comprobante cfd=cfdi.getComprobante()
		def conceptos=cfd.getConceptos().getConceptoArray()
		
		def modelData=conceptos.collect { cc ->
			
			def res=[
			'cantidad':cc.getCantidad()
			 ,'NoIdentificacion':cc.getNoIdentificacion()
			 ,'descripcion':cc.getDescripcion()
			 ,'unidad':cc.getUnidad()
			 ,'ValorUnitario':cc.getValorUnitario()
			 ,'Importe':cc.getImporte()
			 ]
			if(cc.informacionAduaneraArray){
				res.PEDIMENTO_FECHA=cc.informacionAduaneraArray[0]?.fecha.getTime()
				res.PEDIMENTO=cc.informacionAduaneraArray[0]?.numero
				res.ADUANA=cc.informacionAduaneraArray[0]?.aduana
			}
			if(cc.cuentaPredial){
				res.CUENTA_PREDIAL=cc.cuentaPredial.numero
			}
			return res
		}
		def repParams=CfdiPrintUtils.resolverParametros(cfdi)
		params<<repParams
		params.FECHA=cfd.fecha.getTime().format("yyyy-MM-dd'T'HH:mm:ss")
		chain(controller:'jasper',action:'index',model:[data:modelData],params:params)

		
	}
	
	def descargarXml(){
		Cfdi cfdi=Cfdi.findById(params.id)
		if(cfdi==null){
			notFound()
			return
		}
		response.setContentType("application/octet-stream")
		response.setHeader("Content-disposition", "attachment; filename=\"$cfdi.xmlName\"")
		response.outputStream << cfdi.getComprobanteDocument().newInputStream()
		
	}
}

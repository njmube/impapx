package com.luxsoft.impapx

import grails.converters.JSON

class PedimentoControllerBak_TODELETE {

    def index() { }
	
	def agregarPedimento(){
		CuentaPorPagar fac=CuentaPorPagar.get(params.facturaId)
		def pedimento=new Pedimento(params)
		fac.agregarPedimento(pedimento)
		//pedimento.factura=fac
		//fac.pedimentos.add(fac)
		//fac.actualizarPedimentos()
		def errors=[]
		
		if(!pedimento.save(flush:true)){
			errors.addAll(pedimento.errors.allErrors.collect {message(error:it,code:'pedimento.not.unique')})
		}
		render(contentType:"text/json"){
			if(errors){
				success='false'
				errorList=errors
			}else{
				success='true'
				pedimentoId=pedimento.id
				fecha=pedimento.fecha.format("dd/MM/yyyy")
				ped=pedimento.pedimento
				dta=pedimento.dta
				prevalidacion=pedimento.prevalidacion
				referencia=pedimento.referencia
			}
		}
		
		/*
		def res=pedimento.save(failOnError:true)
		//println res
		def data=[fecha:res.fecha.format("dd/MM/yyyy")
			,pedimento:res.pedimento
			,dta:res.dta
			,prevalidacion:res.prevalidacion
			,referencia:res.referencia]
		render data as JSON
		*/
	}

	def listAsJSON(){
		println 'Soliciando partidas JSON'+params
        def pedimentos=Pedimento.findAll("from Pedimento p where p.factura.id=?",[params.facturaId])
        def dataToRender = [:]
        dataToRender.sEcho = params.sEcho
        dataToRender.aaData=[]
		println dataToRender
        dataToRender.iTotalRecords=pedimentos.size()
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords
        pedimentos.each{ prod ->
            dataToRender.aaData <<[
                prod.fecha
                ,prod.pedimento
                ,prod.dta
                ,prod.prevalidacion
                ,prod.referencia
                ]
        }
        render dataToRender as JSON
    }
}

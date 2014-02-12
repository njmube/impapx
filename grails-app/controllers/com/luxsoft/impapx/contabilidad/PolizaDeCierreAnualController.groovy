package com.luxsoft.impapx.contabilidad

class PolizaDeCierreAnualController {

    def polizaService

   def index() {
	   redirect action: 'list', params: params
    }
	 
	def mostrarPoliza(long id){
		def poliza=Poliza.findById(id,[fetch:[partidas:'eager']])
		render (view:'/poliza/poliza2' ,model:[poliza:poliza,partidas:poliza.partidas])
	}
	 
	def list() {
		if(!session.periodoContable){
			PeriodoContable periodo=new PeriodoContable()
			periodo.actualizarConFecha()
			session.periodoContable=periodo
		}
		PeriodoContable periodo=session.periodoContable
		def sort=params.sort?:'fecha'
		def order=params.order?:'desc'
		
		def polizas=Poliza.findAllByTipoAndDescripcionLikeAndFechaBetween('CIERRE_ANUAL'
			,'CIERRE ANUAL %'+periodo.year
			,periodo.inicio,periodo.fin,[sort:sort,order:order])
		[polizaInstanceList: polizas, polizaInstanceTotal: polizas.size()]
	}
	
	def generarSaldos(){
		
	}
	
	def generarPoliza(String fecha){
		Date dia=Date.parse("dd/MM/yyyy",fecha)
		
		params.dia=dia
		
		//Prepara la poliza
		Poliza poliza=new Poliza(tipo:'CIERRE_ANUAL',folio:1, fecha:dia,descripcion:'CIERRE ANUAL '+dia.toYear(),partidas:[])
		// Procesadores
		generar(poliza, dia)
		//Salvar la poliza
		poliza.debe=poliza.partidas.sum (0.0,{it.debe})
		poliza.haber=poliza.partidas.sum(0.0,{it.haber})
		poliza=polizaService.salvarPoliza(poliza)
		redirect action: 'mostrarPoliza', params: [id:poliza.id]
	}
	
	def generar(def poliza , def dia){
		def asiento="Provision "+dia.toYear()
		def saldos=SaldoPorCuentaContable
			.find("from SaldoPorCuentaContable s where s.year=? and s.mes=13 and s.cuenta.deResultado=true ")
	}
	
	
	
}

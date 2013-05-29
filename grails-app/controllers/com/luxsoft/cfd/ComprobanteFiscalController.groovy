package com.luxsoft.cfd

import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x2.ComprobanteDocument.Comprobante.Conceptos.Concepto;
import mx.gob.sat.cfd.x2.TInformacionAduanera;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateUtils;

import com.luxsoft.impapx.Empresa;
import com.luxsoft.impapx.Venta;
import com.luxsoft.impapx.contabilidad.PeriodoContable;
import com.luxsoft.impapx.cxc.CancelacionDeCargo;

class ComprobanteFiscalController {
	
	

    def index() { 
		redirect action: 'list', params: params
	}
	
	def actualizarPeriodo(PeriodoContable periodo){
		periodo.actualizarConFecha()
		session.periodoContable=periodo
		redirect action:'list'
	}
	
	 def list() {
		 def periodo=session.periodoContable
		if(!periodo){
			println 'Asignando periodo contable:..'
			periodo=new PeriodoContable()
			periodo.actualizarConFecha()
			session.periodoContable=periodo
		}
        params.max = 1000
		params.sort='fecha,folio'
		params.order='asc'
		def year=periodo.year
		def month=periodo.month
		
		def res=[]
		def list= ComprobanteFiscal.findAll("from ComprobanteFiscal c where year(c.fecha)=? and month(c.fecha)=?",[year,month],[readOnly:true])
		
		if(!list)
			list=[]
		ComprobanteFiscal.withNewSession { session ->
			def cancelados=ComprobanteFiscal.findAll("from ComprobanteFiscal c where year(c.fecha)=? and month(c.fecha)=? and c.origen in (select x.cargo.id from CancelacionDeCargo x)"
				,[year,month],[readOnly:true])
			session.clear()
			session.close()
			cancelados.each{it.estado='0'}
			res.addAll(cancelados)
		} 
		
		
		
		res.addAll(list)
		res.sort{
			it.fecha
		}
		
       [cfdList:res, cfdTotal:res.size()]
    }
	 
	 def actualizar(){
		 /*
		 println 'Actualizando CFDs'
		 def year=2013
		 def month=3
		 def ventas=Venta.findAll("from Venta v where year(v.fecha)=? and month(v.fecha)=? and v.cfd!=null",[year,month])
		 ventas.each{
		 	println 'Actualizando CFD: '+it.cfd.folio
			 try{
				 def cfd=it.cfd
				 
				 cfd.rfc=cfd.comprobante.receptor.rfc
				 cfd.fecha=cfd.comprobante.fecha.getTime()
				 cfd.serie=cfd.comprobante.serie
				 cfd.numeroDeAprobacion=cfd.comprobante.noAprobacion
				 cfd.anoAprobacion=cfd.comprobante.anoAprobacion
				 cfd.total=cfd.comprobante.total
				 cfd.impuesto=cfd.comprobante.getImpuestos().getTotalImpuestosTrasladados()
				 cfd.tipoCfd=cfd.comprobante.getTipoDeComprobante().toString().substring(0, 1).toUpperCase()
				 cfd.estado='1'
				 Concepto concepto=cfd.comprobante.conceptos.getConceptoArray(0)
				 if(concepto!=null){
					 if(concepto.informacionAduaneraArray){
						 cfd.pedimento=concepto.informacionAduaneraArray[0].numero
						 cfd.aduana=concepto.informacionAduaneraArray[0].aduana
						 cfd.fechaPedimento=concepto.informacionAduaneraArray[0].fecha.getTime()
					 }
				 }
				 cfd.save(failOnError:true)
				 if(cfd.hasErrors()){
					 println cfd.errors
				 }
				 
			 }catch(Exception ex){
			 	println ExceptionUtils.getRootCauseMessage(ex);
			 }
			 
		 }*/
	 }
	 
	 def generarArchivo(){
		 def periodo=session.periodoContable
		 def year=periodo.year
		 def month=periodo.month		 
		 def res=[]
		 
		 def list= ComprobanteFiscal.findAll("from ComprobanteFiscal c where year(c.fecha)=? and month(c.fecha)=?",[year,month],[readOnly:true])
		 
		 if(!list)
			 list=[]
		 
		 ComprobanteFiscal.withNewSession { session ->
			 def cancelados=ComprobanteFiscal.findAll("from ComprobanteFiscal c where year(c.fecha)=? and month(c.fecha)=? and c.origen in (select x.cargo.id from CancelacionDeCargo x)"
				 ,[year,month],[readOnly:true])
			 session.clear()
			 session.close()
			 cancelados.each{it.estado='0'}
			 res.addAll(cancelados)
		 }
		 
		 res.addAll(list)
		 res.sort{
			 it.fecha
		 }
		 def empresa=Empresa.list().iterator().next()
		 def sm=month.toString().padLeft(2,'0')
		 def fileName="1$empresa.rfc$sm$year"+'.txt'
		 def file=new File(grailsApplication.config.cfd.report.path+fileName).withWriter('UTF-8', {file ->
			 res.each{ it ->
				 def fecha=new Date(it.fecha.time).format("dd/MM/yyyy hh:mm:ss")
				 def fechaPedimento= it.fechaPedimento ? new Date(it.fechaPedimento.time).format("dd/MM/yyyy")  : ''
				 String line="|$it.rfc|$it.serie|$it.folio|$it.anoAprobacion$it.numeroDeAprobacion|$fecha|$it.total|$it.impuesto|$it.estado|$it.tipoCfd|${it.pedimento?:''}|$fechaPedimento|${it.aduana?:''}|"
				 file.writeLine(line)
			 }
		 })
		 //println 'Arhvo generado: '+file
		 flash.message="Archivo para SAT generado: "+fileName
		 render( view:'list', model:[cfdList:res, cfdTotal:res.size()])
	 }
	 
	 def actualizarDatos(){
		 def comprobantes=ComprobanteFiscal.list()
		 
		 comprobantes.each{c ->
			 Comprobante cfd=it.getComprobante()
			 Concepto conceptoRow=cfd.getConceptos().iterator().next()
			 TInformacionAduanera aduana
			 if(conceptoRow){
				 aduana=conceptoRow.getInformacionAduaneraArray(0)
				 
			 }
			 c.fecha=cfd.getFecha()
			 c.folio=cfd.getFolio()
			 c.serie=cfd.getSerie()
			 c.numeroDeAprobacion=cfd.getNoAprobacion()
			 c.rfc=cfd.getSerie()
			 c.tipoCfd='I'
			 c.estado='1'
			 c.xmlPath=xml.toURI().toURL().toString()
			 c.anoAprobacion=cfd.getAnoAprobacion()
			 c.impuesto=impuestos.getTotalImpuestosTrasladados()
			 c.total=cfd.getTotal()
			 c.aduana=aduana.aduana
			 c.pedimeno=aduana.numero
			 c.fechaPedimento=aduana.fecha.getTime()
		 }
	 }
}

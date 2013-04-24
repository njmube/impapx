package com.luxsoft.impapx.cxc

import com.luxsoft.cfd.ComprobanteFiscal;

class CXCNota extends CXCAbono{
	
	ComprobanteFiscal cfd
	List partidas
	String tipo
	BigDecimal descuento=0.0
	
	static hasMany =[partidas:CXCNotaDet]

    static constraints = {
		cfd(nullable:true)
		tipo(nullable:false,inList:['DESCUENTO','BONIFICACION','DEVOLUCION'])
		descuento(validator:{val,obj -> 
				
				if(obj.tipo=='DESCUENTO' && val<=0.0){return 'descuentoRequerido'}
			}
		)
    }
	
	static mapping ={
		partidas cascade:"all-delete-orphan"
	}
	
	def actualizarImportes(){
		if(cfd==null){
			importe=partidas.sum(0,{ it.importe*this.tc})
			impuesto=importe*(impuestoTasa/100)
			total=importe+impuesto
		}
	}
	
	def beforeUpdate(){
		if(cfd!=null){
			//throw new RuntimeException("CFD Generado para la nota, no se puede modificar")
			actualizarImportes()
		}
	}
	
	def beforeInsert(){
		super.beforeInsert()
		//if(tipo!='DESCUENTO')
		//descuento=0.0
		//importe=partidas.sum(0,{ it.importe*this.tc})
	}
}

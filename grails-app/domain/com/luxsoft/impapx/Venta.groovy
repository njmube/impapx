package com.luxsoft.impapx

import java.math.BigDecimal;

import com.luxsoft.cfd.ComprobanteFiscal;

import util.Rounding;


class Venta {
	
	
	Cliente cliente
	Date fecha
	
	Currency moneda=Currency.getInstance('MXN');
	
	BigDecimal tc=1.0
	BigDecimal importe=0
	BigDecimal descuentos=0
	BigDecimal subtotal=0
	
	BigDecimal impuestos=0
	BigDecimal total=0
	
	int plazo=0
	Date vencimiento
	String formaDePago
	String cuentaDePago="0000"
	
	BigDecimal kilos=0
	
	String comentario
	
	Date dateCreated
	Date lastUpdated
	List partidas
	ComprobanteFiscal cfd
	BigDecimal saldo
	BigDecimal pagosAplicados=0
	
	String tipo="VENTA";
	
	static hasMany = [partidas:VentaDet]

    static constraints = {
		
		cliente(nullable:false)
		fecha(nullable:false)
		importe(nullable:false,scale:2)
		descuentos(nullable:false,scale:2)
		subtotal(nullable:false,scale:2)
		impuestos(nullable:false,scale:2)
		total(nullable:false,scale:2)
		moneda(nullable:false)
		tc(nullable:false,scale:4)
		plazo(nullable:false)
		vencimiento(nullable:false)
		formaDePago(nullable:false,maxSize:20)
		cuentaDePago(nullable:false,maxSize:4)
		kilos(nullable:false,scale:3)
		comentario(blank:true,maxSize:300)
		cfd(nullable:true)
		saldo(nullable:true)
		tipo(inList:['VENTA','NOTA_DE_CARGO'])
    }
	
	static mapping = {
		partidas lazy:false
		cliente fetch:'join'
		partidas cascade: "all-delete-orphan"
		sort "id"
		pagosAplicados formula:'(select ifnull(sum(x.total),0) from CXCAplicacion x where x.factura_id=id)'
	}
	
	static transients = ['saldoActual']
	
	public BigDecimal getSaldoActual(){
		def pag=pagosAplicados?:0.0
		return total-pag
	}
	
	def beforeUpdate(){
		if(tipo=='VENTA'){
			importe=partidas.sum(0.0,{it.importe})
			importe=Rounding.round(importe, 2)
			
			subtotal=importe-descuentos
			impuestos=subtotal*0.16
			impuestos=Rounding.round(impuestos, 2)
			total=subtotal+impuestos
		} 
	}
	
	def getPedimento(){
		return 'PED_ PENDIENTE'
	}
	
	def getAduana(){
		return 'ADUANA_PENDIENTE'
	}
	
	Date getPedimentoFecha(){
		return null;
	}
	
}

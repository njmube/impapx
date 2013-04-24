package com.luxsoft.impapx

class LuxorUtilTagLib {
	
	static namespace='lx'
	
	/**
	 * Renders la fecha en el fomrato estandarizado dd/MM/yyyy
	 * @attrs date REQUIRED La fecha a formatear
	 */
	def shortDate={ attrs ->
		out << g.formatDate(date:attrs.date,format:"dd/MM/yyyy")
	}
	
	/**
	 * Formatea un numero a una cantidad moentaria  
	 * 
	 * @attrs number REQUIRED El numero a formatear en moneda
	 */
	def moneyFormat={ attrs ->
		out <<g.formatNumber(number:attrs.number, type:"currency", currencyCode:"MXN",locale:"es_MX")
	}
	
	/**
	 * Da formato adecuado a los cantidades utlizadas en medidas de kilos
	 * @attrs number REQUIRED El numero de kilos a dar formato
	 */
	def kilosFormat={attrs ->
		out <<g.formatNumber(number:attrs.number, format:"###,###,###")
	}
	
	/**
	 * Da formato adecuado a cantidades utilizadas para expresar millares de hojas
	 * 
	 * @attrs number REQUIRED El numero de millares a dar formato
	 */
	def millaresFormat={attrs ->
		out <<g.formatNumber(number:attrs.number, format:"###,###,###.###")
	}

}

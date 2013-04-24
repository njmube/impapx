package com.luxsoft.cfd



class FolioFiscal {
	
	String serie
	Long folio
	Long folioInicial
	Long folioFinal
	Date asignacion
	int noAprobacion
	int anoAprobacion
	String directorioAlmacenar
	
	

    static constraints = {
		serie()
		folioInicial()
		folioFinal()
		asignacion()
		noAprobacion()
		anoAprobacion()
		directorioAlmacenar(nullable:true)
    }
	
	/**
	 * Incrementa el folio en uno y lo regresa
	 *
	 * @return
	 */
	Long next(){
		
		if(folio>=folioFinal)
			throw new RuntimeException("Se llego al limite de folios fiscales folio actual: "+folio+" folio final: "+folioFinal)
		folio++
		return folio;  
	}
}

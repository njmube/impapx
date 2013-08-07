package com.luxsoft.impapx

class ImpapFilters {

    def filters = {
		
		def empresa;
		
		jasperReport(controller:'jasper',action:'*'){
			before={
				if(!empresa){
					empresa=Empresa.getAll().get(0)
				}
				params.COMPANY=empresa.nombre
				//println 'Antes de ejcutar reporte con params: '+params
			}
			
		}
		/*
        all(controller:'*', action:'*') {
            before = {

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }*/
    }
}

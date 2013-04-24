databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "changelog") {
		// TODO add changes and preconditions here
	}	

	include file: 'change-log-1.0.1.groovy'

	include file: 'change-log-1.0.2.groovy'

	include file: 'change-log-1.0.3.groovy'
	
	include file: 'ColumnaDeCargoEnVentas.groovy'

	include file: 'CancelacionDeCargos.groovy'

	include file: 'RequisitadoEnCxP.groovy'

	include file: 'CxPNotaDeCreditoConcepto.groovy'

	include file: 'CxPNotaDeCreditoDocumento.groovy'
	

	include file: 'CxPAplicacionCxP.groovy'

	include file: 'PagoProveedorComplemento.groovy'

	

	include file: 'TiposDeNotaCxC.groovy'

	include file: 'CXCNota_Descuento.groovy'

	//include file: 'CXCNota_Tipo.groovy'

	include file: 'CFDUpdate.groovy'

	include file: 'CFDUpdate2.groovy'

	

	

	include file: 'tesoreria-compra-moneda.groovy'

	include file: 'tesoreria-compra-moneda3.groovy'

	include file: 'saldoDeCuentaBancos.groovy'


	

	include file: 'contabilidad.groovy'
	include file: 'contabilidad2.groovy'
	include file: 'contabilidad3.groovy'
	include file: 'contabilidad4.groovy'
	include file: 'contabilidad5.groovy'

	//include file: 'ajustesVarios.groovy'
	include file: 'contabilidad6.groovy'
	

	

	

	include file: 'cheques.groovy'

	include file: 'contabilidad7.groovy'

	

	include file: 'contabilidad8.groovy'

	

	include file: 'contabilidad9.groovy'

	include file: 'contabilidad10.groovy'

	include file: 'contabilidad11.groovy'

	include file: 'contabilidad12.groovy'

	include file: 'contabilidad13.groovy'

	include file: 'contabilidad14.groovy'

	include file: 'contabilidad15.groovy'

	

	include file: 'CXC_Parche1.groovy'

	include file: 'GastosParche_1.groovy'

	include file: 'GastosParche_2.groovy'

	

	include file: 'AnticiposParche_1.groovy'

	include file: 'GastosParche_3.groovy'

	include file: 'AnticiposParche_3.groovy'

	include file: 'ReportesParche_1.groovy'

	include file: 'ReportesParche_10.groovy'
}

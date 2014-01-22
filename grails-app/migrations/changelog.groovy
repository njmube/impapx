databaseChangeLog = {

	changeSet(author: "Ruben Cancino (generated)", id: "changelog") {
		// TODO add changes and preconditions here
	}	
	/*
	include file: 'creacionDeCuentaDeGastosGenerica.groovy'
	include file: 'ajusteFacturaDeGastos.groovy'
	include file: 'ajusteFacturaDeGastos2.groovy'
	include file: 'cambiosPagoProveedor.groovy'
	include file: 'cambiosCXCPago.groovy'
	include file: 'cancelacionDeCheques.groovy'
	include file: 'cambiosProveedor.groovy'
	include file: 'cambiosPolizaDet.groovy'
	include file: 'ventaGenerica.groovy'
	*/
	//include file: 'addProvisionadaToCxcImportacion.groovy'
	
	/*
	 * create table cfdi (id bigint not null auto_increment, version bigint not null
, cadena_original longtext, comentario longtext, date_created datetime not null, descuentos decimal(19,2) not null
, emisor longtext not null, fecha datetime not null, folio varchar(20) not null, importe decimal(19,2) not null
, impuesto decimal(19,2) not null, last_updated datetime not null, origen varchar(255) not null, receptor longtext not null, rfc varchar(13) not null
, serie varchar(15) not null, subtotal decimal(19,2) not null, timbrado datetime, tipo varchar(12) not null
, tipo_de_cfdi varchar(1) not null, total decimal(19,2) not null, url varchar(255), uuid longtext, xml mediumblob not null
, xml_name varchar(200), primary key (id)) engine=InnoDB




	 */

	//include file: 'add-cfdi-model.groovy'

	//include file: 'add-cfdi-model.groovy'
}

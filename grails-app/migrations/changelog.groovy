databaseChangeLog = {

	changeSet(author: "Ruben Cancino (generated)", id: "changelog") {
		// TODO add changes and preconditions here
	}

	

	include file: 'creacionDeCuentaDeGastosGenerica.groovy'

	

	include file: 'ajusteFacturaDeGastos.groovy'
}

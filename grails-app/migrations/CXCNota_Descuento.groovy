databaseChangeLog = {

	changeSet(author: "Ruben Cancino", id: "1355073975129-1") {
		comment("Columna de descuento para notas de credito de descuento")
		addColumn(tableName: "cxcabono") {
			column(name: "descuento", type: "decimal(19,2)")
		}
	}
}

databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1367250885852-1") {
		addColumn(tableName: "pago_proveedor") {
			column(name: "tipo_de_cambio", type: "decimal(19,2)")
		}
	}
}

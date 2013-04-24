databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1359585018017-2") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "ret_imp", type: "decimal(19,2)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1359585018017-3") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "ret_tasa", type: "decimal(19,2)")
		}
	}

	
}

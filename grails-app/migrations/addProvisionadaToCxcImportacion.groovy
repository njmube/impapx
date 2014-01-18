databaseChangeLog = {
	

	changeSet(author: "rubencancinoramos (generated)", id: "1346872969699-5") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "provisionada", type: "bigint")
		}
	}
	
}

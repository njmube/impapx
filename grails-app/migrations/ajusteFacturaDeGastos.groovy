databaseChangeLog = {

	changeSet(author: "rubencancinoramos (generated)", id: "1366929444375-1") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "descuento", type: "decimal(19,2)")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366929444375-2") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "rembolso", type: "decimal(19,2)")
		}
	}
}

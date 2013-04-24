databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1361823510868-1") {
		addColumn(tableName: "concepto_de_gasto") {
			column(name: "tipo", type: "varchar(15)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1361823510868-2") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "incrementable", type: "bit")
		}
	}

	
}

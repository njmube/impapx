databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1368718802561-1") {
		addColumn(tableName: "poliza_det") {
			column(name: "concepto", type: "varchar(50)")
		}
	}

	
}

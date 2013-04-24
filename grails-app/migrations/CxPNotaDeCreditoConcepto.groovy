databaseChangeLog = {

	changeSet(author: "pato (generated)", id: "1354666463487-1") {
		addColumn(tableName: "abono") {
			column(name: "concepto", type: "varchar(20)")
		}
	}

	
}

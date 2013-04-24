databaseChangeLog = {

	changeSet(author: "Ruben Cancino (generated)", id: "1354674904080-1") {
		addColumn(tableName: "abono") {
			column(name: "documento", type: "varchar(20)")
		}
	}

	changeSet(author: "Ruben Cancino (generated)", id: "1354674904080-2") {
		dropColumn(columnName: "disponible", tableName: "abono")
	}
}

databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1367339124443-1") {
		addColumn(tableName: "cheque") {
			column(name: "cancelacion", type: "datetime")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1367339124443-2") {
		addColumn(tableName: "cheque") {
			column(name: "comentario_cancelacion", type: "varchar(255)")
		}
	}
}

databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1362771544032-1") {
		addColumn(tableName: "embarque_det") {
			column(name: "gramos", type: "decimal(19,2)")
		}
	}
}

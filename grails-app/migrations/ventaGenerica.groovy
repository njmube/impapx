databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1369849713301-1") {
		addColumn(tableName: "venta") {
			column(name: "clase", type: "varchar(40)")
		}
	}
	
}

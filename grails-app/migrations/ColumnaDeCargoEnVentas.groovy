databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1354549825544-1") {
		addColumn(tableName: "venta") {
			column(name: "tipo", type: "varchar(13)",defaultValue:"VENTA") {
				constraints(nullable: "false")
			}
		}
	}
}

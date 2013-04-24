databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1353775296778-1") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "xml_path", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}

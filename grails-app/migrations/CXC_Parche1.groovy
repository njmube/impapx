databaseChangeLog = {
	

	changeSet(author: "rubencancinoramos (generated)", id: "1360261228186-3") {
		addColumn(tableName: "cxcaplicacion") {
			column(name: "factura_id", type: "bigint")
		}
	}	

	changeSet(author: "rubencancinoramos (generated)", id: "1360261228186-13") {
		addForeignKeyConstraint(baseColumnNames: "factura_id", baseTableName: "cxcaplicacion", constraintName: "FKE09181DB4EDD07F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "venta", referencesUniqueColumn: "false")
	}	

	changeSet(author: "rubencancinoramos (generated)", id: "1360261228186-21") {
		createIndex(indexName: "FKE09181DB4EDD07F", tableName: "cxcaplicacion") {
			column(name: "factura_id")
		}
	}

}

databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1367275764815-1") {
		addColumn(tableName: "cxcabono") {
			column(name: "ingreso_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1367275764815-2") {
		addForeignKeyConstraint(baseColumnNames: "ingreso_id", baseTableName: "cxcabono", constraintName: "FKA67508E1517ED21A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "movimiento_de_cuenta", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1367275764815-3") {
		createIndex(indexName: "FKA67508E1517ED21A", tableName: "cxcabono") {
			column(name: "ingreso_id")
		}
	}

	
}

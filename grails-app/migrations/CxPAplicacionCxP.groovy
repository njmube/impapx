databaseChangeLog = {

	changeSet(author: "pato (generated)", id: "1354728894006-1") {
		addColumn(tableName: "aplicacion") {
			column(name: "factura_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pato (generated)", id: "1354728894006-2") {
		addForeignKeyConstraint(baseColumnNames: "factura_id", baseTableName: "aplicacion", constraintName: "FKA1A6EF0F8281048D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_por_pagar", referencesUniqueColumn: "false")
	}

	changeSet(author: "pato (generated)", id: "1354728894006-3") {
		createIndex(indexName: "FKA1A6EF0F8281048D", tableName: "aplicacion") {
			column(name: "factura_id")
		}
	}
}

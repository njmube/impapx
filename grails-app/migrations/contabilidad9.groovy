databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1358983294461-1") {
		addColumn(tableName: "pedimento") {
			column(name: "proveedor_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1358983294461-2") {
		addForeignKeyConstraint(baseColumnNames: "proveedor_id", baseTableName: "pedimento", constraintName: "FK3D12D9D73ED34B6B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "proveedor", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1358983294461-3") {
		createIndex(indexName: "FK3D12D9D73ED34B6B", tableName: "pedimento") {
			column(name: "proveedor_id")
		}
	}
}

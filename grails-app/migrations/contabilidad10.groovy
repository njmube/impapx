databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1358985012048-1") {
		addColumn(tableName: "pedimento") {
			column(name: "incrementable1_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1358985012048-2") {
		addForeignKeyConstraint(baseColumnNames: "incrementable1_id", baseTableName: "pedimento", constraintName: "FK3D12D9D734C69FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_por_pagar", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1358985012048-3") {
		createIndex(indexName: "FK3D12D9D734C69FD", tableName: "pedimento") {
			column(name: "incrementable1_id")
		}
	}
}

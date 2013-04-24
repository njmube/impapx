databaseChangeLog = {

	changeSet(author: "Ruben Cancino (generated)", id: "1354807275045-1") {
		addColumn(tableName: "abono") {
			column(name: "pago_proveedor_id", type: "bigint")
		}
	}

	changeSet(author: "Ruben Cancino (generated)", id: "1354807275045-2") {
		addForeignKeyConstraint(baseColumnNames: "pago_proveedor_id", baseTableName: "abono", constraintName: "FK58522AF5571D194", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pago_proveedor", referencesUniqueColumn: "false")
	}

	changeSet(author: "Ruben Cancino (generated)", id: "1354807275045-3") {
		createIndex(indexName: "FK58522AF5571D194", tableName: "abono") {
			column(name: "pago_proveedor_id")
		}
	}
}

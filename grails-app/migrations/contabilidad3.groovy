databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1357686990767-1") {
		addColumn(tableName: "proveedor") {
			column(name: "cuenta_contable_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357686990767-2") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "proveedor", constraintName: "FKDF24CADE1F383816", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1357686990767-3") {
		createIndex(indexName: "FKDF24CADE1F383816", tableName: "proveedor") {
			column(name: "cuenta_contable_id")
		}
	}
}

databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1357672223980-1") {
		addColumn(tableName: "cliente") {
			column(name: "cuenta_contable_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357672223980-2") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "cliente", constraintName: "FK334B85FA1F383816", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1357672223980-3") {
		createIndex(indexName: "FK334B85FA1F383816", tableName: "cliente") {
			column(name: "cuenta_contable_id")
		}
	}
}

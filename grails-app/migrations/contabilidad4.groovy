databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1357687944729-1") {
		addColumn(tableName: "cuenta_bancaria") {
			column(name: "cuenta_contable_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357687944729-2") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "cuenta_bancaria", constraintName: "FKFFDD87541F383816", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1357687944729-3") {
		createIndex(indexName: "FKFFDD87541F383816", tableName: "cuenta_bancaria") {
			column(name: "cuenta_contable_id")
		}
	}
}

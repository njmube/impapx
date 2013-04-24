databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1358962829330-1") {
		addColumn(tableName: "movimiento_de_cuenta") {
			column(name: "cuenta_deudora_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1358962829330-2") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_deudora_id", baseTableName: "movimiento_de_cuenta", constraintName: "FKE715E69CEB206422", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1358962829330-3") {
		createIndex(indexName: "FKE715E69CEB206422", tableName: "movimiento_de_cuenta") {
			column(name: "cuenta_deudora_id")
		}
	}
}

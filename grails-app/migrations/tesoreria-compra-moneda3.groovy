databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1356975578779-1") {
		addColumn(tableName: "compra_de_moneda") {
			column(name: "ingreso_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356975578779-2") {
		addForeignKeyConstraint(baseColumnNames: "ingreso_id", baseTableName: "compra_de_moneda", constraintName: "FK7DA3ED13517ED21A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "movimiento_de_cuenta", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1356975578779-3") {
		createIndex(indexName: "FK7DA3ED13517ED21A", tableName: "compra_de_moneda") {
			column(name: "ingreso_id")
		}
	}
}

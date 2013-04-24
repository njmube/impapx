databaseChangeLog = {

	/*
	changeSet(author: "RUBEN (generated)", id: "1358989976045-1") {
		addColumn(tableName: "cuenta_de_gastos") {
			column(name: "proveedor_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}*/

	changeSet(author: "rubencancinoramos (generated)", id: "1358994083893-1") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "proveedor_id", tableName: "cuenta_de_gastos")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1358994083893-2") {
		addForeignKeyConstraint(baseColumnNames: "proveedor_id", baseTableName: "cuenta_de_gastos", constraintName: "FKB1377E263ED34B6B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "proveedor", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1358994083893-3") {
		createIndex(indexName: "FKB1377E263ED34B6B", tableName: "cuenta_de_gastos") {
			column(name: "proveedor_id")
		}
	}
}

databaseChangeLog = {

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-1") {
		addColumn(tableName: "anticipo") {
			column(name: "complemento_id", type: "bigint")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-2") {
		addColumn(tableName: "anticipo") {
			column(name: "requisicion_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-3") {
		addNotNullConstraint(columnDataType: "varchar(8)", columnName: "tipo", tableName: "poliza")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-4") {
		addNotNullConstraint(columnDataType: "varchar(8)", columnName: "tipo", tableName: "poliza_det")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-5") {
		dropForeignKeyConstraint(baseTableName: "requisicion", baseTableSchemaName: "impapx_dev", constraintName: "FK1791964DEF16863C")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-6") {
		addForeignKeyConstraint(baseColumnNames: "complemento_id", baseTableName: "anticipo", constraintName: "FKE327DB47B35BF68F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "requisicion", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-7") {
		addForeignKeyConstraint(baseColumnNames: "requisicion_id", baseTableName: "anticipo", constraintName: "FKE327DB47D23C08CB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "requisicion", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-8") {
		createIndex(indexName: "FKE327DB47B35BF68F", tableName: "anticipo") {
			column(name: "complemento_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-9") {
		createIndex(indexName: "FKE327DB47D23C08CB", tableName: "anticipo") {
			column(name: "requisicion_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-10") {
		dropColumn(columnName: "anticipo_id", tableName: "requisicion")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361111168595-11") {
		dropTable(tableName: "cuenta_por_pagar_vtoimportaciones")
	}
}

databaseChangeLog = {

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-1") {
		createTable(tableName: "anticipo") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "anticipoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "complemento_id", type: "bigint")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "embarque_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "gastos_de_importacion", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuestos_aduanales", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "requisicion_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sobrante_id", type: "bigint")

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-2") {
		addNotNullConstraint(columnDataType: "integer", columnName: "plazo", tableName: "cuenta_bancaria")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-3") {
		addNotNullConstraint(columnDataType: "varchar(8)", columnName: "tipo", tableName: "poliza")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-4") {
		addNotNullConstraint(columnDataType: "varchar(8)", columnName: "tipo", tableName: "poliza_det")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-5") {
		addForeignKeyConstraint(baseColumnNames: "complemento_id", baseTableName: "anticipo", constraintName: "FKE327DB47B35BF68F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "requisicion", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-6") {
		addForeignKeyConstraint(baseColumnNames: "embarque_id", baseTableName: "anticipo", constraintName: "FKE327DB47D7625569", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "embarque", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-7") {
		addForeignKeyConstraint(baseColumnNames: "requisicion_id", baseTableName: "anticipo", constraintName: "FKE327DB47D23C08CB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "requisicion", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-8") {
		addForeignKeyConstraint(baseColumnNames: "sobrante_id", baseTableName: "anticipo", constraintName: "FKE327DB47FD86B921", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "movimiento_de_cuenta", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-9") {
		createIndex(indexName: "FKE327DB47B35BF68F", tableName: "anticipo") {
			column(name: "complemento_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-10") {
		createIndex(indexName: "FKE327DB47D23C08CB", tableName: "anticipo") {
			column(name: "requisicion_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-11") {
		createIndex(indexName: "FKE327DB47D7625569", tableName: "anticipo") {
			column(name: "embarque_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-12") {
		createIndex(indexName: "FKE327DB47FD86B921", tableName: "anticipo") {
			column(name: "sobrante_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1361115968880-13") {
		dropTable(tableName: "cuenta_por_pagar_vtoimportaciones")
	}

	

}

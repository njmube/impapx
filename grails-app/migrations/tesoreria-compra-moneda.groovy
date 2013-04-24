databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1356737919234-1") {
		createTable(tableName: "compra_de_moneda") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "compra_de_monPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_destino_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_origen_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "diferencia_cambiaria", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "forma_de_pago", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pago_proveedor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "requisicion_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_cambio", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_cambio_compra", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356737919234-2") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_destino_id", baseTableName: "compra_de_moneda", constraintName: "FK7DA3ED13C47D18DD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1356737919234-3") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_origen_id", baseTableName: "compra_de_moneda", constraintName: "FK7DA3ED13DBEA256D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1356737919234-4") {
		addForeignKeyConstraint(baseColumnNames: "pago_proveedor_id", baseTableName: "compra_de_moneda", constraintName: "FK7DA3ED135571D194", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pago_proveedor", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1356737919234-5") {
		addForeignKeyConstraint(baseColumnNames: "requisicion_id", baseTableName: "compra_de_moneda", constraintName: "FK7DA3ED13D23C08CB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "requisicion", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1356737919234-6") {
		createIndex(indexName: "FK7DA3ED135571D194", tableName: "compra_de_moneda") {
			column(name: "pago_proveedor_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356737919234-7") {
		createIndex(indexName: "FK7DA3ED13C47D18DD", tableName: "compra_de_moneda") {
			column(name: "cuenta_destino_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356737919234-8") {
		createIndex(indexName: "FK7DA3ED13D23C08CB", tableName: "compra_de_moneda") {
			column(name: "requisicion_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356737919234-9") {
		createIndex(indexName: "FK7DA3ED13DBEA256D", tableName: "compra_de_moneda") {
			column(name: "cuenta_origen_id")
		}
	}
}

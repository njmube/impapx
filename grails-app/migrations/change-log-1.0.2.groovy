databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1353600661061-1") {
		createTable(tableName: "abono") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "abonoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(200)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "disponible", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto_tasa", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuestos", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "proveedor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "tc", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "class", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1353600661061-2") {
		createTable(tableName: "aplicacion") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "aplicacionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "abono_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(200)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto_tasa", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "aplicaciones_idx", type: "integer")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1353600661061-3") {
		addColumn(tableName: "distribucion_det") {
			column(name: "fecha_de_entrada", type: "datetime")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1353600661061-4") {
		addColumn(tableName: "distribucion_det") {
			column(name: "programacion_de_entrega", type: "datetime")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1353600661061-5") {
		addColumn(tableName: "proveedor") {
			column(name: "vencimento_bl",defaultValueBoolean:false, type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1353600661061-6") {
		addForeignKeyConstraint(baseColumnNames: "proveedor_id", baseTableName: "abono", constraintName: "FK58522AF3ED34B6B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "proveedor", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1353600661061-7") {
		addForeignKeyConstraint(baseColumnNames: "abono_id", baseTableName: "aplicacion", constraintName: "FKA1A6EF0F4CC6ACB8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "abono", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1353600661061-8") {
		createIndex(indexName: "FK58522AF3ED34B6B", tableName: "abono") {
			column(name: "proveedor_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1353600661061-9") {
		createIndex(indexName: "FKA1A6EF0F4CC6ACB8", tableName: "aplicacion") {
			column(name: "abono_id")
		}
	}
}

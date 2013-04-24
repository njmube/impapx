databaseChangeLog = {
	

	changeSet(author: "rubencancinoramos (generated)", id: "1360343876092-2") {
		createTable(tableName: "concepto_de_gasto") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "concepto_de_gPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "concepto_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "egreso_id", type: "bigint")

			column(name: "factura_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "ietu", type: "decimal(19,2)") {
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

			column(name: "retension", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "retension_isr", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "retension_isr_tasa", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "retension_tasa", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	

	changeSet(author: "rubencancinoramos (generated)", id: "1360343876092-9") {
		addForeignKeyConstraint(baseColumnNames: "concepto_id", baseTableName: "concepto_de_gasto", constraintName: "FKF0056C0E15440032", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1360343876092-10") {
		addForeignKeyConstraint(baseColumnNames: "egreso_id", baseTableName: "concepto_de_gasto", constraintName: "FKF0056C0EE8F7855A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "movimiento_de_cuenta", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1360343876092-11") {
		addForeignKeyConstraint(baseColumnNames: "factura_id", baseTableName: "concepto_de_gasto", constraintName: "FKF0056C0E3074CC4B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_por_pagar", referencesUniqueColumn: "false")
	}

	

	changeSet(author: "rubencancinoramos (generated)", id: "1360343876092-16") {
		createIndex(indexName: "FKF0056C0E15440032", tableName: "concepto_de_gasto") {
			column(name: "concepto_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1360343876092-17") {
		createIndex(indexName: "FKF0056C0E3074CC4B", tableName: "concepto_de_gasto") {
			column(name: "factura_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1360343876092-18") {
		createIndex(indexName: "FKF0056C0EE8F7855A", tableName: "concepto_de_gasto") {
			column(name: "egreso_id")
		}
	}
	
}

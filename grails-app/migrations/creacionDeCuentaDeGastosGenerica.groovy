databaseChangeLog = {

	changeSet(author: "rubencancinoramos (generated)", id: "1366872969697-1") {
		createTable(tableName: "cuenta_de_gastos_generica") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cuenta_de_gasPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(250)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuestos", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "proveedor_id", type: "bigint")

			column(name: "rembolso", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "retension", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "retension_isr", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366872969697-2") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "cuenta_generica_id", type: "bigint")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366872969697-3") {
		addForeignKeyConstraint(baseColumnNames: "proveedor_id", baseTableName: "cuenta_de_gastos_generica", constraintName: "FK968A6F633ED34B6B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "proveedor", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366872969697-4") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_generica_id", baseTableName: "cuenta_por_pagar", constraintName: "FK937313243BAF62AD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_de_gastos_generica", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366872969697-5") {
		createIndex(indexName: "FK968A6F633ED34B6B", tableName: "cuenta_de_gastos_generica") {
			column(name: "proveedor_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366872969697-6") {
		createIndex(indexName: "FK937313243BAF62AD", tableName: "cuenta_por_pagar") {
			column(name: "cuenta_generica_id")
		}
	}
}

databaseChangeLog = {

	changeSet(author: "Ruben Cancino (generated)", id: "1366848650734-1") {
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

			column(name: "referencia", type: "varchar(255)")

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

	changeSet(author: "Ruben Cancino (generated)", id: "1366848650734-2") {
		createTable(tableName: "cuenta_de_gastos_generica_factura_de_gastos") {
			column(name: "cuenta_de_gastos_generica_facturas_id", type: "bigint")

			column(name: "factura_de_gastos_id", type: "bigint")
		}
	}

	changeSet(author: "Ruben Cancino (generated)", id: "1366848650734-3") {
		addForeignKeyConstraint(baseColumnNames: "proveedor_id", baseTableName: "cuenta_de_gastos_generica", constraintName: "FK968A6F633ED34B6B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "proveedor", referencesUniqueColumn: "false")
	}

	changeSet(author: "Ruben Cancino (generated)", id: "1366848650734-4") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_de_gastos_generica_facturas_id", baseTableName: "cuenta_de_gastos_generica_factura_de_gastos", constraintName: "FK95F8D77A29E19D17", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_de_gastos_generica", referencesUniqueColumn: "false")
	}

	changeSet(author: "pato (generated)", id: "1366848650734-5") {
		addForeignKeyConstraint(baseColumnNames: "factura_de_gastos_id", baseTableName: "cuenta_de_gastos_generica_factura_de_gastos", constraintName: "FK95F8D77AB1A2EDAD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_por_pagar", referencesUniqueColumn: "false")
	}

	changeSet(author: "Ruben Cancino (generated)", id: "1366848650734-6") {
		createIndex(indexName: "FK968A6F633ED34B6B", tableName: "cuenta_de_gastos_generica") {
			column(name: "proveedor_id")
		}
	}

	changeSet(author: "Ruben Cancino (generated)", id: "1366848650734-7") {
		createIndex(indexName: "FK95F8D77A29E19D17", tableName: "cuenta_de_gastos_generica_factura_de_gastos") {
			column(name: "cuenta_de_gastos_generica_facturas_id")
		}
	}

	changeSet(author: "Ruben Cancino (generated)", id: "1366848650734-8") {
		createIndex(indexName: "FK95F8D77AB1A2EDAD", tableName: "cuenta_de_gastos_generica_factura_de_gastos") {
			column(name: "factura_de_gastos_id")
		}
	}
}

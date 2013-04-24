databaseChangeLog = {

	
	changeSet(author: "rubencancinoramos (generated)", id: "1358271416100-2") {
		createTable(tableName: "saldo_por_cuenta_contable") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "saldo_por_cuePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cierre", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "haber", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "mes", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "saldo_final", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "saldo_inicial", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "year", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}	

	changeSet(author: "rubencancinoramos (generated)", id: "1358271416100-4") {
		addNotNullConstraint(columnDataType: "varchar(15)", columnName: "concepto", tableName: "requisicion")
	}

	

	changeSet(author: "rubencancinoramos (generated)", id: "1358271416100-6") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "saldo_por_cuenta_contable", constraintName: "FK46AB8F61806F2751", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}


	changeSet(author: "rubencancinoramos (generated)", id: "1358271416100-8") {
		createIndex(indexName: "FK46AB8F61806F2751", tableName: "saldo_por_cuenta_contable") {
			column(name: "cuenta_id")
		}
	}
}

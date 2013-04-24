databaseChangeLog = {

	changeSet(author: "rubencancinoramos (generated)", id: "1358528006743-1") {
		createTable(tableName: "cheque") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "chequePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "egreso_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha_impresion", type: "datetime")

			column(name: "folio", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1358528006743-2") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "cheque", constraintName: "FKAED8F221E5999646", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_bancaria", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1358528006743-3") {
		addForeignKeyConstraint(baseColumnNames: "egreso_id", baseTableName: "cheque", constraintName: "FKAED8F221E8F7855A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "movimiento_de_cuenta", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1358528006743-4") {
		createIndex(indexName: "FKAED8F221E5999646", tableName: "cheque") {
			column(name: "cuenta_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1358528006743-5") {
		createIndex(indexName: "FKAED8F221E8F7855A", tableName: "cheque") {
			column(name: "egreso_id")
		}
	}
}

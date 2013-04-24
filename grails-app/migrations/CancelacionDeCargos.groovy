databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1354559825010-1") {
		createTable(tableName: "cancelacion_de_cargo") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cancelacion_dPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cargo_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "usuario", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1354559825010-2") {
		addForeignKeyConstraint(baseColumnNames: "cargo_id", baseTableName: "cancelacion_de_cargo", constraintName: "FKB0D98EF13EF319BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "venta", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1354559825010-3") {
		createIndex(indexName: "FKB0D98EF13EF319BB", tableName: "cancelacion_de_cargo") {
			column(name: "cargo_id")
		}
	}
}

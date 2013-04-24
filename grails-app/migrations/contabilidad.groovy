databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1357588725191-1") {
		createTable(tableName: "cuenta_contable") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cuenta_contabPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "varchar(100)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "de_resultado", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "detalle", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "naturaleza", type: "varchar(9)") {
				constraints(nullable: "false")
			}

			column(name: "padre_id", type: "bigint")

			column(name: "presentacion_contable", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "presentacion_financiera", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "presentacion_fiscal", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "presentacion_presupuestal", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "sub_tipo", type: "varchar(255)")

			column(name: "tipo", type: "varchar(7)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357588725191-2") {
		createTable(tableName: "poliza") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "polizaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(250)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "haber", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(7)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357588725191-3") {
		createTable(tableName: "poliza_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "poliza_detPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "asiento", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "debe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "haber", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "poliza_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "referencia", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "partidas_idx", type: "integer")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357588725191-4") {
		addForeignKeyConstraint(baseColumnNames: "padre_id", baseTableName: "cuenta_contable", constraintName: "FK4D4545A33DCE98D3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1357588725191-5") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "poliza_det", constraintName: "FK84EDB1B7806F2751", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1357588725191-6") {
		addForeignKeyConstraint(baseColumnNames: "poliza_id", baseTableName: "poliza_det", constraintName: "FK84EDB1B71D8360C5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "poliza", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1357588725191-7") {
		createIndex(indexName: "FK4D4545A33DCE98D3", tableName: "cuenta_contable") {
			column(name: "padre_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357588725191-8") {
		createIndex(indexName: "FK84EDB1B71D8360C5", tableName: "poliza_det") {
			column(name: "poliza_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357588725191-9") {
		createIndex(indexName: "FK84EDB1B7806F2751", tableName: "poliza_det") {
			column(name: "cuenta_id")
		}
	}
}

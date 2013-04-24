databaseChangeLog = {

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-1") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-2") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-3") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-4") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-5") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46AF5916CE6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-6") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46A9ABC30C6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-7") {
		createIndex(indexName: "authority_unique_1353003878645", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-8") {
		createIndex(indexName: "username_unique_1353003878706", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-9") {
		createIndex(indexName: "FK143BF46A9ABC30C6", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1353003881234-10") {
		createIndex(indexName: "FK143BF46AF5916CE6", tableName: "user_role") {
			column(name: "role_id")
		}
	}
}

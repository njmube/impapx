databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1390522845658-1") {
		createTable(tableName: "cfdi_folio") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cfdi_folioPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "emisor", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "serie", type: "varchar(10)") {
				constraints(nullable: "false")
			}
		}
	}

	
}

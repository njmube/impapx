databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1368300048421-1") {
		addColumn(tableName: "proveedor") {
			column(name: "nacionalidad", type: "varchar(255)") {
				constraints(nullable: "true")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1368300048421-2") {
		addColumn(tableName: "proveedor") {
			column(name: "pais_de_origen", type: "varchar(255)") {
				constraints(nullable: "true")
			}
		}
	}

	
}

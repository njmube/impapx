databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1358898494856-1") {
		addColumn(tableName: "poliza_det") {
			column(name: "entidad", type: "varchar(50)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1358898494856-2") {
		addColumn(tableName: "poliza_det") {
			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1358898494856-3") {
		addColumn(tableName: "poliza_det") {
			column(name: "origen", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1358898494856-4") {
		addColumn(tableName: "poliza_det") {
			column(name: "tipo", type: "varchar(7)") {
				constraints(nullable: "false")
			}
		}
	}
}

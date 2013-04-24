databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1360177814259-2") {
		addColumn(tableName: "cuenta_bancaria") {
			column(name: "dias_inversion_isr", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1360177814259-3") {
		addColumn(tableName: "cuenta_bancaria") {
			column(name: "tasa_isr", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	
}

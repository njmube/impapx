databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1356134435378-1") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "aduana", type: "varchar(255)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356134435378-2") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "fecha_pedimento", type: "datetime")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356134435378-3") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "pedimento", type: "varchar(255)")
		}
	}
}

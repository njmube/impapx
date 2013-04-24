databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1361913469739-1") {
		addColumn(tableName: "proveedor_producto") {
			column(name: "gramos", type: "decimal(19,2)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1361913469739-2") {
		dropTable(tableName: "poliza_det_resp20130214")
	}

	changeSet(author: "RUBEN (generated)", id: "1361913469739-3") {
		dropTable(tableName: "poliza_resp20130214")
	}

	changeSet(author: "RUBEN (generated)", id: "1361913469739-4") {
		dropTable(tableName: "saldo_por_cuenta_contable_resp20130214")
	}
}

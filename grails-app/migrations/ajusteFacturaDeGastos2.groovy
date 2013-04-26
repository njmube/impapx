databaseChangeLog = {

	changeSet(author: "rubencancinoramos (generated)", id: "1366948879157-1") {
		addColumn(tableName: "concepto_de_gasto") {
			column(name: "comentario_otros", type: "varchar(255)")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366948879157-2") {
		addColumn(tableName: "concepto_de_gasto") {
			column(name: "descuento", type: "decimal(19,2)")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366948879157-3") {
		addColumn(tableName: "concepto_de_gasto") {
			column(name: "fecha_rembolso", type: "datetime")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366948879157-4") {
		addColumn(tableName: "concepto_de_gasto") {
			column(name: "otros", type: "decimal(19,2)")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366948879157-5") {
		addColumn(tableName: "concepto_de_gasto") {
			column(name: "rembolso", type: "decimal(19,2)")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366948879157-6") {
		addColumn(tableName: "cuenta_de_gastos_generica") {
			column(name: "otros", type: "decimal(19,2)")
		}
	}

	changeSet(author: "rubencancinoramos (generated)", id: "1366948879157-7") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "otros", type: "decimal(19,2)")
		}
	}
}

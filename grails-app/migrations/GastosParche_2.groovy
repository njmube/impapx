databaseChangeLog = {	

	changeSet(author: "rubencancinoramos (generated)", id: "1360376843055-2") {
		addColumn(tableName: "cuenta_por_pagar") {
			column(name: "retension_isr", type: "decimal(19,2)")
		}
	}


}

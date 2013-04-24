databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1356109443453-1") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "ano_aprobacion", type: "integer")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356109443453-2") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "estado", type: "varchar(1)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356109443453-3") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "fecha", type: "datetime")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356109443453-4") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "impuesto", type: "decimal(19,2)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356109443453-5") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "numero_de_aprobacion", type: "integer")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356109443453-6") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "rfc", type: "varchar(13)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356109443453-7") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "serie", type: "varchar(50)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356109443453-8") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "tipo_cfd", type: "varchar(1)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1356109443453-9") {
		addColumn(tableName: "comprobante_fiscal") {
			column(name: "total", type: "decimal(19,2)")
		}
	}
}

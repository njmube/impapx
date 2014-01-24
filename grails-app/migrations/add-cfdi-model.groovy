databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1390344779462-1") {
		createTable(tableName: "cfdi") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cfdiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cadena_original", type: "longtext")

			column(name: "comentario", type: "longtext")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "descuentos", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "emisor", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "receptor", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "serie", type: "varchar(15)") {
				constraints(nullable: "false")
			}

			column(name: "subtotal", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "timbrado", type: "datetime")

			column(name: "tipo", type: "varchar(12)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_cfdi", type: "varchar(1)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "varchar(255)")

			column(name: "uuid", type: "longtext")

			column(name: "xml", type: "mediumblob") {
				constraints(nullable: "false")
			}

			column(name: "xml_name", type: "varchar(200)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1390344779462-2") {
		addColumn(tableName: "empresa") {
			column(name: "certificado_digital", type: "mediumblob")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1390344779462-3") {
		addColumn(tableName: "empresa") {
			column(name: "certificado_digital_pfx", type: "mediumblob")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1390344779462-4") {
		addColumn(tableName: "empresa") {
			column(name: "llave_privada", type: "mediumblob")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1390344779462-5") {
		addColumn(tableName: "empresa") {
			column(name: "numero_de_certificado", type: "varchar(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1390344779462-6") {
		addColumn(tableName: "empresa") {
			column(name: "password_pfx", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	
	/*
	 * changeSet(author: "RUBEN (generated)", id: "1390344779462-7") {
		modifyDataType(columnName: "descuento", newDataType: "decimal(19,2)", tableName: "compra_det")
	}
	changeSet(author: "RUBEN (generated)", id: "1390344779462-8") {
		modifyDataType(columnName: "provisionada", newDataType: "integer", tableName: "cuenta_por_pagar")
	}

	changeSet(author: "RUBEN (generated)", id: "1390344779462-9") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "concepto", tableName: "movimiento_de_cuenta")
	}

	changeSet(author: "RUBEN (generated)", id: "1390344779462-10") {
		modifyDataType(columnName: "tipo_de_cambio", newDataType: "decimal(19,2)", tableName: "pago_proveedor")
	}

	changeSet(author: "RUBEN (generated)", id: "1390344779462-11") {
		addNotNullConstraint(columnDataType: "decimal(19,2)", columnName: "tipo_de_cambio", tableName: "pago_proveedor")
	}
	*/
}

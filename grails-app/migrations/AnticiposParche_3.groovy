databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1361899528914-1") {
		addColumn(tableName: "embarque_det") {
			column(name: "incrementables", type: "decimal(19,2)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1361899528914-2") {
		addColumn(tableName: "requisicion_det") {
			column(name: "embarque_id", type: "bigint")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1361899528914-3") {
		addColumn(tableName: "requisicion_det") {
			column(name: "gastos_de_importacion", type: "decimal(19,2)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1361899528914-4") {
		addColumn(tableName: "requisicion_det") {
			column(name: "impuestos_aduanales", type: "decimal(19,2)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1361899528914-5") {
		dropForeignKeyConstraint(baseTableName: "anticipo", baseTableSchemaName: "impapx", constraintName: "FKE327DB47D7625569")
	}

	changeSet(author: "RUBEN (generated)", id: "1361899528914-6") {
		addForeignKeyConstraint(baseColumnNames: "embarque_id", baseTableName: "requisicion_det", constraintName: "FK2D405B21D7625569", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "embarque", referencesUniqueColumn: "false")
	}

	changeSet(author: "RUBEN (generated)", id: "1361899528914-7") {
		createIndex(indexName: "FK2D405B21D7625569", tableName: "requisicion_det") {
			column(name: "embarque_id")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1361899528914-8") {
		dropColumn(columnName: "embarque_id", tableName: "anticipo")
	}

	
}

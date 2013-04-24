databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1357746410878-1") {
		addColumn(tableName: "cliente") {
			column(name: "sub_cuenta_operativa", type: "varchar(4)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357746410878-2") {
		addColumn(tableName: "proveedor") {
			column(name: "sub_cuenta_operativa", type: "varchar(4)")
		}
	}

	changeSet(author: "RUBEN (generated)", id: "1357746410878-3") {
		dropForeignKeyConstraint(baseTableName: "cliente",  constraintName: "FK334B85FA1F383816")
	}

	changeSet(author: "RUBEN (generated)", id: "1357746410878-4") {
		dropForeignKeyConstraint(baseTableName: "proveedor", constraintName: "FKDF24CADE1F383816")
	}

	changeSet(author: "RUBEN (generated)", id: "1357746410878-5") {
		dropColumn(columnName: "cuenta_contable_id", tableName: "cliente")
	}

	changeSet(author: "RUBEN (generated)", id: "1357746410878-6") {
		dropColumn(columnName: "cuenta_contable_id", tableName: "proveedor")
	}
}

databaseChangeLog = {

	changeSet(author: "Ruben Cancino ", id: "1357172397303-1") {
		addColumn(tableName: "saldo_de_cuenta") {
			column(name: "saldo_finalmn", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ruben Cancino ", id: "1357172397303-2") {
		addColumn(tableName: "saldo_de_cuenta") {
			column(name: "tc", type: "decimal(19,6)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Ruben Cancino", id: "1357172397303-3") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "cierre", tableName: "saldo_de_cuenta")
	}
}

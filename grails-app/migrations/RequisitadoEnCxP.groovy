databaseChangeLog = {

	changeSet(author: "RUBEN (generated)", id: "1354648076464-1") {
		dropColumn(columnName: "requisitado", tableName: "cuenta_por_pagar")
	}
}

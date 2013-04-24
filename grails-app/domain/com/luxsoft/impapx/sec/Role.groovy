package com.luxsoft.impapx.sec

class Role {

	String authority

	static mapping = {
		cache true
		//datasource 'dataSource'
	}

	
	
	static constraints = {
		authority blank: false, unique: true
	}
}

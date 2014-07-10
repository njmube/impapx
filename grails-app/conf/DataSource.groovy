dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

dataSource_importacion{
	dialect = org.hibernate.dialect.MySQL5InnoDBDialect
	driverClassName = 'com.mysql.jdbc.Driver'
	username = 'root'
	password = 'sys'
	url = 'jdbc:mysql://10.10.1.228/produccion'
	dbCreate = ''
	readOnly=true
	properties {
		maxActive = 4
		maxIdle = 2
		minIdle = 1
		initialSize = 1
		minEvictableIdleTimeMillis = 60000
		timeBetweenEvictionRunsMillis = 60000
		maxWait = 10000
		validationQuery = "/* ping */"
	}
}

// environment specific settings
environments {
    development {
		
		
		dataSource {
			dbCreate = ""
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
			username = 'root'
			password = 'sys'			
			//url = 'jdbc:mysql://10.10.1.228/paperx?autoReconnect=true'
			url = 'jdbc:mysql://localhost/impapx?autoReconnect=true'
			//url = 'jdbc:mysql://10.10.1.227/impapx'
			pooled = true
			properties {
			   maxActive = 3
			   maxIdle = 2
			   initialSize = 2
			   minEvictableIdleTimeMillis=1800000
			   timeBetweenEvictionRunsMillis=1800000
			   numTestsPerEvictionRun=3
			   testOnBorrow=true
			   testWhileIdle=true
			   testOnReturn=true
			   maxWait = 10000
			   
			}
		}
		/*
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            //url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			      url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }*/
		
		dataSource_importacion{
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
			username = 'root'
			password = 'sys'
			url = 'jdbc:mysql://localhost/produccion'
			dbCreate = ''
			readOnly=true
			properties {
				maxActive = 2
				maxIdle = 1
				minIdle = 1
				initialSize = 1
				minEvictableIdleTimeMillis = 60000
				timeBetweenEvictionRunsMillis = 60000
				maxWait = 10000
				validationQuery = "/* ping */"
			}
		}
		
		
		
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = ""
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
			username = 'root'
			password = 'sys'
			//url = 'jdbc:mysql://10.10.1.227/impapx'
			url = 'jdbc:mysql://10.10.1.228/paperx'
			pooled = true
			properties {
			   maxActive = 10
			   maxIdle = 7
			   initialSize = 5
			   minEvictableIdleTimeMillis=1800000
			   timeBetweenEvictionRunsMillis=1800000
			   numTestsPerEvictionRun=3
			   testOnBorrow=true
			   testWhileIdle=true
			   testOnReturn=true
			   validationQuery = "/* ping */"
			   maxWait = 10000
			   
			}
        }
    }
	
	paper {
		dataSource {
			dbCreate = ""
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
			username = 'root'
			password = 'sys'
			//url = 'jdbc:mysql://10.10.1.228/paperx'
			url = 'jdbc:mysql://10.10.1.228/paperx'
			pooled = true
			properties {
			   maxActive = 5
			   maxIdle = 3
			   initialSize = 3
			   minEvictableIdleTimeMillis=1800000
			   timeBetweenEvictionRunsMillis=1800000
			   numTestsPerEvictionRun=3
			   testOnBorrow=true
			   testWhileIdle=true
			   testOnReturn=true
			   validationQuery = "/* ping */"
			   maxWait = 10000
			   
			}
		}
	}
	
}

grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
//grails.project.war.file = "target/${impapx.empresa}.war"


grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
       inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "http://repository.codehaus.org"
		mavenRepo "http://luxsoftnet.com/m2/repository"
		mavenRepo "https://repository.jboss.org/nexus/"
		mavenRepo "http://repo.spring.io/milestone/"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        //runtime 'mysql:mysql-connector-java:5.1.20'
		runtime 'mysql:mysql-connector-java:5.1.27'
		compile 'org.apache.xmlbeans:xmlbeans:2.4.0'
		compile 'mx.gob.sat:cfd:2.2'
		compile 'org.bouncycastle:bcprov-jdk14:1.45'
		test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
		
		compile 'org.apache.xmlbeans:xmlbeans:2.4.0'
		compile 'com.luxsoft:cfdi:2.3'
		compile 'axis:axis:1.4'
		compile 'com.edicom.ediwinws:cfdiClient:1.0'
		compile 'org.apache.commons:commons-compress:1.1'
		compile 'net.glxn:qrgen:1.2'
		
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.2"
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"

        //runtime ":database-migration:1.1"
		runtime ":database-migration:1.3.8"
        compile ':cache:1.0.0'
		//compile ':jquery-validation-ui:1.4'
		runtime ':jasper:1.6.1'
		compile ":spring-security-core:1.2.7.3"
		compile ":spring-events:1.2"
		test(":spock:0.7") {
			exclude "spock-grails-support"
		}
		runtime ":filterpane:2.1.6"
		compile ':jquery-date-time-picker:0.1.0'
    }
}

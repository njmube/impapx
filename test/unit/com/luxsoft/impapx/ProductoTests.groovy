package com.luxsoft.impapx



import grails.test.mixin.*
import org.junit.*
import static org.junit.Assert.*
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Producto)
class ProductoTests {

    void tesToString() {
       def prod=new Producto(clave:"pol74",descripcion:'POL74 DESC')
	   assertEquals('POL74 DESC (pol74)', prod.toString())
    }
}

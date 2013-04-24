package com.luxsoft.impapx

import static org.junit.Assert.*
import org.junit.*

class MarcaIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSaveAndUpdate() {
        def marca =new Marca(nombre:'MarcaDePrueba1')
		assertNotNull marca.save()
		
		def found=Marca.get(marca.id)
		assertEquals 'MarcaDePrueba1', found.nombre
    }
	
	void testDelete(){
		def marca=new Marca(nombre:'MarcaDePrueba1')
		assertNotNull marca.save()
		
		marca.delete()
		assertFalse Marca.exists(marca.id)
	}
}

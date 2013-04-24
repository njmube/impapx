package com.luxsoft.impapx

import static org.junit.Assert.*
import org.junit.*

class ClaseIntegrationTests {

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
        def clase=new Clase(nombre:'ClaseDePrueba1')
		assertNotNull clase.save()
		
		def found=Clase.get(clase.id)
		assertEquals 'ClaseDePrueba1',found.nombre
    }
	
	@Test
	void testDelete(){
		def clase=new Clase(nombre:'ClaseDePrueba1')
		clase.delete()
		
		assertFalse Clase.exists(clase.id)
	}
}

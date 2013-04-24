package com.luxsoft.impapx

import static org.junit.Assert.*
import org.junit.*

class LineaIntegrationTests {

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
		 
		Linea linea=new Linea(nombre:'LineaDePrueba1')
		assertNotNull linea.save(flush:true)
		log.info 'Linea salvada..'+linea.id
		Linea found = Linea.get(linea.id)
		assertNotNull found
		assertEquals 'LineaDePrueba1',found.nombre
		
		found.nombre='LineaDePrueba2'
		linea.save(flush:true)
		
		linea=Linea.get found.id
		assertEquals 'LineaDePrueba2',linea.nombre
		
    }
	
	@Test
	void testDelete(){
		def linea =new Linea(nombre:'LineaDePruebaDelete')
		assertNotNull linea.save()
		
		def found=Linea.get(linea.id)
		found.delete()
		assertFalse Linea.exists(found.id);
	}
	

}

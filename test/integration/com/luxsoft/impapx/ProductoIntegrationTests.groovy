package com.luxsoft.impapx

import static org.junit.Assert.*
import org.junit.*

class ProductoIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSave() {
		def  prod=Producto.build(
			clave:'POL74',
			descripcion:'PRODUCTO DE PRUEBA POL 74'
			,marca:Marca.build(nombre:'MarcaDynamic'))
		assertNotNull prod.id
		log.info('Linea del producto: '+prod?.linea?.nombre)
		log.info('Marca del producto: '+prod.marca.nombre)
		
		def found = Producto.get(prod.id)
		assertEquals 'PRODUCTO DE PRUEBA POL 74',found.descripcion
		assertEquals 'MarcaDynamic',found.marca.nombre
    }
	
	void testUpdate(){
		def prod=Producto.build(clave:'POL74',descripcion:'POL74 de prueba')
		assertNotNull prod.id
		prod.descripcion='PRODUCTO DE PRUEBA POL74'
		prod.save()
		
		def found=Producto.get(prod.id)
		assertNotNull found
		assertEquals 'PRODUCTO DE PRUEBA POL74',found.descripcion
		
	}
	
	void testDelete(){
		def prod=Producto.build(clave:'POL74',descripcion:'POL74 de prueba')
		assertNotNull prod.id
		prod.delete()
		
		assertFalse Producto.exists(prod.id)
	}
	
}

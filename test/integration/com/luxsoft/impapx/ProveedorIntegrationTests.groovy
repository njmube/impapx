package com.luxsoft.impapx

import static org.junit.Assert.*
import org.junit.*

class ProveedorIntegrationTests {

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
        def prov=Proveedor.build(nombre:'GEA INTERNATIONAL PAPERS')
		assertNotNull prov.id
		
		def found=Proveedor.get(prov.id);
		assertEquals('GEA INTERNATIONAL PAPERS',found.nombre)
		
    }
	
	@Test
	void testSaveWithProductos(){
		
		def prov=Proveedor.build(nombre:'GEA INTERNATIONAL PAPERS')
		assertNotNull prov.id
		
		def prod1=Producto.build(clave:'POL74')
		def prod2=Producto.build(clave:'POL50')
		
		def provProd1=new ProveedorProducto(producto:prod1,codigo:prod1.clave,descripcion:prod1.descripcion)
		def provProd2=new ProveedorProducto(producto:prod2,codigo:prod2.clave,descripcion:prod2.descripcion)
		prov.addToProductos(provProd1)
		prov.addToProductos(provProd2)
		
		prov.save()
		def found =Proveedor.get(prov.id)
		assertEquals 2,found.productos.size()

		Collection col=prov.productos
		assertNotNull col.find {it.producto.clave=='POL74'}	
		
		

		
	}
}

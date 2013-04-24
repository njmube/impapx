package com.luxsoft.impapx

import static org.junit.Assert.*
import org.junit.*

class CompraIntegrationTests {

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
		def prov=Proveedor.build(nombre:'ProveedorCompraTest1')
        def compra = new Compra(
				 proveedor:prov
				,fecha:new Date()
				,comentario:'prueba de compra'
			)
		assertNotNull compra.save()
		def found=Compra.get(compra.id)
		assertEquals 'prueba de compra',found.comentario
		
		//Agregamos productos
		Producto prod1=Producto.build(clave:'POL74',linea:Linea.build(nombre:"lin1"))
		Producto prod2=Producto.build(clave:'POL50',linea:Linea.build(nombre:"lin2"))
		CompraDet det1=CompraDet.build(producto:prod1,solicitado:10,precio:1,importe:10)
		CompraDet det2=CompraDet.build(producto:prod2,solicitado:20,precio:2,importe:40)
		
		found.addToPartidas(det1)
		found.addToPartidas(det2)
		assertNotNull found.save()
		
		compra=Compra.get(found.id)
		assertNotNull compra
		assertEquals 2,compra.partidas.size()
		
		assertNotNull compra.partidas.find{it.producto.clave='POL74'}
		
		
    }
}

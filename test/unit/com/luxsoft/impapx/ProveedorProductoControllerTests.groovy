package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(ProveedorProductoController)
@Mock(ProveedorProducto)
class ProveedorProductoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/proveedorProducto/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.proveedorProductoInstanceList.size() == 0
        assert model.proveedorProductoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.proveedorProductoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.proveedorProductoInstance != null
        assert view == '/proveedorProducto/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/proveedorProducto/show/1'
        assert controller.flash.message != null
        assert ProveedorProducto.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/proveedorProducto/list'

        populateValidParams(params)
        def proveedorProducto = new ProveedorProducto(params)

        assert proveedorProducto.save() != null

        params.id = proveedorProducto.id

        def model = controller.show()

        assert model.proveedorProductoInstance == proveedorProducto
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/proveedorProducto/list'

        populateValidParams(params)
        def proveedorProducto = new ProveedorProducto(params)

        assert proveedorProducto.save() != null

        params.id = proveedorProducto.id

        def model = controller.edit()

        assert model.proveedorProductoInstance == proveedorProducto
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/proveedorProducto/list'

        response.reset()

        populateValidParams(params)
        def proveedorProducto = new ProveedorProducto(params)

        assert proveedorProducto.save() != null

        // test invalid parameters in update
        params.id = proveedorProducto.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/proveedorProducto/edit"
        assert model.proveedorProductoInstance != null

        proveedorProducto.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/proveedorProducto/show/$proveedorProducto.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        proveedorProducto.clearErrors()

        populateValidParams(params)
        params.id = proveedorProducto.id
        params.version = -1
        controller.update()

        assert view == "/proveedorProducto/edit"
        assert model.proveedorProductoInstance != null
        assert model.proveedorProductoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/proveedorProducto/list'

        response.reset()

        populateValidParams(params)
        def proveedorProducto = new ProveedorProducto(params)

        assert proveedorProducto.save() != null
        assert ProveedorProducto.count() == 1

        params.id = proveedorProducto.id

        controller.delete()

        assert ProveedorProducto.count() == 0
        assert ProveedorProducto.get(proveedorProducto.id) == null
        assert response.redirectedUrl == '/proveedorProducto/list'
    }
}

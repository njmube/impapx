package com.luxsoft.impapx.tesoreria



import org.junit.*
import grails.test.mixin.*

@TestFor(PagoProveedorController)
@Mock(PagoProveedor)
class PagoProveedorControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pagoProveedor/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pagoProveedorInstanceList.size() == 0
        assert model.pagoProveedorInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pagoProveedorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pagoProveedorInstance != null
        assert view == '/pagoProveedor/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pagoProveedor/show/1'
        assert controller.flash.message != null
        assert PagoProveedor.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pagoProveedor/list'

        populateValidParams(params)
        def pagoProveedor = new PagoProveedor(params)

        assert pagoProveedor.save() != null

        params.id = pagoProveedor.id

        def model = controller.show()

        assert model.pagoProveedorInstance == pagoProveedor
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pagoProveedor/list'

        populateValidParams(params)
        def pagoProveedor = new PagoProveedor(params)

        assert pagoProveedor.save() != null

        params.id = pagoProveedor.id

        def model = controller.edit()

        assert model.pagoProveedorInstance == pagoProveedor
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pagoProveedor/list'

        response.reset()

        populateValidParams(params)
        def pagoProveedor = new PagoProveedor(params)

        assert pagoProveedor.save() != null

        // test invalid parameters in update
        params.id = pagoProveedor.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pagoProveedor/edit"
        assert model.pagoProveedorInstance != null

        pagoProveedor.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pagoProveedor/show/$pagoProveedor.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pagoProveedor.clearErrors()

        populateValidParams(params)
        params.id = pagoProveedor.id
        params.version = -1
        controller.update()

        assert view == "/pagoProveedor/edit"
        assert model.pagoProveedorInstance != null
        assert model.pagoProveedorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pagoProveedor/list'

        response.reset()

        populateValidParams(params)
        def pagoProveedor = new PagoProveedor(params)

        assert pagoProveedor.save() != null
        assert PagoProveedor.count() == 1

        params.id = pagoProveedor.id

        controller.delete()

        assert PagoProveedor.count() == 0
        assert PagoProveedor.get(pagoProveedor.id) == null
        assert response.redirectedUrl == '/pagoProveedor/list'
    }
}

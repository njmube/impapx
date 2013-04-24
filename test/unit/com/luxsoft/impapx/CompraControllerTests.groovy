package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(CompraController)
@Mock(Compra)
class CompraControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/compra/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.compraInstanceList.size() == 0
        assert model.compraInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.compraInstance != null
    }

    void testSave() {
        controller.save()

        assert model.compraInstance != null
        assert view == '/compra/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/compra/show/1'
        assert controller.flash.message != null
        assert Compra.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/compra/list'

        populateValidParams(params)
        def compra = new Compra(params)

        assert compra.save() != null

        params.id = compra.id

        def model = controller.show()

        assert model.compraInstance == compra
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/compra/list'

        populateValidParams(params)
        def compra = new Compra(params)

        assert compra.save() != null

        params.id = compra.id

        def model = controller.edit()

        assert model.compraInstance == compra
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/compra/list'

        response.reset()

        populateValidParams(params)
        def compra = new Compra(params)

        assert compra.save() != null

        // test invalid parameters in update
        params.id = compra.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/compra/edit"
        assert model.compraInstance != null

        compra.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/compra/show/$compra.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        compra.clearErrors()

        populateValidParams(params)
        params.id = compra.id
        params.version = -1
        controller.update()

        assert view == "/compra/edit"
        assert model.compraInstance != null
        assert model.compraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/compra/list'

        response.reset()

        populateValidParams(params)
        def compra = new Compra(params)

        assert compra.save() != null
        assert Compra.count() == 1

        params.id = compra.id

        controller.delete()

        assert Compra.count() == 0
        assert Compra.get(compra.id) == null
        assert response.redirectedUrl == '/compra/list'
    }
}

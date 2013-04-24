package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(CuentaPorPagarController)
@Mock(CuentaPorPagar)
class CuentaPorPagarControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cuentaPorPagar/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cuentaPorPagarInstanceList.size() == 0
        assert model.cuentaPorPagarInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cuentaPorPagarInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cuentaPorPagarInstance != null
        assert view == '/cuentaPorPagar/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cuentaPorPagar/show/1'
        assert controller.flash.message != null
        assert CuentaPorPagar.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaPorPagar/list'

        populateValidParams(params)
        def cuentaPorPagar = new CuentaPorPagar(params)

        assert cuentaPorPagar.save() != null

        params.id = cuentaPorPagar.id

        def model = controller.show()

        assert model.cuentaPorPagarInstance == cuentaPorPagar
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaPorPagar/list'

        populateValidParams(params)
        def cuentaPorPagar = new CuentaPorPagar(params)

        assert cuentaPorPagar.save() != null

        params.id = cuentaPorPagar.id

        def model = controller.edit()

        assert model.cuentaPorPagarInstance == cuentaPorPagar
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaPorPagar/list'

        response.reset()

        populateValidParams(params)
        def cuentaPorPagar = new CuentaPorPagar(params)

        assert cuentaPorPagar.save() != null

        // test invalid parameters in update
        params.id = cuentaPorPagar.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cuentaPorPagar/edit"
        assert model.cuentaPorPagarInstance != null

        cuentaPorPagar.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cuentaPorPagar/show/$cuentaPorPagar.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cuentaPorPagar.clearErrors()

        populateValidParams(params)
        params.id = cuentaPorPagar.id
        params.version = -1
        controller.update()

        assert view == "/cuentaPorPagar/edit"
        assert model.cuentaPorPagarInstance != null
        assert model.cuentaPorPagarInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cuentaPorPagar/list'

        response.reset()

        populateValidParams(params)
        def cuentaPorPagar = new CuentaPorPagar(params)

        assert cuentaPorPagar.save() != null
        assert CuentaPorPagar.count() == 1

        params.id = cuentaPorPagar.id

        controller.delete()

        assert CuentaPorPagar.count() == 0
        assert CuentaPorPagar.get(cuentaPorPagar.id) == null
        assert response.redirectedUrl == '/cuentaPorPagar/list'
    }
}

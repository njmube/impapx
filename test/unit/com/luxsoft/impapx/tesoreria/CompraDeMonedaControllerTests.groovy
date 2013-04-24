package com.luxsoft.impapx.tesoreria



import org.junit.*
import grails.test.mixin.*

@TestFor(CompraDeMonedaController)
@Mock(CompraDeMoneda)
class CompraDeMonedaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/compraDeMoneda/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.compraDeMonedaInstanceList.size() == 0
        assert model.compraDeMonedaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.compraDeMonedaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.compraDeMonedaInstance != null
        assert view == '/compraDeMoneda/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/compraDeMoneda/show/1'
        assert controller.flash.message != null
        assert CompraDeMoneda.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/compraDeMoneda/list'

        populateValidParams(params)
        def compraDeMoneda = new CompraDeMoneda(params)

        assert compraDeMoneda.save() != null

        params.id = compraDeMoneda.id

        def model = controller.show()

        assert model.compraDeMonedaInstance == compraDeMoneda
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/compraDeMoneda/list'

        populateValidParams(params)
        def compraDeMoneda = new CompraDeMoneda(params)

        assert compraDeMoneda.save() != null

        params.id = compraDeMoneda.id

        def model = controller.edit()

        assert model.compraDeMonedaInstance == compraDeMoneda
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/compraDeMoneda/list'

        response.reset()

        populateValidParams(params)
        def compraDeMoneda = new CompraDeMoneda(params)

        assert compraDeMoneda.save() != null

        // test invalid parameters in update
        params.id = compraDeMoneda.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/compraDeMoneda/edit"
        assert model.compraDeMonedaInstance != null

        compraDeMoneda.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/compraDeMoneda/show/$compraDeMoneda.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        compraDeMoneda.clearErrors()

        populateValidParams(params)
        params.id = compraDeMoneda.id
        params.version = -1
        controller.update()

        assert view == "/compraDeMoneda/edit"
        assert model.compraDeMonedaInstance != null
        assert model.compraDeMonedaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/compraDeMoneda/list'

        response.reset()

        populateValidParams(params)
        def compraDeMoneda = new CompraDeMoneda(params)

        assert compraDeMoneda.save() != null
        assert CompraDeMoneda.count() == 1

        params.id = compraDeMoneda.id

        controller.delete()

        assert CompraDeMoneda.count() == 0
        assert CompraDeMoneda.get(compraDeMoneda.id) == null
        assert response.redirectedUrl == '/compraDeMoneda/list'
    }
}

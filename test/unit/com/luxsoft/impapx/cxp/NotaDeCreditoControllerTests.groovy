package com.luxsoft.impapx.cxp



import org.junit.*
import grails.test.mixin.*

@TestFor(NotaDeCreditoController)
@Mock(NotaDeCredito)
class NotaDeCreditoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/notaDeCredito/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.notaDeCreditoInstanceList.size() == 0
        assert model.notaDeCreditoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.notaDeCreditoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.notaDeCreditoInstance != null
        assert view == '/notaDeCredito/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/notaDeCredito/show/1'
        assert controller.flash.message != null
        assert NotaDeCredito.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/notaDeCredito/list'

        populateValidParams(params)
        def notaDeCredito = new NotaDeCredito(params)

        assert notaDeCredito.save() != null

        params.id = notaDeCredito.id

        def model = controller.show()

        assert model.notaDeCreditoInstance == notaDeCredito
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/notaDeCredito/list'

        populateValidParams(params)
        def notaDeCredito = new NotaDeCredito(params)

        assert notaDeCredito.save() != null

        params.id = notaDeCredito.id

        def model = controller.edit()

        assert model.notaDeCreditoInstance == notaDeCredito
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/notaDeCredito/list'

        response.reset()

        populateValidParams(params)
        def notaDeCredito = new NotaDeCredito(params)

        assert notaDeCredito.save() != null

        // test invalid parameters in update
        params.id = notaDeCredito.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/notaDeCredito/edit"
        assert model.notaDeCreditoInstance != null

        notaDeCredito.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/notaDeCredito/show/$notaDeCredito.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        notaDeCredito.clearErrors()

        populateValidParams(params)
        params.id = notaDeCredito.id
        params.version = -1
        controller.update()

        assert view == "/notaDeCredito/edit"
        assert model.notaDeCreditoInstance != null
        assert model.notaDeCreditoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/notaDeCredito/list'

        response.reset()

        populateValidParams(params)
        def notaDeCredito = new NotaDeCredito(params)

        assert notaDeCredito.save() != null
        assert NotaDeCredito.count() == 1

        params.id = notaDeCredito.id

        controller.delete()

        assert NotaDeCredito.count() == 0
        assert NotaDeCredito.get(notaDeCredito.id) == null
        assert response.redirectedUrl == '/notaDeCredito/list'
    }
}

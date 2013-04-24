package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(PedimentoController)
@Mock(Pedimento)
class PedimentoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pedimento/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pedimentoInstanceList.size() == 0
        assert model.pedimentoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pedimentoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pedimentoInstance != null
        assert view == '/pedimento/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pedimento/show/1'
        assert controller.flash.message != null
        assert Pedimento.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pedimento/list'

        populateValidParams(params)
        def pedimento = new Pedimento(params)

        assert pedimento.save() != null

        params.id = pedimento.id

        def model = controller.show()

        assert model.pedimentoInstance == pedimento
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pedimento/list'

        populateValidParams(params)
        def pedimento = new Pedimento(params)

        assert pedimento.save() != null

        params.id = pedimento.id

        def model = controller.edit()

        assert model.pedimentoInstance == pedimento
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pedimento/list'

        response.reset()

        populateValidParams(params)
        def pedimento = new Pedimento(params)

        assert pedimento.save() != null

        // test invalid parameters in update
        params.id = pedimento.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pedimento/edit"
        assert model.pedimentoInstance != null

        pedimento.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pedimento/show/$pedimento.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pedimento.clearErrors()

        populateValidParams(params)
        params.id = pedimento.id
        params.version = -1
        controller.update()

        assert view == "/pedimento/edit"
        assert model.pedimentoInstance != null
        assert model.pedimentoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pedimento/list'

        response.reset()

        populateValidParams(params)
        def pedimento = new Pedimento(params)

        assert pedimento.save() != null
        assert Pedimento.count() == 1

        params.id = pedimento.id

        controller.delete()

        assert Pedimento.count() == 0
        assert Pedimento.get(pedimento.id) == null
        assert response.redirectedUrl == '/pedimento/list'
    }
}

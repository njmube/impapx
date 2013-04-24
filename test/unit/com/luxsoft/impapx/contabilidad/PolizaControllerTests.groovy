package com.luxsoft.impapx.contabilidad



import org.junit.*
import grails.test.mixin.*

@TestFor(PolizaController)
@Mock(Poliza)
class PolizaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/poliza/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.polizaInstanceList.size() == 0
        assert model.polizaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.polizaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.polizaInstance != null
        assert view == '/poliza/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/poliza/show/1'
        assert controller.flash.message != null
        assert Poliza.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/poliza/list'

        populateValidParams(params)
        def poliza = new Poliza(params)

        assert poliza.save() != null

        params.id = poliza.id

        def model = controller.show()

        assert model.polizaInstance == poliza
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/poliza/list'

        populateValidParams(params)
        def poliza = new Poliza(params)

        assert poliza.save() != null

        params.id = poliza.id

        def model = controller.edit()

        assert model.polizaInstance == poliza
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/poliza/list'

        response.reset()

        populateValidParams(params)
        def poliza = new Poliza(params)

        assert poliza.save() != null

        // test invalid parameters in update
        params.id = poliza.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/poliza/edit"
        assert model.polizaInstance != null

        poliza.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/poliza/show/$poliza.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        poliza.clearErrors()

        populateValidParams(params)
        params.id = poliza.id
        params.version = -1
        controller.update()

        assert view == "/poliza/edit"
        assert model.polizaInstance != null
        assert model.polizaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/poliza/list'

        response.reset()

        populateValidParams(params)
        def poliza = new Poliza(params)

        assert poliza.save() != null
        assert Poliza.count() == 1

        params.id = poliza.id

        controller.delete()

        assert Poliza.count() == 0
        assert Poliza.get(poliza.id) == null
        assert response.redirectedUrl == '/poliza/list'
    }
}

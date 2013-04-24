package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(RequisicionController)
@Mock(Requisicion)
class RequisicionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/requisicion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.requisicionInstanceList.size() == 0
        assert model.requisicionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.requisicionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.requisicionInstance != null
        assert view == '/requisicion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/requisicion/show/1'
        assert controller.flash.message != null
        assert Requisicion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/requisicion/list'

        populateValidParams(params)
        def requisicion = new Requisicion(params)

        assert requisicion.save() != null

        params.id = requisicion.id

        def model = controller.show()

        assert model.requisicionInstance == requisicion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/requisicion/list'

        populateValidParams(params)
        def requisicion = new Requisicion(params)

        assert requisicion.save() != null

        params.id = requisicion.id

        def model = controller.edit()

        assert model.requisicionInstance == requisicion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/requisicion/list'

        response.reset()

        populateValidParams(params)
        def requisicion = new Requisicion(params)

        assert requisicion.save() != null

        // test invalid parameters in update
        params.id = requisicion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/requisicion/edit"
        assert model.requisicionInstance != null

        requisicion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/requisicion/show/$requisicion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        requisicion.clearErrors()

        populateValidParams(params)
        params.id = requisicion.id
        params.version = -1
        controller.update()

        assert view == "/requisicion/edit"
        assert model.requisicionInstance != null
        assert model.requisicionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/requisicion/list'

        response.reset()

        populateValidParams(params)
        def requisicion = new Requisicion(params)

        assert requisicion.save() != null
        assert Requisicion.count() == 1

        params.id = requisicion.id

        controller.delete()

        assert Requisicion.count() == 0
        assert Requisicion.get(requisicion.id) == null
        assert response.redirectedUrl == '/requisicion/list'
    }
}

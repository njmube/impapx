package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(RequisicionDetController)
@Mock(RequisicionDet)
class RequisicionDetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/requisicionDet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.requisicionDetInstanceList.size() == 0
        assert model.requisicionDetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.requisicionDetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.requisicionDetInstance != null
        assert view == '/requisicionDet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/requisicionDet/show/1'
        assert controller.flash.message != null
        assert RequisicionDet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/requisicionDet/list'

        populateValidParams(params)
        def requisicionDet = new RequisicionDet(params)

        assert requisicionDet.save() != null

        params.id = requisicionDet.id

        def model = controller.show()

        assert model.requisicionDetInstance == requisicionDet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/requisicionDet/list'

        populateValidParams(params)
        def requisicionDet = new RequisicionDet(params)

        assert requisicionDet.save() != null

        params.id = requisicionDet.id

        def model = controller.edit()

        assert model.requisicionDetInstance == requisicionDet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/requisicionDet/list'

        response.reset()

        populateValidParams(params)
        def requisicionDet = new RequisicionDet(params)

        assert requisicionDet.save() != null

        // test invalid parameters in update
        params.id = requisicionDet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/requisicionDet/edit"
        assert model.requisicionDetInstance != null

        requisicionDet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/requisicionDet/show/$requisicionDet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        requisicionDet.clearErrors()

        populateValidParams(params)
        params.id = requisicionDet.id
        params.version = -1
        controller.update()

        assert view == "/requisicionDet/edit"
        assert model.requisicionDetInstance != null
        assert model.requisicionDetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/requisicionDet/list'

        response.reset()

        populateValidParams(params)
        def requisicionDet = new RequisicionDet(params)

        assert requisicionDet.save() != null
        assert RequisicionDet.count() == 1

        params.id = requisicionDet.id

        controller.delete()

        assert RequisicionDet.count() == 0
        assert RequisicionDet.get(requisicionDet.id) == null
        assert response.redirectedUrl == '/requisicionDet/list'
    }
}

package com.luxsoft.impapx.tesoreria



import org.junit.*
import grails.test.mixin.*

@TestFor(ComisionController)
@Mock(Comision)
class ComisionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/comision/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.comisionInstanceList.size() == 0
        assert model.comisionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.comisionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.comisionInstance != null
        assert view == '/comision/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/comision/show/1'
        assert controller.flash.message != null
        assert Comision.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/comision/list'

        populateValidParams(params)
        def comision = new Comision(params)

        assert comision.save() != null

        params.id = comision.id

        def model = controller.show()

        assert model.comisionInstance == comision
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/comision/list'

        populateValidParams(params)
        def comision = new Comision(params)

        assert comision.save() != null

        params.id = comision.id

        def model = controller.edit()

        assert model.comisionInstance == comision
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/comision/list'

        response.reset()

        populateValidParams(params)
        def comision = new Comision(params)

        assert comision.save() != null

        // test invalid parameters in update
        params.id = comision.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/comision/edit"
        assert model.comisionInstance != null

        comision.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/comision/show/$comision.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        comision.clearErrors()

        populateValidParams(params)
        params.id = comision.id
        params.version = -1
        controller.update()

        assert view == "/comision/edit"
        assert model.comisionInstance != null
        assert model.comisionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/comision/list'

        response.reset()

        populateValidParams(params)
        def comision = new Comision(params)

        assert comision.save() != null
        assert Comision.count() == 1

        params.id = comision.id

        controller.delete()

        assert Comision.count() == 0
        assert Comision.get(comision.id) == null
        assert response.redirectedUrl == '/comision/list'
    }
}

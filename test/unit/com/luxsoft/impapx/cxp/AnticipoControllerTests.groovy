package com.luxsoft.impapx.cxp



import org.junit.*
import grails.test.mixin.*

@TestFor(AnticipoController)
@Mock(Anticipo)
class AnticipoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/anticipo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.anticipoInstanceList.size() == 0
        assert model.anticipoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.anticipoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.anticipoInstance != null
        assert view == '/anticipo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/anticipo/show/1'
        assert controller.flash.message != null
        assert Anticipo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/anticipo/list'

        populateValidParams(params)
        def anticipo = new Anticipo(params)

        assert anticipo.save() != null

        params.id = anticipo.id

        def model = controller.show()

        assert model.anticipoInstance == anticipo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/anticipo/list'

        populateValidParams(params)
        def anticipo = new Anticipo(params)

        assert anticipo.save() != null

        params.id = anticipo.id

        def model = controller.edit()

        assert model.anticipoInstance == anticipo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/anticipo/list'

        response.reset()

        populateValidParams(params)
        def anticipo = new Anticipo(params)

        assert anticipo.save() != null

        // test invalid parameters in update
        params.id = anticipo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/anticipo/edit"
        assert model.anticipoInstance != null

        anticipo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/anticipo/show/$anticipo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        anticipo.clearErrors()

        populateValidParams(params)
        params.id = anticipo.id
        params.version = -1
        controller.update()

        assert view == "/anticipo/edit"
        assert model.anticipoInstance != null
        assert model.anticipoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/anticipo/list'

        response.reset()

        populateValidParams(params)
        def anticipo = new Anticipo(params)

        assert anticipo.save() != null
        assert Anticipo.count() == 1

        params.id = anticipo.id

        controller.delete()

        assert Anticipo.count() == 0
        assert Anticipo.get(anticipo.id) == null
        assert response.redirectedUrl == '/anticipo/list'
    }
}

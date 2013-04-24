package com.luxsoft.impapx.tesoreria



import org.junit.*
import grails.test.mixin.*

@TestFor(TraspasoController)
@Mock(Traspaso)
class TraspasoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/traspaso/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.traspasoInstanceList.size() == 0
        assert model.traspasoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.traspasoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.traspasoInstance != null
        assert view == '/traspaso/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/traspaso/show/1'
        assert controller.flash.message != null
        assert Traspaso.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/traspaso/list'

        populateValidParams(params)
        def traspaso = new Traspaso(params)

        assert traspaso.save() != null

        params.id = traspaso.id

        def model = controller.show()

        assert model.traspasoInstance == traspaso
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/traspaso/list'

        populateValidParams(params)
        def traspaso = new Traspaso(params)

        assert traspaso.save() != null

        params.id = traspaso.id

        def model = controller.edit()

        assert model.traspasoInstance == traspaso
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/traspaso/list'

        response.reset()

        populateValidParams(params)
        def traspaso = new Traspaso(params)

        assert traspaso.save() != null

        // test invalid parameters in update
        params.id = traspaso.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/traspaso/edit"
        assert model.traspasoInstance != null

        traspaso.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/traspaso/show/$traspaso.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        traspaso.clearErrors()

        populateValidParams(params)
        params.id = traspaso.id
        params.version = -1
        controller.update()

        assert view == "/traspaso/edit"
        assert model.traspasoInstance != null
        assert model.traspasoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/traspaso/list'

        response.reset()

        populateValidParams(params)
        def traspaso = new Traspaso(params)

        assert traspaso.save() != null
        assert Traspaso.count() == 1

        params.id = traspaso.id

        controller.delete()

        assert Traspaso.count() == 0
        assert Traspaso.get(traspaso.id) == null
        assert response.redirectedUrl == '/traspaso/list'
    }
}

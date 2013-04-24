package com.luxsoft.impapx.cxc



import org.junit.*
import grails.test.mixin.*

@TestFor(CXCNotaController)
@Mock(CXCNota)
class CXCNotaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/CXCNota/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.CXCNotaInstanceList.size() == 0
        assert model.CXCNotaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.CXCNotaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.CXCNotaInstance != null
        assert view == '/CXCNota/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/CXCNota/show/1'
        assert controller.flash.message != null
        assert CXCNota.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/CXCNota/list'

        populateValidParams(params)
        def CXCNota = new CXCNota(params)

        assert CXCNota.save() != null

        params.id = CXCNota.id

        def model = controller.show()

        assert model.CXCNotaInstance == CXCNota
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/CXCNota/list'

        populateValidParams(params)
        def CXCNota = new CXCNota(params)

        assert CXCNota.save() != null

        params.id = CXCNota.id

        def model = controller.edit()

        assert model.CXCNotaInstance == CXCNota
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/CXCNota/list'

        response.reset()

        populateValidParams(params)
        def CXCNota = new CXCNota(params)

        assert CXCNota.save() != null

        // test invalid parameters in update
        params.id = CXCNota.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/CXCNota/edit"
        assert model.CXCNotaInstance != null

        CXCNota.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/CXCNota/show/$CXCNota.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        CXCNota.clearErrors()

        populateValidParams(params)
        params.id = CXCNota.id
        params.version = -1
        controller.update()

        assert view == "/CXCNota/edit"
        assert model.CXCNotaInstance != null
        assert model.CXCNotaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/CXCNota/list'

        response.reset()

        populateValidParams(params)
        def CXCNota = new CXCNota(params)

        assert CXCNota.save() != null
        assert CXCNota.count() == 1

        params.id = CXCNota.id

        controller.delete()

        assert CXCNota.count() == 0
        assert CXCNota.get(CXCNota.id) == null
        assert response.redirectedUrl == '/CXCNota/list'
    }
}

package com.luxsoft.cfd



import org.junit.*
import grails.test.mixin.*

@TestFor(FolioFiscalController)
@Mock(FolioFiscal)
class FolioFiscalControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/folioFiscal/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.folioFiscalInstanceList.size() == 0
        assert model.folioFiscalInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.folioFiscalInstance != null
    }

    void testSave() {
        controller.save()

        assert model.folioFiscalInstance != null
        assert view == '/folioFiscal/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/folioFiscal/show/1'
        assert controller.flash.message != null
        assert FolioFiscal.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/folioFiscal/list'

        populateValidParams(params)
        def folioFiscal = new FolioFiscal(params)

        assert folioFiscal.save() != null

        params.id = folioFiscal.id

        def model = controller.show()

        assert model.folioFiscalInstance == folioFiscal
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/folioFiscal/list'

        populateValidParams(params)
        def folioFiscal = new FolioFiscal(params)

        assert folioFiscal.save() != null

        params.id = folioFiscal.id

        def model = controller.edit()

        assert model.folioFiscalInstance == folioFiscal
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/folioFiscal/list'

        response.reset()

        populateValidParams(params)
        def folioFiscal = new FolioFiscal(params)

        assert folioFiscal.save() != null

        // test invalid parameters in update
        params.id = folioFiscal.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/folioFiscal/edit"
        assert model.folioFiscalInstance != null

        folioFiscal.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/folioFiscal/show/$folioFiscal.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        folioFiscal.clearErrors()

        populateValidParams(params)
        params.id = folioFiscal.id
        params.version = -1
        controller.update()

        assert view == "/folioFiscal/edit"
        assert model.folioFiscalInstance != null
        assert model.folioFiscalInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/folioFiscal/list'

        response.reset()

        populateValidParams(params)
        def folioFiscal = new FolioFiscal(params)

        assert folioFiscal.save() != null
        assert FolioFiscal.count() == 1

        params.id = folioFiscal.id

        controller.delete()

        assert FolioFiscal.count() == 0
        assert FolioFiscal.get(folioFiscal.id) == null
        assert response.redirectedUrl == '/folioFiscal/list'
    }
}

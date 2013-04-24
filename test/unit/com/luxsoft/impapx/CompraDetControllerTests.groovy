package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(CompraDetController)
@Mock(CompraDet)
class CompraDetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/compraDet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.compraDetInstanceList.size() == 0
        assert model.compraDetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.compraDetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.compraDetInstance != null
        assert view == '/compraDet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/compraDet/show/1'
        assert controller.flash.message != null
        assert CompraDet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/compraDet/list'

        populateValidParams(params)
        def compraDet = new CompraDet(params)

        assert compraDet.save() != null

        params.id = compraDet.id

        def model = controller.show()

        assert model.compraDetInstance == compraDet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/compraDet/list'

        populateValidParams(params)
        def compraDet = new CompraDet(params)

        assert compraDet.save() != null

        params.id = compraDet.id

        def model = controller.edit()

        assert model.compraDetInstance == compraDet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/compraDet/list'

        response.reset()

        populateValidParams(params)
        def compraDet = new CompraDet(params)

        assert compraDet.save() != null

        // test invalid parameters in update
        params.id = compraDet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/compraDet/edit"
        assert model.compraDetInstance != null

        compraDet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/compraDet/show/$compraDet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        compraDet.clearErrors()

        populateValidParams(params)
        params.id = compraDet.id
        params.version = -1
        controller.update()

        assert view == "/compraDet/edit"
        assert model.compraDetInstance != null
        assert model.compraDetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/compraDet/list'

        response.reset()

        populateValidParams(params)
        def compraDet = new CompraDet(params)

        assert compraDet.save() != null
        assert CompraDet.count() == 1

        params.id = compraDet.id

        controller.delete()

        assert CompraDet.count() == 0
        assert CompraDet.get(compraDet.id) == null
        assert response.redirectedUrl == '/compraDet/list'
    }
}

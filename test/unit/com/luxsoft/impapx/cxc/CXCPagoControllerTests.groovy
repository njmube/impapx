package com.luxsoft.impapx.cxc



import org.junit.*
import grails.test.mixin.*

@TestFor(CXCPagoController)
@Mock(CXCPago)
class CXCPagoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/CXCPago/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.CXCPagoInstanceList.size() == 0
        assert model.CXCPagoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.CXCPagoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.CXCPagoInstance != null
        assert view == '/CXCPago/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/CXCPago/show/1'
        assert controller.flash.message != null
        assert CXCPago.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/CXCPago/list'

        populateValidParams(params)
        def CXCPago = new CXCPago(params)

        assert CXCPago.save() != null

        params.id = CXCPago.id

        def model = controller.show()

        assert model.CXCPagoInstance == CXCPago
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/CXCPago/list'

        populateValidParams(params)
        def CXCPago = new CXCPago(params)

        assert CXCPago.save() != null

        params.id = CXCPago.id

        def model = controller.edit()

        assert model.CXCPagoInstance == CXCPago
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/CXCPago/list'

        response.reset()

        populateValidParams(params)
        def CXCPago = new CXCPago(params)

        assert CXCPago.save() != null

        // test invalid parameters in update
        params.id = CXCPago.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/CXCPago/edit"
        assert model.CXCPagoInstance != null

        CXCPago.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/CXCPago/show/$CXCPago.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        CXCPago.clearErrors()

        populateValidParams(params)
        params.id = CXCPago.id
        params.version = -1
        controller.update()

        assert view == "/CXCPago/edit"
        assert model.CXCPagoInstance != null
        assert model.CXCPagoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/CXCPago/list'

        response.reset()

        populateValidParams(params)
        def CXCPago = new CXCPago(params)

        assert CXCPago.save() != null
        assert CXCPago.count() == 1

        params.id = CXCPago.id

        controller.delete()

        assert CXCPago.count() == 0
        assert CXCPago.get(CXCPago.id) == null
        assert response.redirectedUrl == '/CXCPago/list'
    }
}

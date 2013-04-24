package com.luxsoft.impapx.tesoreria



import org.junit.*
import grails.test.mixin.*

@TestFor(SaldoDeCuentaController)
@Mock(SaldoDeCuenta)
class SaldoDeCuentaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/saldoDeCuenta/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.saldoDeCuentaInstanceList.size() == 0
        assert model.saldoDeCuentaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.saldoDeCuentaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.saldoDeCuentaInstance != null
        assert view == '/saldoDeCuenta/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/saldoDeCuenta/show/1'
        assert controller.flash.message != null
        assert SaldoDeCuenta.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/saldoDeCuenta/list'

        populateValidParams(params)
        def saldoDeCuenta = new SaldoDeCuenta(params)

        assert saldoDeCuenta.save() != null

        params.id = saldoDeCuenta.id

        def model = controller.show()

        assert model.saldoDeCuentaInstance == saldoDeCuenta
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/saldoDeCuenta/list'

        populateValidParams(params)
        def saldoDeCuenta = new SaldoDeCuenta(params)

        assert saldoDeCuenta.save() != null

        params.id = saldoDeCuenta.id

        def model = controller.edit()

        assert model.saldoDeCuentaInstance == saldoDeCuenta
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/saldoDeCuenta/list'

        response.reset()

        populateValidParams(params)
        def saldoDeCuenta = new SaldoDeCuenta(params)

        assert saldoDeCuenta.save() != null

        // test invalid parameters in update
        params.id = saldoDeCuenta.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/saldoDeCuenta/edit"
        assert model.saldoDeCuentaInstance != null

        saldoDeCuenta.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/saldoDeCuenta/show/$saldoDeCuenta.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        saldoDeCuenta.clearErrors()

        populateValidParams(params)
        params.id = saldoDeCuenta.id
        params.version = -1
        controller.update()

        assert view == "/saldoDeCuenta/edit"
        assert model.saldoDeCuentaInstance != null
        assert model.saldoDeCuentaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/saldoDeCuenta/list'

        response.reset()

        populateValidParams(params)
        def saldoDeCuenta = new SaldoDeCuenta(params)

        assert saldoDeCuenta.save() != null
        assert SaldoDeCuenta.count() == 1

        params.id = saldoDeCuenta.id

        controller.delete()

        assert SaldoDeCuenta.count() == 0
        assert SaldoDeCuenta.get(saldoDeCuenta.id) == null
        assert response.redirectedUrl == '/saldoDeCuenta/list'
    }
}

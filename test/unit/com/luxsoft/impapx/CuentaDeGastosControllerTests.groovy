package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(CuentaDeGastosController)
@Mock(CuentaDeGastos)
class CuentaDeGastosControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cuentaDeGastos/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cuentaDeGastosInstanceList.size() == 0
        assert model.cuentaDeGastosInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cuentaDeGastosInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cuentaDeGastosInstance != null
        assert view == '/cuentaDeGastos/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cuentaDeGastos/show/1'
        assert controller.flash.message != null
        assert CuentaDeGastos.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaDeGastos/list'

        populateValidParams(params)
        def cuentaDeGastos = new CuentaDeGastos(params)

        assert cuentaDeGastos.save() != null

        params.id = cuentaDeGastos.id

        def model = controller.show()

        assert model.cuentaDeGastosInstance == cuentaDeGastos
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaDeGastos/list'

        populateValidParams(params)
        def cuentaDeGastos = new CuentaDeGastos(params)

        assert cuentaDeGastos.save() != null

        params.id = cuentaDeGastos.id

        def model = controller.edit()

        assert model.cuentaDeGastosInstance == cuentaDeGastos
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaDeGastos/list'

        response.reset()

        populateValidParams(params)
        def cuentaDeGastos = new CuentaDeGastos(params)

        assert cuentaDeGastos.save() != null

        // test invalid parameters in update
        params.id = cuentaDeGastos.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cuentaDeGastos/edit"
        assert model.cuentaDeGastosInstance != null

        cuentaDeGastos.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cuentaDeGastos/show/$cuentaDeGastos.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cuentaDeGastos.clearErrors()

        populateValidParams(params)
        params.id = cuentaDeGastos.id
        params.version = -1
        controller.update()

        assert view == "/cuentaDeGastos/edit"
        assert model.cuentaDeGastosInstance != null
        assert model.cuentaDeGastosInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cuentaDeGastos/list'

        response.reset()

        populateValidParams(params)
        def cuentaDeGastos = new CuentaDeGastos(params)

        assert cuentaDeGastos.save() != null
        assert CuentaDeGastos.count() == 1

        params.id = cuentaDeGastos.id

        controller.delete()

        assert CuentaDeGastos.count() == 0
        assert CuentaDeGastos.get(cuentaDeGastos.id) == null
        assert response.redirectedUrl == '/cuentaDeGastos/list'
    }
}

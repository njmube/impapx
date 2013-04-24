package com.luxsoft.impapx.tesoreria



import org.junit.*
import grails.test.mixin.*

@TestFor(MovimientoDeCuentaController)
@Mock(MovimientoDeCuenta)
class MovimientoDeCuentaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/movimientoDeCuenta/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.movimientoDeCuentaInstanceList.size() == 0
        assert model.movimientoDeCuentaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.movimientoDeCuentaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.movimientoDeCuentaInstance != null
        assert view == '/movimientoDeCuenta/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/movimientoDeCuenta/show/1'
        assert controller.flash.message != null
        assert MovimientoDeCuenta.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/movimientoDeCuenta/list'

        populateValidParams(params)
        def movimientoDeCuenta = new MovimientoDeCuenta(params)

        assert movimientoDeCuenta.save() != null

        params.id = movimientoDeCuenta.id

        def model = controller.show()

        assert model.movimientoDeCuentaInstance == movimientoDeCuenta
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/movimientoDeCuenta/list'

        populateValidParams(params)
        def movimientoDeCuenta = new MovimientoDeCuenta(params)

        assert movimientoDeCuenta.save() != null

        params.id = movimientoDeCuenta.id

        def model = controller.edit()

        assert model.movimientoDeCuentaInstance == movimientoDeCuenta
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/movimientoDeCuenta/list'

        response.reset()

        populateValidParams(params)
        def movimientoDeCuenta = new MovimientoDeCuenta(params)

        assert movimientoDeCuenta.save() != null

        // test invalid parameters in update
        params.id = movimientoDeCuenta.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/movimientoDeCuenta/edit"
        assert model.movimientoDeCuentaInstance != null

        movimientoDeCuenta.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/movimientoDeCuenta/show/$movimientoDeCuenta.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        movimientoDeCuenta.clearErrors()

        populateValidParams(params)
        params.id = movimientoDeCuenta.id
        params.version = -1
        controller.update()

        assert view == "/movimientoDeCuenta/edit"
        assert model.movimientoDeCuentaInstance != null
        assert model.movimientoDeCuentaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/movimientoDeCuenta/list'

        response.reset()

        populateValidParams(params)
        def movimientoDeCuenta = new MovimientoDeCuenta(params)

        assert movimientoDeCuenta.save() != null
        assert MovimientoDeCuenta.count() == 1

        params.id = movimientoDeCuenta.id

        controller.delete()

        assert MovimientoDeCuenta.count() == 0
        assert MovimientoDeCuenta.get(movimientoDeCuenta.id) == null
        assert response.redirectedUrl == '/movimientoDeCuenta/list'
    }
}

package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(FacturaDeGastosController)
@Mock(FacturaDeGastos)
class FacturaDeGastosControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/facturaDeGastos/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.facturaDeGastosInstanceList.size() == 0
        assert model.facturaDeGastosInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.facturaDeGastosInstance != null
    }

    void testSave() {
        controller.save()

        assert model.facturaDeGastosInstance != null
        assert view == '/facturaDeGastos/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/facturaDeGastos/show/1'
        assert controller.flash.message != null
        assert FacturaDeGastos.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaDeGastos/list'

        populateValidParams(params)
        def facturaDeGastos = new FacturaDeGastos(params)

        assert facturaDeGastos.save() != null

        params.id = facturaDeGastos.id

        def model = controller.show()

        assert model.facturaDeGastosInstance == facturaDeGastos
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaDeGastos/list'

        populateValidParams(params)
        def facturaDeGastos = new FacturaDeGastos(params)

        assert facturaDeGastos.save() != null

        params.id = facturaDeGastos.id

        def model = controller.edit()

        assert model.facturaDeGastosInstance == facturaDeGastos
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaDeGastos/list'

        response.reset()

        populateValidParams(params)
        def facturaDeGastos = new FacturaDeGastos(params)

        assert facturaDeGastos.save() != null

        // test invalid parameters in update
        params.id = facturaDeGastos.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/facturaDeGastos/edit"
        assert model.facturaDeGastosInstance != null

        facturaDeGastos.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/facturaDeGastos/show/$facturaDeGastos.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        facturaDeGastos.clearErrors()

        populateValidParams(params)
        params.id = facturaDeGastos.id
        params.version = -1
        controller.update()

        assert view == "/facturaDeGastos/edit"
        assert model.facturaDeGastosInstance != null
        assert model.facturaDeGastosInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/facturaDeGastos/list'

        response.reset()

        populateValidParams(params)
        def facturaDeGastos = new FacturaDeGastos(params)

        assert facturaDeGastos.save() != null
        assert FacturaDeGastos.count() == 1

        params.id = facturaDeGastos.id

        controller.delete()

        assert FacturaDeGastos.count() == 0
        assert FacturaDeGastos.get(facturaDeGastos.id) == null
        assert response.redirectedUrl == '/facturaDeGastos/list'
    }
}

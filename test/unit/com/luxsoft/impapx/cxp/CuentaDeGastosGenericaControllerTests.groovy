package com.luxsoft.impapx.cxp



import org.junit.*
import grails.test.mixin.*

@TestFor(CuentaDeGastosGenericaController)
@Mock(CuentaDeGastosGenerica)
class CuentaDeGastosGenericaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cuentaDeGastosGenerica/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cuentaDeGastosGenericaInstanceList.size() == 0
        assert model.cuentaDeGastosGenericaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cuentaDeGastosGenericaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cuentaDeGastosGenericaInstance != null
        assert view == '/cuentaDeGastosGenerica/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cuentaDeGastosGenerica/show/1'
        assert controller.flash.message != null
        assert CuentaDeGastosGenerica.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaDeGastosGenerica/list'

        populateValidParams(params)
        def cuentaDeGastosGenerica = new CuentaDeGastosGenerica(params)

        assert cuentaDeGastosGenerica.save() != null

        params.id = cuentaDeGastosGenerica.id

        def model = controller.show()

        assert model.cuentaDeGastosGenericaInstance == cuentaDeGastosGenerica
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaDeGastosGenerica/list'

        populateValidParams(params)
        def cuentaDeGastosGenerica = new CuentaDeGastosGenerica(params)

        assert cuentaDeGastosGenerica.save() != null

        params.id = cuentaDeGastosGenerica.id

        def model = controller.edit()

        assert model.cuentaDeGastosGenericaInstance == cuentaDeGastosGenerica
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaDeGastosGenerica/list'

        response.reset()

        populateValidParams(params)
        def cuentaDeGastosGenerica = new CuentaDeGastosGenerica(params)

        assert cuentaDeGastosGenerica.save() != null

        // test invalid parameters in update
        params.id = cuentaDeGastosGenerica.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cuentaDeGastosGenerica/edit"
        assert model.cuentaDeGastosGenericaInstance != null

        cuentaDeGastosGenerica.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cuentaDeGastosGenerica/show/$cuentaDeGastosGenerica.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cuentaDeGastosGenerica.clearErrors()

        populateValidParams(params)
        params.id = cuentaDeGastosGenerica.id
        params.version = -1
        controller.update()

        assert view == "/cuentaDeGastosGenerica/edit"
        assert model.cuentaDeGastosGenericaInstance != null
        assert model.cuentaDeGastosGenericaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cuentaDeGastosGenerica/list'

        response.reset()

        populateValidParams(params)
        def cuentaDeGastosGenerica = new CuentaDeGastosGenerica(params)

        assert cuentaDeGastosGenerica.save() != null
        assert CuentaDeGastosGenerica.count() == 1

        params.id = cuentaDeGastosGenerica.id

        controller.delete()

        assert CuentaDeGastosGenerica.count() == 0
        assert CuentaDeGastosGenerica.get(cuentaDeGastosGenerica.id) == null
        assert response.redirectedUrl == '/cuentaDeGastosGenerica/list'
    }
}

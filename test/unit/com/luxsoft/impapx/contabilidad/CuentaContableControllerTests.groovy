package com.luxsoft.impapx.contabilidad



import org.junit.*
import grails.test.mixin.*

@TestFor(CuentaContableController)
@Mock(CuentaContable)
class CuentaContableControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cuentaContable/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cuentaContableInstanceList.size() == 0
        assert model.cuentaContableInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cuentaContableInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cuentaContableInstance != null
        assert view == '/cuentaContable/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cuentaContable/show/1'
        assert controller.flash.message != null
        assert CuentaContable.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaContable/list'

        populateValidParams(params)
        def cuentaContable = new CuentaContable(params)

        assert cuentaContable.save() != null

        params.id = cuentaContable.id

        def model = controller.show()

        assert model.cuentaContableInstance == cuentaContable
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaContable/list'

        populateValidParams(params)
        def cuentaContable = new CuentaContable(params)

        assert cuentaContable.save() != null

        params.id = cuentaContable.id

        def model = controller.edit()

        assert model.cuentaContableInstance == cuentaContable
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cuentaContable/list'

        response.reset()

        populateValidParams(params)
        def cuentaContable = new CuentaContable(params)

        assert cuentaContable.save() != null

        // test invalid parameters in update
        params.id = cuentaContable.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cuentaContable/edit"
        assert model.cuentaContableInstance != null

        cuentaContable.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cuentaContable/show/$cuentaContable.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cuentaContable.clearErrors()

        populateValidParams(params)
        params.id = cuentaContable.id
        params.version = -1
        controller.update()

        assert view == "/cuentaContable/edit"
        assert model.cuentaContableInstance != null
        assert model.cuentaContableInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cuentaContable/list'

        response.reset()

        populateValidParams(params)
        def cuentaContable = new CuentaContable(params)

        assert cuentaContable.save() != null
        assert CuentaContable.count() == 1

        params.id = cuentaContable.id

        controller.delete()

        assert CuentaContable.count() == 0
        assert CuentaContable.get(cuentaContable.id) == null
        assert response.redirectedUrl == '/cuentaContable/list'
    }
}

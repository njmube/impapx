package com.luxsoft.impapx.contabilidad



import org.junit.*
import grails.test.mixin.*

@TestFor(SaldoPorCuentaContableController)
@Mock(SaldoPorCuentaContable)
class SaldoPorCuentaContableControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/saldoPorCuentaContable/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.saldoPorCuentaContableInstanceList.size() == 0
        assert model.saldoPorCuentaContableInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.saldoPorCuentaContableInstance != null
    }

    void testSave() {
        controller.save()

        assert model.saldoPorCuentaContableInstance != null
        assert view == '/saldoPorCuentaContable/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/saldoPorCuentaContable/show/1'
        assert controller.flash.message != null
        assert SaldoPorCuentaContable.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/saldoPorCuentaContable/list'

        populateValidParams(params)
        def saldoPorCuentaContable = new SaldoPorCuentaContable(params)

        assert saldoPorCuentaContable.save() != null

        params.id = saldoPorCuentaContable.id

        def model = controller.show()

        assert model.saldoPorCuentaContableInstance == saldoPorCuentaContable
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/saldoPorCuentaContable/list'

        populateValidParams(params)
        def saldoPorCuentaContable = new SaldoPorCuentaContable(params)

        assert saldoPorCuentaContable.save() != null

        params.id = saldoPorCuentaContable.id

        def model = controller.edit()

        assert model.saldoPorCuentaContableInstance == saldoPorCuentaContable
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/saldoPorCuentaContable/list'

        response.reset()

        populateValidParams(params)
        def saldoPorCuentaContable = new SaldoPorCuentaContable(params)

        assert saldoPorCuentaContable.save() != null

        // test invalid parameters in update
        params.id = saldoPorCuentaContable.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/saldoPorCuentaContable/edit"
        assert model.saldoPorCuentaContableInstance != null

        saldoPorCuentaContable.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/saldoPorCuentaContable/show/$saldoPorCuentaContable.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        saldoPorCuentaContable.clearErrors()

        populateValidParams(params)
        params.id = saldoPorCuentaContable.id
        params.version = -1
        controller.update()

        assert view == "/saldoPorCuentaContable/edit"
        assert model.saldoPorCuentaContableInstance != null
        assert model.saldoPorCuentaContableInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/saldoPorCuentaContable/list'

        response.reset()

        populateValidParams(params)
        def saldoPorCuentaContable = new SaldoPorCuentaContable(params)

        assert saldoPorCuentaContable.save() != null
        assert SaldoPorCuentaContable.count() == 1

        params.id = saldoPorCuentaContable.id

        controller.delete()

        assert SaldoPorCuentaContable.count() == 0
        assert SaldoPorCuentaContable.get(saldoPorCuentaContable.id) == null
        assert response.redirectedUrl == '/saldoPorCuentaContable/list'
    }
}

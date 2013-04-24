package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(EmpresaController)
@Mock(Empresa)
class EmpresaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/empresa/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.empresaInstanceList.size() == 0
        assert model.empresaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.empresaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.empresaInstance != null
        assert view == '/empresa/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/empresa/show/1'
        assert controller.flash.message != null
        assert Empresa.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/empresa/list'

        populateValidParams(params)
        def empresa = new Empresa(params)

        assert empresa.save() != null

        params.id = empresa.id

        def model = controller.show()

        assert model.empresaInstance == empresa
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/empresa/list'

        populateValidParams(params)
        def empresa = new Empresa(params)

        assert empresa.save() != null

        params.id = empresa.id

        def model = controller.edit()

        assert model.empresaInstance == empresa
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/empresa/list'

        response.reset()

        populateValidParams(params)
        def empresa = new Empresa(params)

        assert empresa.save() != null

        // test invalid parameters in update
        params.id = empresa.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/empresa/edit"
        assert model.empresaInstance != null

        empresa.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/empresa/show/$empresa.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        empresa.clearErrors()

        populateValidParams(params)
        params.id = empresa.id
        params.version = -1
        controller.update()

        assert view == "/empresa/edit"
        assert model.empresaInstance != null
        assert model.empresaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/empresa/list'

        response.reset()

        populateValidParams(params)
        def empresa = new Empresa(params)

        assert empresa.save() != null
        assert Empresa.count() == 1

        params.id = empresa.id

        controller.delete()

        assert Empresa.count() == 0
        assert Empresa.get(empresa.id) == null
        assert response.redirectedUrl == '/empresa/list'
    }
}

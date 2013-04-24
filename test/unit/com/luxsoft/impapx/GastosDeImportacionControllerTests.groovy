package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(GastosDeImportacionController)
@Mock(GastosDeImportacion)
class GastosDeImportacionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gastosDeImportacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gastosDeImportacionInstanceList.size() == 0
        assert model.gastosDeImportacionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.gastosDeImportacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gastosDeImportacionInstance != null
        assert view == '/gastosDeImportacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gastosDeImportacion/show/1'
        assert controller.flash.message != null
        assert GastosDeImportacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gastosDeImportacion/list'

        populateValidParams(params)
        def gastosDeImportacion = new GastosDeImportacion(params)

        assert gastosDeImportacion.save() != null

        params.id = gastosDeImportacion.id

        def model = controller.show()

        assert model.gastosDeImportacionInstance == gastosDeImportacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gastosDeImportacion/list'

        populateValidParams(params)
        def gastosDeImportacion = new GastosDeImportacion(params)

        assert gastosDeImportacion.save() != null

        params.id = gastosDeImportacion.id

        def model = controller.edit()

        assert model.gastosDeImportacionInstance == gastosDeImportacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gastosDeImportacion/list'

        response.reset()

        populateValidParams(params)
        def gastosDeImportacion = new GastosDeImportacion(params)

        assert gastosDeImportacion.save() != null

        // test invalid parameters in update
        params.id = gastosDeImportacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gastosDeImportacion/edit"
        assert model.gastosDeImportacionInstance != null

        gastosDeImportacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gastosDeImportacion/show/$gastosDeImportacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gastosDeImportacion.clearErrors()

        populateValidParams(params)
        params.id = gastosDeImportacion.id
        params.version = -1
        controller.update()

        assert view == "/gastosDeImportacion/edit"
        assert model.gastosDeImportacionInstance != null
        assert model.gastosDeImportacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gastosDeImportacion/list'

        response.reset()

        populateValidParams(params)
        def gastosDeImportacion = new GastosDeImportacion(params)

        assert gastosDeImportacion.save() != null
        assert GastosDeImportacion.count() == 1

        params.id = gastosDeImportacion.id

        controller.delete()

        assert GastosDeImportacion.count() == 0
        assert GastosDeImportacion.get(gastosDeImportacion.id) == null
        assert response.redirectedUrl == '/gastosDeImportacion/list'
    }
}

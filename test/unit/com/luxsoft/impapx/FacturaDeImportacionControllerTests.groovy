package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(FacturaDeImportacionController)
@Mock(FacturaDeImportacion)
class FacturaDeImportacionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/facturaDeImportacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.facturaDeImportacionInstanceList.size() == 0
        assert model.facturaDeImportacionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.facturaDeImportacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.facturaDeImportacionInstance != null
        assert view == '/facturaDeImportacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/facturaDeImportacion/show/1'
        assert controller.flash.message != null
        assert FacturaDeImportacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaDeImportacion/list'

        populateValidParams(params)
        def facturaDeImportacion = new FacturaDeImportacion(params)

        assert facturaDeImportacion.save() != null

        params.id = facturaDeImportacion.id

        def model = controller.show()

        assert model.facturaDeImportacionInstance == facturaDeImportacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaDeImportacion/list'

        populateValidParams(params)
        def facturaDeImportacion = new FacturaDeImportacion(params)

        assert facturaDeImportacion.save() != null

        params.id = facturaDeImportacion.id

        def model = controller.edit()

        assert model.facturaDeImportacionInstance == facturaDeImportacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaDeImportacion/list'

        response.reset()

        populateValidParams(params)
        def facturaDeImportacion = new FacturaDeImportacion(params)

        assert facturaDeImportacion.save() != null

        // test invalid parameters in update
        params.id = facturaDeImportacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/facturaDeImportacion/edit"
        assert model.facturaDeImportacionInstance != null

        facturaDeImportacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/facturaDeImportacion/show/$facturaDeImportacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        facturaDeImportacion.clearErrors()

        populateValidParams(params)
        params.id = facturaDeImportacion.id
        params.version = -1
        controller.update()

        assert view == "/facturaDeImportacion/edit"
        assert model.facturaDeImportacionInstance != null
        assert model.facturaDeImportacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/facturaDeImportacion/list'

        response.reset()

        populateValidParams(params)
        def facturaDeImportacion = new FacturaDeImportacion(params)

        assert facturaDeImportacion.save() != null
        assert FacturaDeImportacion.count() == 1

        params.id = facturaDeImportacion.id

        controller.delete()

        assert FacturaDeImportacion.count() == 0
        assert FacturaDeImportacion.get(facturaDeImportacion.id) == null
        assert response.redirectedUrl == '/facturaDeImportacion/list'
    }
}

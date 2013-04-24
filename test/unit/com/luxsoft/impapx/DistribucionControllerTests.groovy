package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(DistribucionController)
@Mock(Distribucion)
class DistribucionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/distribucion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.distribucionInstanceList.size() == 0
        assert model.distribucionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.distribucionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.distribucionInstance != null
        assert view == '/distribucion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/distribucion/show/1'
        assert controller.flash.message != null
        assert Distribucion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/distribucion/list'

        populateValidParams(params)
        def distribucion = new Distribucion(params)

        assert distribucion.save() != null

        params.id = distribucion.id

        def model = controller.show()

        assert model.distribucionInstance == distribucion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/distribucion/list'

        populateValidParams(params)
        def distribucion = new Distribucion(params)

        assert distribucion.save() != null

        params.id = distribucion.id

        def model = controller.edit()

        assert model.distribucionInstance == distribucion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/distribucion/list'

        response.reset()

        populateValidParams(params)
        def distribucion = new Distribucion(params)

        assert distribucion.save() != null

        // test invalid parameters in update
        params.id = distribucion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/distribucion/edit"
        assert model.distribucionInstance != null

        distribucion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/distribucion/show/$distribucion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        distribucion.clearErrors()

        populateValidParams(params)
        params.id = distribucion.id
        params.version = -1
        controller.update()

        assert view == "/distribucion/edit"
        assert model.distribucionInstance != null
        assert model.distribucionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/distribucion/list'

        response.reset()

        populateValidParams(params)
        def distribucion = new Distribucion(params)

        assert distribucion.save() != null
        assert Distribucion.count() == 1

        params.id = distribucion.id

        controller.delete()

        assert Distribucion.count() == 0
        assert Distribucion.get(distribucion.id) == null
        assert response.redirectedUrl == '/distribucion/list'
    }
}

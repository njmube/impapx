package com.luxsoft.impapx.tesoreria



import org.junit.*
import grails.test.mixin.*

@TestFor(InversionController)
@Mock(Inversion)
class InversionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/inversion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.inversionInstanceList.size() == 0
        assert model.inversionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.inversionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.inversionInstance != null
        assert view == '/inversion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/inversion/show/1'
        assert controller.flash.message != null
        assert Inversion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/inversion/list'

        populateValidParams(params)
        def inversion = new Inversion(params)

        assert inversion.save() != null

        params.id = inversion.id

        def model = controller.show()

        assert model.inversionInstance == inversion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/inversion/list'

        populateValidParams(params)
        def inversion = new Inversion(params)

        assert inversion.save() != null

        params.id = inversion.id

        def model = controller.edit()

        assert model.inversionInstance == inversion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/inversion/list'

        response.reset()

        populateValidParams(params)
        def inversion = new Inversion(params)

        assert inversion.save() != null

        // test invalid parameters in update
        params.id = inversion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/inversion/edit"
        assert model.inversionInstance != null

        inversion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/inversion/show/$inversion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        inversion.clearErrors()

        populateValidParams(params)
        params.id = inversion.id
        params.version = -1
        controller.update()

        assert view == "/inversion/edit"
        assert model.inversionInstance != null
        assert model.inversionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/inversion/list'

        response.reset()

        populateValidParams(params)
        def inversion = new Inversion(params)

        assert inversion.save() != null
        assert Inversion.count() == 1

        params.id = inversion.id

        controller.delete()

        assert Inversion.count() == 0
        assert Inversion.get(inversion.id) == null
        assert response.redirectedUrl == '/inversion/list'
    }
}

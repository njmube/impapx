package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(EmbarqueDetController)
@Mock(EmbarqueDet)
class EmbarqueDetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/embarqueDet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.embarqueDetInstanceList.size() == 0
        assert model.embarqueDetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.embarqueDetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.embarqueDetInstance != null
        assert view == '/embarqueDet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/embarqueDet/show/1'
        assert controller.flash.message != null
        assert EmbarqueDet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/embarqueDet/list'

        populateValidParams(params)
        def embarqueDet = new EmbarqueDet(params)

        assert embarqueDet.save() != null

        params.id = embarqueDet.id

        def model = controller.show()

        assert model.embarqueDetInstance == embarqueDet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/embarqueDet/list'

        populateValidParams(params)
        def embarqueDet = new EmbarqueDet(params)

        assert embarqueDet.save() != null

        params.id = embarqueDet.id

        def model = controller.edit()

        assert model.embarqueDetInstance == embarqueDet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/embarqueDet/list'

        response.reset()

        populateValidParams(params)
        def embarqueDet = new EmbarqueDet(params)

        assert embarqueDet.save() != null

        // test invalid parameters in update
        params.id = embarqueDet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/embarqueDet/edit"
        assert model.embarqueDetInstance != null

        embarqueDet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/embarqueDet/show/$embarqueDet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        embarqueDet.clearErrors()

        populateValidParams(params)
        params.id = embarqueDet.id
        params.version = -1
        controller.update()

        assert view == "/embarqueDet/edit"
        assert model.embarqueDetInstance != null
        assert model.embarqueDetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/embarqueDet/list'

        response.reset()

        populateValidParams(params)
        def embarqueDet = new EmbarqueDet(params)

        assert embarqueDet.save() != null
        assert EmbarqueDet.count() == 1

        params.id = embarqueDet.id

        controller.delete()

        assert EmbarqueDet.count() == 0
        assert EmbarqueDet.get(embarqueDet.id) == null
        assert response.redirectedUrl == '/embarqueDet/list'
    }
}

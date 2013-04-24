package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(EmbarqueController)
@Mock(Embarque)
class EmbarqueControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/embarque/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.embarqueInstanceList.size() == 0
        assert model.embarqueInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.embarqueInstance != null
    }

    void testSave() {
        controller.save()

        assert model.embarqueInstance != null
        assert view == '/embarque/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/embarque/show/1'
        assert controller.flash.message != null
        assert Embarque.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/embarque/list'

        populateValidParams(params)
        def embarque = new Embarque(params)

        assert embarque.save() != null

        params.id = embarque.id

        def model = controller.show()

        assert model.embarqueInstance == embarque
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/embarque/list'

        populateValidParams(params)
        def embarque = new Embarque(params)

        assert embarque.save() != null

        params.id = embarque.id

        def model = controller.edit()

        assert model.embarqueInstance == embarque
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/embarque/list'

        response.reset()

        populateValidParams(params)
        def embarque = new Embarque(params)

        assert embarque.save() != null

        // test invalid parameters in update
        params.id = embarque.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/embarque/edit"
        assert model.embarqueInstance != null

        embarque.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/embarque/show/$embarque.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        embarque.clearErrors()

        populateValidParams(params)
        params.id = embarque.id
        params.version = -1
        controller.update()

        assert view == "/embarque/edit"
        assert model.embarqueInstance != null
        assert model.embarqueInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/embarque/list'

        response.reset()

        populateValidParams(params)
        def embarque = new Embarque(params)

        assert embarque.save() != null
        assert Embarque.count() == 1

        params.id = embarque.id

        controller.delete()

        assert Embarque.count() == 0
        assert Embarque.get(embarque.id) == null
        assert response.redirectedUrl == '/embarque/list'
    }
}

package com.luxsoft.impapx



import org.junit.*
import grails.test.mixin.*

@TestFor(DistribucionDetController)
@Mock(DistribucionDet)
class DistribucionDetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/distribucionDet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.distribucionDetInstanceList.size() == 0
        assert model.distribucionDetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.distribucionDetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.distribucionDetInstance != null
        assert view == '/distribucionDet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/distribucionDet/show/1'
        assert controller.flash.message != null
        assert DistribucionDet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/distribucionDet/list'

        populateValidParams(params)
        def distribucionDet = new DistribucionDet(params)

        assert distribucionDet.save() != null

        params.id = distribucionDet.id

        def model = controller.show()

        assert model.distribucionDetInstance == distribucionDet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/distribucionDet/list'

        populateValidParams(params)
        def distribucionDet = new DistribucionDet(params)

        assert distribucionDet.save() != null

        params.id = distribucionDet.id

        def model = controller.edit()

        assert model.distribucionDetInstance == distribucionDet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/distribucionDet/list'

        response.reset()

        populateValidParams(params)
        def distribucionDet = new DistribucionDet(params)

        assert distribucionDet.save() != null

        // test invalid parameters in update
        params.id = distribucionDet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/distribucionDet/edit"
        assert model.distribucionDetInstance != null

        distribucionDet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/distribucionDet/show/$distribucionDet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        distribucionDet.clearErrors()

        populateValidParams(params)
        params.id = distribucionDet.id
        params.version = -1
        controller.update()

        assert view == "/distribucionDet/edit"
        assert model.distribucionDetInstance != null
        assert model.distribucionDetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/distribucionDet/list'

        response.reset()

        populateValidParams(params)
        def distribucionDet = new DistribucionDet(params)

        assert distribucionDet.save() != null
        assert DistribucionDet.count() == 1

        params.id = distribucionDet.id

        controller.delete()

        assert DistribucionDet.count() == 0
        assert DistribucionDet.get(distribucionDet.id) == null
        assert response.redirectedUrl == '/distribucionDet/list'
    }
}

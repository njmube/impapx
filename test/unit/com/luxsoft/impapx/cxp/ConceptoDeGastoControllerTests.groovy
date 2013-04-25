package com.luxsoft.impapx.cxp



import org.junit.*
import grails.test.mixin.*

@TestFor(ConceptoDeGastoController)
@Mock(ConceptoDeGasto)
class ConceptoDeGastoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/conceptoDeGasto/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.conceptoDeGastoInstanceList.size() == 0
        assert model.conceptoDeGastoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.conceptoDeGastoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.conceptoDeGastoInstance != null
        assert view == '/conceptoDeGasto/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/conceptoDeGasto/show/1'
        assert controller.flash.message != null
        assert ConceptoDeGasto.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/conceptoDeGasto/list'

        populateValidParams(params)
        def conceptoDeGasto = new ConceptoDeGasto(params)

        assert conceptoDeGasto.save() != null

        params.id = conceptoDeGasto.id

        def model = controller.show()

        assert model.conceptoDeGastoInstance == conceptoDeGasto
    }

    void testEdit() {
        controller.editConcepto()

        assert flash.message != null
        assert response.redirectedUrl == '/conceptoDeGasto/list'

        populateValidParams(params)
        def conceptoDeGasto = new ConceptoDeGasto(params)

        assert conceptoDeGasto.save() != null

        params.id = conceptoDeGasto.id

        def model = controller.editConcepto()

        assert model.conceptoDeGastoInstance == conceptoDeGasto
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/conceptoDeGasto/list'

        response.reset()

        populateValidParams(params)
        def conceptoDeGasto = new ConceptoDeGasto(params)

        assert conceptoDeGasto.save() != null

        // test invalid parameters in update
        params.id = conceptoDeGasto.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/conceptoDeGasto/edit"
        assert model.conceptoDeGastoInstance != null

        conceptoDeGasto.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/conceptoDeGasto/show/$conceptoDeGasto.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        conceptoDeGasto.clearErrors()

        populateValidParams(params)
        params.id = conceptoDeGasto.id
        params.version = -1
        controller.update()

        assert view == "/conceptoDeGasto/edit"
        assert model.conceptoDeGastoInstance != null
        assert model.conceptoDeGastoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/conceptoDeGasto/list'

        response.reset()

        populateValidParams(params)
        def conceptoDeGasto = new ConceptoDeGasto(params)

        assert conceptoDeGasto.save() != null
        assert ConceptoDeGasto.count() == 1

        params.id = conceptoDeGasto.id

        controller.delete()

        assert ConceptoDeGasto.count() == 0
        assert ConceptoDeGasto.get(conceptoDeGasto.id) == null
        assert response.redirectedUrl == '/conceptoDeGasto/list'
    }
}

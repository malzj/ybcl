package cbjlfws


import org.junit.*
import grails.test.mixin.*

@TestFor(FwsGongNengController)
@Mock(FwsGongNeng)
class FwsGongNengControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fwsGongNeng/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fwsGongNengInstanceList.size() == 0
        assert model.fwsGongNengInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fwsGongNengInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fwsGongNengInstance != null
        assert view == '/fwsGongNeng/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fwsGongNeng/show/1'
        assert controller.flash.message != null
        assert FwsGongNeng.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsGongNeng/list'

        populateValidParams(params)
        def fwsGongNeng = new FwsGongNeng(params)

        assert fwsGongNeng.save() != null

        params.id = fwsGongNeng.id

        def model = controller.show()

        assert model.fwsGongNengInstance == fwsGongNeng
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsGongNeng/list'

        populateValidParams(params)
        def fwsGongNeng = new FwsGongNeng(params)

        assert fwsGongNeng.save() != null

        params.id = fwsGongNeng.id

        def model = controller.edit()

        assert model.fwsGongNengInstance == fwsGongNeng
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsGongNeng/list'

        response.reset()

        populateValidParams(params)
        def fwsGongNeng = new FwsGongNeng(params)

        assert fwsGongNeng.save() != null

        // test invalid parameters in update
        params.id = fwsGongNeng.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fwsGongNeng/edit"
        assert model.fwsGongNengInstance != null

        fwsGongNeng.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fwsGongNeng/show/$fwsGongNeng.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fwsGongNeng.clearErrors()

        populateValidParams(params)
        params.id = fwsGongNeng.id
        params.version = -1
        controller.update()

        assert view == "/fwsGongNeng/edit"
        assert model.fwsGongNengInstance != null
        assert model.fwsGongNengInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fwsGongNeng/list'

        response.reset()

        populateValidParams(params)
        def fwsGongNeng = new FwsGongNeng(params)

        assert fwsGongNeng.save() != null
        assert FwsGongNeng.count() == 1

        params.id = fwsGongNeng.id

        controller.delete()

        assert FwsGongNeng.count() == 0
        assert FwsGongNeng.get(fwsGongNeng.id) == null
        assert response.redirectedUrl == '/fwsGongNeng/list'
    }
}

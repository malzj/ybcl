package cbjlfws


import org.junit.*
import grails.test.mixin.*

@TestFor(FwsStationManageController)
@Mock(FwsStationManage)
class FwsStationManageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fwsStationManage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fwsStationManageInstanceList.size() == 0
        assert model.fwsStationManageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fwsStationManageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fwsStationManageInstance != null
        assert view == '/fwsStationManage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fwsStationManage/show/1'
        assert controller.flash.message != null
        assert FwsStationManage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStationManage/list'

        populateValidParams(params)
        def fwsStationManage = new FwsStationManage(params)

        assert fwsStationManage.save() != null

        params.id = fwsStationManage.id

        def model = controller.show()

        assert model.fwsStationManageInstance == fwsStationManage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStationManage/list'

        populateValidParams(params)
        def fwsStationManage = new FwsStationManage(params)

        assert fwsStationManage.save() != null

        params.id = fwsStationManage.id

        def model = controller.edit()

        assert model.fwsStationManageInstance == fwsStationManage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStationManage/list'

        response.reset()

        populateValidParams(params)
        def fwsStationManage = new FwsStationManage(params)

        assert fwsStationManage.save() != null

        // test invalid parameters in update
        params.id = fwsStationManage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fwsStationManage/edit"
        assert model.fwsStationManageInstance != null

        fwsStationManage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fwsStationManage/show/$fwsStationManage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fwsStationManage.clearErrors()

        populateValidParams(params)
        params.id = fwsStationManage.id
        params.version = -1
        controller.update()

        assert view == "/fwsStationManage/edit"
        assert model.fwsStationManageInstance != null
        assert model.fwsStationManageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fwsStationManage/list'

        response.reset()

        populateValidParams(params)
        def fwsStationManage = new FwsStationManage(params)

        assert fwsStationManage.save() != null
        assert FwsStationManage.count() == 1

        params.id = fwsStationManage.id

        controller.delete()

        assert FwsStationManage.count() == 0
        assert FwsStationManage.get(fwsStationManage.id) == null
        assert response.redirectedUrl == '/fwsStationManage/list'
    }
}

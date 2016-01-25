package cbjlfws

import grails.test.mixin.*

@TestFor(FwsStationDepartmentController)
@Mock(FwsStationDepartment)
class FwsStationDepartmentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fwsStationDepartment/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fwsStationDepartmentInstanceList.size() == 0
        assert model.fwsStationDepartmentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fwsStationDepartmentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fwsStationDepartmentInstance != null
        assert view == '/fwsStationDepartment/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fwsStationDepartment/show/1'
        assert controller.flash.message != null
        assert FwsStationDepartment.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStationDepartment/list'

        populateValidParams(params)
        def fwsStationDepartment = new FwsStationDepartment(params)

        assert fwsStationDepartment.save() != null

        params.id = fwsStationDepartment.id

        def model = controller.show()

        assert model.fwsStationDepartmentInstance == fwsStationDepartment
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStationDepartment/list'

        populateValidParams(params)
        def fwsStationDepartment = new FwsStationDepartment(params)

        assert fwsStationDepartment.save() != null

        params.id = fwsStationDepartment.id

        def model = controller.edit()

        assert model.fwsStationDepartmentInstance == fwsStationDepartment
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStationDepartment/list'

        response.reset()

        populateValidParams(params)
        def fwsStationDepartment = new FwsStationDepartment(params)

        assert fwsStationDepartment.save() != null

        // test invalid parameters in update
        params.id = fwsStationDepartment.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fwsStationDepartment/edit"
        assert model.fwsStationDepartmentInstance != null

        fwsStationDepartment.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fwsStationDepartment/show/$fwsStationDepartment.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fwsStationDepartment.clearErrors()

        populateValidParams(params)
        params.id = fwsStationDepartment.id
        params.version = -1
        controller.update()

        assert view == "/fwsStationDepartment/edit"
        assert model.fwsStationDepartmentInstance != null
        assert model.fwsStationDepartmentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fwsStationDepartment/list'

        response.reset()

        populateValidParams(params)
        def fwsStationDepartment = new FwsStationDepartment(params)

        assert fwsStationDepartment.save() != null
        assert FwsStationDepartment.count() == 1

        params.id = fwsStationDepartment.id

        controller.delete()

        assert FwsStationDepartment.count() == 0
        assert FwsStationDepartment.get(fwsStationDepartment.id) == null
        assert response.redirectedUrl == '/fwsStationDepartment/list'
    }
}

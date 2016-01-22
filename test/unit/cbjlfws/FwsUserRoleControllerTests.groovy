package cbjlfws


import org.junit.*
import grails.test.mixin.*

@TestFor(FwsUserRoleController)
@Mock(FwsUserRole)
class FwsUserRoleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fwsUserRole/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fwsUserRoleInstanceList.size() == 0
        assert model.fwsUserRoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fwsUserRoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fwsUserRoleInstance != null
        assert view == '/fwsUserRole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fwsUserRole/show/1'
        assert controller.flash.message != null
        assert FwsUserRole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsUserRole/list'

        populateValidParams(params)
        def fwsUserRole = new FwsUserRole(params)

        assert fwsUserRole.save() != null

        params.id = fwsUserRole.id

        def model = controller.show()

        assert model.fwsUserRoleInstance == fwsUserRole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsUserRole/list'

        populateValidParams(params)
        def fwsUserRole = new FwsUserRole(params)

        assert fwsUserRole.save() != null

        params.id = fwsUserRole.id

        def model = controller.edit()

        assert model.fwsUserRoleInstance == fwsUserRole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsUserRole/list'

        response.reset()

        populateValidParams(params)
        def fwsUserRole = new FwsUserRole(params)

        assert fwsUserRole.save() != null

        // test invalid parameters in update
        params.id = fwsUserRole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fwsUserRole/edit"
        assert model.fwsUserRoleInstance != null

        fwsUserRole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fwsUserRole/show/$fwsUserRole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fwsUserRole.clearErrors()

        populateValidParams(params)
        params.id = fwsUserRole.id
        params.version = -1
        controller.update()

        assert view == "/fwsUserRole/edit"
        assert model.fwsUserRoleInstance != null
        assert model.fwsUserRoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fwsUserRole/list'

        response.reset()

        populateValidParams(params)
        def fwsUserRole = new FwsUserRole(params)

        assert fwsUserRole.save() != null
        assert FwsUserRole.count() == 1

        params.id = fwsUserRole.id

        controller.delete()

        assert FwsUserRole.count() == 0
        assert FwsUserRole.get(fwsUserRole.id) == null
        assert response.redirectedUrl == '/fwsUserRole/list'
    }
}

package cbjlfws



import org.junit.*
import grails.test.mixin.*

@TestFor(FwsUserController)
@Mock(FwsUser)
class FwsUserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fwsUser/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fwsUserInstanceList.size() == 0
        assert model.fwsUserInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fwsUserInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fwsUserInstance != null
        assert view == '/fwsUser/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fwsUser/show/1'
        assert controller.flash.message != null
        assert FwsUser.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsUser/list'

        populateValidParams(params)
        def fwsUser = new FwsUser(params)

        assert fwsUser.save() != null

        params.id = fwsUser.id

        def model = controller.show()

        assert model.fwsUserInstance == fwsUser
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsUser/list'

        populateValidParams(params)
        def fwsUser = new FwsUser(params)

        assert fwsUser.save() != null

        params.id = fwsUser.id

        def model = controller.edit()

        assert model.fwsUserInstance == fwsUser
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsUser/list'

        response.reset()

        populateValidParams(params)
        def fwsUser = new FwsUser(params)

        assert fwsUser.save() != null

        // test invalid parameters in update
        params.id = fwsUser.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fwsUser/edit"
        assert model.fwsUserInstance != null

        fwsUser.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fwsUser/show/$fwsUser.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fwsUser.clearErrors()

        populateValidParams(params)
        params.id = fwsUser.id
        params.version = -1
        controller.update()

        assert view == "/fwsUser/edit"
        assert model.fwsUserInstance != null
        assert model.fwsUserInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fwsUser/list'

        response.reset()

        populateValidParams(params)
        def fwsUser = new FwsUser(params)

        assert fwsUser.save() != null
        assert FwsUser.count() == 1

        params.id = fwsUser.id

        controller.delete()

        assert FwsUser.count() == 0
        assert FwsUser.get(fwsUser.id) == null
        assert response.redirectedUrl == '/fwsUser/list'
    }
}

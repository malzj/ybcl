package cbjlfws



import org.junit.*
import grails.test.mixin.*

@TestFor(YbUserController)
@Mock(YbUser)
class YbUserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ybUser/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ybUserInstanceList.size() == 0
        assert model.ybUserInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ybUserInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ybUserInstance != null
        assert view == '/ybUser/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ybUser/show/1'
        assert controller.flash.message != null
        assert YbUser.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ybUser/list'

        populateValidParams(params)
        def ybUser = new YbUser(params)

        assert ybUser.save() != null

        params.id = ybUser.id

        def model = controller.show()

        assert model.ybUserInstance == ybUser
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ybUser/list'

        populateValidParams(params)
        def ybUser = new YbUser(params)

        assert ybUser.save() != null

        params.id = ybUser.id

        def model = controller.edit()

        assert model.ybUserInstance == ybUser
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ybUser/list'

        response.reset()

        populateValidParams(params)
        def ybUser = new YbUser(params)

        assert ybUser.save() != null

        // test invalid parameters in update
        params.id = ybUser.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ybUser/edit"
        assert model.ybUserInstance != null

        ybUser.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ybUser/show/$ybUser.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ybUser.clearErrors()

        populateValidParams(params)
        params.id = ybUser.id
        params.version = -1
        controller.update()

        assert view == "/ybUser/edit"
        assert model.ybUserInstance != null
        assert model.ybUserInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ybUser/list'

        response.reset()

        populateValidParams(params)
        def ybUser = new YbUser(params)

        assert ybUser.save() != null
        assert YbUser.count() == 1

        params.id = ybUser.id

        controller.delete()

        assert YbUser.count() == 0
        assert YbUser.get(ybUser.id) == null
        assert response.redirectedUrl == '/ybUser/list'
    }
}

package cbjlfws



import org.junit.*
import grails.test.mixin.*

@TestFor(YbRoleController)
@Mock(YbRole)
class YbRoleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ybRole/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ybRoleInstanceList.size() == 0
        assert model.ybRoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ybRoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ybRoleInstance != null
        assert view == '/ybRole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ybRole/show/1'
        assert controller.flash.message != null
        assert YbRole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ybRole/list'

        populateValidParams(params)
        def ybRole = new YbRole(params)

        assert ybRole.save() != null

        params.id = ybRole.id

        def model = controller.show()

        assert model.ybRoleInstance == ybRole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ybRole/list'

        populateValidParams(params)
        def ybRole = new YbRole(params)

        assert ybRole.save() != null

        params.id = ybRole.id

        def model = controller.edit()

        assert model.ybRoleInstance == ybRole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ybRole/list'

        response.reset()

        populateValidParams(params)
        def ybRole = new YbRole(params)

        assert ybRole.save() != null

        // test invalid parameters in update
        params.id = ybRole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ybRole/edit"
        assert model.ybRoleInstance != null

        ybRole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ybRole/show/$ybRole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ybRole.clearErrors()

        populateValidParams(params)
        params.id = ybRole.id
        params.version = -1
        controller.update()

        assert view == "/ybRole/edit"
        assert model.ybRoleInstance != null
        assert model.ybRoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ybRole/list'

        response.reset()

        populateValidParams(params)
        def ybRole = new YbRole(params)

        assert ybRole.save() != null
        assert YbRole.count() == 1

        params.id = ybRole.id

        controller.delete()

        assert YbRole.count() == 0
        assert YbRole.get(ybRole.id) == null
        assert response.redirectedUrl == '/ybRole/list'
    }
}

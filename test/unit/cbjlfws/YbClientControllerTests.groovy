package cbjlfws



import org.junit.*
import grails.test.mixin.*

@TestFor(YbClientController)
@Mock(YbClient)
class YbClientControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ybClient/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ybClientInstanceList.size() == 0
        assert model.ybClientInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ybClientInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ybClientInstance != null
        assert view == '/ybClient/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ybClient/show/1'
        assert controller.flash.message != null
        assert YbClient.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ybClient/list'

        populateValidParams(params)
        def ybClient = new YbClient(params)

        assert ybClient.save() != null

        params.id = ybClient.id

        def model = controller.show()

        assert model.ybClientInstance == ybClient
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ybClient/list'

        populateValidParams(params)
        def ybClient = new YbClient(params)

        assert ybClient.save() != null

        params.id = ybClient.id

        def model = controller.edit()

        assert model.ybClientInstance == ybClient
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ybClient/list'

        response.reset()

        populateValidParams(params)
        def ybClient = new YbClient(params)

        assert ybClient.save() != null

        // test invalid parameters in update
        params.id = ybClient.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ybClient/edit"
        assert model.ybClientInstance != null

        ybClient.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ybClient/show/$ybClient.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ybClient.clearErrors()

        populateValidParams(params)
        params.id = ybClient.id
        params.version = -1
        controller.update()

        assert view == "/ybClient/edit"
        assert model.ybClientInstance != null
        assert model.ybClientInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ybClient/list'

        response.reset()

        populateValidParams(params)
        def ybClient = new YbClient(params)

        assert ybClient.save() != null
        assert YbClient.count() == 1

        params.id = ybClient.id

        controller.delete()

        assert YbClient.count() == 0
        assert YbClient.get(ybClient.id) == null
        assert response.redirectedUrl == '/ybClient/list'
    }
}

package cbjlfws



import org.junit.*
import grails.test.mixin.*

@TestFor(GongNengController)
@Mock(GongNeng)
class GongNengControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gongNeng/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gongNengInstanceList.size() == 0
        assert model.gongNengInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.gongNengInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gongNengInstance != null
        assert view == '/gongNeng/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gongNeng/show/1'
        assert controller.flash.message != null
        assert GongNeng.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gongNeng/list'

        populateValidParams(params)
        def gongNeng = new GongNeng(params)

        assert gongNeng.save() != null

        params.id = gongNeng.id

        def model = controller.show()

        assert model.gongNengInstance == gongNeng
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gongNeng/list'

        populateValidParams(params)
        def gongNeng = new GongNeng(params)

        assert gongNeng.save() != null

        params.id = gongNeng.id

        def model = controller.edit()

        assert model.gongNengInstance == gongNeng
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gongNeng/list'

        response.reset()

        populateValidParams(params)
        def gongNeng = new GongNeng(params)

        assert gongNeng.save() != null

        // test invalid parameters in update
        params.id = gongNeng.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gongNeng/edit"
        assert model.gongNengInstance != null

        gongNeng.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gongNeng/show/$gongNeng.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gongNeng.clearErrors()

        populateValidParams(params)
        params.id = gongNeng.id
        params.version = -1
        controller.update()

        assert view == "/gongNeng/edit"
        assert model.gongNengInstance != null
        assert model.gongNengInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gongNeng/list'

        response.reset()

        populateValidParams(params)
        def gongNeng = new GongNeng(params)

        assert gongNeng.save() != null
        assert GongNeng.count() == 1

        params.id = gongNeng.id

        controller.delete()

        assert GongNeng.count() == 0
        assert GongNeng.get(gongNeng.id) == null
        assert response.redirectedUrl == '/gongNeng/list'
    }
}

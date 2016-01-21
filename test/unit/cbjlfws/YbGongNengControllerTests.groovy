package cbjlfws



import org.junit.*
import grails.test.mixin.*

@TestFor(YbGongNengController)
@Mock(YbGongNeng)
class YbGongNengControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ybGongNeng/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ybGongNengInstanceList.size() == 0
        assert model.ybGongNengInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ybGongNengInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ybGongNengInstance != null
        assert view == '/ybGongNeng/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ybGongNeng/show/1'
        assert controller.flash.message != null
        assert YbGongNeng.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ybGongNeng/list'

        populateValidParams(params)
        def ybGongNeng = new YbGongNeng(params)

        assert ybGongNeng.save() != null

        params.id = ybGongNeng.id

        def model = controller.show()

        assert model.ybGongNengInstance == ybGongNeng
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ybGongNeng/list'

        populateValidParams(params)
        def ybGongNeng = new YbGongNeng(params)

        assert ybGongNeng.save() != null

        params.id = ybGongNeng.id

        def model = controller.edit()

        assert model.ybGongNengInstance == ybGongNeng
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ybGongNeng/list'

        response.reset()

        populateValidParams(params)
        def ybGongNeng = new YbGongNeng(params)

        assert ybGongNeng.save() != null

        // test invalid parameters in update
        params.id = ybGongNeng.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ybGongNeng/edit"
        assert model.ybGongNengInstance != null

        ybGongNeng.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ybGongNeng/show/$ybGongNeng.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ybGongNeng.clearErrors()

        populateValidParams(params)
        params.id = ybGongNeng.id
        params.version = -1
        controller.update()

        assert view == "/ybGongNeng/edit"
        assert model.ybGongNengInstance != null
        assert model.ybGongNengInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ybGongNeng/list'

        response.reset()

        populateValidParams(params)
        def ybGongNeng = new YbGongNeng(params)

        assert ybGongNeng.save() != null
        assert YbGongNeng.count() == 1

        params.id = ybGongNeng.id

        controller.delete()

        assert YbGongNeng.count() == 0
        assert YbGongNeng.get(ybGongNeng.id) == null
        assert response.redirectedUrl == '/ybGongNeng/list'
    }
}

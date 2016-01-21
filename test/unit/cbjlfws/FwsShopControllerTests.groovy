package cbjlfws



import org.junit.*
import grails.test.mixin.*

@TestFor(FwsShopController)
@Mock(FwsShop)
class FwsShopControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fwsShop/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fwsShopInstanceList.size() == 0
        assert model.fwsShopInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fwsShopInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fwsShopInstance != null
        assert view == '/fwsShop/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fwsShop/show/1'
        assert controller.flash.message != null
        assert FwsShop.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsShop/list'

        populateValidParams(params)
        def fwsShop = new FwsShop(params)

        assert fwsShop.save() != null

        params.id = fwsShop.id

        def model = controller.show()

        assert model.fwsShopInstance == fwsShop
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsShop/list'

        populateValidParams(params)
        def fwsShop = new FwsShop(params)

        assert fwsShop.save() != null

        params.id = fwsShop.id

        def model = controller.edit()

        assert model.fwsShopInstance == fwsShop
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsShop/list'

        response.reset()

        populateValidParams(params)
        def fwsShop = new FwsShop(params)

        assert fwsShop.save() != null

        // test invalid parameters in update
        params.id = fwsShop.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fwsShop/edit"
        assert model.fwsShopInstance != null

        fwsShop.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fwsShop/show/$fwsShop.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fwsShop.clearErrors()

        populateValidParams(params)
        params.id = fwsShop.id
        params.version = -1
        controller.update()

        assert view == "/fwsShop/edit"
        assert model.fwsShopInstance != null
        assert model.fwsShopInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fwsShop/list'

        response.reset()

        populateValidParams(params)
        def fwsShop = new FwsShop(params)

        assert fwsShop.save() != null
        assert FwsShop.count() == 1

        params.id = fwsShop.id

        controller.delete()

        assert FwsShop.count() == 0
        assert FwsShop.get(fwsShop.id) == null
        assert response.redirectedUrl == '/fwsShop/list'
    }
}

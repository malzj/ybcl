package cbjlfws



import org.junit.*
import grails.test.mixin.*

@TestFor(FwsStationController)
@Mock(FwsStation)
class FwsStationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fwsStation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fwsStationInstanceList.size() == 0
        assert model.fwsStationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fwsStationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fwsStationInstance != null
        assert view == '/fwsStation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fwsStation/show/1'
        assert controller.flash.message != null
        assert FwsStation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStation/list'

        populateValidParams(params)
        def fwsStation = new FwsStation(params)

        assert fwsStation.save() != null

        params.id = fwsStation.id

        def model = controller.show()

        assert model.fwsStationInstance == fwsStation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStation/list'

        populateValidParams(params)
        def fwsStation = new FwsStation(params)

        assert fwsStation.save() != null

        params.id = fwsStation.id

        def model = controller.edit()

        assert model.fwsStationInstance == fwsStation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fwsStation/list'

        response.reset()

        populateValidParams(params)
        def fwsStation = new FwsStation(params)

        assert fwsStation.save() != null

        // test invalid parameters in update
        params.id = fwsStation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fwsStation/edit"
        assert model.fwsStationInstance != null

        fwsStation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fwsStation/show/$fwsStation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fwsStation.clearErrors()

        populateValidParams(params)
        params.id = fwsStation.id
        params.version = -1
        controller.update()

        assert view == "/fwsStation/edit"
        assert model.fwsStationInstance != null
        assert model.fwsStationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fwsStation/list'

        response.reset()

        populateValidParams(params)
        def fwsStation = new FwsStation(params)

        assert fwsStation.save() != null
        assert FwsStation.count() == 1

        params.id = fwsStation.id

        controller.delete()

        assert FwsStation.count() == 0
        assert FwsStation.get(fwsStation.id) == null
        assert response.redirectedUrl == '/fwsStation/list'
    }
}

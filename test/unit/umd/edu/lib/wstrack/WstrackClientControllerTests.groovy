package umd.edu.lib.wstrack



import org.junit.*

import umd.edu.lib.wstrackClient.WstrackClientController;
import grails.test.mixin.*

@TestFor(WstrackClientController)
@Mock(WstrackClient)
class WstrackClientControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/wstrackClient/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.wstrackClientInstanceList.size() == 0
        assert model.wstrackClientInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.wstrackClientInstance != null
    }

    void testSave() {
        controller.save()

        assert model.wstrackClientInstance != null
        assert view == '/wstrackClient/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/wstrackClient/show/1'
        assert controller.flash.message != null
        assert WstrackClient.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/wstrackClient/list'


        populateValidParams(params)
        def wstrackClient = new WstrackClient(params)

        assert wstrackClient.save() != null

        params.id = wstrackClient.id

        def model = controller.show()

        assert model.wstrackClientInstance == wstrackClient
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/wstrackClient/list'


        populateValidParams(params)
        def wstrackClient = new WstrackClient(params)

        assert wstrackClient.save() != null

        params.id = wstrackClient.id

        def model = controller.edit()

        assert model.wstrackClientInstance == wstrackClient
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/wstrackClient/list'

        response.reset()


        populateValidParams(params)
        def wstrackClient = new WstrackClient(params)

        assert wstrackClient.save() != null

        // test invalid parameters in update
        params.id = wstrackClient.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/wstrackClient/edit"
        assert model.wstrackClientInstance != null

        wstrackClient.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/wstrackClient/show/$wstrackClient.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        wstrackClient.clearErrors()

        populateValidParams(params)
        params.id = wstrackClient.id
        params.version = -1
        controller.update()

        assert view == "/wstrackClient/edit"
        assert model.wstrackClientInstance != null
        assert model.wstrackClientInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/wstrackClient/list'

        response.reset()

        populateValidParams(params)
        def wstrackClient = new WstrackClient(params)

        assert wstrackClient.save() != null
        assert WstrackClient.count() == 1

        params.id = wstrackClient.id

        controller.delete()

        assert WstrackClient.count() == 0
        assert WstrackClient.get(wstrackClient.id) == null
        assert response.redirectedUrl == '/wstrackClient/list'
    }
}

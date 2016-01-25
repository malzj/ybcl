package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class FwsBossController {

    def index() {
        def msga =""
        def p = params.id

        if (p=='1'){
              msga="您输入账户有误"
        }else{
            msga="您的公司账号已过期"
        }
        [msg:msga]
    }
    def login(){
        def username = params.username
        def password = params.password
        def ybClient =  YbClient.findByUsernameAndPassword(username,password)
        if(ybClient){
            session.ybClient = ybClient
            def date = new Date()
            if (date<=ybClient.overTime&&date>=ybClient.beginTime){
                   redirect(action: "fwsShopList")
            }else {
                redirect(action: "index",msg:"您的公司账号已过期",id: 2)
            }
        }else {
            redirect(action: "index",id: 1)
        }
    }
    def fwsShopList(){
         def ybClient = session.ybClient
         def fwsShopList = FwsShop.findAllByYbClient(ybClient)
        def msg = ""
        if (params.id=="1"){
            msg="您需要再次购买服务商店"
        }
        [fwsShopList:fwsShopList,msg: msg]
    }
    def fwsShopCreate(){
          [fwsShopInstance: new FwsShop(params)]
      }
    def fwsShopSave(){
       def ybClient = session.ybClient
        def fwsShopList = FwsShop.findAllByYbClient(ybClient)
        if (ybClient.number>fwsShopList.size()){
            def fwsShopInstance = new FwsShop(params)
            fwsShopInstance.ybClient=session.ybClient
            if (!fwsShopInstance.save(flush: true)) {
                render(view: "fwsShopCreate", model: [fwsShopInstance: fwsShopInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), fwsShopInstance.id])
            redirect(action: "fwsShopList", id: fwsShopInstance.id)
        }else {
            redirect(action: "fwsShopList", id: "1")
        }

    }


    def fwsUserList(){
        def fwsShopId = params.id
        def fwsShop = FwsShop.get(fwsShopId)
          def name = fwsShop.name
          def fwsUserList = FwsUser.findAllByFwsShop(fwsShop)
        [fwsUserList:fwsUserList,id: fwsShopId,name:name,fwsUserInstanceTotal: FwsUser.countByFwsShop(fwsShop)]
    }
    def fwsUserCreate(){
        def fwsShopId = params.id
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        def gongnenglist = FwsGongNeng.list()
        def departmentList = fwsShop.department
        [fwsUserInstance: new FwsUser(params),id:fwsShopId,name: name,gongnenglist:gongnenglist,departmentList:departmentList]
    }
    def fwsUserSave(){
        def fwsUserInstance = new FwsUser(params)
        def gongnenglist = params.gongneng
        def departmentId= params.departmentId
        def fwsShopId= params.fwsShopId
        fwsUserInstance.fwsShop= FwsShop.get(fwsShopId)
        fwsUserInstance.department=Department.get(departmentId)
        def i = 0
        if (!fwsUserInstance.save(flush: true)) {
            render(view: "fwsUserCreate", model: [fwsUserInstance: fwsUserInstance])
            return
        }
        for (i; i < gongnenglist.size(); i++) {
            def userId = fwsUserInstance.id
            def gongnengId = gongnenglist[i]
            def fwsRole = new FwsUserRole()
            fwsRole.fwsUserRoleTime = new Date()
            fwsRole.fwsUserRoleGongNengId = gongnengId
            fwsRole.fwsUserRoleId = userId
            fwsRole.save()
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ybUser.label', default: 'YbUser'), fwsUserInstance.id])
        redirect(action: "fwsUserList", id:fwsShopId)
    }
    def fwsUserShow(Long id){
        def fwsUserInstance = FwsUser.get(id)
        def fwsShopId = fwsUserInstance.fwsShopId
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        def gongnenglist = FwsUserRole.findAllByFwsUserRoleId(fwsUserInstance.id)
        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "list")
            return
        }
        def size = gongnenglist.size()

        def i = 0
        def listgongneng = []
        for (i; i < size; i++) {
            def s = gongnenglist.get(i)
            def gongnengId = s.fwsUserRoleGongNengId
            def g = FwsGongNeng.findById(gongnengId)
            print(s)
            listgongneng << g
        }

        [fwsUserInstance: fwsUserInstance,id: fwsUserInstance.fwsShop.id,name: name,listgongneng:listgongneng]
    }
    def fwsUserEdit(Long id) {
        def fwsUserInstance = FwsUser.get(id)
        def fwsShopId = fwsUserInstance.fwsShopId
        def departmentList =FwsShop.get(fwsShopId).department
        def gongnenglist = FwsUserRole.findAllByFwsUserRoleId(id)
        def size = gongnenglist.size()

        def i = 0
        def listgongneng = []
        for (i; i < size; i++) {
            def s = gongnenglist.get(i)
            def gongnengId = s.fwsUserRoleGongNengId
            def g = FwsGongNeng.findById(gongnengId)
            print(s)
            listgongneng << g
        }
        def gongnenglistrole = FwsGongNeng.list()

        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "list")
            return
        }

        [fwsUserInstance: fwsUserInstance,name:fwsUserInstance.fwsShop.name,id: fwsShopId,departmentList:departmentList,gongnenglistrole:gongnenglistrole,listgongneng:listgongneng]
    }
    def fwsUserUpdate(Long id, Long version) {
        def fwsUserInstance = FwsUser.get(id)
           fwsUserInstance.department=Department.get(params.departmentId)
        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "fwsUserList")
            return
        }

        if (version != null) {
            if (fwsUserInstance.version > version) {
                fwsUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsUser.label', default: 'FwsUser')] as Object[],
                        "Another user has updated this FwsUser while you were editing")
                render(view: "fwsUserEdit", model: [fwsUserInstance: fwsUserInstance])
                return
            }
        }

        fwsUserInstance.properties = params

        if (!fwsUserInstance.save(flush: true)) {
            render(view: "fwsUserEdit", model: [fwsUserInstance: fwsUserInstance])
            return
        }


        def gongnenglist = params.gongneng
        def role = FwsUserRole.findAllByFwsUserRoleId(id)
        def x = 0
        for (x; x < role.size(); x++) {
            def roleId = role.get(x).id
            def roleuser = FwsUserRole.get(roleId)
            roleuser.delete()
        }
        def i = 0
        for (i; i < gongnenglist.size(); i++) {
            def userId = fwsUserInstance.id
            def gongnengId = gongnenglist[i]
            def fwsRole = new FwsUserRole()
            fwsRole.fwsUserRoleTime = new Date()
            fwsRole.fwsUserRoleGongNengId = gongnengId
            fwsRole.fwsUserRoleId = userId
            fwsRole.save()
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ybUser.label', default: 'YbUser'), fwsUserInstance.id])
        redirect(action: "fwsUserShow", id: fwsUserInstance.id)
    }
    def fwsUserDelete(Long id) {
        def fwsUserInstance = FwsUser.get(id)
        def fwsShopId= fwsUserInstance.fwsShop.id
        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "fwsUserList",id: fwsShopId)
            return
        }

        try {
            fwsUserInstance.delete()
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "fwsUserList",id: fwsShopId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "fwsUserShow", id: id)
        }
    }


    //王钧民

    //列表
    def fwsStationList(Integer max) {
        def fwsShopId = params.id
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        def fwsStationList = FwsStation.findAllByFwsShop(fwsShop)
        params.max = Math.min(max ?: 10, 100)
        [fwsStationList: fwsStationList, id: fwsShopId, name: name, fwsStationListInstanceTotal: FwsStation.countByFwsShop(fwsShop)]
    }
    //添加
    def fwsStationCreate() {
        def fwsShopId = params.id
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        [fwsStationInstance: new FwsStation(params),id: fwsShopId,name: name]
    }
    //保存
    def fwsStationSave() {
        def fwsStationInstance = new FwsStation(params)
        def fwsShopId = params.fwsShopId
        fwsStationInstance.fwsShop = FwsShop.get(fwsShopId)
        if (!fwsStationInstance.save(flush: true)) {
                render(view: "fwsStationCreate", model: [fwsStationInstance: fwsStationInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), fwsStationInstance.id])
            redirect(action: "fwsStationList", id: fwsShopId)
        }
    //查看
    def fwsStationShow(Long id) {
        def fwsStationInstance = FwsStation.get(id)
        def fwsShopId = fwsStationInstance.fwsShopId
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        if (!fwsStationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "fwsStationList")
            return
        }

        [fwsStationInstance: fwsStationInstance,name: name,id: fwsShopId]
    }
    //删除
    def fwsStationDelete(Long id) {
        def fwsStationInstance = FwsStation.get(id)
        def fwsShopId = fwsStationInstance.fwsShop.id
        if (!fwsStationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "fwsStationList",id: fwsShopId)
            return
        }

        try {
            fwsStationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "fwsStationList",id: fwsShopId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "fwsStationShow", id: id)
        }
    }
    //编辑
    def fwsStationEdit(Long id) {
        def fwsStationInstance = FwsStation.get(id)
        def fwsShopId = fwsStationInstance.fwsShopId
        def fwsShop = FwsShop.get(fwsShopId)
        def fwsStationList = FwsShop.get(fwsShopId).department
        def name = fwsShop.name
        if (!fwsStationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "fwsStationList")
            return
        }

        [fwsStationInstance: fwsStationInstance,name: name,id: fwsShopId,fwsStationList: fwsStationList]
    }
    //更新
    def fwsStationUpdate(Long id, Long version) {
        def fwsStationInstance = FwsStation.get(id)
        if (!fwsStationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "fwsStationList")
            return
        }

        if (version != null) {
            if (fwsStationInstance.version > version) {
                fwsStationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsStation.label', default: 'FwsStation')] as Object[],
                        "Another user has updated this FwsStation while you were editing")
                render(view: "fwsStationEdit", model: [fwsStationInstance: fwsStationInstance])
                return
            }
        }

        fwsStationInstance.properties = params

        if (!fwsStationInstance.save(flush: true)) {
            render(view: "fwsStationEdit", model: [fwsStationInstance: fwsStationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), fwsStationInstance.id])
        redirect(action: "fwsStationShow", id: fwsStationInstance.id)
    }








}
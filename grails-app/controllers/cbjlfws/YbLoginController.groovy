package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class YbLoginController {

    def index() {

    }
    def login(){
        def username = params.username
        def password = params.password
        def ybUser = YbUser.findByUsernameAndPassword(username,password)
        if (ybUser){
            session.user=ybUser
            redirect(action:"ybindex")
        }else (
                redirect(action: "index",msg:"您输入账号有误")
        )
    }
    def side(){
        def user = session.user
        def gongnenglist = YbRole.findAllByYbUserId(user.id)
        def size = gongnenglist.size()
        print(size)
        def i=0
        def list=[]
        for (i;i<size;i++){
            def  s =gongnenglist.get(i)
            def gongnengId = s.ybGongNengId
            def g = YbGongNeng.findById(gongnengId)
            print(s)
            list<<g
        }

        return  list
    }
    def ybindex(){
        def user = session.user
        def gongnenglist = YbRole.findAllByYbUserId(user.id)
        def size = gongnenglist.size()
        print(size)
        def i=0
        def list=[]
        for (i;i<size;i++){
            def  s =gongnenglist.get(i)
            def gongnengId = s.ybGongNengId
            def g = YbGongNeng.findById(gongnengId)
            print(s)
            list<<g
        }

        [list: list]
    }
    def ybUserList(Integer max){
        params.max = Math.min(max ?: 10, 100)
        def list = side()
        [ybUserInstanceList: YbUser.list(params), ybUserInstanceTotal: YbUser.count(),list: list]
    }
    def ybUserCreate() {
        def list = side()
        def gongnenglist = YbGongNeng.list()
        [ybUserInstance: new YbUser(params),list: list,gongnenglist:gongnenglist]
    }
    def ybUserSave(){
        def ybUserInstance = new YbUser(params)
        def gongnenglist = params.gongneng
        def i=0
        if (!ybUserInstance.save(flush: true)) {
            render(view: "create", model: [ybUserInstance: ybUserInstance])
            return
        }
        for (i;i<gongnenglist.size();i++){
           def userId = ybUserInstance.id
           def gongnengId = gongnenglist[i]
           def ybRole = new YbRole()
           ybRole.time = new Date()
           ybRole.YbGongNengId = gongnengId
           ybRole.YbUserId = userId
            ybRole.save()
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ybUser.label', default: 'YbUser'), ybUserInstance.id])
        redirect(action: "ybUserList", id: ybUserInstance.id)
    }
    def ybUsershow(Long id) {
        def list = side()
        def ybUserInstance = YbUser.get(id)
        if (!ybUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "list")
            return
        }
        def gongnenglist = YbRole.findAllByYbUserId(id)
        def size = gongnenglist.size()

        def i=0
        def listgongneng=[]
        for (i;i<size;i++){
            def  s =gongnenglist.get(i)
            def gongnengId = s.ybGongNengId
            def g = YbGongNeng.findById(gongnengId)
            print(s)
            listgongneng<<g
        }

        [ybUserInstance: ybUserInstance,list: list,listgongneng:listgongneng]
    }
    def ybUserEdit(Long id) {
        def list = side()

        def ybUserInstance = YbUser.get(id)
        if (!ybUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "list")
            return
        }
        def gongnenglist = YbRole.findAllByYbUserId(id)
        def size = gongnenglist.size()

        def i=0
        def listgongneng=[]
        for (i;i<size;i++){
            def  s =gongnenglist.get(i)
            def gongnengId = s.ybGongNengId
            def g = YbGongNeng.findById(gongnengId)
            print(s)
            listgongneng<<g
        }
        def gongnenglistrole = YbGongNeng.list()


        [ybUserInstance: ybUserInstance,list:list,listgongneng:listgongneng,gongnenglistrole:gongnenglistrole]
    }
    def ybUserUpdate(Long id, Long version) {
        def ybUserInstance = YbUser.get(id)
        if (!ybUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "ybUserList")
            return
        }

        if (version != null) {
            if (ybUserInstance.version > version) {
                ybUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ybUser.label', default: 'YbUser')] as Object[],
                        "Another user has updated this YbUser while you were editing")
                render(view: "ybUserEdit", model: [ybUserInstance: ybUserInstance])
                return
            }
        }

        ybUserInstance.properties = params

        if (!ybUserInstance.save(flush: true)) {
            render(view: "ybUserEdit", model: [ybUserInstance: ybUserInstance])
            return
        }
        def gongnenglist = params.gongneng
        def role = YbRole.findAllByYbUserId(id)
        def x=0
        for (x;x<role.size();x++){
            def roleId = role.get(x).id
              def roleuser =  YbRole.get(roleId)
            roleuser.delete()
        }
        def i=0
        for (i;i<gongnenglist.size();i++){
            def userId = ybUserInstance.id
            def gongnengId = gongnenglist[i]
            def ybRole = new YbRole()
            ybRole.time = new Date()
            ybRole.YbGongNengId = gongnengId
            ybRole.YbUserId = userId
            ybRole.save()
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ybUser.label', default: 'YbUser'), ybUserInstance.id])
        redirect(action: "ybUsershow", id: ybUserInstance.id)
    }
    def ybUserdelete(Long id) {
        def ybUserInstance = YbUser.get(id)
        if (!ybUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "ybUserList")
            return
        }

        try {
            ybUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "ybUserList")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "show", id: id)
        }
    }


    //金成柱
    //功能列表
    def ybGongNengList(Integer max){
        params.max = Math.min(max ?: 10, 100)   //分页作用
        def list = side()
        [ybGongNengInstanceList: YbGongNeng.list(params), ybGongNengInstanceTotal: YbGongNeng.count(), list:list]
    }

    def ybGongNengCreate(){
        def list = side()
        [ybGongNengInstance: new YbGongNeng(params), list: list]
    }

    def ybGongNengSave() {
        def ybGongNengInstance = new YbGongNeng(params)
        if (!ybGongNengInstance.save(flush: true)) {
            render(view: "ybGongNengCreate", model: [ybGongNengInstance: ybGongNengInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), ybGongNengInstance.id])
        redirect(action: "ybGongNengList", id: ybGongNengInstance.id)
    }

    def ybGongNengShow(Long id) {
        def list = side()
        def ybGongNengInstance = YbGongNeng.get(id)
        if (!ybGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "list")
            return
        }

        [ybGongNengInstance: ybGongNengInstance, list: list]
    }

    def ybGongNengDelete(Long id) {
//        def list = side()
        def ybGongNengInstance = YbGongNeng.get(id)
        if (!ybGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "ybGongNengList")
            return
        }

        try {
            ybGongNengInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "ybGongNengList")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "ybGongNengShow", id: id)
        }
    }

    def ybGongNengEdit(Long id) {
        def list = side()
        def ybGongNengInstance = YbGongNeng.get(id)
        if (!ybGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "list")
            return
        }

        [ybGongNengInstance: ybGongNengInstance, list: list]
    }

    def ybGongNengUpdate(Long id, Long version) {
        def ybGongNengInstance = YbGongNeng.get(id)
        if (!ybGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "ybGongNengList")
            return
        }

        if (version != null) {
            if (ybGongNengInstance.version > version) {
                ybGongNengInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ybGongNeng.label', default: 'YbGongNeng')] as Object[],
                        "Another user has updated this YbGongNeng while you were editing")
                render(view: "ybGongNengEdit", model: [ybGongNengInstance: ybGongNengInstance])
                return
            }
        }

        ybGongNengInstance.properties = params

        if (!ybGongNengInstance.save(flush: true)) {
            render(view: "ybGongNengEdit", model: [ybGongNengInstance: ybGongNengInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), ybGongNengInstance.id])
        redirect(action: "ybGongNengShow", id: ybGongNengInstance.id)
    }

















}

package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class YbLoginController {

    def index() {
        def msg =""
        msg= params.msg
        [msg:msg]
    }

    def login() {
        def username = params.username
        def password = params.password
        def ybUser = YbUser.findByUsernameAndPassword(username, password)
        if (ybUser) {
            session.user = ybUser
            redirect(action: "ybindex")
        } else (
                redirect(action: "index", msg: "您输入账号有误")
        )
    }

    def side() {
        def user = session.user
        def gongnenglist = YbRole.findAllByYbUserId(user.id)
        def size = gongnenglist.size()
        print(size)
        def i = 0
        def list = []
        for (i; i < size; i++) {
            def s = gongnenglist.get(i)
            def gongnengId = s.ybGongNengId
            def g = YbGongNeng.findById(gongnengId)
            print(s)
            list << g
        }

        return list
    }

    def ybindex() {
        def user = session.user
        def gongnenglist = YbRole.findAllByYbUserId(user.id)
        def size = gongnenglist.size()
        print(size)
        def i = 0
        def list = []
        for (i; i < size; i++) {
            def s = gongnenglist.get(i)
            def gongnengId = s.ybGongNengId
            def g = YbGongNeng.findById(gongnengId)
            print(s)
            list << g
        }

        [list: list]
    }

    def ybUserList(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def list = side()
        [ybUserInstanceList: YbUser.list(params), ybUserInstanceTotal: YbUser.count(), list: list]
    }

    def ybUserCreate() {
        def list = side()
        def gongnenglist = YbGongNeng.list()
        [ybUserInstance: new YbUser(params), list: list, gongnenglist: gongnenglist]
    }

    def ybUserSave() {
        def ybUserInstance = new YbUser(params)
        def gongnenglist = params.gongneng
        def i = 0
        if (!ybUserInstance.save(flush: true)) {
            render(view: "create", model: [ybUserInstance: ybUserInstance])
            return
        }
        for (i; i < gongnenglist.size(); i++) {
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

        def i = 0
        def listgongneng = []
        for (i; i < size; i++) {
            def s = gongnenglist.get(i)
            def gongnengId = s.ybGongNengId
            def g = YbGongNeng.findById(gongnengId)
            print(s)
            listgongneng << g
        }

        [ybUserInstance: ybUserInstance, list: list, listgongneng: listgongneng]
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

        def i = 0
        def listgongneng = []
        for (i; i < size; i++) {
            def s = gongnenglist.get(i)
            def gongnengId = s.ybGongNengId
            def g = YbGongNeng.findById(gongnengId)
            print(s)
            listgongneng << g
        }
        def gongnenglistrole = YbGongNeng.list()


        [ybUserInstance: ybUserInstance, list: list, listgongneng: listgongneng, gongnenglistrole: gongnenglistrole]
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
        def x = 0
        for (x; x < role.size(); x++) {
            def roleId = role.get(x).id
            def roleuser = YbRole.get(roleId)
            roleuser.delete()
        }
        def i = 0
        for (i; i < gongnenglist.size(); i++) {
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

    def ybClientList(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def list = side()
        [ybClientInstanceList: YbClient.list(params), ybClientInstanceTotal: YbClient.count(), list: list]
    }

    def ybClientCreate() {
        def list = side()
        [ybClientInstance: new YbClient(params), list: list]
    }

    def ybClientSave() {
        def ybClientInstance = new YbClient(params)
        if (!ybClientInstance.save(flush: true)) {
            render(view: "ybClientCreate", model: [ybClientInstance: ybClientInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ybClient.label', default: 'YbClient'), ybClientInstance.id])
        redirect(action: "ybClientList", id: ybClientInstance.id)
    }

    def ybClientShow(Long id) {
        def list = side()
        def ybClientInstance = YbClient.get(id)
        if (!ybClientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "ybClientList")
            return
        }
    }

    def ybClientDelete(Long id) {
        def ybClientInstance = YbClient.get(id)
        if (!ybClientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "ybClientList")
            return
        }


        try {
            ybClientInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "ybClientList")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "ybClientShow", id: id)
        }
    }

    def ybClientEdit(Long id) {
        def list = side()
        def ybClientInstance = YbClient.get(id)
        if (!ybClientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "list")
            return
        }

        [ybClientInstance: ybClientInstance, list: list]
    }

    def ybClientUpdate(Long id, Long version) {
        def ybClientInstance = YbClient.get(id)
        if (!ybClientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "ybClientList")
            return
        }

        if (version != null) {
            if (ybClientInstance.version > version) {
                ybClientInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ybClient.label', default: 'YbClient')] as Object[],
                        "Another user has updated this YbClient while you were editing")
                render(view: "edit", model: [ybClientInstance: ybClientInstance])
                return
            }
        }

        ybClientInstance.properties = params

        if (!ybClientInstance.save(flush: true)) {
            render(view: "ybClientEdit", model: [ybClientInstance: ybClientInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ybClient.label', default: 'YbClient'), ybClientInstance.id])
        redirect(action: "ybClientShow", id: ybClientInstance.id)
    }
        //金成柱
        //功能列表
        def ybGongNengList(Integer max) {
            params.max = Math.min(max ?: 10, 100)   //分页作用
            def list = side()
            [ybGongNengInstanceList: YbGongNeng.list(params), ybGongNengInstanceTotal: YbGongNeng.count(), list: list]
        }

        def ybGongNengCreate() {
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

//王钧民
        def ybRoleList(Integer max) {
            params.max = Math.min(max ?: 10, 100)
            def list = side()
            [ybRoleInstanceList: YbRole.list(params), ybRoleInstanceTotal: YbRole.count(), list: list]
        }
//新建用户完成后
        def ybRoleCreate() {
            def list = side()
            [ybRoleInstance: new YbRole(params), list: list]
        }
//save
        def ybRoleSave() {
            def ybRoleInstance = new YbRole(params)
            if (!ybRoleInstance.save(flush: true)) {
                render(view: "ybRoleSave", model: [ybRoleInstance: ybRoleInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ybRole.label', default: 'YbRole'), ybRoleInstance.id])
            redirect(action: "ybRoleList", id: ybRoleInstance.id)
        }

//show
        def ybRoleshow(Long id) {
            def list = side()
            def ybRoleInstance = YbRole.get(id)
            if (!ybRoleInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
                redirect(action: "list")
                return
            }

            [ybRoleInstance: ybRoleInstance, list: list]
        }
//del
        def ybRoaldelete(Long id) {
            def ybRoleInstance = YbRole.get(id)
            if (!ybRoleInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
                redirect(action: "ybRoleList")
                return
            }

            try {
                ybRoleInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
                redirect(action: "ybRoleList")
            }
            catch (DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
                redirect(action: "ybRoleshow", id: id)
            }
        }

//edit
        def ybRoaledit(Long id) {
            def list = side()
            def ybRoleInstance = YbRole.get(id)
            if (!ybRoleInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
                redirect(action: "list")
                return
            }

            [ybRoleInstance: ybRoleInstance, list: list]
        }
//update
        def ybRoleUpdate(Long id, Long version) {
            def ybRoleInstance = YbRole.get(id)
            if (!ybRoleInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
                redirect(action: "ybRoleList")
                return
            }

            if (version != null) {
                if (ybRoleInstance.version > version) {
                    ybRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ybRole.label', default: 'YbRole')] as Object[],
                            "Another user has updated this YbRole while you were editing")
                    render(view: "ybRoaledit", model: [ybRoleInstance: ybRoleInstance])
                    return
                }
            }

            ybRoleInstance.properties = params

            if (!ybRoleInstance.save(flush: true)) {
                render(view: "ybRoaledit", model: [ybRoleInstance: ybRoleInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ybRole.label', default: 'YbRole'), ybRoleInstance.id])
            redirect(action: "ybRoleshow", id: ybRoleInstance.id)
        }

//金成柱 服务商功能
    //列表
    def fwsGongNengList(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def list = side()
        [fwsGongNengInstanceList: FwsGongNeng.list(params), fwsGongNengInstanceTotal: FwsGongNeng.count(), list: list]
    }

    //添加
    def fwsGongNengCreate() {
        def list = side()
        [fwsGongNengInstance: new FwsGongNeng(params), list: list]
    }

    //保存
    def fwsGongNengSave() {
        def fwsGongNengInstance = new FwsGongNeng(params)
        if (!fwsGongNengInstance.save(flush: true)) {
            render(view: "fwsGongNengCreate", model: [fwsGongNengInstance: fwsGongNengInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), fwsGongNengInstance.id])
        redirect(action: "fwsGongNengList", id: fwsGongNengInstance.id)
    }

    //查看
    def fwsGongNengShow(Long id) {
        def list = side()
        def fwsGongNengInstance = FwsGongNeng.get(id)
        if (!fwsGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "list")
            return
        }

        [fwsGongNengInstance: fwsGongNengInstance, list: list]
    }

    //删除
    def fwsGongNengDelete(Long id) {
        def fwsGongNengInstance = FwsGongNeng.get(id)
        if (!fwsGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "fwsGongNengList")
            return
        }

        try {
            fwsGongNengInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "fwsGongNengList")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "fwsGongNengShow", id: id)
        }
    }

    //编辑
    def fwsGongNengEdit(Long id) {
        def list = side()
        def fwsGongNengInstance = FwsGongNeng.get(id)
        if (!fwsGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "list")
            return
        }

        [fwsGongNengInstance: fwsGongNengInstance, list: list]
    }

    //更新
    def fwsGongNengUpdate(Long id, Long version) {
        def fwsGongNengInstance = FwsGongNeng.get(id)
        if (!fwsGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "fwsGongNengList")
            return
        }

        if (version != null) {
            if (fwsGongNengInstance.version > version) {
                fwsGongNengInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng')] as Object[],
                        "Another user has updated this FwsGongNeng while you were editing")
                render(view: "fwsGongNengEdit", model: [fwsGongNengInstance: fwsGongNengInstance])
                return
            }
        }

        fwsGongNengInstance.properties = params

        if (!fwsGongNengInstance.save(flush: true)) {
            render(view: "fwsGongNengEdit", model: [fwsGongNengInstance: fwsGongNengInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), fwsGongNengInstance.id])
        redirect(action: "fwsGongNengShow", id: fwsGongNengInstance.id)
    }

    //王钧民
    def fwsUserRolelist(Integer max) {

        params.max = Math.min(max ?: 10, 100)
        def list = side()
        [fwsUserRoleInstanceList: FwsUserRole.list(params), fwsUserRoleInstanceTotal: FwsUserRole.count(),list: list]
    }
    def fwsRoleCreate() {
        def list = side()
        [fwsUserRoleInstance: new FwsUserRole(params),list: list]
    }
    def fwsUserRolesave() {
        def fwsUserRoleInstance = new FwsUserRole(params)
        if (!fwsUserRoleInstance.save(flush: true)) {
            render(view: "fwsRoleCreate", model: [fwsUserRoleInstance: fwsUserRoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), fwsUserRoleInstance.id])
        redirect(action: "fwsUserRolelist", id: fwsUserRoleInstance.id)
    }
    def fwsUserRoleshow(Long id) {
        def list = side()
        def fwsUserRoleInstance = FwsUserRole.get(id)
        if (!fwsUserRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "list")
            return
        }

        [fwsUserRoleInstance: fwsUserRoleInstance,list: list]
    }
    def fwsUserdelete(Long id) {
        def fwsUserRoleInstance = FwsUserRole.get(id)
        if (!fwsUserRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "fwsUserRolelist")
            return
        }

        try {
            fwsUserRoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "fwsUserRolelist")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "fwsUserRoleshow", id: id)
        }
    }
    def fwsUserRoleedit(Long id) {
        def list = side()
        def fwsUserRoleInstance = FwsUserRole.get(id)
        if (!fwsUserRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "list")
            return
        }

        [fwsUserRoleInstance: fwsUserRoleInstance,list: list]
    }
    def fwsUserRoleupdate(Long id, Long version) {
        def fwsUserRoleInstance = FwsUserRole.get(id)
        if (!fwsUserRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "fwsUserRolelist")
            return
        }

        if (version != null) {
            if (fwsUserRoleInstance.version > version) {
                fwsUserRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsUserRole.label', default: 'FwsUserRole')] as Object[],
                        "Another user has updated this FwsUserRole while you were editing")
                render(view: "fwsUserRoleedit", model: [fwsUserRoleInstance: fwsUserRoleInstance])
                return
            }
        }

        fwsUserRoleInstance.properties = params

        if (!fwsUserRoleInstance.save(flush: true)) {
            render(view: "fwsUserRoleedit", model: [fwsUserRoleInstance: fwsUserRoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), fwsUserRoleInstance.id])
        redirect(action: "fwsUserRoleshow", id: fwsUserRoleInstance.id)
    }



}


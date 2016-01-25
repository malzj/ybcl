package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class FwsBossController {

    def index() {
        def msga = ""
        def p = params.id

        if (p == '1') {
            msga = "您输入账户有误"
        } else {
            msga = "您的公司账号已过期"
        }
        [msg: msga]
    }

    def login() {
        def username = params.username
        def password = params.password
        def ybClient = YbClient.findByUsernameAndPassword(username, password)
        if (ybClient) {
            session.ybClient = ybClient
            def date = new Date()
            if (date <= ybClient.overTime && date >= ybClient.beginTime) {
                redirect(action: "fwsShopList")
            } else {
                redirect(action: "index", msg: "您的公司账号已过期", id: 2)
            }
        } else {
            redirect(action: "index", id: 1)
        }
    }

    def fwsShopList() {
        def ybClient = session.ybClient
        def fwsShopList = FwsShop.findAllByYbClient(ybClient)
        def msg = ""
        if (params.id == "1") {
            msg = "您需要再次购买服务商店"
        }
        [fwsShopList: fwsShopList, msg: msg]
    }

    def fwsShopCreate() {
        [fwsShopInstance: new FwsShop(params)]
    }

    def fwsShopSave() {
        def ybClient = session.ybClient
        def fwsShopList = FwsShop.findAllByYbClient(ybClient)
        if (ybClient.number > fwsShopList.size()) {
            def fwsShopInstance = new FwsShop(params)
            fwsShopInstance.ybClient = session.ybClient
            if (!fwsShopInstance.save(flush: true)) {
                render(view: "fwsShopCreate", model: [fwsShopInstance: fwsShopInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), fwsShopInstance.id])
            redirect(action: "fwsShopList", id: fwsShopInstance.id)
        } else {
            redirect(action: "fwsShopList", id: "1")
        }

    }

    def fwsUserList() {
        //获取服务商ID
        def fwsShopId = params.id
        //获取服务商对象
        def fwsShop = FwsShop.get(fwsShopId)
        //获取服务商属性名字
        def name = fwsShop.name
        //通过服务商对象获取服务商list
        def fwsUserList = FwsUser.findAllByFwsShop(fwsShop)
        [fwsUserList: fwsUserList, id: fwsShopId, name: name, fwsUserInstanceTotal: FwsUser.countByFwsShop(fwsShop)]
    }

    def fwsUserCreate() {
        def fwsShopId = params.id
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        def gongnenglist = FwsGongNeng.list()
        def departmentList = fwsShop.department
        [fwsUserInstance: new FwsUser(params), id: fwsShopId, name: name, gongnenglist: gongnenglist, departmentList: departmentList]
    }

    def fwsUserSave() {
        //创建新对象
        def fwsUserInstance = new FwsUser(params)
        //获取功能列表
        def gongnenglist = params.gongneng
        //获取部门id
        def departmentId = params.departmentId
        //获取服务商Id
        def fwsShopId = params.fwsShopId
        //新建对象的服务商绑定
        fwsUserInstance.fwsShop = FwsShop.get(fwsShopId)
        //新建对象的部门绑定
        fwsUserInstance.department = Department.get(departmentId)
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
        redirect(action: "fwsUserList", id: fwsShopId)
    }

    def fwsUserShow(Long id) {
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

        [fwsUserInstance: fwsUserInstance, id: fwsUserInstance.fwsShop.id, name: name, listgongneng: listgongneng]
    }

    def fwsUserEdit(Long id) {
        def fwsUserInstance = FwsUser.get(id)
        def fwsShopId = fwsUserInstance.fwsShopId
        def departmentList = FwsShop.get(fwsShopId).department
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

        [fwsUserInstance: fwsUserInstance, name: fwsUserInstance.fwsShop.name, id: fwsShopId, departmentList: departmentList, gongnenglistrole: gongnenglistrole, listgongneng: listgongneng]
    }

    def fwsUserUpdate(Long id, Long version) {
        def fwsUserInstance = FwsUser.get(id)
        fwsUserInstance.department = Department.get(params.departmentId)
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
        def fwsShopId = fwsUserInstance.fwsShop.id
        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "fwsUserList", id: fwsShopId)
            return
        }

        try {
            fwsUserInstance.delete()
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "fwsUserList", id: fwsShopId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "fwsUserShow", id: id)
        }
    }

    //金成柱

    //列表
    def fwsDepartmentList(Integer max) {
        def fwsShopId = params.id
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        def fwsDepartmentList = Department.findAllByFwsShop(fwsShop)
        params.max = Math.min(max ?: 10, 100)
        [fwsDepartmentList: fwsDepartmentList, id: fwsShopId, name: name, departmentInstanceTotal: Department.countByFwsShop(fwsShop)]
    }

    //添加
    def fwsDepartmentCreate() {
        def fwsShopId = params.id
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        [departmentInstance: new Department(params), id: fwsShopId, name: name]
    }

    //保存
    def fwsDepartmentSave() {
        def departmentInstance = new Department(params)
        def fwsShopId = params.fwsShopId
        //为什么要有这句话?!
        departmentInstance.fwsShop = FwsShop.get(fwsShopId)
        if (!departmentInstance.save(flush: true)) {
            render(view: "fwsDepartmentCreate", model: [departmentInstance: departmentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'department.label', default: 'Department'), departmentInstance.id])
        redirect(action: "fwsDepartmentList", id: fwsShopId)
    }

    //查看
    def fwsDepartmentShow(Long id) {
        def departmentInstance = Department.get(id)
        def fwsShopId = departmentInstance.fwsShopId
        def fwsShop = FwsShop.get(fwsShopId)
        def name = fwsShop.name
        if (!departmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'department.label', default: 'Department'), id])
            redirect(action: "fwsDepartmentList")
            return
        }

        [departmentInstance: departmentInstance, name: name, id: fwsShopId]
    }

    //删除
    def fwsDepartmentDelete(Long id) {
        def departmentInstance = Department.get(id)
        def fwsShopId = departmentInstance.fwsShop.id
        if (!departmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'department.label', default: 'Department'), id])
            redirect(action: "fwsDepartmentList", id: fwsShopId)
            return
        }

        try {
            departmentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'department.label', default: 'Department'), id])
            redirect(action: "fwsDepartmentList", id: fwsShopId)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'department.label', default: 'Department'), id])
            redirect(action: "fwsDepartmentShow", id: id)
        }
    }

    //编辑
    def fwsDepartmentEdit(Long id) {
        def departmentInstance = Department.get(id)
        def fwsShopId = departmentInstance.fwsShopId
        def fwsShop = FwsShop.get(fwsShopId)
        def departmentList = FwsShop.get(fwsShopId).department
        def name = fwsShop.name
        if (!departmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'department.label', default: 'Department'), id])
            redirect(action: "fwsDepartmentList")
            return
        }

        [departmentInstance: departmentInstance, name: name, id: fwsShopId, departmentList: departmentList]
    }

    //更新
    def fwsDepartmentUpdate(Long id, Long version) {
        def departmentInstance = Department.get(id)
        if (!departmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'department.label', default: 'Department'), id])
            redirect(action: "fwsDepartmentList")
            return
        }

        if (version != null) {
            if (departmentInstance.version > version) {
                departmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'department.label', default: 'Department')] as Object[],
                        "Another user has updated this Department while you were editing")
                render(view: "fwsDepartmentEdit", model: [departmentInstance: departmentInstance])
                return
            }
        }

        departmentInstance.properties = params

        if (!departmentInstance.save(flush: true)) {
            render(view: "fwsDepartmentEdit", model: [departmentInstance: departmentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'department.label', default: 'Department'), departmentInstance.id])
        redirect(action: "fwsDepartmentShow", id: departmentInstance.id)
    }
}
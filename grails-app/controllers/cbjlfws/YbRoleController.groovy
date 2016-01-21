package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class YbRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ybRoleInstanceList: YbRole.list(params), ybRoleInstanceTotal: YbRole.count()]
    }
//方法需要复制
    def create() {
        [ybRoleInstance: new YbRole(params)]
    }

    def save() {
        def ybRoleInstance = new YbRole(params)
        if (!ybRoleInstance.save(flush: true)) {
            render(view: "create", model: [ybRoleInstance: ybRoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ybRole.label', default: 'YbRole'), ybRoleInstance.id])
        redirect(action: "show", id: ybRoleInstance.id)
    }

    def show(Long id) {
        def ybRoleInstance = YbRole.get(id)
        if (!ybRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
            redirect(action: "list")
            return
        }

        [ybRoleInstance: ybRoleInstance]
    }

    def edit(Long id) {
        def ybRoleInstance = YbRole.get(id)
        if (!ybRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
            redirect(action: "list")
            return
        }

        [ybRoleInstance: ybRoleInstance]
    }

    def update(Long id, Long version) {
        def ybRoleInstance = YbRole.get(id)
        if (!ybRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ybRoleInstance.version > version) {
                ybRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ybRole.label', default: 'YbRole')] as Object[],
                        "Another user has updated this YbRole while you were editing")
                render(view: "edit", model: [ybRoleInstance: ybRoleInstance])
                return
            }
        }

        ybRoleInstance.properties = params

        if (!ybRoleInstance.save(flush: true)) {
            render(view: "edit", model: [ybRoleInstance: ybRoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ybRole.label', default: 'YbRole'), ybRoleInstance.id])
        redirect(action: "show", id: ybRoleInstance.id)
    }

    def delete(Long id) {
        def ybRoleInstance = YbRole.get(id)
        if (!ybRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
            redirect(action: "list")
            return
        }

        try {
            ybRoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ybRole.label', default: 'YbRole'), id])
            redirect(action: "show", id: id)
        }
    }
}

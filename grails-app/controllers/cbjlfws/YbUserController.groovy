package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class YbUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ybUserInstanceList: YbUser.list(params), ybUserInstanceTotal: YbUser.count()]
    }

    def create() {
        [ybUserInstance: new YbUser(params)]
    }

    def save() {
        def ybUserInstance = new YbUser(params)
        if (!ybUserInstance.save(flush: true)) {
            render(view: "create", model: [ybUserInstance: ybUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ybUser.label', default: 'YbUser'), ybUserInstance.id])
        redirect(action: "show", id: ybUserInstance.id)
    }

    def show(Long id) {
        def ybUserInstance = YbUser.get(id)
        if (!ybUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "list")
            return
        }

        [ybUserInstance: ybUserInstance]
    }

    def edit(Long id) {
        def ybUserInstance = YbUser.get(id)
        if (!ybUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "list")
            return
        }

        [ybUserInstance: ybUserInstance]
    }

    def update(Long id, Long version) {
        def ybUserInstance = YbUser.get(id)
        if (!ybUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ybUserInstance.version > version) {
                ybUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ybUser.label', default: 'YbUser')] as Object[],
                          "Another user has updated this YbUser while you were editing")
                render(view: "edit", model: [ybUserInstance: ybUserInstance])
                return
            }
        }

        ybUserInstance.properties = params

        if (!ybUserInstance.save(flush: true)) {
            render(view: "edit", model: [ybUserInstance: ybUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ybUser.label', default: 'YbUser'), ybUserInstance.id])
        redirect(action: "show", id: ybUserInstance.id)
    }

    def delete(Long id) {
        def ybUserInstance = YbUser.get(id)
        if (!ybUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "list")
            return
        }

        try {
            ybUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ybUser.label', default: 'YbUser'), id])
            redirect(action: "show", id: id)
        }
    }
}

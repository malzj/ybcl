package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class FwsUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fwsUserInstanceList: FwsUser.list(params), fwsUserInstanceTotal: FwsUser.count()]
    }

    def create() {
        [fwsUserInstance: new FwsUser(params)]
    }

    def save() {
        def fwsUserInstance = new FwsUser(params)
        if (!fwsUserInstance.save(flush: true)) {
            render(view: "create", model: [fwsUserInstance: fwsUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), fwsUserInstance.id])
        redirect(action: "show", id: fwsUserInstance.id)
    }

    def show(Long id) {
        def fwsUserInstance = FwsUser.get(id)
        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "list")
            return
        }

        [fwsUserInstance: fwsUserInstance]
    }

    def edit(Long id) {
        def fwsUserInstance = FwsUser.get(id)
        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "list")
            return
        }

        [fwsUserInstance: fwsUserInstance]
    }

    def update(Long id, Long version) {
        def fwsUserInstance = FwsUser.get(id)
        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fwsUserInstance.version > version) {
                fwsUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsUser.label', default: 'FwsUser')] as Object[],
                        "Another user has updated this FwsUser while you were editing")
                render(view: "edit", model: [fwsUserInstance: fwsUserInstance])
                return
            }
        }

        fwsUserInstance.properties = params

        if (!fwsUserInstance.save(flush: true)) {
            render(view: "edit", model: [fwsUserInstance: fwsUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), fwsUserInstance.id])
        redirect(action: "show", id: fwsUserInstance.id)
    }

    def delete(Long id) {
        def fwsUserInstance = FwsUser.get(id)
        if (!fwsUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "list")
            return
        }

        try {
            fwsUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsUser.label', default: 'FwsUser'), id])
            redirect(action: "show", id: id)
        }
    }
}

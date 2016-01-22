package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class FwsUserRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fwsUserRoleInstanceList: FwsUserRole.list(params), fwsUserRoleInstanceTotal: FwsUserRole.count()]
    }

    def create() {
        [fwsUserRoleInstance: new FwsUserRole(params)]
    }

    def save() {
        def fwsUserRoleInstance = new FwsUserRole(params)
        if (!fwsUserRoleInstance.save(flush: true)) {
            render(view: "create", model: [fwsUserRoleInstance: fwsUserRoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), fwsUserRoleInstance.id])
        redirect(action: "show", id: fwsUserRoleInstance.id)
    }

    def show(Long id) {
        def fwsUserRoleInstance = FwsUserRole.get(id)
        if (!fwsUserRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "list")
            return
        }

        [fwsUserRoleInstance: fwsUserRoleInstance]
    }

    def edit(Long id) {
        def fwsUserRoleInstance = FwsUserRole.get(id)
        if (!fwsUserRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "list")
            return
        }

        [fwsUserRoleInstance: fwsUserRoleInstance]
    }

    def update(Long id, Long version) {
        def fwsUserRoleInstance = FwsUserRole.get(id)
        if (!fwsUserRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fwsUserRoleInstance.version > version) {
                fwsUserRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsUserRole.label', default: 'FwsUserRole')] as Object[],
                        "Another user has updated this FwsUserRole while you were editing")
                render(view: "edit", model: [fwsUserRoleInstance: fwsUserRoleInstance])
                return
            }
        }

        fwsUserRoleInstance.properties = params

        if (!fwsUserRoleInstance.save(flush: true)) {
            render(view: "edit", model: [fwsUserRoleInstance: fwsUserRoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), fwsUserRoleInstance.id])
        redirect(action: "show", id: fwsUserRoleInstance.id)
    }

    def delete(Long id) {
        def fwsUserRoleInstance = FwsUserRole.get(id)
        if (!fwsUserRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "list")
            return
        }

        try {
            fwsUserRoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsUserRole.label', default: 'FwsUserRole'), id])
            redirect(action: "show", id: id)
        }
    }
}

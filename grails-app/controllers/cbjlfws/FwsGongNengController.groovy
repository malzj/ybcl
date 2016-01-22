package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class FwsGongNengController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fwsGongNengInstanceList: FwsGongNeng.list(params), fwsGongNengInstanceTotal: FwsGongNeng.count()]
    }

    def create() {
        [fwsGongNengInstance: new FwsGongNeng(params)]
    }

    def save() {
        def fwsGongNengInstance = new FwsGongNeng(params)
        if (!fwsGongNengInstance.save(flush: true)) {
            render(view: "create", model: [fwsGongNengInstance: fwsGongNengInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), fwsGongNengInstance.id])
        redirect(action: "show", id: fwsGongNengInstance.id)
    }

    def show(Long id) {
        def fwsGongNengInstance = FwsGongNeng.get(id)
        if (!fwsGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "list")
            return
        }

        [fwsGongNengInstance: fwsGongNengInstance]
    }

    def edit(Long id) {
        def fwsGongNengInstance = FwsGongNeng.get(id)
        if (!fwsGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "list")
            return
        }

        [fwsGongNengInstance: fwsGongNengInstance]
    }

    def update(Long id, Long version) {
        def fwsGongNengInstance = FwsGongNeng.get(id)
        if (!fwsGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fwsGongNengInstance.version > version) {
                fwsGongNengInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng')] as Object[],
                        "Another user has updated this FwsGongNeng while you were editing")
                render(view: "edit", model: [fwsGongNengInstance: fwsGongNengInstance])
                return
            }
        }

        fwsGongNengInstance.properties = params

        if (!fwsGongNengInstance.save(flush: true)) {
            render(view: "edit", model: [fwsGongNengInstance: fwsGongNengInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), fwsGongNengInstance.id])
        redirect(action: "show", id: fwsGongNengInstance.id)
    }

    def delete(Long id) {
        def fwsGongNengInstance = FwsGongNeng.get(id)
        if (!fwsGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "list")
            return
        }

        try {
            fwsGongNengInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsGongNeng.label', default: 'FwsGongNeng'), id])
            redirect(action: "show", id: id)
        }
    }
}

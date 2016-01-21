package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class FwsShopController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fwsShopInstanceList: FwsShop.list(params), fwsShopInstanceTotal: FwsShop.count()]
    }

    def create() {
        [fwsShopInstance: new FwsShop(params)]
    }

    def save() {
        def fwsShopInstance = new FwsShop(params)
        if (!fwsShopInstance.save(flush: true)) {
            render(view: "create", model: [fwsShopInstance: fwsShopInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), fwsShopInstance.id])
        redirect(action: "show", id: fwsShopInstance.id)
    }

    def show(Long id) {
        def fwsShopInstance = FwsShop.get(id)
        if (!fwsShopInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), id])
            redirect(action: "list")
            return
        }

        [fwsShopInstance: fwsShopInstance]
    }

    def edit(Long id) {
        def fwsShopInstance = FwsShop.get(id)
        if (!fwsShopInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), id])
            redirect(action: "list")
            return
        }

        [fwsShopInstance: fwsShopInstance]
    }

    def update(Long id, Long version) {
        def fwsShopInstance = FwsShop.get(id)
        if (!fwsShopInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fwsShopInstance.version > version) {
                fwsShopInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsShop.label', default: 'FwsShop')] as Object[],
                        "Another user has updated this FwsShop while you were editing")
                render(view: "edit", model: [fwsShopInstance: fwsShopInstance])
                return
            }
        }

        fwsShopInstance.properties = params

        if (!fwsShopInstance.save(flush: true)) {
            render(view: "edit", model: [fwsShopInstance: fwsShopInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), fwsShopInstance.id])
        redirect(action: "show", id: fwsShopInstance.id)
    }

    def delete(Long id) {
        def fwsShopInstance = FwsShop.get(id)
        if (!fwsShopInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), id])
            redirect(action: "list")
            return
        }

        try {
            fwsShopInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsShop.label', default: 'FwsShop'), id])
            redirect(action: "show", id: id)
        }
    }
}

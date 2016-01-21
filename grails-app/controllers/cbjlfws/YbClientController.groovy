package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class YbClientController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ybClientInstanceList: YbClient.list(params), ybClientInstanceTotal: YbClient.count()]
    }

    def create() {
        [ybClientInstance: new YbClient(params)]
    }

    def save() {
        def ybClientInstance = new YbClient(params)
        if (!ybClientInstance.save(flush: true)) {
            render(view: "create", model: [ybClientInstance: ybClientInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ybClient.label', default: 'YbClient'), ybClientInstance.id])
        redirect(action: "show", id: ybClientInstance.id)
    }

    def show(Long id) {
        def ybClientInstance = YbClient.get(id)
        if (!ybClientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "list")
            return
        }

        [ybClientInstance: ybClientInstance]
    }

    def edit(Long id) {
        def ybClientInstance = YbClient.get(id)
        if (!ybClientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "list")
            return
        }

        [ybClientInstance: ybClientInstance]
    }

    def update(Long id, Long version) {
        def ybClientInstance = YbClient.get(id)
        if (!ybClientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "list")
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
            render(view: "edit", model: [ybClientInstance: ybClientInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ybClient.label', default: 'YbClient'), ybClientInstance.id])
        redirect(action: "show", id: ybClientInstance.id)
    }

    def delete(Long id) {
        def ybClientInstance = YbClient.get(id)
        if (!ybClientInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "list")
            return
        }

        try {
            ybClientInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ybClient.label', default: 'YbClient'), id])
            redirect(action: "show", id: id)
        }
    }
}

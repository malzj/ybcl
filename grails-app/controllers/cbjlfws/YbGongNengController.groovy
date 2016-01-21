package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class YbGongNengController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ybGongNengInstanceList: YbGongNeng.list(params), ybGongNengInstanceTotal: YbGongNeng.count()]
    }

    def create() {
        [ybGongNengInstance: new YbGongNeng(params)]
    }

    def save() {
        def ybGongNengInstance = new YbGongNeng(params)
        if (!ybGongNengInstance.save(flush: true)) {
            render(view: "create", model: [ybGongNengInstance: ybGongNengInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), ybGongNengInstance.id])
        redirect(action: "show", id: ybGongNengInstance.id)
    }

    def show(Long id) {
        def ybGongNengInstance = YbGongNeng.get(id)
        if (!ybGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "list")
            return
        }

        [ybGongNengInstance: ybGongNengInstance]
    }

    def edit(Long id) {
        def ybGongNengInstance = YbGongNeng.get(id)
        if (!ybGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "list")
            return
        }

        [ybGongNengInstance: ybGongNengInstance]
    }

    def update(Long id, Long version) {
        def ybGongNengInstance = YbGongNeng.get(id)
        if (!ybGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ybGongNengInstance.version > version) {
                ybGongNengInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ybGongNeng.label', default: 'YbGongNeng')] as Object[],
                        "Another user has updated this YbGongNeng while you were editing")
                render(view: "edit", model: [ybGongNengInstance: ybGongNengInstance])
                return
            }
        }

        ybGongNengInstance.properties = params

        if (!ybGongNengInstance.save(flush: true)) {
            render(view: "edit", model: [ybGongNengInstance: ybGongNengInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), ybGongNengInstance.id])
        redirect(action: "show", id: ybGongNengInstance.id)
    }

    def delete(Long id) {
        def ybGongNengInstance = YbGongNeng.get(id)
        if (!ybGongNengInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "list")
            return
        }

        try {
            ybGongNengInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ybGongNeng.label', default: 'YbGongNeng'), id])
            redirect(action: "show", id: id)
        }
    }
}

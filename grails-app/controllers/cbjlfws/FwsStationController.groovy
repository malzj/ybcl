package cbjlfws

import org.springframework.dao.DataIntegrityViolationException

class FwsStationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fwsStationInstanceList: FwsStation.list(params), fwsStationInstanceTotal: FwsStation.count()]
    }

    def create() {
        [fwsStationInstance: new FwsStation(params)]
    }

    def save() {
        def fwsStationInstance = new FwsStation(params)
        if (!fwsStationInstance.save(flush: true)) {
            render(view: "create", model: [fwsStationInstance: fwsStationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), fwsStationInstance.id])
        redirect(action: "show", id: fwsStationInstance.id)
    }

    def show(Long id) {
        def fwsStationInstance = FwsStation.get(id)
        if (!fwsStationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "list")
            return
        }

        [fwsStationInstance: fwsStationInstance]
    }

    def edit(Long id) {
        def fwsStationInstance = FwsStation.get(id)
        if (!fwsStationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "list")
            return
        }

        [fwsStationInstance: fwsStationInstance]
    }

    def update(Long id, Long version) {
        def fwsStationInstance = FwsStation.get(id)
        if (!fwsStationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fwsStationInstance.version > version) {
                fwsStationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fwsStation.label', default: 'FwsStation')] as Object[],
                        "Another user has updated this FwsStation while you were editing")
                render(view: "edit", model: [fwsStationInstance: fwsStationInstance])
                return
            }
        }

        fwsStationInstance.properties = params

        if (!fwsStationInstance.save(flush: true)) {
            render(view: "edit", model: [fwsStationInstance: fwsStationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), fwsStationInstance.id])
        redirect(action: "show", id: fwsStationInstance.id)
    }

    def delete(Long id) {
        def fwsStationInstance = FwsStation.get(id)
        if (!fwsStationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "list")
            return
        }

        try {
            fwsStationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fwsStation.label', default: 'FwsStation'), id])
            redirect(action: "show", id: id)
        }
    }
}

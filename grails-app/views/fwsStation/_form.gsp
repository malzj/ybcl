<%@ page import="cbjlfws.FwsStation" %>



<div class="fieldcontain ${hasErrors(bean: fwsStationInstance, field: 'fwsShop', 'error')} required">
	<label for="fwsShop">
		<g:message code="fwsStation.fwsShop.label" default="Fws Shop" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fwsShop" name="fwsShop.id" from="${cbjlfws.FwsShop.list()}" optionKey="id" required="" value="${fwsStationInstance?.fwsShop?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsStationInstance, field: 'number', 'error')} required">
	<label for="number">
		<g:message code="fwsStation.number.label" default="Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="number" type="number" value="${fwsStationInstance.number}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsStationInstance, field: 'stationName', 'error')} ">
	<label for="stationName">
		<g:message code="fwsStation.stationName.label" default="Station Name" />
		
	</label>
	<g:textField name="stationName" value="${fwsStationInstance?.stationName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsStationInstance, field: 'stationNumber', 'error')} required">
	<label for="stationNumber">
		<g:message code="fwsStation.stationNumber.label" default="Station Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="stationNumber" type="number" value="${fwsStationInstance.stationNumber}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsStationInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="fwsStation.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${fwsStationInstance?.time}"  />
</div>


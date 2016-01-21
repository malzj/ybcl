
<%@ page import="cbjlfws.FwsStation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsStation.label', default: 'FwsStation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fwsStation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fwsStation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fwsStation">
			
				<g:if test="${fwsStationInstance?.fwsShop}">
				<li class="fieldcontain">
					<span id="fwsShop-label" class="property-label"><g:message code="fwsStation.fwsShop.label" default="Fws Shop" /></span>
					
						<span class="property-value" aria-labelledby="fwsShop-label"><g:link controller="fwsShop" action="show" id="${fwsStationInstance?.fwsShop?.id}">${fwsStationInstance?.fwsShop?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsStationInstance?.number}">
				<li class="fieldcontain">
					<span id="number-label" class="property-label"><g:message code="fwsStation.number.label" default="Number" /></span>
					
						<span class="property-value" aria-labelledby="number-label"><g:fieldValue bean="${fwsStationInstance}" field="number"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsStationInstance?.stationName}">
				<li class="fieldcontain">
					<span id="stationName-label" class="property-label"><g:message code="fwsStation.stationName.label" default="Station Name" /></span>
					
						<span class="property-value" aria-labelledby="stationName-label"><g:fieldValue bean="${fwsStationInstance}" field="stationName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsStationInstance?.stationNumber}">
				<li class="fieldcontain">
					<span id="stationNumber-label" class="property-label"><g:message code="fwsStation.stationNumber.label" default="Station Number" /></span>
					
						<span class="property-value" aria-labelledby="stationNumber-label"><g:fieldValue bean="${fwsStationInstance}" field="stationNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsStationInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="fwsStation.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${fwsStationInstance?.time}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fwsStationInstance?.id}" />
					<g:link class="edit" action="edit" id="${fwsStationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>


<%@ page import="cbjlfws.FwsStation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsStation.label', default: 'FwsStation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fwsStation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fwsStation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="fwsStation.fwsShop.label" default="Fws Shop" /></th>
					
						<g:sortableColumn property="number" title="${message(code: 'fwsStation.number.label', default: 'Number')}" />
					
						<g:sortableColumn property="stationName" title="${message(code: 'fwsStation.stationName.label', default: 'Station Name')}" />
					
						<g:sortableColumn property="stationNumber" title="${message(code: 'fwsStation.stationNumber.label', default: 'Station Number')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'fwsStation.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fwsStationInstanceList}" status="i" var="fwsStationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fwsStationInstance.id}">${fieldValue(bean: fwsStationInstance, field: "fwsShop")}</g:link></td>
					
						<td>${fieldValue(bean: fwsStationInstance, field: "number")}</td>
					
						<td>${fieldValue(bean: fwsStationInstance, field: "stationName")}</td>
					
						<td>${fieldValue(bean: fwsStationInstance, field: "stationNumber")}</td>
					
						<td><g:formatDate date="${fwsStationInstance.time}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fwsStationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

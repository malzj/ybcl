
<%@ page import="cbjlfws.YbClient" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ybClient.label', default: 'YbClient')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ybClient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ybClient" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="beginTime" title="${message(code: 'ybClient.beginTime.label', default: 'Begin Time')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'ybClient.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="number" title="${message(code: 'ybClient.number.label', default: 'Number')}" />
					
						<g:sortableColumn property="overTime" title="${message(code: 'ybClient.overTime.label', default: 'Over Time')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'ybClient.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'ybClient.phone.label', default: 'Phone')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ybClientInstanceList}" status="i" var="ybClientInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ybClientInstance.id}">${fieldValue(bean: ybClientInstance, field: "beginTime")}</g:link></td>
					
						<td>${fieldValue(bean: ybClientInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: ybClientInstance, field: "number")}</td>
					
						<td><g:formatDate date="${ybClientInstance.overTime}" /></td>
					
						<td>${fieldValue(bean: ybClientInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: ybClientInstance, field: "phone")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ybClientInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

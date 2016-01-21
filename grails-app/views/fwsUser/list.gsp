
<%@ page import="cbjlfws.FwsUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsUser.label', default: 'FwsUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fwsUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fwsUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="fwsUser.fwsShop.label" default="Fws Shop" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'fwsUser.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'fwsUser.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'fwsUser.phone.label', default: 'Phone')}" />
					
						<g:sortableColumn property="position" title="${message(code: 'fwsUser.position.label', default: 'Position')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'fwsUser.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fwsUserInstanceList}" status="i" var="fwsUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fwsUserInstance.id}">${fieldValue(bean: fwsUserInstance, field: "fwsShop")}</g:link></td>
					
						<td>${fieldValue(bean: fwsUserInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: fwsUserInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: fwsUserInstance, field: "phone")}</td>
					
						<td>${fieldValue(bean: fwsUserInstance, field: "position")}</td>
					
						<td><g:formatDate date="${fwsUserInstance.time}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fwsUserInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

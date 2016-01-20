
<%@ page import="cbjlfws.YbUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ybUser.label', default: 'YbUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ybUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ybUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'ybUser.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'ybUser.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'ybUser.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'ybUser.phone.label', default: 'Phone')}" />
					
						<g:sortableColumn property="position" title="${message(code: 'ybUser.position.label', default: 'Position')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'ybUser.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ybUserInstanceList}" status="i" var="ybUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ybUserInstance.id}">${fieldValue(bean: ybUserInstance, field: "username")}</g:link></td>
					
						<td>${fieldValue(bean: ybUserInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: ybUserInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: ybUserInstance, field: "phone")}</td>
					
						<td>${fieldValue(bean: ybUserInstance, field: "position")}</td>
					
						<td><g:formatDate date="${ybUserInstance.time}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ybUserInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

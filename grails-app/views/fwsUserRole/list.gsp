
<%@ page import="cbjlfws.FwsUserRole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsUserRole.label', default: 'FwsUserRole')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fwsUserRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fwsUserRole" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fwsUserRoleGongNengId" title="${message(code: 'fwsUserRole.fwsUserRoleGongNengId.label', default: 'Fws User Role Gong Neng Id')}" />
					
						<g:sortableColumn property="fwsUserRoleId" title="${message(code: 'fwsUserRole.fwsUserRoleId.label', default: 'Fws User Role Id')}" />
					
						<g:sortableColumn property="fwsUserRoleTime" title="${message(code: 'fwsUserRole.fwsUserRoleTime.label', default: 'Fws User Role Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fwsUserRoleInstanceList}" status="i" var="fwsUserRoleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fwsUserRoleInstance.id}">${fieldValue(bean: fwsUserRoleInstance, field: "fwsUserRoleGongNengId")}</g:link></td>
					
						<td>${fieldValue(bean: fwsUserRoleInstance, field: "fwsUserRoleId")}</td>
					
						<td><g:formatDate date="${fwsUserRoleInstance.fwsUserRoleTime}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fwsUserRoleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

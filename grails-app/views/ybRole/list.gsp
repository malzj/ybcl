
<%@ page import="cbjlfws.YbRole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ybRole.label', default: 'YbRole')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ybRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ybRole" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="time" title="${message(code: 'ybRole.time.label', default: 'Time')}" />
					
						<g:sortableColumn property="ybGongNengId" title="${message(code: 'ybRole.ybGongNengId.label', default: 'Yb Gong Neng Id')}" />
					
						<g:sortableColumn property="ybUserId" title="${message(code: 'ybRole.ybUserId.label', default: 'Yb User Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ybRoleInstanceList}" status="i" var="ybRoleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ybRoleInstance.id}">${fieldValue(bean: ybRoleInstance, field: "time")}</g:link></td>
					
						<td>${fieldValue(bean: ybRoleInstance, field: "ybGongNengId")}</td>
					
						<td>${fieldValue(bean: ybRoleInstance, field: "ybUserId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ybRoleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

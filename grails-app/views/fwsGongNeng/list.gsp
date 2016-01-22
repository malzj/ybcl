
<%@ page import="cbjlfws.FwsGongNeng" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsGongNeng.label', default: 'FwsGongNeng')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fwsGongNeng" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fwsGongNeng" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="functionLink" title="${message(code: 'fwsGongNeng.functionLink.label', default: 'Function Link')}" />
					
						<g:sortableColumn property="functionName" title="${message(code: 'fwsGongNeng.functionName.label', default: 'Function Name')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'fwsGongNeng.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fwsGongNengInstanceList}" status="i" var="fwsGongNengInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fwsGongNengInstance.id}">${fieldValue(bean: fwsGongNengInstance, field: "functionLink")}</g:link></td>
					
						<td>${fieldValue(bean: fwsGongNengInstance, field: "functionName")}</td>
					
						<td><g:formatDate date="${fwsGongNengInstance.time}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fwsGongNengInstanceTotal}" />
			</div>
		</div>
	</body>
</html>


<%@ page import="cbjlfws.YbGongNeng" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ybGongNeng.label', default: 'YbGongNeng')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ybGongNeng" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ybGongNeng" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="functionLink" title="${message(code: 'ybGongNeng.functionLink.label', default: 'Function Link')}" />
					
						<g:sortableColumn property="functionName" title="${message(code: 'ybGongNeng.functionName.label', default: 'Function Name')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'ybGongNeng.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ybGongNengInstanceList}" status="i" var="ybGongNengInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ybGongNengInstance.id}">${fieldValue(bean: ybGongNengInstance, field: "functionLink")}</g:link></td>
					
						<td>${fieldValue(bean: ybGongNengInstance, field: "functionName")}</td>
					
						<td><g:formatDate date="${ybGongNengInstance.time}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ybGongNengInstanceTotal}" />
			</div>
		</div>
	</body>
</html>


<%@ page import="cbjlfws.YbRole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ybRole.label', default: 'YbRole')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ybRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ybRole" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ybRole">
			
				<g:if test="${ybRoleInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="ybRole.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${ybRoleInstance?.time}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ybRoleInstance?.ybGongNengId}">
				<li class="fieldcontain">
					<span id="ybGongNengId-label" class="property-label"><g:message code="ybRole.ybGongNengId.label" default="Yb Gong Neng Id" /></span>
					
						<span class="property-value" aria-labelledby="ybGongNengId-label"><g:fieldValue bean="${ybRoleInstance}" field="ybGongNengId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ybRoleInstance?.ybUserId}">
				<li class="fieldcontain">
					<span id="ybUserId-label" class="property-label"><g:message code="ybRole.ybUserId.label" default="Yb User Id" /></span>
					
						<span class="property-value" aria-labelledby="ybUserId-label"><g:fieldValue bean="${ybRoleInstance}" field="ybUserId"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ybRoleInstance?.id}" />
					<g:link class="edit" action="edit" id="${ybRoleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

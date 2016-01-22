
<%@ page import="cbjlfws.FwsUserRole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsUserRole.label', default: 'FwsUserRole')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fwsUserRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fwsUserRole" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fwsUserRole">
			
				<g:if test="${fwsUserRoleInstance?.fwsUserRoleGongNengId}">
				<li class="fieldcontain">
					<span id="fwsUserRoleGongNengId-label" class="property-label"><g:message code="fwsUserRole.fwsUserRoleGongNengId.label" default="Fws User Role Gong Neng Id" /></span>
					
						<span class="property-value" aria-labelledby="fwsUserRoleGongNengId-label"><g:fieldValue bean="${fwsUserRoleInstance}" field="fwsUserRoleGongNengId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsUserRoleInstance?.fwsUserRoleId}">
				<li class="fieldcontain">
					<span id="fwsUserRoleId-label" class="property-label"><g:message code="fwsUserRole.fwsUserRoleId.label" default="Fws User Role Id" /></span>
					
						<span class="property-value" aria-labelledby="fwsUserRoleId-label"><g:fieldValue bean="${fwsUserRoleInstance}" field="fwsUserRoleId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsUserRoleInstance?.fwsUserRoleTime}">
				<li class="fieldcontain">
					<span id="fwsUserRoleTime-label" class="property-label"><g:message code="fwsUserRole.fwsUserRoleTime.label" default="Fws User Role Time" /></span>
					
						<span class="property-value" aria-labelledby="fwsUserRoleTime-label"><g:formatDate date="${fwsUserRoleInstance?.fwsUserRoleTime}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fwsUserRoleInstance?.id}" />
					<g:link class="edit" action="edit" id="${fwsUserRoleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

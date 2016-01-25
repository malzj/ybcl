
<%@ page import="cbjlfws.Department" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'department.label', default: 'Department')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-department" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-department" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list department">
			
				<g:if test="${departmentInstance?.fwsShop}">
				<li class="fieldcontain">
					<span id="fwsShop-label" class="property-label"><g:message code="department.fwsShop.label" default="Fws Shop" /></span>
					
						<span class="property-value" aria-labelledby="fwsShop-label"><g:link controller="fwsShop" action="show" id="${departmentInstance?.fwsShop?.id}">${departmentInstance?.fwsShop?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${departmentInstance?.fwsUser}">
				<li class="fieldcontain">
					<span id="fwsUser-label" class="property-label"><g:message code="department.fwsUser.label" default="Fws User" /></span>
					
						<g:each in="${departmentInstance.fwsUser}" var="f">
						<span class="property-value" aria-labelledby="fwsUser-label"><g:link controller="fwsUser" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${departmentInstance?.linkMan}">
				<li class="fieldcontain">
					<span id="linkMan-label" class="property-label"><g:message code="department.linkMan.label" default="Link Man" /></span>
					
						<span class="property-value" aria-labelledby="linkMan-label"><g:fieldValue bean="${departmentInstance}" field="linkMan"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${departmentInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="department.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${departmentInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${departmentInstance?.number}">
				<li class="fieldcontain">
					<span id="number-label" class="property-label"><g:message code="department.number.label" default="Number" /></span>
					
						<span class="property-value" aria-labelledby="number-label"><g:fieldValue bean="${departmentInstance}" field="number"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${departmentInstance?.id}" />
					<g:link class="edit" action="edit" id="${departmentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

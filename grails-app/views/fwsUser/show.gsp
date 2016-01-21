
<%@ page import="cbjlfws.FwsUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsUser.label', default: 'FwsUser')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fwsUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fwsUser" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fwsUser">
			
				<g:if test="${fwsUserInstance?.fwsShop}">
				<li class="fieldcontain">
					<span id="fwsShop-label" class="property-label"><g:message code="fwsUser.fwsShop.label" default="Fws Shop" /></span>
					
						<span class="property-value" aria-labelledby="fwsShop-label"><g:link controller="fwsShop" action="show" id="${fwsUserInstance?.fwsShop?.id}">${fwsUserInstance?.fwsShop?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsUserInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="fwsUser.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${fwsUserInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsUserInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="fwsUser.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${fwsUserInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsUserInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="fwsUser.phone.label" default="Phone" /></span>
					
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${fwsUserInstance}" field="phone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsUserInstance?.position}">
				<li class="fieldcontain">
					<span id="position-label" class="property-label"><g:message code="fwsUser.position.label" default="Position" /></span>
					
						<span class="property-value" aria-labelledby="position-label"><g:fieldValue bean="${fwsUserInstance}" field="position"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsUserInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="fwsUser.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${fwsUserInstance?.time}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsUserInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="fwsUser.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${fwsUserInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fwsUserInstance?.id}" />
					<g:link class="edit" action="edit" id="${fwsUserInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

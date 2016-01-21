
<%@ page import="cbjlfws.YbClient" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ybClient.label', default: 'YbClient')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ybClient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-ybClient" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list ybClient">
			
				<g:if test="${ybClientInstance?.beginTime}">
				<li class="fieldcontain">
					<span id="beginTime-label" class="property-label"><g:message code="ybClient.beginTime.label" default="Begin Time" /></span>
					
						<span class="property-value" aria-labelledby="beginTime-label"><g:formatDate date="${ybClientInstance?.beginTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ybClientInstance?.fwsShop}">
				<li class="fieldcontain">
					<span id="fwsShop-label" class="property-label"><g:message code="ybClient.fwsShop.label" default="Fws Shop" /></span>
					
						<g:each in="${ybClientInstance.fwsShop}" var="f">
						<span class="property-value" aria-labelledby="fwsShop-label"><g:link controller="fwsShop" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${ybClientInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="ybClient.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${ybClientInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ybClientInstance?.number}">
				<li class="fieldcontain">
					<span id="number-label" class="property-label"><g:message code="ybClient.number.label" default="Number" /></span>
					
						<span class="property-value" aria-labelledby="number-label"><g:fieldValue bean="${ybClientInstance}" field="number"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ybClientInstance?.overTime}">
				<li class="fieldcontain">
					<span id="overTime-label" class="property-label"><g:message code="ybClient.overTime.label" default="Over Time" /></span>
					
						<span class="property-value" aria-labelledby="overTime-label"><g:formatDate date="${ybClientInstance?.overTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${ybClientInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="ybClient.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${ybClientInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ybClientInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="ybClient.phone.label" default="Phone" /></span>
					
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${ybClientInstance}" field="phone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ybClientInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="ybClient.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${ybClientInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ybClientInstance?.id}" />
					<g:link class="edit" action="edit" id="${ybClientInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>


<%@ page import="cbjlfws.FwsShop" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsShop.label', default: 'FwsShop')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fwsShop" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fwsShop" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fwsShop">
			
				<g:if test="${fwsShopInstance?.addrass}">
				<li class="fieldcontain">
					<span id="addrass-label" class="property-label"><g:message code="fwsShop.addrass.label" default="Addrass" /></span>
					
						<span class="property-value" aria-labelledby="addrass-label"><g:fieldValue bean="${fwsShopInstance}" field="addrass"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsShopInstance?.coordinate}">
				<li class="fieldcontain">
					<span id="coordinate-label" class="property-label"><g:message code="fwsShop.coordinate.label" default="Coordinate" /></span>
					
						<span class="property-value" aria-labelledby="coordinate-label"><g:fieldValue bean="${fwsShopInstance}" field="coordinate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsShopInstance?.fwsStation}">
				<li class="fieldcontain">
					<span id="fwsStation-label" class="property-label"><g:message code="fwsShop.fwsStation.label" default="Fws Station" /></span>
					
						<g:each in="${fwsShopInstance.fwsStation}" var="f">
						<span class="property-value" aria-labelledby="fwsStation-label"><g:link controller="fwsStation" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${fwsShopInstance?.fwsUser}">
				<li class="fieldcontain">
					<span id="fwsUser-label" class="property-label"><g:message code="fwsShop.fwsUser.label" default="Fws User" /></span>
					
						<g:each in="${fwsShopInstance.fwsUser}" var="f">
						<span class="property-value" aria-labelledby="fwsUser-label"><g:link controller="fwsUser" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${fwsShopInstance?.linkMan}">
				<li class="fieldcontain">
					<span id="linkMan-label" class="property-label"><g:message code="fwsShop.linkMan.label" default="Link Man" /></span>
					
						<span class="property-value" aria-labelledby="linkMan-label"><g:fieldValue bean="${fwsShopInstance}" field="linkMan"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsShopInstance?.linkManPhone}">
				<li class="fieldcontain">
					<span id="linkManPhone-label" class="property-label"><g:message code="fwsShop.linkManPhone.label" default="Link Man Phone" /></span>
					
						<span class="property-value" aria-labelledby="linkManPhone-label"><g:fieldValue bean="${fwsShopInstance}" field="linkManPhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsShopInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="fwsShop.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${fwsShopInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsShopInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="fwsShop.phone.label" default="Phone" /></span>
					
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${fwsShopInstance}" field="phone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsShopInstance?.ybClient}">
				<li class="fieldcontain">
					<span id="ybClient-label" class="property-label"><g:message code="fwsShop.ybClient.label" default="Yb Client" /></span>
					
						<span class="property-value" aria-labelledby="ybClient-label"><g:link controller="ybClient" action="show" id="${fwsShopInstance?.ybClient?.id}">${fwsShopInstance?.ybClient?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fwsShopInstance?.id}" />
					<g:link class="edit" action="edit" id="${fwsShopInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

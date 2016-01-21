
<%@ page import="cbjlfws.FwsGongNeng" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsGongNeng.label', default: 'FwsGongNeng')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fwsGongNeng" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fwsGongNeng" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fwsGongNeng">
			
				<g:if test="${fwsGongNengInstance?.functionLink}">
				<li class="fieldcontain">
					<span id="functionLink-label" class="property-label"><g:message code="fwsGongNeng.functionLink.label" default="Function Link" /></span>
					
						<span class="property-value" aria-labelledby="functionLink-label"><g:fieldValue bean="${fwsGongNengInstance}" field="functionLink"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsGongNengInstance?.functionName}">
				<li class="fieldcontain">
					<span id="functionName-label" class="property-label"><g:message code="fwsGongNeng.functionName.label" default="Function Name" /></span>
					
						<span class="property-value" aria-labelledby="functionName-label"><g:fieldValue bean="${fwsGongNengInstance}" field="functionName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fwsGongNengInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="fwsGongNeng.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${fwsGongNengInstance?.time}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fwsGongNengInstance?.id}" />
					<g:link class="edit" action="edit" id="${fwsGongNengInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

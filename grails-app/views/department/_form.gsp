<%@ page import="cbjlfws.Department" %>



<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'fwsShop', 'error')} required">
	<label for="fwsShop">
		<g:message code="department.fwsShop.label" default="Fws Shop" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fwsShop" name="fwsShop.id" from="${cbjlfws.FwsShop.list()}" optionKey="id" required="" value="${departmentInstance?.fwsShop?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'fwsUser', 'error')} ">
	<label for="fwsUser">
		<g:message code="department.fwsUser.label" default="Fws User" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${departmentInstance?.fwsUser?}" var="f">
    <li><g:link controller="fwsUser" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="fwsUser" action="create" params="['department.id': departmentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'fwsUser.label', default: 'FwsUser')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'linkMan', 'error')} ">
	<label for="linkMan">
		<g:message code="department.linkMan.label" default="Link Man" />
		
	</label>
	<g:textField name="linkMan" value="${departmentInstance?.linkMan}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="department.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${departmentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'number', 'error')} ">
	<label for="number">
		<g:message code="department.number.label" default="Number" />
		
	</label>
	<g:textField name="number" value="${departmentInstance?.number}"/>
</div>


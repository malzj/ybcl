<%@ page import="cbjlfws.YbClient" %>



<div class="fieldcontain ${hasErrors(bean: ybClientInstance, field: 'beginTime', 'error')} required">
	<label for="beginTime">
		<g:message code="ybClient.beginTime.label" default="Begin Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="beginTime" precision="day"  value="${ybClientInstance?.beginTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: ybClientInstance, field: 'fwsShop', 'error')} ">
	<label for="fwsShop">
		<g:message code="ybClient.fwsShop.label" default="Fws Shop" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${ybClientInstance?.fwsShop?}" var="f">
    <li><g:link controller="fwsShop" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="fwsShop" action="create" params="['ybClient.id': ybClientInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'fwsShop.label', default: 'FwsShop')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: ybClientInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="ybClient.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${ybClientInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybClientInstance, field: 'number', 'error')} required">
	<label for="number">
		<g:message code="ybClient.number.label" default="Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="number" type="number" value="${ybClientInstance.number}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybClientInstance, field: 'overTime', 'error')} required">
	<label for="overTime">
		<g:message code="ybClient.overTime.label" default="Over Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="overTime" precision="day"  value="${ybClientInstance?.overTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: ybClientInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="ybClient.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${ybClientInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybClientInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="ybClient.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${ybClientInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybClientInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="ybClient.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${ybClientInstance?.username}"/>
</div>


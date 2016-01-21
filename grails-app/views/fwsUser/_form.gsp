<%@ page import="cbjlfws.FwsUser" %>



<div class="fieldcontain ${hasErrors(bean: fwsUserInstance, field: 'fwsShop', 'error')} required">
	<label for="fwsShop">
		<g:message code="fwsUser.fwsShop.label" default="Fws Shop" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fwsShop" name="fwsShop.id" from="${cbjlfws.FwsShop.list()}" optionKey="id" required="" value="${fwsUserInstance?.fwsShop?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsUserInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="fwsUser.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${fwsUserInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsUserInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="fwsUser.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${fwsUserInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsUserInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="fwsUser.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${fwsUserInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsUserInstance, field: 'position', 'error')} ">
	<label for="position">
		<g:message code="fwsUser.position.label" default="Position" />
		
	</label>
	<g:textField name="position" value="${fwsUserInstance?.position}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsUserInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="fwsUser.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${fwsUserInstance?.time}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: fwsUserInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="fwsUser.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${fwsUserInstance?.username}"/>
</div>


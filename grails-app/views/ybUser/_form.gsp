<%@ page import="cbjlfws.YbUser" %>



<div class="fieldcontain ${hasErrors(bean: ybUserInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="ybUser.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${ybUserInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybUserInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="ybUser.password.label" default="Password" />
		
	</label>
	<g:textField name="password" value="${ybUserInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybUserInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="ybUser.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${ybUserInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybUserInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="ybUser.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${ybUserInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybUserInstance, field: 'position', 'error')} ">
	<label for="position">
		<g:message code="ybUser.position.label" default="Position" />
		
	</label>
	<g:textField name="position" value="${ybUserInstance?.position}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybUserInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="ybUser.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${ybUserInstance?.time}"  />
</div>


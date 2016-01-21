<%@ page import="cbjlfws.YbRole" %>



<div class="fieldcontain ${hasErrors(bean: ybRoleInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="ybRole.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${ybRoleInstance?.time}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: ybRoleInstance, field: 'ybGongNengId', 'error')} ">
	<label for="ybGongNengId">
		<g:message code="ybRole.ybGongNengId.label" default="Yb Gong Neng Id" />
		
	</label>
	<g:textField name="ybGongNengId" value="${ybRoleInstance?.ybGongNengId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybRoleInstance, field: 'ybUserId', 'error')} ">
	<label for="ybUserId">
		<g:message code="ybRole.ybUserId.label" default="Yb User Id" />
		
	</label>
	<g:textField name="ybUserId" value="${ybRoleInstance?.ybUserId}"/>
</div>


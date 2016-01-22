<%@ page import="cbjlfws.FwsUserRole" %>



<div class="fieldcontain ${hasErrors(bean: fwsUserRoleInstance, field: 'fwsUserRoleGongNengId', 'error')} ">
	<label for="fwsUserRoleGongNengId">
		<g:message code="fwsUserRole.fwsUserRoleGongNengId.label" default="Fws User Role Gong Neng Id" />
		
	</label>
	<g:textField name="fwsUserRoleGongNengId" value="${fwsUserRoleInstance?.fwsUserRoleGongNengId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsUserRoleInstance, field: 'fwsUserRoleId', 'error')} ">
	<label for="fwsUserRoleId">
		<g:message code="fwsUserRole.fwsUserRoleId.label" default="Fws User Role Id" />
		
	</label>
	<g:textField name="fwsUserRoleId" value="${fwsUserRoleInstance?.fwsUserRoleId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsUserRoleInstance, field: 'fwsUserRoleTime', 'error')} required">
	<label for="fwsUserRoleTime">
		<g:message code="fwsUserRole.fwsUserRoleTime.label" default="Fws User Role Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fwsUserRoleTime" precision="day"  value="${fwsUserRoleInstance?.fwsUserRoleTime}"  />
</div>


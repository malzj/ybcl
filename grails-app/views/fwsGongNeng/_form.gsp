<%@ page import="cbjlfws.FwsGongNeng" %>



<div class="fieldcontain ${hasErrors(bean: fwsGongNengInstance, field: 'functionLink', 'error')} ">
	<label for="functionLink">
		<g:message code="fwsGongNeng.functionLink.label" default="Function Link" />
		
	</label>
	<g:textField name="functionLink" value="${fwsGongNengInstance?.functionLink}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsGongNengInstance, field: 'functionName', 'error')} ">
	<label for="functionName">
		<g:message code="fwsGongNeng.functionName.label" default="Function Name" />
		
	</label>
	<g:textField name="functionName" value="${fwsGongNengInstance?.functionName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsGongNengInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="fwsGongNeng.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${fwsGongNengInstance?.time}"  />
</div>


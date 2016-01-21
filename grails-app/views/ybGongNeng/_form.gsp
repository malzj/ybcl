<%@ page import="cbjlfws.YbGongNeng" %>



<div class="fieldcontain ${hasErrors(bean: ybGongNengInstance, field: 'functionLink', 'error')} ">
	<label for="functionLink">
		<g:message code="ybGongNeng.functionLink.label" default="Function Link" />
		
	</label>
	<g:textField name="functionLink" value="${ybGongNengInstance?.functionLink}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybGongNengInstance, field: 'functionName', 'error')} ">
	<label for="functionName">
		<g:message code="ybGongNeng.functionName.label" default="Function Name" />
		
	</label>
	<g:textField name="functionName" value="${ybGongNengInstance?.functionName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ybGongNengInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="ybGongNeng.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${ybGongNengInstance?.time}"  />
</div>


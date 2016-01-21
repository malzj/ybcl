<%@ page import="cbjlfws.FwsShop" %>



<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'addrass', 'error')} ">
	<label for="addrass">
		<g:message code="fwsShop.addrass.label" default="Addrass" />
		
	</label>
	<g:textField name="addrass" value="${fwsShopInstance?.addrass}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'coordinate', 'error')} ">
	<label for="coordinate">
		<g:message code="fwsShop.coordinate.label" default="Coordinate" />
		
	</label>
	<g:textField name="coordinate" value="${fwsShopInstance?.coordinate}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'fwsStation', 'error')} ">
	<label for="fwsStation">
		<g:message code="fwsShop.fwsStation.label" default="Fws Station" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${fwsShopInstance?.fwsStation?}" var="f">
    <li><g:link controller="fwsStation" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="fwsStation" action="create" params="['fwsShop.id': fwsShopInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'fwsStation.label', default: 'FwsStation')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'fwsUser', 'error')} ">
	<label for="fwsUser">
		<g:message code="fwsShop.fwsUser.label" default="Fws User" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${fwsShopInstance?.fwsUser?}" var="f">
    <li><g:link controller="fwsUser" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="fwsUser" action="create" params="['fwsShop.id': fwsShopInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'fwsUser.label', default: 'FwsUser')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'linkMan', 'error')} ">
	<label for="linkMan">
		<g:message code="fwsShop.linkMan.label" default="Link Man" />
		
	</label>
	<g:textField name="linkMan" value="${fwsShopInstance?.linkMan}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'linkManPhone', 'error')} ">
	<label for="linkManPhone">
		<g:message code="fwsShop.linkManPhone.label" default="Link Man Phone" />
		
	</label>
	<g:textField name="linkManPhone" value="${fwsShopInstance?.linkManPhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="fwsShop.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${fwsShopInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="fwsShop.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${fwsShopInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fwsShopInstance, field: 'ybClient', 'error')} required">
	<label for="ybClient">
		<g:message code="fwsShop.ybClient.label" default="Yb Client" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ybClient" name="ybClient.id" from="${cbjlfws.YbClient.list()}" optionKey="id" required="" value="${fwsShopInstance?.ybClient?.id}" class="many-to-one"/>
</div>


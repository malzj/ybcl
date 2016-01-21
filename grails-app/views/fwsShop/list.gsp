
<%@ page import="cbjlfws.FwsShop" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fwsShop.label', default: 'FwsShop')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fwsShop" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fwsShop" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="addrass" title="${message(code: 'fwsShop.addrass.label', default: 'Addrass')}" />
					
						<g:sortableColumn property="coordinate" title="${message(code: 'fwsShop.coordinate.label', default: 'Coordinate')}" />
					
						<g:sortableColumn property="linkMan" title="${message(code: 'fwsShop.linkMan.label', default: 'Link Man')}" />
					
						<g:sortableColumn property="linkManPhone" title="${message(code: 'fwsShop.linkManPhone.label', default: 'Link Man Phone')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'fwsShop.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'fwsShop.phone.label', default: 'Phone')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fwsShopInstanceList}" status="i" var="fwsShopInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fwsShopInstance.id}">${fieldValue(bean: fwsShopInstance, field: "addrass")}</g:link></td>
					
						<td>${fieldValue(bean: fwsShopInstance, field: "coordinate")}</td>
					
						<td>${fieldValue(bean: fwsShopInstance, field: "linkMan")}</td>
					
						<td>${fieldValue(bean: fwsShopInstance, field: "linkManPhone")}</td>
					
						<td>${fieldValue(bean: fwsShopInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: fwsShopInstance, field: "phone")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fwsShopInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

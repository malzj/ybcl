<%--
  Created by IntelliJ IDEA.
  User: malmemeda
  Date: 16-1-22
  Time: 下午3:39
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<g:link controller="fwsBoss" action="fwsShopCreate" class="btn btn-info" style="display:block;float:right;">新建服务商店</g:link>
<g:each in="${fwsShopList}" status="i" var="shop">
    <g:link action="fwsUserList" id="${shop.id}">${shop.name}</g:link>
</g:each>
<p>${msg}</p>
</body>
</html>
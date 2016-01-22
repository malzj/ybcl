<%--
  Created by IntelliJ IDEA.
  User: malmemeda
  Date: 16-1-21
  Time: 上午9:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>亦宝车联后台管理</title>
</head>

<body>

<g:each in="${list}" status="i" var="li">


    <g:link action="${li?.functionLink}">${li?.functionName}</g:link>

</g:each>

</body>
</html>
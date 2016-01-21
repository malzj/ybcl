<%--
  Created by IntelliJ IDEA.
  User: malmemeda
  Date: 16-1-20
  Time: 下午5:25
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="${resource(dir: 'bootstrap/css', file: 'signin.css')}" rel="stylesheet" type="text/css">
    <link href="${resource(dir: 'bootstrap/css', file: 'bootstrap.min.css')}" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>亦宝车联首页</title>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">亦宝车联</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><g:link controller="ybLogin" action="list">用户管理</g:link></li>
                <li><g:link action="ybGongNengList">功能管理</g:link></li>
                <li><g:link action="">用户权限</g:link></li>
                <li><g:link action="">亦宝车联客户管理</g:link></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<tbody>
<div class="container">
    <table class="table table-condensed">

        <thead>
        <tr>
            <th>功能名称</th>
            <th>功能连接</th>
            <th>时间</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${ybGongNengInstanceList}" status="i" var="ybGongNengInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${ybGongNengInstance.id}">${fieldValue(bean: ybGongNengInstance, field: "functionName")}</g:link></td>


                <td>${fieldValue(bean: ybGongNengInstance, field: "functionLink")}</td>

                <td><g:formatDate date="${ybGongNengInstance.time}" /></td>

            </tr>
        </g:each>
        </tbody>
    </table>


</div>
</tbody>
</body>
</html>
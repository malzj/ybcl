<%--
  Created by IntelliJ IDEA.
  User: malmemeda
  Date: 16-1-20
  Time: 下午5:12
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="${resource(dir: 'bootstrap/css', file: 'signin.css')}" rel="stylesheet" type="text/css">
    <link href="${resource(dir: 'bootstrap/css', file: 'bootstrap.min.css')}" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆</title>
</head>

<body>
<div class="container">

    <g:form class="form-signin" action="login">
        <h2 class="form-signin-heading">亦宝车联Boss</h2>
        <label for="inputEmail" class="sr-only">账户</label>
        <input type="text" id="inputEmail" name="username" class="form-control" placeholder="账户" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
        <p>${msg}</p>
    </g:form>
</div>
</body>
</html>
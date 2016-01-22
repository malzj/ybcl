<%--
  Created by IntelliJ IDEA.
  User: malmemeda
  Date: 16-1-22
  Time: 下午4:01
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<g:form action="fwsShopSave" method="post">
    <table>
      <tr>
          <td>店名称</td><td><input value="" type="text" name="name"/></td>
      </tr>
    <tr>
        <td>店地址</td><td><input value="" type="text" name="addrass"/></td>
    </tr>
    <tr>
        <td>店坐标</td><td><input value="" type="text" name="coordinate"/></td>
    </tr>
    <tr>
        <td>电话</td><td><input value="" type="text" name="phone"/></td>
    </tr>
    <tr>
        <td>联系人</td><td><input value="" type="text" name="linkMan"/></td>
    </tr>
    <tr>
        <td>联系人电话</td><td><input value="" type="text" name="linkManPhone"/></td>
    </tr>
        <tr>
            <td></td>
            <td align="right"><button type="submit" class="btn btn-info">保存</button><a href="javascript:history.go(-1);" class="ml20 btn btn-info">取消</a></td>
        </tr>
    </table>
</g:form>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 15-6-16
  Time: 下午3:59
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>亦宝车联后台管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="${resource(dir: 'css', file: 'bootstrap.min.css')}" rel="stylesheet">
    <link href="${resource(dir: 'css', file: 'bootstrap-reset.css')}" rel="stylesheet">
    <!--external css-->
    <link href="${resource(dir: 'assets/font-awesome/css', file: 'font-awesome.css')}" rel="stylesheet">
    <link href="${resource(dir: 'assets/jquery-easy-pie-chart', file: 'jquery.easy-pie-chart.css')}" rel="stylesheet">
    <link href="${resource(dir: 'css', file: 'owl.carousel.css')}" rel="stylesheet">

    <!--right slidebar-->
    <link href="${resource(dir: 'css', file: 'slidebars.css')}" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${resource(dir: 'css', file: 'styleone.css')}" rel="stylesheet">
    <link href="${resource(dir: 'css', file: 'style-responsive.css')}" rel="stylesheet">

    <link href="${resource(dir: 'css', file: 'ownset.css')}" rel="stylesheet">
</head>

<body>

<section id="container" >
    <!--header start-->
    <g:render template="header" />
    <!--header end-->
    <!--sidebar start-->
    <g:render template="sidebar" />
    <!--sidebar end-->
    <!--main content start-->
    <section id="main-content" class="col-xs-10" style="padding-left: 0;">
        <section class="wrapper">
            <div class="middle_content">
                <div class="m_box">
                    <header class="panel-heading">
                        <span><i class="yh"></i>修改用户</span>
                    </header>
                    <g:form action="ybUserUpdate" method="post">

                        <g:hiddenField name="id" value="${ybUserInstance?.id}" />
                        <g:hiddenField name="version" value="${ybUserInstance?.version}" />
                        <table>
                                   <tr>
                                       <td>姓名：</td>
                                       <td width="345"><input name="name" class="form-control form-control-inline input-medium default-date-picker" type="text" value="${ybUserInstance?.name}"></td>
                                    </tr>
                        <tr>
                            <td>用户名：</td>
                            <td><input name="username" class="form-control form-control-inline input-medium default-date-picker" type="text" value="${ybUserInstance?.username}"></td>
                        </tr>
                        <tr>
                            <td>密码：</td>
                            <td><input name="password" class="form-control form-control-inline input-medium default-date-picker" type="text" value="${ybUserInstance?.password}"></td>
                        </tr>

                        <tr>
                            <td>电话：</td>
                            <td><input name="phone" class="form-control form-control-inline input-medium default-date-picker" type="text" value="${ybUserInstance?.phone}"></td>
                        </tr>


                        <tr>
                            <td>职位：</td>
                            <td><input name="position" class="form-control form-control-inline input-medium default-date-picker" type="text" value="${ybUserInstance?.position}"></td>
                        </tr>
                        <tr>
                        <tr>
                            <td>权限：</td>
                            <td>
                                %{--<g:each in="${listgongneng}" status="i" var="gongneng">--}%
                                    %{--<label class="checkbox-inline">--}%
                                        %{--<g:if test="${companyUserInstance.pid==pinfo.id}">selected="selected" </g:if>--}%
                                        %{--<input name="gongneng" type="checkbox" checked="checked" id="inlineCheckbox1" value="${gongneng.id}"> ${gongneng.functionName}--}%
                                    %{--</label>--}%
                                %{--</g:each>  --}%
                                <g:each in="${gongnenglistrole}" status="i" var="gongneng">
                                    <label class="checkbox-inline">
                                        %{--<g:if test="${listgongneng.get(i).functionName==gongneng.functionName}"> checked="checked"" </g:if>--}%


                                        <input name="gongneng" type="checkbox"    <g:if test="${cbjlfws.YbRole.findByYbGongNengIdAndYbUserIdAnd(gongneng.id,ybUserInstance.id)}"> checked="checked"" </g:if> id="inlineCheckbox1" value="${gongneng.id}"> ${gongneng.functionName}
                                    </label>
                                </g:each>

                            </td>
                        </tr>
                        <td>
                            创建时间：
                        </td>
                        <td>
                            <g:datePicker name="time" precision="day"  value="${ybUserInstance?.time}"  />
                        </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td align="right"><button type="submit" class="btn btn-info">保存</button><a href="javascript:history.go(-1);" class="ml20 btn btn-info">取消</a></td>
                        </tr>
                        </table>
                    </g:form>
                </div>
            </div>
        </section>
        <!--main content end-->

    </section>

</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="${resource(dir: 'js', file: 'jquery.js')}"></script>
<script src="${resource(dir: 'js', file: 'bootstrap.min.js')}"></script>
<script class="include" type="text/javascript" src="${resource(dir: 'js', file: 'jquery.dcjqaccordion.2.7.js')}"></script>
<script src="${resource(dir: 'js', file: 'jquery.scrollTo.min.js')}"></script>
<script src="${resource(dir: 'js', file: 'jquery.nicescroll.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'js', file: 'jquery.sparkline.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'assets/jquery-easy-pie-chart/', file: 'jquery.easy-pie-chart.js')}"></script>
<script src="${resource(dir: 'js', file: 'owl.carousel.js')}" ></script>
<script src="${resource(dir: 'js', file: 'jquery.customSelect.min.js')}" ></script>
<script src="${resource(dir: 'js', file: 'respond.min.js')}" ></script>

<!--right slidebar-->
<script src="${resource(dir: 'js', file: 'slidebars.min.js')}"></script>

<!--common script for all pages-->
<script src="${resource(dir: 'js', file: 'common-scripts.js')}"></script>

<!--script for this page-->
<script src="${resource(dir: 'js', file: 'sparkline-chart.js')}"></script>
<script src="${resource(dir: 'js', file: 'easy-pie-chart.js')}"></script>
<script src="${resource(dir: 'js', file: 'count.js')}"></script>



</body>
</html>
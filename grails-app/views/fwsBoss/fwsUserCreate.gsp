<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 15-6-16
  Time: 下午3:59
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${name}后台管理系统</title>

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
    <section id="main-content">
        <section class="wrapper mt80">
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">


                        <header class="panel-heading">
                            新增用户
                        </header>
                        <div class="panel-body">
                            <g:form class="form-horizontal tasi-form" controller="fwsBoss" action="fwsUserSave" method= "post" enctype= "multipart/form-data">
                                <table>
                                <input name="fwsShopId" type="hidden" value="${id}">

                                    <tr>
                                        <td>用户名：</td>
                                        <td width="345"><input name="username" class="form-control form-control-inline input-medium default-date-picker" type="text" value=""></td>
                                    </tr>
                                      <tr>
                                    <td>密码：</td>
                                    <td><input name="password" class="form-control form-control-inline input-medium default-date-picker" type="text" value=""></td>
                                </tr>
                                <tr>
                                    <td>姓名：</td>
                                    <td><input name="name" class="form-control form-control-inline input-medium default-date-picker" type="text" value=""></td>
                                </tr>
                                <tr>
                                    <td>手机号：</td>
                                    <td><input name="phone" class="form-control form-control-inline input-medium default-date-picker" type="text" value=""></td>
                                </tr>
                                <tr>
                                    <td>职位：</td>
                                    <td><input name="position" class="form-control form-control-inline input-medium default-date-picker" type="text" value=""></td>
                                </tr>
                                <tr>
                                    <td>工资：</td>
                                    <td><input name="money" class="form-control form-control-inline input-medium default-date-picker" type="text" value=""></td>
                                </tr>
                              <tr>
                               <td>部门：</td>
                               <td>
                                <g:select name="departmentId" from="${departmentList}" optionKey="id" optionValue="name" required="" value="" class="form-control form-control-inline input-medium"/>
                                </td>
                            </tr>
                                <tr>
                                    <td>权限：</td>
                                    <td>
                                        <g:each in="${gongnenglist}" status="i" var="gongneng">
                                            <label class="checkbox-inline">
                                                <input name="gongneng" type="checkbox" id="inlineCheckbox1" value="${gongneng.id}"> ${gongneng.functionName}
                                            </label>
                                        </g:each>

                                    </td>
                                </tr>
                                <td>
                                    创建时间：
                                </td>
                                <td>
                                    <g:datePicker name="time" precision="day"  value=""  />
                                </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td align="right"><button type="submit" class="btn btn-info">保存</button><a href="javascript:history.go(-1);" class="ml20 btn btn-info">取消</a></td>
                                </tr>
                                </table>
                            </g:form>
                        </div>
                    </section>
                </div>
            </div>


        </section>
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
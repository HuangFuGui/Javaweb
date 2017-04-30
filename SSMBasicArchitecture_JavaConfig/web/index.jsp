<%--
  Created by IntelliJ IDEA.
  User: huangfugui
  Date: 2017/4/27
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>接口测试</title>
  </head>
  <body>
  <form>
    用户名：<input type="text" id="username" />
    密码：<input type="text" id="password">
    <input type="button" value="提交测试" id="btn" />
  </form>
  </body>
  <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script type="application/javascript">
    $("#btn").on('click',function () {
      var username = $("#username").val();
      var password = $("#password").val();
      alert(username);
      alert(password);
      $.ajax({
        url:"<%=basePath%>basic/register",
        type:"POST",
        data:{username:username,password:password},
        success:function (result) {
          alert(result);
        },
        error:function () {
          alert("System error!");
        }
      });
    });
  </script>
</html>

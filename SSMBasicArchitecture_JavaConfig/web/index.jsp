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
    <input type="button" value="提交测试" id="btn1" /><hr />
    昵称：<input type="text" id="nickname">
    <input type="button" value="提交测试" id="btn2" /><hr />
    <form id="uploadForm">
      <input type="text" id="uploadText">
      <input type="file" id="uploadFiles" multiple="multiple">
      <input type="button" value="提交测试" id="uploadBtn">
    </form>
  </form>
  </body>

  <script src="jquery-2.0.0.min.js" type="text/javascript"></script>
  <script type="text/javascript" charset="GB2312">
    $("#btn1").on('click', function () {
      var username = $("#username").val();
      var password = $("#password").val();
      $.ajax({
        url: "<%=basePath%>basic/login",
        type: "POST",
        data: {username: username, password: password},
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });

    $("#btn2").on('click', function () {
      var nickname = $("#nickname").val();
      $.ajax({
        url: "<%=basePath%>basic/updateNickname",
        type: "POST",
        data: {nickname: nickname},
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });

    $("#uploadBtn").on('click', function () {
      var formData = new FormData($("#uploadForm")[0]);
      formData.append("uploadText", $("#uploadText").val());
      for(var i=0;i<$('#uploadFiles')[0].files.length;i++){
        formData.append("uploadFiles",$('#uploadFiles')[0].files[i]);
      }
      $.ajax({
        url: "<%=basePath%>basic/upload",
        type: "POST",
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (result) {
          alert(result);
        },
        error: function () {
          alert("System error!");
        }
      });
    });
  </script>
</html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'request.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <h1>学习request内置对象(Get与Post)</h1><hr>
     <form name="requestForm" action="respond.jsp" method="post">
	     <table>
	     <tr> 
	     		<td>用户名：<input  type="text"  name ="username" /></td>
	     </tr>
	     
	    <%-- <tr>
	    		<td>密码：<input type = "password"  name = "password" /></td>
	    </tr>--%>
	    <tr>
	    <td>
	    <input type="checkbox" name="favourite" value="basketball">篮球
	    <input type="checkbox" name="favourite" value="music">音乐
	    <input type="checkbox" name="favourite" value="website">网站
	    <input type="checkbox" name="favourite" value="fitness">健身
	    </td>
	    </tr>
	    <tr>
	    		<td><input type="submit"  value="确认" /></td>
	    </tr>
	     </table>
     </form>
    <br>
    <br>
    <%--Tomcat 8.0.33直接URL中文传参没问题 --%>
    <!-- Tomcat 8.0.33直接URL中文传参没问题 -->
    <a href="respond.jsp?username=黄复贵咯&favourite=489s&favourite=黄复贵">测试URL（中文）传参</a>    
  </body>
</html>

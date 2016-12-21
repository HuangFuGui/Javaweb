<%@ page language="java" import="java.util.*,java.net.*"  contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  <h1>用户登录</h1>
  
  <%
          request.setCharacterEncoding("utf-8");
		  String username = "";
		  String password = "";
		  Cookie[] cookies = request.getCookies();    /*从cookie中取值*/
		  if(cookies!=null&&cookies.length>0)
		  {
			  for(Cookie c:cookies)
			  {
			 	  if(c.getName().equals("username"))
				  {
						username = URLDecoder.decode(c.getValue(),"utf-8");
				  }
				  if(c.getName().equals("password"))
				  {
						password = URLDecoder.decode(c.getValue(), "utf-8") ;
				  }
				  
			  }
		  }
   %>
   
  <form action="doLogin.jsp"  method="post">
	  <table>
		  <tr>
		   		<td>用户名：<input type="text" name="username"  value="<%=username %>"/></td>
		   </tr>
		   <tr>
		   		<td>密码：<input type="password" name="password"  value="<%=password%>" /> </td>
		   </tr>
		   <tr>
		   		<td><input type="checkbox"  name="isUseCookie" checked="checked">保存用户名与密码</td>
		   </tr>
		   <tr>
		   		<td  style="text-align: center;"><input type="submit" value="登录"></td>
		   </tr>
	   </table>
   </form>
  </body>
</html>

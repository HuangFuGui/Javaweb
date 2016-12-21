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
  <h1>登录成功</h1>
  
  <%
      request.setCharacterEncoding("utf-8");
	  String[] isUseCookies = request.getParameterValues("isUseCookie");
	  if(isUseCookies!=null&&isUseCookies.length>0)    /*如果勾选了保存用户名与密码，则保存此时cookie信息*/
	  {
	     /*
	     *
	     *Cookie是保存在客户端的String文本文件，一般用于保存不重要的信息，例如实现化简登录；
	       session是保存在服务器的Object（对象）类型，一般保存重要信息，例如登录状态时用户的密码；
	       session安全性比Cookie高；
	     *****/
	     /*使用URLEncoder解决在Cookie中无法保存中文的问题*/
		  String username =  URLEncoder.encode(request.getParameter("username"), "utf-8");
		  String password =  URLEncoder.encode(request.getParameter("password"), "utf-8");
		  
		  Cookie usernameCookie = new Cookie("username",username);
		  Cookie passwordCookie = new Cookie("password",password);
		  usernameCookie.setMaxAge(864000);   /*十天*/
		  passwordCookie.setMaxAge(864000);
		  
		  response.addCookie(usernameCookie);
		  response.addCookie(passwordCookie);
	  }
	  else     /*没有勾选保存用户名与密码，则清空cookie信息*/
	  {
		  Cookie[] cookies = request.getCookies();
		  if(cookies!=null&&cookies.length>0)
		  {
			  for(Cookie c:cookies)
			  {
			 	  if(c.getName().equals("username")||c.getName().equals("password"))
				  {
					  c.setMaxAge(0);
					  response.addCookie(c);
				  }
			  }
		  }
	  }
   %>
   
  <a href="userInformation.jsp" target="_blank">查看用户登录信息</a>
  </body>
</html>

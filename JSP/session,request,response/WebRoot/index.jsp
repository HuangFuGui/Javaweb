<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8" %>
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
    <h1>This is my first JSP page. 哈哈</h1>
    <hr>
    <%--客户端不可见注释 --%> 
    <%                                                     /*执行java脚本，动态代码*/
    	out.println("欢迎学习JSP");     
     %>
     <%!      /*声明变量或函数*/
     String s = "haha";
     int add(int x,int y)
     {
         return x+y;
     }
      %>
      <br >
     I am <%=s %><br >     <%--使用声明的变量 --%>
     10 + 5 = <%=add(10,5) %><br >      
  </body>
</html>

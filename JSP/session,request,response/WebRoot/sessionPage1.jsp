<%@ page language="java" import="java.util.*,java.text.*"  contentType="text/html; charset=utf-8" %>
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
    <h1>Session内置对象</h1>
    <hr>
    <%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    Date d = new Date(session.getCreationTime());
    session.setAttribute("username", "administrator");
    session.setAttribute("password", "123456");
    session.setAttribute("Email", "1151650717@qq.com");
    
    /*设置session生命周期，单位为秒，10秒*/
  //  session.setMaxInactiveInterval(10);    
     %>
    session创建时间：<%=sdf.format(d)%><br >
    sessionID编号：<%=session.getId() %><br >
    从session中获取用户名：<%=session.getAttribute("username") %><br ><br >
    <a href="sessionPage2.jsp" target="_blank">跳转到sessionPage2.jsp</a>
  </body>
</html>

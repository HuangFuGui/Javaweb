<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'respond.jsp' starting page</title>
    
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
  <%
  		request.setCharacterEncoding("utf-8");
   %>
    <h1>respond page</h1><br>
    用户名：<%=request.getParameter("username") %><br />
    爱好：
    <%
    		String[] s = request.getParameterValues("favourite");
    		for(int i=0;i<s.length;i++)
    		{
    				out.println(s[i]);
    		}
     %>
     
   <%--
     <%
     /*请求重定向，客户端行为，请求对象不会被保存*/
     response.sendRedirect("testRespondTransmit.jsp");     
     %>
      --%>
        
     <%
     /*服务器端行为，请求对象会被保存，地址栏URL不会改变*/
     request.getRequestDispatcher("testRespondTransmit.jsp").forward(request, response);    
     /*与forward动作<jsp:forward page = "testRespondTransmit.jsp"><jsp:param value="" name=""></jsp:forward>一样，
     都是服务器内部请求转发*/
      %>
      
     
  </body>
</html>

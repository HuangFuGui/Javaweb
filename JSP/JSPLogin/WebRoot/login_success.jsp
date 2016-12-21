<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
String username = request.getParameter("username");
String password = request.getParameter("password");
if(username.equals("admin")&&password.equals("admin"))
{             /*服务器内部请求转发，地址栏不会发生改变，依然为login_success.jsp*/
	request.getRequestDispatcher("login_success_fake.jsp").forward(request, response);     /*request，response对象被传递，能取值*/
	//response.sendRedirect("login_success.jsp");    /*取不到用户名*/
}
else
{     /*客户端请求重定向*/
	response.sendRedirect("login_fail.jsp");
}
 %>

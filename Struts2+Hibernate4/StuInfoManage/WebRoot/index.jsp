<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  response.sendRedirect(path+"/users/Users_login.jsp");
%>

<%--
一开始登录不进去，报错：java.lang.reflect.InvocationTargetException     调用目标异议
说明jar包有冲突！
原因是：struts里面的 antlr-2.7.2.jar和hibernate 里面的anltr-2.7.6.jar冲突
解决方法：
	1.先将当前项目的struts 2.0 Libraries （Struts2默认jar包）修改，把其中的antlr-2.7.2.jar删除
	（当然在MyEclipse中的core文件夹下还会存在antlr-2.7.2.jar，在项目中只是是否被调用罢了）
	（要重新建一个Libraries_Me）
	2.把已经部署到Tomcat中的antlr-2.7.2.jar也要删掉！
 --%>

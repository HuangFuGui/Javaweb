<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="entity.Items" %>
<%@ page import="dao.ItemsDAO" %>
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
		<h1>Show all items in database：mystream && table：goods</h1><hr>
		<%
			int i=0;
			ItemsDAO itemsDAO = new ItemsDAO();
			ArrayList<Items> list = itemsDAO.getAllItems();
			for(i=0;i<list.size();i++)
			{
				Items item = list.get(i);
		%>
			<div style="width:300px; height:200px; margin-top: 100px;">
			<img src="<%=item.getPicture() %>" style="width:200px; height:200px;" ><br />
			<span>id:<%=item.getId() %></span><br />
			<a href="details.jsp?id=<%=item.getId() %>"><span>goodsname:<%=item.getGoodsname() %></span></a>
			</div>
		<%
			}
		%>
  </body>
</html>

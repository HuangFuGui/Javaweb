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
		<h1>Show one items in database：mystream && table：goods by id</h1><hr>
		<%
			int i=0;
			ItemsDAO itemsDAO = new ItemsDAO();
		 	Items item = itemsDAO.getItemsByid(Integer.parseInt(request.getParameter("id")));
            if(item!=null)
            {
		%>		
			<div style="width:300px; margin-top: 100px;margin-bottom: 100px;">
			<img src="<%=item.getPicture() %>" style="width:200px;" ><br />
			<span>id:<%=item.getId() %></span><br />
			<span>goodsname:<%=item.getGoodsname() %></span>
			</div>
		<%
			}
		%>
		
		
		<%
		/*Cookies*/
		String list = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0)
		{
			for(Cookie c:cookies)
			{
				if(c.getName().equals("ListViewCookie"));
				{
					list = c.getValue();
				}
			}
		}
		list= list+request.getParameter("id")+",";
		String[] arr = list.split(",");
		if(arr!=null&&arr.length>0)
		{
		if(arr.length>1000)
		{
	     list="";
		}
		}
		Cookie cookie = new Cookie("ListViewCookie",list);
		cookie.setMaxAge(86400);
		response.addCookie(cookie);
		
		 %>
		 
		 
		 <h3>你浏览过的商品</h3><hr />
		 <%
		 ArrayList<Items> listItems = itemsDAO.getViewList(list);
		 if(listItems!=null&&listItems.size()>0)
		 {
			 for(Items it:listItems)
			 {
		
		%>	 
			<div style="width:300px; margin-top: 100px;">
			<img src="<%=it.getPicture() %>" style="width:200px;" ><br />
			<span>id:<%=it.getId() %></span><br />
			<a href="details.jsp?id=<%=it.getId() %>"><span>goodsname:<%=it.getGoodsname() %></span></a>
			</div>
	    <% 
	          }
		 }
		 %>
  </body>
</html>

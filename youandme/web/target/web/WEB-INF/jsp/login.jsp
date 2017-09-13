<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>


<head>
	<meta charset='GBK'>
	<title>login</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/login.css">
</head>

<body>
<div class="outerContainer">

	<div class="navContainer">
		<div class="nav">
			<a href="#">主页</a>
			<a href="#">关于</a>
			<span>语言：简体中文</span>
		</div>
	</div>

 	<div class="innerContainer">

		<span>欢迎使用 YouAndMe</span>
		<span>联系你的好友和更多精彩。获取你感兴趣的实时更新。并通过每个视角观看事件实时呈现。</span>
		<span>Copyright &copy; Huangfugui 2017<br /> Theme Social Contact</span>

		<div class="loginDiv">
			<input type="Text" placeholder="用户名,手机或邮箱" id="stringToLogin" class="inputTag" />
			<input type="Password" placeholder="密码" id="passwordToLogin" class="inputTag" />
			<div id="loginButton">
				登录
			</div>
			<input type="checkbox" id="checkBox" checked="checked" />
			<span>记住我</span>
		</div>

		<div class="registerDiv">
			<span>第一次使用 YouAndMe？</span><hr  />
			<input type="text" placeholder="用户名" class="inputTag" id="username"/>
			<input type="email" placeholder="邮箱" class="inputTag" id="email"/>
			<input type="password" placeholder="密码" class="inputTag" id="password"/>
			<div id="registerButton">
				注册 YouAndMe
			</div>
		</div>

	</div>
</div>
</body>

<script src="<%=basePath%>resources/js/jquery-2.0.0.min.js" type="text/javascript"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="<%=basePath%>resources/js/youandme.js" type="text/javascript" charset="GB2312"></script>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入jstl标签库 -->
<%@include file="common/tag.jsp"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset='GBK'>
  <title>login</title>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/userDetail.css">
  <script type="text/javascript" src="<%=basePath%>resources/js/jquery-2.0.0.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>resources/js/userDetail.js" charset="GBK"></script>
</head>


<body>
<div class="outerContainer">

  <!--导航栏 -->
  <div style="position:fixed;width:1366px;z-index:100;">
    <div class="navContainer">
      <div class="nav">
        <a href="../index">主页</a>
        <a href="#">关于</a>
        <span>YouAndMe</span>
        <!-- //TODO 搜索 -->
        <input type="text" class="navSearch" placeholder="搜索用户" />
        <span>语言：简体中文</span>
      </div>
    </div>
  </div>

  <div class="blueDiv">
    <img src="<%=basePath%>user_space/${userModel.headImg}">
    <span>${userModel.username}</span>
    <span>${userModel.email}</span>
    <span></span>
    <span>${userModel.address}</span>
    <span></span>
    <span>${userModel.joinTime}</span>
    <span>${userModel.description}</span>
  </div>

  <div class="littleNav">
    <a href="#">我的动态
      <span>${userModel.dynamicsNum}</span>
    </a>
    <a href="#">我的关注
      <span>${userModel.followingNum}</span>
    </a>
    <a href="#">我的粉丝
      <span>${userModel.followersNum}</span>
    </a>
    <button id="changeProfile">资料设置</button>
  </div>

</div>

<div class="dialogContainer"></div>
<div class="dialog">
  <span>更改你的信息</span>
  <img src="<%=basePath%>user_space/${userModel.headImg}" id="dialog_headImg">

  <form id="changeHeadImgForm">
  <a href="">
    <input type="file" name="headImg" class="dialog__input1" /><!-- 注意要给出name，否则后台无法取到-->
  </a>
  </form>

  <span></span>
  <input type="text" class="inputTag dialog_input2" value="${userModel.username}" id="dialog_value2">
  <span></span>
  <input type="text" class="inputTag dialog_input3" value="${userModel.email}" id="dialog_value3">
  <span></span>
  <input type="text" class="inputTag dialog_input4" value="${userModel.address}" id="dialog_value4">
  <span></span>
  <textarea type="text" id="dialog_value5">${userModel.description}</textarea>
  <button id="dialog_sureChange">确认修改</button>
</div>
<script type="text/javascript">
  var basePath = '<%=basePath%>';
</script>
</body>
</html>

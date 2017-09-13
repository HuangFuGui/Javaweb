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
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/plupload/css/jquery.plupload.queue.css">
    <script type="text/javascript" src="<%=basePath%>resources/plupload/js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/plupload/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/plupload/js/jquery.plupload.queue.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/plupload/js/zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/plupload.js" charset="GBK"></script>
</head>
<body>


<div class="outerContainer">

  <!--导航栏 -->
  <div style="position:fixed;width:1366px;z-index:100;">
    <div class="navContainer">
      <div class="nav">
        <a href="#">主页</a>
        <a href="#">关于</a>
        <span>YouAndMe</span>
        <!-- //TODO 搜索 -->
        <input type="text" class="navSearch" placeholder="Search User" />
        <span>语言：简体中文</span>
        <div class="navSearchResult"></div>
      </div>
    </div>
  </div>

  <div id="uploader">
    <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
  </div>

  <button id="toStop">暂停一下</button>
  <button id="toStart">继续上传</button>

  <span class="myShare">我共享的资料</span>
  <div class="myShareContent">
    <div class="myShareContent_tips_container">
    <span class="myShareContent_tips">你还没有共享资料，快上传吧~</span>
    </div>
  </div>

</div>
</body>

<script type="text/javascript">
    var basePath = '<%=basePath%>'
</script>

</html>

<%@ page import="java.util.prefs.BackingStoreException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--引入jstl标签库 -->
<%@include file="common/tag.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>index</title>
    <link href="<%=basePath%>resources/css/index.css" rel="stylesheet">
    <script src="<%=basePath%>resources/js/jquery-2.0.0.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>resources/js/index.js" type="text/javascript" charset="GB2312"></script>
</head>
<body>

<div class="outerContainer">

    <!--导航栏 -->
    <div style="position:fixed;width:1366px;z-index:100;">
        <div class="navContainer">
            <div class="nav">
                <a href="index">主页</a>
                <a href="#">关于</a>
                <span>YouAndMe</span>
                <!-- //TODO 搜索 -->
                <input type="text" class="navSearch" placeholder="Search User" />
                <span>语言：简体中文</span>
                <div class="navSearchResult"></div>
            </div>
        </div>
    </div>

    <!-- 用户部分信息 -->
    <div class="aboutUser">
        <div><img src="<%=basePath%>user_space/${userModel.headImg}"></div>

        <a href="${userModel.userId}/userDetail">${userModel.username}
            <h6><img src="<%=basePath%>resources/src/location.png"> ${userModel.address}</h6>
        </a>

        <a href="#">Dynamics
            <span>${userModel.dynamicsNum}</span>
        </a>
        <a href="#">Following
            <span>${userModel.followingNum}</span>
        </a>
        <a href="#">Followers
            <span>${userModel.followersNum}</span>
        </a>
    </div>


    <!-- 中间动态部分 -->
    <div class="DynamicsContainer">

        <!-- 发表动态div -->
        <div class="postDynamicsDiv">
            <!--用户头像 -->
            <img src="<%=basePath%>user_space/${userModel.headImg}">
            <!--发表动态form表单 -->
            <form id= "postDynamicsForm" enctype="multipart/form-data">

            <textarea placeholder="What's in your mind now ?" name="dynamicsText"></textarea>
            <span></span>
            <span>Share your life with others.</span>
            <a>
                <input type="file" name="dynamicsFile" />
            </a>
           <!-- <button id="dynamicsButton">Post</button>-->  <!--form表单内用button标签默认get方式！地址栏会改变 -->
            <input type="button" value="Post" class="postButton" id="dynamicsButton"/>

            </form>

        </div>

        <!-- 用户动态 -->
        <div id="dynamicsContainerFade"></div>
        <c:forEach var="dynamics" items="${dynamicsModel}">
            <c:set var="dynamicsFileType" value="${dynamics.dynamicsFile}"></c:set>
            <c:set var="dynamicsFileTypeFinal" value="${fn:substringAfter(dynamicsFileType,'.')}"></c:set>
            <!--动态为文字+图片 -->
            <c:if test="${dynamicsFileTypeFinal =='jpg'||dynamicsFileTypeFinal =='png'}">
                <div class="DynamicsDiv" id="${dynamics.dynamicsId}">
                    <!--用户头像 -->
                    <img src="<%=basePath%>user_space/${dynamics.user.headImg}">
                    <span>${dynamics.user.username}</span><!--发表动态的用户名字-->
                    <span><fmt:formatDate value="${dynamics.createTime}" pattern="yyyy/MM/dd HH:mm:ss"></fmt:formatDate></span>
                    <span></span>
                    <span>浏览(0)</span>
                    <span>${dynamics.dynamicsText}</span>
                    <img src="<%=basePath%>user_space/${dynamics.dynamicsFile}">
                    <span><h4>16</h4></span>
                    <span onclick="likeIt(this)" id='${dynamics.dynamicsId}like'><h4 id='${dynamics.dynamicsId}likeh4'>${dynamics.likeNum}</h4></span>
                    <span onclick="commentIt(this)"><h4>13</h4></span>
                </div>
            </c:if>
            <!--动态为文字+视频 -->
            <c:if test="${dynamicsFileTypeFinal=='mp4'}">
                <div class="DynamicsDiv" id="${dynamics.dynamicsId}">
                    <img src="<%=basePath%>user_space/${dynamics.user.headImg}">
                    <span>${dynamics.user.username}</span>
                    <span><fmt:formatDate value="${dynamics.createTime}" pattern="yyyy/MM/dd HH:mm:ss"></fmt:formatDate></span>
                    <span></span>
                    <span>浏览(0)</span>
                    <span>${dynamics.dynamicsText}</span>
                    <video
                            controls width="640" height="360">
                        <source src="<%=basePath%>user_space/${dynamics.dynamicsFile}" type='video/mp4' />
                    </video>
                    <span><h4>16</h4></span>
                    <span onclick="likeIt(this)" id='${dynamics.dynamicsId}like'><h4 id='${dynamics.dynamicsId}likeh4'>${dynamics.likeNum}</h4></span>
                    <span onclick="commentIt(this)"><h4>13</h4></span>
                </div>
            </c:if>

        </c:forEach>

    </div>

    <div class="searchToContact">
        <span></span>
        <span>Click here to contact</span>
    </div>
    <div class="searchUserToContact">
        <div class="searchUserToContact_head" title="close">
            <span>YouAndMe Chat</span>
            <span></span>
        </div>
        <div class="searchUserToContact_body">
            <div class="searchUserToContact_body_tips">
                <span>Now type in and search friends to contact</span>
            </div>
        </div>
        <input type="text" class="searchUserToContact_search" placeholder="Search friends..." />
    </div>

    <div class="contactDivFade">
        <span></span>
        <span>Open tab</span>
    </div>
    <div class="contactDivTrue">
        <div class="contactDivTrue_left"></div>
        <div class="contactDivTrue_right">
            <div class="contactDivTrue_right_head"></div>
            <div class="contactDivTrue_right_content" id="forScroll">
                <!--聊天内容 -->
            </div>
            <input type="text" placeholder="Communicate..." class="contactDivTrue_right_input" />
        </div>
        <span class="useToClose" title="缩放窗口"></span>
    </div>

</div>

<div class="dynamisDetailOuter"></div>
<div class="dynamisDetailContainer">
    <!--
    <button>
        <img src="src/attention.png">
        <span>关注</span>
    </button>
    -->
</div>

<div id="newsTips" style="display:none"></div>

</body>
<script type="text/javascript">
    var basePath = '<%=basePath%>';
    var socketPath = '<%=socketPath%>';
    var sendUid = ${userModel.userId};
    var sendName = '${userModel.username}';
    var to = -1;

    var arrayCur = 0;
    var arrayObj = new Array(10);

    var myInterval;
    var intervalFlag = 0;

    var newsArrayCur = 0;
    var newsArrayObj = new Array(10);
    var hasOpenedTab = 0;
</script>
</html>
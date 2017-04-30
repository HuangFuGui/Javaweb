<%--
  Created by IntelliJ IDEA.
  User: huangfugui
  Date: 2016/10/28
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Update</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>resources/css/index.css" rel="stylesheet">
</head>
<body>

    <h4 class="curAccount" id="curAccount">Welcome！${account.account}</h4>

    <div class="page-header">
        <c:set var="contact" value="${contact}"></c:set>
        <c:if test="${contact==null}">
            <h1>&nbsp;Fill the following information <small>to add a contact!</small></h1>
        </c:if>
        <c:if test="${contact!=null}">
            <h1>&nbsp;Fill the following information <small>to modify a specific contact!</small></h1>
        </c:if>
    </div>

    <h3 class="selectHeadImg">选择头像：</h3>
    <form id="picForm" class="selectImgForm">
        <input type="file" id="imgChange" name="imgChange" />
    </form>

    <!--新添联系人View -->
    <c:if test="${contact==null}">
        <img src="<%=basePath%>resources/img/defaultHeadImg.png" class="update_head_img" id="update_head_img"/>

        <h3 class="update_name">Name：</h3>
        <input type="text" class="form-control update_name_input" placeholder="Name" id="contactName">

        <h3 class="update_phone">Phone：</h3>
        <input type="text" class="form-control update_phone_input" placeholder="Phone" id="contactPhone">

        <h3 class="update_address">Address：</h3>
        <input type="text" class="form-control update_address_input" placeholder="Address" id="contactAddress">

        <button type="button" class="btn btn-primary updateBtn" id="trulyAdd">添加联系人</button>
    </c:if>
    <!--修改联系人操作View -->
    <c:if test="${contact!=null}">
        <img src="<%=basePath%>resources/img/${contact.headImg}" class="update_head_img" id="update_head_img"/>

        <h3 class="update_name">Name：</h3>
        <input type="text" class="form-control update_name_input" placeholder="Name" id="contactName" value="${contact.contactName}">

        <h3 class="update_phone">Phone：</h3>
        <input type="text" class="form-control update_phone_input" placeholder="Phone" id="contactPhone" value="${contact.contactPhone}">

        <h3 class="update_address">Address：</h3>
        <input type="text" class="form-control update_address_input" placeholder="Address" id="contactAddress" value="${contact.contactAddress}">

        <button type="button" class="btn btn-warning updateBtn" id="trulyUpdate">修改联系人</button>
    </c:if>

    <script src="<%=basePath%>resources/js/jquery-2.0.0.min.js"></script>
    <script src="<%=basePath%>resources/js/index.js" type="text/javascript"></script>
    <script type="text/javascript">
        var basePath = '<%=basePath%>';
        var headImgPath = 'defaultHeadImg.png';
        if(${contact!=null}){
            headImgPath = '${contact.headImg}';
        }
    </script>
</body>
</html>

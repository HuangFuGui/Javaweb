<%--
  Created by IntelliJ IDEA.
  User: huangfugui
  Date: 2016/10/22
  Time: 22:04
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
    <title>Phone book</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>resources/css/index.css" rel="stylesheet">
</head>
<body>

    <c:set var="accountLength" value="${fn:length(account.account)}"></c:set>
    <c:if test="${accountLength==0}">
        <h4 style="color: red" class="curAccount" id="curAccount">你还没登录！</h4>
    </c:if>
    <c:if test="${accountLength>0}">
        <h4 class="curAccount" id="curAccount">Welcome！${account.account}</h4>
    </c:if>

    <div class="page-header">
        <h1>&nbsp;This is your phone book <small>Say Hi to your friends!</small></h1>
    </div>

    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#registerDiv" style="float: right; margin-right: 30px;">
        Register
    </button>
    <div class="modal fade" id="registerDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">Fill the information to register</h4>
                </div>
                <div class="modal-body" id="registerBody">
                    <div class="input-group input-group-lg" style="width: 80%;margin: 0 auto;">
                        <span class="input-group-addon">@</span>
                        <input type="text" class="form-control" placeholder="Username" id="registerUsername">
                    </div>
                    <div class="input-group input-group-lg" style="margin: 0 auto;margin-top: 20px; width: 80%">
                        <span class="input-group-addon">**</span>
                        <input type="password" class="form-control" placeholder="Password" id="registerPassword">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="registerBtn">Submit</button>
                </div>
            </div>
        </div>
    </div>

    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#loginDiv" style="float: right;margin-right: 15px;">
        Login
    </button>
    <div class="modal fade" id="loginDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel2">Login to manage your phone book</h4>
                </div>
                <div class="modal-body" id="loginBody">
                    <div class="input-group input-group-lg" style="width: 80%;margin: 0 auto;">
                        <span class="input-group-addon">@</span>
                        <input type="text" class="form-control" placeholder="Username" id="loginUsername">
                    </div>
                    <div class="input-group input-group-lg" style="margin: 0 auto;margin-top: 20px; width: 80%">
                        <span class="input-group-addon">**</span>
                        <input type="password" class="form-control" placeholder="Password" id="loginPassword">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success" id="loginBtn">Submit</button>
                </div>
            </div>
        </div>
    </div>

    <button type="button" class="btn btn-primary" id="addContactsBtn">
        Add Contacts
    </button>
    <table class="table table-hover table_me">
        <%--表头信息--%>
        <thead>
        <tr class="row">
            <th class="col-md-1">ID</th>
            <th class="col-md-2">Head</th>
            <th class="col-md-2">Name</th>
            <th class="col-md-2">Phone</th>
            <th class="col-md-3">Address</th>
            <th class="col-md-2">Modify</th>
        </tr>
        </thead>

        <%--表格内容--%>
        <tbody id="tbody">
        <c:forEach var="contact"  items="${contacts}">
            <tr class="row" id="row${contact.contactId}">
                <td class="col-md-1">${contact.contactId}</td>
                <td class="col-md-2"><img src="<%=basePath%>resources/img/${contact.headImg}" class="head_img"/></td>
                <td class="col-md-2">${contact.contactName}</td>
                <td class="col-md-2">${contact.contactPhone}</td>
                <td class="col-md-3">${contact.contactAddress}</td>
                <th class="col-md-2">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" id="update${contact.contactId}" onclick="updateContact(this)">Update</button>
                        <button type="button" class="btn btn-default" id="delete${contact.contactId}" onclick="deleteContact(this)">Delete</button>
                    </div>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <script src="<%=basePath%>resources/js/jquery-2.0.0.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/js/index.js" type="text/javascript"></script>
    <script type="text/javascript">
        var basePath = '<%=basePath%>';
    </script>
</body>
</html>

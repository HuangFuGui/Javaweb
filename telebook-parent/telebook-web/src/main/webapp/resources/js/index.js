$(function(){

    /**
     * 注册功能：
     * 1.用户名或密码不能为空
     * 2.用户名不能重复注册
     * 3.可能的注册错误
     * 4.注册成功后弹出登录模态框
     */
    $("#registerBtn").on('click',function(){

        //注册前端
        $(".noticeSpan").css('display','none');
        if($("#registerUsername").val()==""){
            $("#registerBody").append('<span class="noticeSpan">'+"ERROR: Username can not be empty!"+'</span>');
            return;
        }
        if($("#registerPassword").val()==""){
            $("#registerBody").append('<span class="noticeSpan">'+"ERROR: Password can not be empty!"+'</span>');
            return;
        }

        //发送注册请求
        $.ajax({
            type:"POST",
            url:"register",
            data:{username:$("#registerUsername").val(),password:$("#registerPassword").val()},
            success:function(result){
                if(result['success']==false){
                    if(result['data']==-1){
                        $("#registerBody").append('<span class="noticeSpan">'+"ERROR: Username has been registered!"+'</span>');
                        return;
                    }else if(result['data']==0){
                        $("#registerBody").append('<span class="noticeSpan">'+"ERROR: Register failed,please try again!(internal error)"+'</span>');
                        return;
                    }
                }else{
                    alert("注册成功");
                    $("#registerDiv").modal('hide');
                    $("#loginDiv").modal('show');
                }
            },
            error:function(){
                alert("Register internal error!");
            }
        });
    });


    /**
     * 登录功能
     * 1.用户名或密码不能为空
     * 2.用户名或密码错误
     * 3.登录成功后显示id：curAccount显示欢迎+用户名的友好提示
     * 4.异步ajax请求实现登陆后显示联系人列表
     */
    $("#loginBtn").on('click',function(){

        //登录前端
        $(".noticeSpan").css('display','none');
        if($("#loginUsername").val()==""){
            $("#loginBody").append('<span class="noticeSpan">'+"ERROR: Username can not be empty!"+'</span>');
            return;
        }
        if($("#loginPassword").val()==""){
            $("#loginBody").append('<span class="noticeSpan">'+"ERROR: Password can not be empty!"+'</span>');
            return;
        }

        //发送登录请求
        $.ajax({
            type:"POST",
            url:"login",
            data:{username:$("#loginUsername").val(),password:$("#loginPassword").val()},
            success:function(result){
                if(result['success']==false){
                    $("#loginBody").append('<span class="noticeSpan">'+"ERROR: Username or Password error!"+'</span>');
                    return;
                }
                else{
                    $("#loginDiv").modal('hide');
                    $("#tbody").empty();
                    //这种修改的情况一般都用id选择元素，class定义样式，尽量不要用class选择元素，否则css文件无效！
                    //class一般用于广泛定义样式！
                    $("#curAccount").replaceWith('<h4 class="curAccount" id="curAccount">Welcome！'+$("#loginUsername").val()+'</h4>');

                    var list = result['data'];
                    if(list==null){
                        return;//list为null时list.length会报错
                    }
                    for(var i=0;i<list.length;i++){
                        $("#tbody").append('<tr class="row" id="row'+list[i]['contactId']+'"></tr>');
                        $("#row"+list[i]["contactId"]).append(
                            '<td class="col-md-1">'+list[i]['contactId']+'</td>'+
                            '<td class="col-md-2">'+
                            '<img src="'+basePath+'resources/img/'+list[i]['headImg']+'" class="head_img"/></td>'+
                            '<td class="col-md-2">'+list[i]['contactName']+'</td>'+
                            '<td class="col-md-2">'+list[i]['contactPhone']+'</td>'+
                            '<td class="col-md-3">'+list[i]['contactAddress']+'</td>'+
                            '<th class="col-md-2">'+
                            '<div class="btn-group">'+
                            '<button type="button" class="btn btn-default" id="update'+list[i]['contactId']+'" onclick="updateContact(this)">Update</button>'+
                            '<button type="button" class="btn btn-default" id="delete'+list[i]['contactId']+'" onclick="deleteContact(this)">Delete</button>'+
                            '</div>'+
                            '</th>');
                    }
                }
            },
            error:function(){
                alert("Login internal error!");
            }
        });
    });

    /**
     * 添加联系人页面跳转
     */
    $("#addContactsBtn").on('click',function(){
        window.location.href=-1+"/update";
    });

    /**
     * 添加/修改联系人都有的头像改变
     */
    $("#imgChange").on('change',function(){
        //form表单是同步请求，请求未完成前会锁住浏览器，而且请求完成后会跳转页面。
        //ajax是用js提交请求，不会跳转页面。可同步也可异步。
        //Note that synchronous requests may temporarily lock the browser,
        //disabling any actions while the request is active.
        var formData = new FormData($("#picForm")[0]);
        $.ajax({
            type:"POST",
            url:"../headImgChange",
            data:formData,
            async: false,//异步为false，即同步
            cache: false,
            contentType: false,
            processData: false,
            success:function(result){
                if(result['success']==true){//图片已经上传到指定路径成功
                    //（添加/修改联系人）更改头像时将服务器返回的图片名赋予js全局变量headImgPath
                    headImgPath = result['data'];
                    $("#update_head_img").attr('src',basePath+"resources/img/"+headImgPath);
                }
                else{//图片上传失败
                    alert("Upload failed!");
                }
            },
            error:function(){
                alert("HeadImg changes internal error!");
            }
        });
    });

    /**
     *添加联系人实现
     */
    $("#trulyAdd").on('click',function(){
        $.ajax({
            type:"POST",
            url:"../insertContact",
            data:{
                headImgPath:headImgPath,
                contactName:$("#contactName").val(),
                contactPhone:$("#contactPhone").val(),
                contactAddress:$("#contactAddress").val()
            },
            success:function(result){
                if(result['success']==true){
                    alert("添加联系人成功");
                    window.location.href="../index";
                }
                else{
                    alert("添加联系人失败，请重试");
                }
            },
            error:function(){
                alert("Add contact internal error!");
            }
        });
    });

    /**
     * 更新联系人实现
     */
    $("#trulyUpdate").on('click',function(){
        $.ajax({
            type:"POST",
            url:"updateContact",
            data:{
                headImgPath:headImgPath,
                contactName:$("#contactName").val(),
                contactPhone:$("#contactPhone").val(),
                contactAddress:$("#contactAddress").val()
            },
            success:function(result){
                if(result['success']==true){
                    alert("更新联系人成功");
                    window.location.href="../index";
                }
                else{
                    alert("更新联系人失败，请重试");
                }
            },
            error:function(){
                alert("Add contact internal error!");
            }
        });
    });

});

/**
 * 删除联系人
 * @param obj
 */
var deleteContact = function(obj){

    var id = obj.id.substr(6);
    $.ajax({
        type:"POST",
        url:"deleteContact",
        data:{contactId:id},
        success:function(result){
            if(result['success']==true){
                $("#row"+id).remove();
            }else{
                alert("Delete failed!");
            }
        },
        error:function(){
            alert("Delete internal error!");
        }
    });
};

/**
 * 更新联系人页面跳转
 * @param obj
 */
var updateContact = function(obj){
    var id = obj.id.substr(6);
    window.location.href=id+"/update";
};
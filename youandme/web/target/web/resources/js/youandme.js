$(function(){

    /*记住我*/
    var stringToLoginFromCookie = $.cookie("stringToLogin");
    var passwordToLoginFromCookie = $.cookie("passwordToLogin");
    if(stringToLoginFromCookie!=null && passwordToLoginFromCookie!=null && stringToLoginFromCookie!="null"){
        //存在用户登录的cookie信息
        $("#stringToLogin").val($.cookie("stringToLogin"));
        $("#passwordToLogin").val($.cookie("passwordToLogin"));
    }else{
        //不存在用户登录的cookie信息
        $("#stringToLogin").val("");
        $("#passwordToLogin").val("");
    }


    /*注册*/
    $("#registerButton").on('click',function(){

        var username = $("#username").val();
        var password = $("#password").val();
        var email = $("#email").val();
        $.post("registerUser",{username:username,password:password,email:email},function(result){

            alert(result['info']);
            var user = result['data'];
            alert(user['username']);
            $("#username").val("");
            $("#email").val("");
            $("#password").val("");
            $("#stringToLogin").val(user['username']);
            $("#passwordToLogin").val("");
        });
    });

    /*登录*/
    $("#loginButton").on('click',function(){

        var stringToLogin = $("#stringToLogin").val();
        var password = $("#passwordToLogin").val();
        $.post("userLogin",{stringToLogin:stringToLogin,password:password},function(result){

            var isSuccess = result['success'];
            if(isSuccess){//登录成功

                if($("#checkBox").is(":checked")==true){//checkBox被选中
                    //用户信息写入cookie
                    /*$.cookie("stringToLogin",stringToLogin,{expires:7,path:"/"});  硬盘cookie
                    $.cookie("passwordToLogin",password,{expires:7,path:"/"});*/
                    $.cookie("stringToLogin",stringToLogin);//会话cookie
                    $.cookie("passwordToLogin",password);

                }else{//清除可能存在的cookie
                    $.cookie("stringToLogin",null) ;
                    $.cookie("passwordToLogin",null);
                }
                window.location.href= "index";//客户端重定向至首页
            }
            else{//登录失败
                var loginInfo = result['info'];
                alert(loginInfo);//提示失败信息
            }
        });
    });

});

$(function(){

	$("#changeProfile").on('click',function(){
		$(".dialogContainer,.dialog").fadeIn(300);
		$(".dialog").css('transform','scale(1)');
		$("body,html").css('overflow-y','hidden');
	});


	$(".dialogContainer").on('click',function(){

		$(".dialogContainer,.dialog").fadeOut(300,function(){
			$("body,html").css('overflow-y','auto');
			$(this).removeAttr('style');
		});
	});

	$(".dialog__input1").on('change',function(){
		var formData = new FormData($( "#changeHeadImgForm" )[0]);
		$.ajax({
			url: '../changeHeadImg',
			type: 'POST',
			data: formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success:function(result){

				var headRoad = result['data'];
				$("#dialog_headImg").attr('src',basePath+"user_space/"+headRoad);
			},
			error:function(){
				alert("更改头像失败");
			}
		});
	});

	$("#dialog_sureChange").on('click', function () {

		var newUsername = $("#dialog_value2").val();
		var newEmail = $("#dialog_value3").val();
		var newAddress = $("#dialog_value4").val();
		var newDescription = $("#dialog_value5").val();
		//alert(newUsername+newEmail+newAddress+newDescription);
		$.ajax({
			url:'../changeInfo',
			type:'POST',
			data:{newUsername:newUsername,newEmail:newEmail,newAddress:newAddress,newDescription:newDescription},
			success:function(result){
				if(result['success']==true){
					window.location.href = "userDetail";//默认是只改变URL最后一个匹配字符串
				}
				else{
					alert(result['info']);
				}
			},
			error:function(){
				alert("System inner error！");
			}
		});
	});

});
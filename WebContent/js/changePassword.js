window.onload=function(){

	var oldpassword = "";
	var newpassword = "";
	var againpassword = "";
	
	$("#oldPassword").bind("focus",function(){
		$("#mqReminderOld").css({"display":"none"});
	})
	
	$("#oldPassword").bind("blur",function(){
		oldpassword = $(this).attr("value");
		if(oldpassword == ""){
			$("#mqReminderOld").css({"display":"block"});
			$("#mqReminderOld").html("原始密码不能为空!");
		}  
	})

	$("#password").bind("focus",function(){
		newpassword = $(this).attr("value");
		$("#mqReminderNew").html("6-10位字符：只能是数字或字母");
		$("#mqReminderNew").css({"display": "block"});
		$("#mqReminderVer").css({"display": "none"});
	});
	
	$("#password").bind("blur",function(){
		newpassword = $(this).attr("value");
		if(newpassword == ""){
			$("#mqReminderNew").css({"display":"block"});
			$("#mqReminderNew").html("新密码不能为空");
		}else if(newpassword.length<6){
			$("#mqReminderNew").html("新密码长度过短，请重新输入！");
		}else{		
			var str = "^[0-9a-zA-Z]{6,15}$";
			var s = newpassword.search(str);
			var affirm = $("#affirm").attr("value");
			if(s !== 0){
				$("#mqReminderNew").html("密码输入有误，只能是6-10位字符,包含数字或字母");
				$("#mqReminderNew").css({"display": "block"});
			}else{
				$("#mqReminderNew").css({"display": "none"});		
			}	
		}
	})
	$("#affirm").bind("blur",function(){
		againpassword = $(this).attr("value");
		var str = $("#password").attr("value");
		if(str != againpassword){
			$("#mqReminderVer").html("两次输入密码不相符，请重新输入");
			$("#mqReminderVer").css({"display": "block"});
		}else{	
			$("#mqReminderVer").css({"display": "none"});
		}				
	})
	
	$("#submit").bind("click",function(){
		if(newpassword != againpassword){
			$("#mqReminderVer").html("两次输入密码不相符，请重新输入");
			$("#mqReminderVer").css({"display": "block"});
			return;
		}else{	
			$("#mqReminderVer").css({"display": "none"});
		}	
		
	    var Json = {
	    		myoldPassword:oldpassword,
	    		mynewPassword:newpassword
	    }
		$.ajax({
			type:'POST',
			url:'/shareIdea/UserBaseInfo_modifyPassWord.action',
			data:Json,
			dataType:'json',
			async:true,
			success:function(data){
				
				if(data[0].isTrue==false){//如果原始密码不正确
					$("#mqReminderOld").css({"display":"block"});
					if(Json.myoldPassword==""){
						$("#mqReminderOld").html("原始密码不能为空!");
						alert("原始密码不能为空");
					}else{
						$("#mqReminderOld").html("原始密码不正确!");
						alert("原始密码不正确");
					}
				}else{
					
					if(data[1].isRepeat==true){//新密码与原始密码重复
						$("#mqReminderNew").css({"display":"block"});
						if(Json.mynewPassword==""){
							$("#mqReminderNew").html("新密码不能为空!");	
							alert("新密码不能为空");
						}else{
							$("#mqReminderNew").html("新密码不能与原始密码重复！");
							alert("新密码不能与原始密码重复");
						}
						
					}else{
						$("#mqReminderNew").css({"display":"none"});
						$("#modifyTip").html("密码修改成功!");
						alert("密码修改成功");
						$("input[type=password]").val("");
					}
				}	
			},
			error:function(data){
				alert("后台运行异常");
			}
		});
		
	})
	
	$("#again").bind("click",function(){
		newpassword="";
		oldpassword="";
		againpassword="";
		$("#modifyTip").html("");
		$("#mqReminderOld").css({"display":"none"});
		$("#mqReminderNew").css({"display":"none"});
		$("#mqReminderVer").css({"display":"none"});
	});
   
}	



$(document).ready(function(){ 
			
			jQuery.ssp={};          
			jQuery.ssp.mq_forgetpassword3={};   
			$.ssp.mq_forgetpassword3=function(){  

					//新密码输入框失去焦点事件
					$("#password").bind("blur",function(){
						var str = $(this).attr("value");
						if(str.length < 6){
							$("#claim").css({"display":"block"});
						}
						else{
							$("#claim").css({"display":"none"});
						}
					});

					//取消按钮点击事件
					$("#cancel").bind("click",function(){
						window.location.href = "login.jsp";
					});
			}

			$.ssp.mq_forgetpassword3();   
			affirmpasswordOnblur();
			finishClick();
});

function affirmpasswordOnblur(){
	$("#affirm").bind("blur",function(){
		var affirmPass = $(this).attr("value");
		//alert("affirmPass=  " + affirmPass);
		var newPassword = $("#password").attr("value");
		//alert("newPassword +  " + newPassword);
		if(affirmPass == newPassword){
			$("#affirmPassword").css({"display":"none"});
		}
		else{
			$("#affirmPassword").css({"display":"block"});
		}
	});
}

function finishClick(){
	$("#finish").bind("click",function(){
		var str1 = $("#claim").css("display");
		var str2 = $("#affirmPassword").css("display");
		//alert("str1 = " + str1 + "; str2 = " + str2);
		if(str1 == "none" && str2 == "none"){
			$("#finish").submit();
			document.mqforgetThird.submit();
		}
	});
}
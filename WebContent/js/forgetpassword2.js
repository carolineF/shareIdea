$(document).ready(function(){ 
			
			jQuery.ssp={};          
			jQuery.ssp.mq_forgetpassword2={};   
			$.ssp.mq_forgetpassword2=function(){  

				//上一步按钮跳转忘记密码1界面
				$("#back").bind("click",function(){
					window.location.href = "forgetpassword1.html";
				});

				//点击手机验证
				$("#tel").bind("click",function(){
					$(".mqSelect").removeClass("mqSelect");
					$(this).addClass("mqSelect");
				});

				//点击邮箱验证
				$("#email").bind("click",function(){
					$(".mqSelect").removeClass("mqSelect");
					$(this).addClass("mqSelect");
				});
			}

			$.ssp.mq_forgetpassword2();
			getVerify();
		})
		
		
	var stopTime = false;	
		
function getVerify(){
	$("#getverify").bind("click",function(){
		alert("准备Ajax");
		$.ajax({
			type: "POST",
			async: true,
			url: "Idea/SendEmail_getPasswordByEmail.action",
			success: function(data){
				stopTime=true;
			},
			error:function(data){
				// 验证码失败处理
				alert("验证码获取错误，请重新获得");
			}
		});  
		time($("#getverify"),60);
	});
}

function time(obj,wait){
	var timeOut;
	if (wait == 0 || stopTime) {
    	clearTimeout(timeOut);
    	$(obj).removeAttr("disabled");            
    	$(obj).attr("value","获取验证码");
    	wait = 0;
    }else{
    	$(obj).attr("disabled", true);
    	$(obj).attr("value","重新发送(" + wait + ")");
        wait--;
        timeOut = setTimeout(function(){
            time(obj,wait);
        },
        1000);
   	}
}

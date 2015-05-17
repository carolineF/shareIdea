$(document).ready(function(){ 
			jQuery.ssp={};          
			jQuery.ssp.mq_forgetpassword1={};    
			$.ssp.mq_forgetpassword1=function(){  

				var username = $("#username");
				username.bind("click",function(){
					var str = $(this).attr("value");
					if (str === "手机号码/邮箱账号") {
						$(this).attr("value","");
						$(this).css({"color":"black"});
					}
				});		
				username.bind("blur",function(){
					var text = $(this).attr("value");
					if(text === ""){
						$("#account").css({"display": "block"});
					}else{
						$("#account").css({"display":"none"});
					}
				});

			}

			$.ssp.mq_forgetpassword1();              
		})
		
		function reloadVerify(){
			window.location = "UserBaseInfo_image";
		}
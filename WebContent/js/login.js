$(document).ready(function(){ 
			
			jQuery.ssp={};          
			jQuery.ssp.mq_login={};   
			$.ssp.mq_login=function(){  

				//点击账号输入框事件
				var username = $("#username");
				username.bind("click",function(){
					var str = $(this).attr("value");
					if (str === "手机号码/邮箱账号") {
						$(this).attr("value","");
						$(this).css({"color":"black"});
					}
				});		

				//账号输入框失去焦点事件
				username.bind("blur",function(){
					var text = $(this).attr("value");
					if(text === ""){
						$("#account").css({"display": "block"});
					}else{
						$("#account").css({"display":"none"});
					}
				});
				
				//注册按钮点击事件
				$("#register").bind("click",function(){
					window.location= "register.jsp";
				});
				//登陆
				$('#loginbtn').bind('click',function(){ 
					document.login.submit();
				})
			}

			$.ssp.mq_login();              
		})
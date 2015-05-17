var stopTime = false;
$(document).ready(function(){ 
			jQuery.ssp={};          
			jQuery.ssp.mq_register={};   
			$.ssp.mq_register=function(){
				//发送验证码
				$("#getverify").bind("click",function(){
					var emailNo= document.getElementById("number");
					var errorMsg = $("#number").attr("errorMsg");
					if(emailNo.value == ""){
						alert("请先填写账号！");
					}else if(errorMsg != ""){
						alert(errorMsg);
					}else if($(".label").html() == "邮箱账号"){
						var dataJson = {
								email: emailNo.value
							}
							$.ajax({
								type: "POST",
								dataType: "json",
								async: true,
								url: "/shareIdea/SendEmail_execute.action",
								data: dataJson,
								error:function(data){
									// 验证码失败处理
									$(".reminder").html("验证码发送失败，请稍后重新发送！");
									$(".errorBorder").css({"display": "block"});
								}
							});  
							time($("#getverify"),60);
					}else{
						//发送手机号码
						alert("发送手机验证码！")
					}
				});

				//验证昵称是否存在
				$("#name").bind("blur",function(){
					var nameVal = $("#name").val();
					var nameJson = {
							nickName:nameVal
					}
					$.ajax({
						type: "POST",
						dataType: "json",
						async: true,
						url: "/shareIdea/UserBaseInfo_checkUser.action",
						data: nameJson,
						success: function(data){
							var JsonObj = data;
							if(JsonObj.isExist){
								$(".reminder").html("该用户已存在！");
								$(".errorBorder").css({"display": "block"});
								$("#name").attr("right",false);
								$("#name").attr("errorMsg","该用户已存在！");
							}else{
								$(".errorBorder").css({"display": "none"});
								$("#name").attr("right",true);
								$("#name").attr("errorMsg","");
							}
						},
						error:function(data){
						}
					});  
				});
				
				
				// 点击手机注册
				$("#tel").bind("click",function(){
					$(".click").removeClass("click");
					$(this).addClass("click");
					$(".label").html("手机号码");
					$("#number").attr("errorMsg","请输入手机号码！");
					$("#number").attr("value","");
//					$("#getverify").removeAttr("disabled");
//					$("#getverify").attr("value","获取验证码");
				});

				// 点击邮箱注册
				$("#email").bind("click",function(){
					$(".click").removeClass("click");
					$(this).addClass("click");
					$("#number").attr("errorMsg","请输入邮箱账号！");
					$(".label").html("邮箱账号");
					$("#number").attr("value","");
//					$("#getverify").removeAttr("disabled");
//					$("#getverify").attr("value","获取验证码");
				});

				//点击注册按钮跳转到登录页面
				$('#registerbtn').bind('click',function(){ 
					//1.检测所有类名称为.blur的对象的right属性是否为true
					//2.检测所有类名称为.blur的对象的value属性是否为空
					var obj = $(".onblur");
					var i;
					for(i=0; i<obj.length; i++){
						if($(obj[i]).attr("right")!="true"|| $(obj[i]).attr("value")==""){
							//不能提交数据,并显示当前的错误信息，在每个输入框中添加错误信息属性
							$(".reminder").html($(obj[i]).attr("errorMsg"));
							$(".errorBorder").css({"display": "block"});
							break;
						}
					}
					if(i>=obj.length){
						document.myForm.submit();
					}
				});

				//输入框失去焦点事件
				$(".onblur").bind("blur",function(){
					var id = $(this).attr("id");

					var flag = $(".label").text();
					var rightFlag = false;
					//验证账号是否存在
					if(id=="number"){
						var accountVal = $("#number").val();
						var accountJson = {
								accountNo:accountVal
						}
						if(flag == "手机号码"){
							//判断是手机号码输入框
							var text = $(this).attr("value");
							if(text == ""){
								$(".reminder").html("请输入手机号码！");
								$(".errorBorder").css({"display": "block"});
								$("#number").attr("right",false);
								$("#number").attr("errorMsg","请输入手机号码！");
							}else{
								var str = "^1[3,4,8,5,9][0-9]{9}$";
								var s = text.search(str);
								if(s !== 0){
									$(".reminder").html("手机号码有误，请重新输入！");
									$(".errorBorder").css({"display": "block"});
									$("#number").attr("right",false);
									$("#number").attr("errorMsg","手机号码有误，请重新输入！");
								}else{
									rightFlag = true;
								}
							}
						}else{
							//判断是邮箱账号输入框
							var text = $(this).attr("value");
							if(text == ""){
								$(".reminder").html("请输入邮箱账号！");
								$(".errorBorder").css({"display": "block"});
								$("#number").attr("right",false);
								$("#number").attr("errorMsg","请输入邮箱账号！");
							}
							else{
								var str = "^[a-zA-Z0-9]+([._\\-]*[a-zA-Z0-9])*@([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+.){1,63}[a-zA-Z0-9]+$";
								var s = text.search(str);
								if(s !== 0){
									$(".reminder").html("请输入正确的邮箱账号！");
									$(".errorBorder").css({"display": "block"});
									$("#number").attr("right",false);
									$("#number").attr("errorMsg","请输入正确的邮箱账号！");
								}else{
									rightFlag = true;
								}
							}
						}
						if(rightFlag){
							$.ajax({
								type: "POST",
								dataType: "json",
								async: true,
								url: "/shareIdea/UserBaseInfo_checkAccount.action",
								data: accountJson,
								success: function(data){
									var JsonObj = data;
									if(JsonObj.isExist){
										$(".reminder").html("该账号已注册过，请直接登录！");
										$(".errorBorder").css({"display": "block"});
										$("#number").attr("right",false);
										$("#number").attr("errorMsg","该账号已注册过，请直接登录！");
									}else{
										$(".errorBorder").css({"display": "none"});
										$("#number").attr("right",true);
										$("#number").attr("errorMsg","");
									}
								},
								error:function(data){
								}
							});  
						}
					}
					
					//判断是验证码输入框
					if(id == "verify"){
						var text = $(this).attr("value");
						if(text == ""){
							$(".reminder").html("请输入验证码！");
							$(".errorBorder").css({"display": "block"});
							$("#verify").attr("right",false);
							$("#verify").attr("errorMsg","请输入验证码！");
						}
						else{
							var secCodeJson = {secCode:text};
							$.ajax({
								type: "POST",
								dataType: "json",
								async: true,
								url: "/shareIdea/UserBaseInfo_checkSecCode.action",
								data: secCodeJson,
								success:function(data){
									if(data.isRight){
										stopTime = true;
										$(".errorBorder").css({"display": "none"});
										$("#verify").attr("right",true);
										$("#verify").attr("errorMsg","");
									}else{
										$(".reminder").html("验证码有误，请重新输入！");
										$(".errorBorder").css({"display": "block"});
										$("#verify").attr("right",false);
										$("#verify").attr("errorMsg","验证码有误，请重新输入！");
									}
									
								},
								error:function(data){
									$(".reminder").html("验证码有误，请重新输入！");
									$(".errorBorder").css({"display": "block"});
									$("#verify").attr("right",false);
									$("#verify").attr("errorMsg","验证码有误，请重新输入！");
								}
							});
						}
					}

					//判断是昵称输入框
					if(id == "name"){
						var text = $(this).attr("value");
						if(text == ""){
							$(".reminder").html("请输入昵称！");
							$(".errorBorder").css({"display": "block"});
							$("#name").attr("right",false);
							$("#name").attr("errorMsg","请输入昵称！");
						}
						else{
							var str = "^([0-9a-zA-Z]|[\u4e00-\u9fa5]){0,10}$";
							var s = text.search(str);
							if(s == 0){
								$(".errorBorder").css({"display": "none"});
								$("#name").attr("right",true);
								$("#name").attr("errorMsg","");
							}else if(text.length>10){
								$(".reminder").html("输入昵称长度不能超过10字！");
								$(".errorBorder").css({"display": "block"});
								$("#name").attr("right",false);
								$("#name").attr("errorMsg","输入昵称长度不能超过10字！");
							}else{
								$(".reminder").html("昵称输入有误，只能是数字或字母或汉字组成请重新输入！");
								$(".errorBorder").css({"display": "block"});
								$("#name").attr("right",false);
								$("#name").attr("errorMsg","昵称输入有误，只能是数字或字母或汉字组成请重新输入！");
							}
						}
					}
				
					//判断是密码输入框
					if(id == "password"){
						var text = $(this).attr("value");
						if(text == ""){
							$(".reminder").html("请输入密码！");
							$(".errorBorder").css({"display": "block"});
							$("#password").attr("right",false);
							$("#password").attr("errorMsg","请输入密码！");
						}
						else{
							var str = "^[0-9a-zA-Z]{6,15}$";
							var s = text.search(str);
							if(s !== 0){
								$(".reminder").html("密码输入有误，只能是数字或字母组成请重新输入！");
								$(".errorBorder").css({"display": "block"});
								$("#password").attr("right",false);
								$("#password").attr("errorMsg","密码输入有误，只能是数字或字母组成请重新输入！");
							}
							else{
								$(".errorBorder").css({"display": "none"});
								$("#password").attr("right",true);
								$("#password").attr("errorMsg","");
							}
						}
					}
					//判断是确认密码输入框
					if(id == "affirm"){
						var text = $(this).attr("value");
						if(text == ""){
							$(".reminder").html("请输入确认密码！");
							$(".errorBorder").css({"display": "block"});
							$("#affirm").attr("right",false);
							$("#affirm").attr("errorMsg","请输入确认密码！");
						}
						else{
							var str = $("#password").attr("value");
							if(str != text){
								$(".reminder").html("确认密码与密码输入不相符，请重新输入！");
								$(".errorBorder").css({"display": "block"});
								$("#affirm").attr("right",false);
								$("#affirm").attr("errorMsg","确认密码与密码输入不相符，请重新输入！");
							}
							else{
								$(".errorBorder").css({"display": "none"});
								$("#affirm").attr("right",true);
								$("#affirm").attr("errorMsg","");
							}
						}
					}
				});
			}
			$.ssp.mq_register();  
		})
		
		
		
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
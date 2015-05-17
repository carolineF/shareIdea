<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<meta http-equiv="x-ua-compatible" content="ie=emulateie7" /> 

		<title>登录</title>
		<script type="text/javascript" src="./js/jQuery1.7.1.js"></script>
		<link rel="stylesheet" type="text/css" href="./css/base.css"> 
		<link rel="stylesheet" type="text/css" href="./css/login.css">
		<link rel="stylesheet" type="text/css" href="./css/common.css">
		<script type="text/javascript" src="./js/login.js"></script>
		<script type="text/javascript" src="./js/common.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div id="headbackground" class=" background h90 pr w">
			<!--登录头部-->
			<div class="pr w1100 h90 bc">                                               
				<img class="fl" src="./image/logo.png">
				<div class="fr lh90 fo f20 cursorp">联系我们</div>
				<img src="./image/tel.png" class="fr mt30" style="cursor:pointer">
				<div class="fr lh90 fo f20 cursorp mr15"><a class="index" href="javascript:selectedNav('index.jsp')">主页</a></div>
			</div>
		</div>

<div class="pr w h600">

		<div class="w1100 pr bc"><!--"bc"为模块居中-->
			<!--登录主体部分-->		
			<div id="loginbody" class="w800 pr bc ">
				
				<form name="login" id="loginform" class="w300 pa fl" action="/shareIdea/UserBaseInfo_checkLogin" method="post">
					<!--用户登录标志-->
					<div class="w300 h60 f30 fcr lh60 tac pr fo">用户登录</div>
					<!--账号-->
					<div class="w300 h30 pr mt10">
						<div class="f20 pr lh30 ml30 ff fl" style="width:41px">账号</div><!--"ff"位黑体-->
						<input id="username" name="userLoginModel.account" type="text" class="mr30 w180 h30 fr f20 pl5" value="手机号码/邮箱账号">
					</div>
					<!--账号提示-->
					<div id="account" class="f12 ff pr fcr mt5 none">账号不能为空!</div>

					<!--密码-->
					<div class="w300 h30 pr mt20 mb5">
						<div class="w40 f20 pr lh30 ml30 ff fl" style="width:41px">密码</div>
						<input id="password" name="userLoginModel.password" type="password" class="mr30 w180 h30 fr f20 pl5">
					</div>
					<!--错误提示，忘记密码-->
					<div class="rememberforget pr">
						<label class="rememberpassword fl">
							<%
							
								if((String)request.getAttribute("errorContent") != null){
									
							%>
								<%=(String)request.getAttribute("errorContent")%>
							<%
								}		
							%>
						</label>
						<a href="/shareIdea/UserBaseInfo_image" class="forgetpassword fr">忘记密码？</a>
						<a href=""></a>
					</div>
					<!--登录按钮-->
					<div class="w300 h30 mt50 pr">
						<div id="loginbtn" class="fr mr30 f20 ff tac">登录</div>
					</div>
					<!--注册按钮-->
					<div class="w300 h30 mt20 pr mb20">
						<div id="register" class="fr mr30 f20 ff tac">注册</div>
					</div>
				</form>
				<!---->
				<!--动画，图片-->
				<div id="cartoon" class="fr background b">
				</div>
			</div>
		</div>
</div>	
    <!--网页尾部-->
	<div class="zh-Foot fs pa w">
		<div class="w1100 h300 tal bc zh-footItem ">
			<div class="mt30 ml60 fl  zh-footItem1">
				<a href="#">关于我们</a>
				<span>|</span>
				<a href="javascript:selectedNav('strategy.jsp')">策略仓库</a>
				<span>|</span>
				<a href="#">联系我们</a>
			</div>
			
			<div class="mt30 ml60 fl  zh-footItem1">
				<a href="http://www.wquant.com/">微量网</a>
				<span>|</span>
				<a href="http://jyw.lhtz.com/">中量网</a>
				<span>|</span>
				<a href="http://www.baidu.com">百度网</a>
			</div>

			<div class="mt30 ml60  fr">
					<a class="qqShareweixin" href="#">客服QQ 444981081</a>
					<span class="qqShareweixin">|</span>
					<a class="qqShareweixin" href="#">策略共享者群 444981081</a>
					<span class="qqShareweixin">|</span>
					<a class="qqShareweixin" href="#">微信</a>
			</div>
		</div>
	</div>					
					
	</body>	
</html>
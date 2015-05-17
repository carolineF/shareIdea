<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">    

		<title>忘记密码3</title>
		<link rel="stylesheet" type="text/css" href="./css/base.css"> 
		<link rel="stylesheet" type="text/css" href="./css/common.css">
		<link rel="stylesheet" type="text/css" href="./css/forgetpassword3.css">
		<script type="text/javascript" src="./js/jQuery1.7.1.js"></script>
		<script type="text/javascript" src="./js/forgetpassword3.js"></script>
		<script type="text/javascript" src="./js/common.js"></script>
	</head>
	<body>
		<div class="w pa h90" style="background-color:#F0EFEF"></div>
		<div class="w1100 pr bc"><!--"bc"为模块居中-->
			
			<!--头部-->
			<div class="pr w1100 h90">                                               
				<img class="fl" src="./image/logo.png">
				<div class="fr lh90 f20 cursorp">联系我们</div>
				<img src="./image/tel.png" class="fr mt30" style="cursor:pointer">
				<div class="fr lh90 f20 cursorp mr15"><a class="index mqIndex" href="javascript:selectedNav('index.jsp')">主页</a></div>
			</div>

			<!--找回密码-->
			<div class="mqBody">
				<div class="w800 bc mt50 pt30" style="border:1px solid #c9c9c9">
					<h6 class="f30 ml30">找回密码</h6>
					<img src="./image/forget3.png" class="w800 h80">	
					<form name="mqforgetThird" class="w300 bc mt30" action="/shareIdea/UserBaseInfo_forgetThird" method="post">
						
						<!--新密码-->
						<div class="w300 lh40 mt30 f20">
							<span class="">&nbsp;&nbsp;&nbsp;新密码</span>
							<input type="password" id="password" name="newPassword" class="h40 f20 pl5" maxlength="15">
						</div>

						<div id="claim" class="f12 none">请输入6-15位密码！</div>

						<!--确认密码-->
						<div class="w300 lh40 mt30 f20">
							<span class="">确认密码</span>
							<input type="password" id="affirm" class="h40 f20 pl5" maxlength="15">
						</div>
						
						<div id="affirmPassword" style="font-size:16px;color:#fe3232;margin-left:85px;display:none">密码不一致</div>
						
						<!--上一步下一步-->
						
							<input type="button" id="cancel" class="f20 mt30 mb20" value="取消">
							<input type="button" id="finish" class="f20 ml10" value="完成">
						
					</form>
				</div>
			</div>
		</div>

		 <!--网页尾部-->
		<div class="zh-Foot fs  w pa">
			<div class="w1100 h300 tal bc zh-footItem ">
				<div class="mt30 ml60 fl  zh-footItem1">
				<a href="#">关于我们</a>
				<span>|</span>
				<a href="javascript:selectedNav('strategy.html')">策略仓库</a>
				<span>|</span>
				<a href="#">联系我们</a>
			</div>
			
			<div class="mt30 ml60 fl  zh-footItem1">
				<a href="http://www.baidu.com">微量网</a>
				<span>|</span>
				<a href="http://www.baidu.com">中量网</a>
				<span>|</span>
				<a href="http://www.baidu.com">百度网</a>
			</div>
				<div class="mt30 ml60  fr zh-footItem2">
					<a href="#">客服QQ 444981081</a>
					<span>|</span>
					<a href="#">策略共享者群 444981081</a>
					<span>|</span>
					<a href="#">微信</a>
				</div>
			</div>
		</div>
	</body>
</html>
 
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">    

		<title>忘记密码2</title>
		<link rel="stylesheet" type="text/css" href="./css/base.css"> 
		<link rel="stylesheet" type="text/css" href="./css/forgetpassword2.css">
		<link rel="stylesheet" type="text/css" href="./css/common.css">
		<script type="text/javascript" src="./js/jQuery1.7.1.js"></script>
		<script type="text/javascript" src="./js/forgetpassword2.js"></script>
		<script type="text/javascript" src="./js/common.js"></script>
	</head>
	<body>
		<div class="w pa h90" style="background-color:#F0EFEF"></div>
		<div class="w1100 pr bc"><!--"bc"为模块居中-->
			
			<!--头部-->
			<div class="pr w1100 h90">                                               
				<img class="fl" src="./image/logo.png">
				<div class="fr lh90 fo f20 cursorp">联系我们</div>
				<img src="./image/tel.png" class="fr mt30" style="cursor:pointer">
				<div class="fr lh90 fo f20 cursorp mr15"><a class="index mqIndex" href="javascript:selectedNav('index.jsp')">主页</a></div>
			</div>

			<!--找回密码-->
			<div class="mqBody" style="margin-bottom: 47px;">
				<div class="w800 bc mt50 pt30" style="border:1px solid #c9c9c9">
					<h6 class="f30 ml30">找回密码</h6>
					<img src="./image/forget2.png" class="w800 h80">
					
					<form class="w400 bc mt10" style="margin-top:40px" action="/shareIdea/UserBaseInfo_forgetsecond" method="get">
						<!--验证码-->
						<div class="w400 f20">
							<span>验证码</span>
							<input type="text" name="verifyName" id="verify" class="h40 f20 pl5">
							<input type="button" id="getverify" class="fr f20" value="获取验证码">
						</div>
						<div style="font-size:16px;color:#fe3232;margin-left:65px;">
							<%
								if(request.getAttribute("reg") != null){
							%>
								<%=request.getAttribute("reg") %>
							<%
								}
							%>
						</div>
						<!--上一步下一步-->
						
							<input type="button" id="back" class="f20 ml mt30 mb20" value="上一步">
							<input type="submit" id="next" class="f20 ml10" value="下一步">
						
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
    
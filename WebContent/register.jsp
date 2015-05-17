<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>注册</title>
	<link rel="stylesheet" type="text/css" href="./css/base.css"> 
	<link rel="stylesheet" type="text/css" href="./css/register.css">
	<link rel="stylesheet" type="text/css" href="./css/common.css">
	<script type="text/javascript" src="./js/jQuery1.7.1.js"></script>
	<script type="text/javascript" src="./js/register.js"></script>
	<script type="text/javascript" src="./js/common.js"></script>
</head>
<script type="text/javascript">

</script>
<body>
	<div class="w pa h90" style="background-color:#F0EFEF"></div>
	<div class="w1100 pr bc"><!--"bc"为模块居中-->
		<!--注册头部-->
		<div class="pr w1100 h90">                                               
			<img class="fl" src="./image/logo.png">
			<div class="fr lh90 fo f20 cursorp">联系我们</div>
			<img src="./image/tel.png" class="fr mt30" style="cursor:pointer">
			<div class="fr lh90 f20 cursorp mr15"><a class="index" href="javascript:selectedNav('index.jsp')">主页</a></div>
		</div>
	</div>

	<!--注册体-->
	<div class="registerbody">
		<p class="f20 ml10 mb10">注册账号</p>
		<div class="registerbackground">
			<div class="register ">
				
				<!-- 选择手机注册或邮箱注册 -->
				
				<span id="tel" class="tr5 fl click cw">手机注册</span>
			
				<a id="email" class="tr5 fl ml10 cw">邮箱注册</a>

				<!-- form表单 -->
				<form name="myForm" class="form" action="/shareIdea/UserBaseInfo_insertUser.action" method="post">
					<!-- 手机号码或邮箱账号 -->
					<div class="w400 f20" style="margin-top:40px;">
						<span class="label">手机号码</span>
						<input type="text" errorMsg="请输入手机号码！" right="false" name="urm.account" id="number" class="onblur f20 pl5">
					</div>

					<!-- 验证码 -->
					<div class="w400 mt10 f20">
					<span class="">&nbsp;验&nbsp;证&nbsp;码</span>
					<input type="text" errorMsg="请输入验证码！" name="" id="verify" right="false" class="onblur f20 pl5">
					<input type="button"  id="getverify" value="获取验证码"></div>

					<!-- 昵称 -->
					<div class="w400 mt10 f20">
						<span class="">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>
						<input type="text" errorMsg="请输入昵称！" name="urm.nikeName" right="false" id="name" class="onblur f20 pl5">
					</div>

					<!-- 密码 -->
					<div class="w400 mt10 f20">
						<span class="">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
						<input type="password" errorMsg="请输入密码！" name="urm.userPassword" right="false" id="password" class="onblur f20 pl5">
					</div>

					<!-- 确认密码 -->
					<div class="verPassword w400 mt10 f20">
						<span class="">确认密码</span>
						<input type="password" right="false" errorMsg="请输入确认密码！" id="affirm" class="onblur f20 pl5">
					</div>

					<div class="errorBorder">
						<div class="reminder w400"></div>
					</div>
					<!-- 注册按钮 -->
					<input type="button" class="tac"  id="registerbtn" value="注册">
				</form>
			</div>
		</div>
	</div>
	 <!--网页尾部-->
    <div class="zh-Foot fs w pa">
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
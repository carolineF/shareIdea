<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%
	String path = request.getContextPath();
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">    

		<title>忘记密码1</title>
		<link rel="stylesheet" type="text/css" href="./css/base.css"> 
		<link rel="stylesheet" type="text/css" href="./css/forgetpassword1.css">
		<link rel="stylesheet" type="text/css" href="./css/common.css">
		<script type="text/javascript" src="./js/jQuery1.7.1.js"></script>
		<script type="text/javascript" src="./js/forgetpassword1.js"></script>
		<script type="text/javascript" src="./js/common.js"></script>
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
			<div class="mqBody">
				<div class="w800 bc mt50 f30 pt30" style="border:1px solid #c9c9c9">
					<h6 class="f30 ml30">找回密码</h6>
					<img src="./image/forget1.png" class="w800 h80">	
					<form class="w400 bc mt30" action="/shareIdea/UserBaseInfo_forgetFirst" method="POST">
						
						<!--账号-->
						<div class="w400 bc f20 lh40">
							<span>账&nbsp;&nbsp;&nbsp;&nbsp;号</span>
							<input type="text" id="username" name="forgetModel.account" class="username h40 f20 pl5" value="手机号码/邮箱账号">
						</div>

						<div id="account" class="f12 ff pr fcr mt5 none">账号不能为空!</div>

						<!--验证码-->
						<div class="w400 bc f20 lh40 mt30">
							<span class="fl">验证码</span>
							<input type="text" name="forgetModel.verify" class="verify h40 f20 pl5">
							<a href="javascript:reloadVerify();" id="vague" class="fr f12 mt30">看不清，换一张</a>
							<div src="" id="image" class="pl5 w10 fr">
								<img id="mqVerify" alt="验证码" src="./image/verify.jpg"/>
								<script type="text/javascript">
									<%-- alert("<%=request.getContextPath()%>/servlet/ImageServlet"); --%>
								</script>
							</div>
						</div>
						
						<div style="color:#fe3232;font-size:12px;margin-left:70px">
							<%
								if(request.getAttribute("reg") != null){
							%>
								<%=request.getAttribute("reg") %>
							<%
								}
							%>
						</div>
												
						<!--下一步-->
						<input type="submit" id="submit" class="f20 h40 mt30 mb20" value="下一步">
						
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

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<!-- 搜索结果展示页-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">           
<title>SSP策略共享平台</title>
<link rel="stylesheet" type="text/css" href="css/base.css"> 
<link rel="stylesheet" type="text/css" href="css/index.css"> 
<link rel="stylesheet" type="text/css" href="css/common.css"> 
<link rel="stylesheet" type="text/css" href="css/animate.css"> 
<script type="text/javascript" src="js/jQuery1.7.1.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/digt.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
<!--网页header部分。分顶部和导航两部分-->
<div class="f16 fs">
	<!--顶部-->
	<div class="hz-top">
		<div class="w1100 bc tl pr  h35  hz-logReg">
			<!-- 系统消息、联系我们-->
			<span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
			<span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
			<!--登陆|注册-->
			<div class="fr">
				<%
				if(session.getAttribute("userId")!=null){
			%>
				<a href="javascript:selectedNav('ideaInfo')"><%=session.getAttribute("nickName") %></a>
				<a href="#">|</a>  
				<a href="javascript:selectedNav('ExitAction')">退出</a>
			<%		
				}else{
			%>
				<a href="javascript:selectedNav('login.jsp')">登陆</a>
				<a href="#">|</a>  
				<a href="javascript:selectedNav('register.jsp')">注册</a>
			<%		
				}
			%>
			</div>
		</div>
	</div>
	<!--网页导航栏以及logo-->
	<div class="hz-topLogoNav">
		<!--logo-->
		<span class="hz-logo"></span>
		<!--导航栏-->
		<div class="hz-nav">
			<a href="javaScript:selectedNav('index.jsp');" id="hz-navIndex" class="hz-selectNav">主页</a>
			<a href="javaScript:selectedNav('strategy.jsp');" id="hz-navSt">策略仓库</a>
			<a href="javaScript:selectedNav('personSpace.jsp');" id="hz-navPers">个人中心</a>
			<a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload">上传策略</a>
		</div>
	</div>
	<!--网站主体部分-->
	<div class="hz-indexBody">
		<canvas id="canvas"></canvas>
		<canvas id="canvas1" style="position:absolute;top:400px;left:200px;" charset="gb2312"></canvas>
		<div id="animate2">
			<div class="animated">
				<span style="color:#9933cc">s</span>
				<span style="color:#33b5e5">h</span>
				<span style="color:#aa66cc">r</span>
				<span style="color:#ff8800">e</span>
				<span style="color:#0099cc">I</span>
				<span style="color:#9933cc">d</span>
				<span style="color:#33b5e5">e</span>
				<span style="color:#aa66cc">a</span>
			</div>
			<div style="position:relative;text-align:center;top:60px;font-size:50px;">不止分享创意...</div>
		</div>
		<div id="animate3">
			<div class="animated">
				<span style="color:#9933cc">策</span>
				<span style="color:#33b5e5">略</span>
				<span style="color:#aa66cc">共</span>
				<span style="color:#ff8800">享</span>
				<span style="color:#0099cc">平</span>
				<span style="color:#9933cc">台</span>
			</div>
			<div style="position:relative;text-align:center;top:60px;font-size:50px;">准备好了吗？</div>
		</div>	
	</div>
	 <!--网页尾部-->
	<div class="zh-Foot fs w pa">
			<div class="w1100 h300 tal bc zh-footItem ">
				<div class="mt30 ml60 fl  zh-footItem1">
				<a href="#">关于我们</a>
				<span>|</span>
				<a href="javascript:selectedNav('strategy.jsp')">策略仓库</a>
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
	<div id="imageNav">
		<div class="imageNav" id="img1"></div>
		<div class="imageNav" id="img2"></div>
		<div class="imageNav" id="img3"></div>
	</div>
</div>
</body>
</html>
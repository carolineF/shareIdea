<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
    <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">              
	    <title>个人中心</title>
	    <link rel="stylesheet" type="text/css" href="css/base.css"> 
	    <link rel="stylesheet" type="text/css" href="css/changePassword.css"> 
	    <link rel="stylesheet" type="text/css" href="css/common.css"> 
	    <script type="text/javascript" src="js/jQuery1.7.1.js"></script>
	    <script type="text/javascript" src="js/changePassword.js"></script>
	    <script type="text/javascript" src="js/common.js"></script>
	    
    </head>

    <body>
       <!--顶部-->
		<div class="hz-top">
			<div class="w1100 bc tl pr  h35  hz-logReg">
				<!-- 系统消息、联系我们-->
				<span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
				<span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
				<!--用户昵称|退出-->
				<div class="fr">
					<a href="ideaInfo"><%=session.getAttribute("nickName") %></a>  
					<a href="#">|</a>  
					<a href="ExitAction">退出</a>
				</div>
			</div>
		</div>

	    <!--网站的导航栏部分,包括：logo、首页、我的资料、个人资料、通讯录、上传策略-->
	   <!--网页导航栏以及logo-->
		<div class="hz-topLogoNav">
			<!--logo-->
			<span class="hz-logo"></span>
			<!--导航栏-->
			<div class="hz-nav">
				<a href="javaScript:selectedNav('index.jsp');" id="hz-navIndex">主页</a>
				<a href="ideaInfo" id="hz-navSt" class="hz-selectNav">个人中心</a>
				<a href="UserBaseInfo_showInformation" id="hz-navPers">个人资料</a>
				<a href="UserFriendMsgAction" id="hz-navUpload">通讯录</a>
				<a href="newUpload.jsp" id="hz-navUpload">上传策略</a>
			</div>
		</div>

		<!-- 修改密码主体部分 -->
		<div class="changeBody">
			<div class="change w800 bc mt50 background ">
				<h6 class="f30 ml30 mt10">修改密码</h6>	
				<hr style="border-top:1px solid #c9c9c9">
				<span class="f20 ml30">重要提示：</span>
				<span id="modifyTip" style="color:green"></span>
				<form action="/shareIdea/UserBaseInfo_modifyPassWord.action" method="post"  class="w300 bc mt30">

					<!--原始密码-->
					<div class="w400 lh40 mt30 f20">
						<span class="">原始密码</span>
						<!--  <input type="text" id="oldPassword" name="oldPassword" class="input h40 f20 pl5" maxlength="15"> -->
						<input type="password" id="oldPassword" name="modifyModel.oldPassword" class="input h40 f20 pl5" maxlength="15">
					</div>

					<!-- 原始密码提示 -->
					<div id="mqReminderOld" class="mqReminder f12">请输入原始密码</div>
					
					<!--新密码-->
					<div class="w400 lh40 mt20 f20">
						<span class="">&nbsp;&nbsp;&nbsp;新密码</span>
						<input type="password" id="password" name="modifyModel.newPassword"class="input h40 f20 pl5" maxlength="15">
					</div>

					<!-- 新密码提示 -->
					<div id="mqReminderNew" class="mqReminder f12"></div>

					<!--确认密码-->
					<div class="w400 lh40 mt20 f20">
						<span class="">确认密码</span>
						<input type="password" id="affirm" class="input h40 f20 pl5" maxlength="15">
					</div>

					<!-- 确认密码提示 -->
					<div id="mqReminderVer" class="mqReminder f12"></div>

					<!--上一步下一步-->

					<div class="w400 lh40 f20">
						<input type="button" id="submit" class="f20 mt30 mb20" value="提交">
						<input type="reset" id="again" class="f20 ml10" value="重新输入">
					</div>
					
				</form>
			</div>
		</div>

		 <!--网页尾部-->
		<div class="zh-Foot fs">
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
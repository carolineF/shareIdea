<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@page import="com.shareIdea.po.Userbaseinfo,com.shareIdea.factory.DAOFactory" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">    

		<title>点击账号-个人策略</title>
		<link rel="stylesheet" type="text/css" href="./css/base.css"> 
		<link rel="stylesheet" type="text/css" href="./css/personalidea.css">
		<link rel="stylesheet" type="text/css" href="./css/common.css">
		<script type="text/javascript" src="./js/jQuery1.7.1.js"></script>
		<script type="text/javascript" src="./js/personalidea.js"></script>
		<script type="text/javascript" src="./js/common.js"></script>
		<script type="text/javascript">
		 <%
		    Userbaseinfo user=(Userbaseinfo)request.getAttribute("userMsg");
		    String ideaMsg = (String)request.getAttribute("ideaMsg");
		    %>
		    var jsonBlock = <%=ideaMsg%>;

		    var baseInfo = {
		      userName:"<%=user.getNickName()%>",
		      ideaCount:"<%=user.getUserIdeaNo()%>",
		      userJob:"<%=user.getUserProf() %>",
		      userFens:"<%=user.getUserScore()%>",
		      headImageUrl:"<%=user.getHeadimage()%>",
		      pageCount:"9"
		    }
		</script>
	</head>

	<body>
		<!--顶部-->
		<div class="hz-top">
			<div class="w1100 bc tl pr  h35  hz-logReg">
				<!-- 系统消息、联系我们-->
				<span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
				<span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
				<!--登陆|注册-->
				<div class="fr">
					<a href="javascript:selectedNav('ideaInfo')"><%=session.getAttribute("nickName") %></a>  
					<a href="#">|</a>  
					<a href="javascript:selectedNav('ExitAction')">退出</a>
				</div>
			</div>
		</div>
		<!-- 界面 -->
		<div class="bady pr">
			<!--网页导航栏以及logo-->
			<div class="hz-topLogoNav">
				<!--logo-->
				<span class="hz-logo"></span>
				<!--导航栏-->
				<div class="hz-nav">
					<a href="javaScript:selectedNav('index.jsp');" id="hz-navIndex">主页</a>
					<a href="javaScript:selectedNav('strategy.jsp');" id="hz-navSt">策略仓库</a>
					<a href="javaScript:selectedNav('ideaInfo');" id="hz-navPers"  class="hz-selectNav">个人中心</a>
					<a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload">上传策略</a>
				</div>
			</div>

			<!-- 显示主体部分 -->
			<div class="main mt20">
				
				<!-- 头像昵称栏 -->
				<div class="head-name">

					<!-- 头像 -->
					<div class="head-border fl">
						<a id="mqHeadImage" class="head-icon"></a>
					</div>

					<!-- 昵称 职业 -->
					<div class="fl">

						<!-- 昵称 -->
						<div class="name">
							<span>昵称：</span>
							<span id="name"></span>
						</div>

						<!-- 职业 -->
						<div class="job">
							<span>职业：</span>
							<span id="job"></span>
						</div>
					</div>

					<!-- 策略 粉丝 -->
					<div class="idea-fans fl">

						<!-- 策略 -->
						<div class="idea">
							<span>策略：</span>
							<span id="idea"></span>
						</div>

						<!-- 粉丝 -->
						<div class="fans">
							<span>粉丝：</span>
							<span id="fans"></span>
						</div>
					</div>

					<!-- 加好友按钮 -->
					<div id="addFriend" class="fl">加为好友</div>

				</div>

				<!-- 策略显示部分 -->
				<div class="idea-body pr">

					<!-- 右侧显示区 -->
					<div class="right fr">
						<!-- 策略显示表格 -->
						<ul id="ideaBlock">
							
						</ul>
							
						</div>
					

					</div>
				</div>

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
	</body>
</html>
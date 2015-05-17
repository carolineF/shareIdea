<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- 搜索结果展示页-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset='utf-8'">           
<title>SSP策略共享平台</title>
<link rel="stylesheet" type="text/css" href="css/base.css"> 
<link rel="stylesheet" type="text/css" href="css/search.css"> 
<link rel="stylesheet" type="text/css" href="css/common.css"> 
<script type="text/javascript" src="js/jQuery1.7.1.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>

<script type="text/javascript">
	<%
		 String msg = (String)request.getAttribute("msg");
	%>
	var jsonBlock = <%=msg%>;
</script>
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
				<a href="javascript:selectedNav('login.jsp')">登陆</a>  
				<a href="#">|</a>  
				<a href="javascript:selectedNav('register.jsp')">注册</a>
			</div>
		</div>
	</div>
	<!--网页导航栏以及logo-->
	<div class="hz-topLogoNav">
		<!--logo-->
		<span class="hz-logo"></span>
		<!--导航栏-->
		<div class="hz-nav">
			
			<a href="javaScript:selectedNav('index.jsp');" id="hz-navIndex">主页</a>
			<a href="javaScript:selectedNav('strategy.jsp');" id="hz-navSt" class="hz-selectNav">策略仓库</a>
			<a href="javaScript:selectedNav('personSpace.jsp');" id="hz-navPers">个人中心</a>
			<a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload">上传策略</a>
		</div>
	</div>

	<!--网页body部分-->
	<div class="hz-serachBody w1100">
		<!--搜索目标-->
		<div class="hz-target tal f20">
			<span>搜索目标&nbsp;:&nbsp;</span>
			<span id="hz-targerMsg">所属分类信息或者关键字</span>
		</div>
		<!--搜索框-->
		<div id="hz-search">
			<input type="text" id="hz-searchText"/>
			<a href="#" id="hz-searchBtn" class="tac f20">搜索一下</a>
		</div>

		<!-- 策略显示表格 -->
		<ul id="ideaBlock">
		</ul>	
		<!-- 翻页栏 -->
         <div class="w h50">
            <div class="hz-pageUp pr tac w500" style="margin-top:10px;">
               <span id="hz-firstPage" class="bd-btn">首页</span>
               <span id="hz-lastPage" class="bd-btn">上一页</span>
               <a href="javascript:void()" id="hz-page1" class="bd-btn bd-nowBtn">1</a>
               <a href="javascript:void()" id="hz-page2" class="bd-btn">2</a>
               <a href="javascript:void()" id="hz-page3" class="bd-btn">3</a>
               <a href="javascript:void()" id="hz-page4" class="bd-btn">4</a>
               <a href="javascript:void()" id="hz-page5" class="bd-btn ">5</a>
               <span  id="hz-nextPage" class="bd-btn">下一页</span>
               <span  id="finalPage" class="bd-btn">尾页</span>
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
</div>	

</body>	
</html>
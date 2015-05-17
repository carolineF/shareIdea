<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.shareIdea.factory.DAOFactory" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<!-- 搜索结果展示页-->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">           
	<title>SSP策略共享平台</title>
	<link rel="stylesheet" type="text/css" href="css/base.css"> 
	<link rel="stylesheet" type="text/css" href="css/strategy.css"> 
	<link rel="stylesheet" type="text/css" href="css/common.css"> 
	<script type="text/javascript" src="js/jQuery1.7.1.js"></script>
	<script type="text/javascript" src="js/strategy.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
<%
	String msg = DAOFactory.getIIdeaInfoDAOInstance().getStrategyIdeaMsg("现货,通用", 9, 1);
%>
<script type="text/javascript">
	var jsonBlock = <%=msg%>
	
var jsonSearch = {
		num:[
				{
					hotStr: "",
				}
		]
	}
</script>
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
			<a href="javaScript:selectedNav('index.jsp');" id="hz-navIndex">主页</a>
			<a href="javaScript:selectedNav('strategy.jsp');" id="hz-navSt" class="hz-selectNav">策略仓库</a>
			<a href="javaScript:selectedNav('ideaInfo');" id="hz-navPers">个人中心</a>
			<a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload">上传策略</a>
		</div>
	</div>
	<!--网页主体部分策略仓库-->
	<div class="hz-bodySt">

		<div class="firstDeg">
			<ul>
				<li id="hz-security" class="hz-checkSort1">现货</li>
				<li id="hz-futures">期货</li>
				<li id="hz-CFT">期权</li>
				<li id="hz-fu">外汇</li>
				<li id="hz-CT">组合</li>
			</ul>
		</div> 
		<!--二级和三级分类    这个是证券的二三级分类-->
		<div class="hz-sort23Deg fr tac" id="hz-sortSeurity">
			<!--二级分类-->
			<div class="hz-sort2Deg">
				<a href="#" class="hz-s2deg hz-select2Deg" id="hz-chose2Deg1">通用</a>
				<a href="#" class="hz-s2deg" id="hz-chose2Deg2">高频</a>
				<div class="hz-serach">
					<div id="hz-searchBorder">
						<input type="text" name="search1" onclick="this.select()" id="hz-searchText"/>
						<a href="#" id="hz-searchBtn" class="tac f20">搜索一下</a>
					</div>
				</div>
			</div>
			<!--三级分类-->
			<div class="hz-sort3Deg"><!--第二行-->
				<div class="hz-tr1">
					<span>类型</span>
					<span  id="hz-sortType" class="hz-sortAll hz-sort3CheckedAll">全部</span>
				</div>
				<!--具体类型-->
				<div class="hz-tr2">
					<ul>
						<li flag='1' id="hz-sort1">保守型</li>
						<li flag='1' id="hz-sort2">稳健型</li>
						<li flag='1' id="hz-sort3">激励型</li>
						<li flag="1" id="hz-sort4">权变型</li>
					</ul>
				</div>
			</div>
			<div class="hz-sort3Deg" style="border:none;"><!--第三行-->
				<div class="hz-tr1">
					<span>投资额度</span>
					<span id="hz-hasType" class="hz-sortAll hz-sort3CheckedAll">全部</span>
				</div>
				<!--具体类型-->
				<div class="hz-tr2">
					<ul>
						<li  flag='2' in=1 id="hz-sort5">十万以下</li>
						<li  flag='2' in=1 id="hz-sort6">10~100万</li>
						<li  flag='2' in=1 id="hz-sort7">100~1000万</li>
						<li  flag='2' in=1 id="hz-sort8">1000万~1亿</li>
						<li  flag='2' in=1 id="hz-sort9">1亿以上</li>
					</ul>
				</div>
			</div>
		</div>

		

		<!--热门搜索-->
		<div class="hz-hotSts tac">
			<span class="hz-HotStstitle f20">热门搜索</span>
			<ol id="hotSearch">
				
			</ol>
		</div>
		<div class="hz-showSts">
			<!-- 策略显示表格 -->
			<ul id="ideaBlock">
			</ul>

			<!-- 翻页栏 -->
         <div class="w h50" id="mqPage">
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
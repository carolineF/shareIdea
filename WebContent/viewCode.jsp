<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>查看代码</title>
</head>
<link rel="stylesheet" type="text/css" href="./css/base.css">
<link rel="stylesheet" type="text/css" href="./css/ssp-vc.css">
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="Stylesheet" type="text/css" href="css/prettify.css" />
<script src="js/jQuery1.7.1.js" type="text/javascript"></script>
<script src="js/prettify.js" type="text/javascript"></script>
<script type="text/javascript">
<%
	String code = (String)request.getAttribute("code");
	String codeContent = (String)request.getAttribute("codeContent");
	String codeComment = (String)request.getAttribute("codeComment");
%>
var jsonCodeInformation = <%=code%>;
var jsonCommentInfomation = <%=codeComment%>
</script>
<script src="js/ssp-viewCode.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>

<body onselectstart="return false">
 <!--网站头部:页头和导航-->
 <!--顶部-->
   <div class="hz-top">
      <div class="w1100 bc tl pr  h35  hz-logReg">
         <!-- 系统消息、联系我们-->
         <span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
         <span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
         <!--登陆|注册-->
         <div class="fr">
            <a href="ideaInfo"><%=request.getSession().getAttribute("nickName") %></a>  
            <a href="#">|</a>  
            <a href="ExitAction">退出</a>
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

 <!--查看资源代码网站主体-->
 <div class="bd-main w fwr">
   <!--主体上部：策略共享代码展示区-->
 	<div class="bd-main-content w1100 mt15">
 		<div class="bd-main-top">
         <div class="cr f20 w90 fl ml15 fb" style="color:#e44545;">策略共享</div>
      </div>
      <!-- TODO 查看资源代码主体上侧代码区 -->
      <div id="bd-main-con" class="m10">
         <div class="bd-main-title">
            <div id="bd-cf-name" class="fl f20">xyz.java</div>
            <div id="bd-upload-time" class="fr w250 ml20 mt5 f16" style="height:40px;">上传时间：2010-12-12  09:10:20</div>
            <div id="bd-upload-name" class="fr mt5 f16" >昵称：</div>
         </div>
         <div id="bd-des" class="bb1s">
            <div class="bd-tit-bar tac f20">资源描述</div>
            <div id="bd-zyms-item" class="mt5 ml100 fl">
               <div id="bd-load-score" class="bd-zy-info fl mt10 ml10">资源积分:
               <span id="score" style="color:red">0分</span>
               </div>
               <div id="bd-load-size" class="bd-zy-info fr mt10 ml10">
               资源大小:
               <span id="capacity" style="color:red">10M</span>
               </div>
               <div id="bd-load-cnt" class="bd-zy-info fl mt10 ml10">
               下载次数:
               <span id="downLoadCount" style="color:red">10000次</span>
               </div>
               <div class="bd-zy-info fr mt10 ml10">评论次数: <i id="bd-cmt-total" style="color:red">4</i>
               <span style="color:red">次</span>
               </div>
               <div id="bd-strategy" class=" w300 fl mt10 ml10">所属策略:
               <span id="parentIdea" style="color:red"></span>
               </div>
            </div>
            <div class="bd-zyms-btn w250 h50 fl ml100 mt50 tac">
               <a href="javaScript:selectedNav('viewStrategy.jsp')" id="bd-vs-btn" class="bd-button fl w100">查看策略</a>
               <div id="bd-run-btn" class="fl w100">在线运行</div>
            </div>
         </div>
         <!-- TODO代码展示区 -->
         <div id="bd-show" class="bb1s">
            <div class="bd-tit-bar tac f20">代码展示</div>
            <pre id="bd-show-c"><%=codeContent%></pre>
         </div>
         <form action="downloadCodeFile.action" name="downCode" method="post">
         	<div id="bd-load-btn" class="bd-sub-btn f16 w100 mt5 tac">下&nbsp;&nbsp;载</div>
         	<input type="hidden" name="codeId" value="">
         	<input type="hidden" name="fileName" value="">
      	 </form>
      </div> 
      <!-- 查看资源代码主体底部：资源评论区 -->
      <div class="bd-main-cmt ml10">
         <div class="bd-main-cmt-top f20">
            <p class="fb">资源评论</p> 
         </div>            
         <ul class="bd-cmt-blocks">
           <!--他人评论区-->
         </ul>
      </div>
      <!-- 查看资源代码我的评论区域 -->
      <div class="bd-my-cmt m10">
         <p class="mt10 f20 fb">我要评论</p>
         <div class="bd-mycmt-con ml100">
            <textarea id="bd-mycmt-text" style="overflow:hidden;" class="f16" placeholder="我来说两句..."></textarea>
         </div>
         <div>
            <span class="ml100"></span>
            <span id="bd-font-count">0/100</span>
            <div id="bd-submit" class="bd-sub-btn w100 f16 tac">发表评论</div>
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

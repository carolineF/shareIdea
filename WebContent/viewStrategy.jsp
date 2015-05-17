<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
<head>
<script type="text/javascript">
	var jsonCodeInformation = <%=request.getAttribute("jsoninfo")%>
    var jasonCode =  <%=request.getAttribute("jsonCode")%>
    var jsonCommentInfomation = <%=request.getAttribute("comment")%>
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
   <title>查看策略</title>
</head>
<link rel="stylesheet" type="text/css" href="./css/base.css">
<link rel="stylesheet" type="text/css" href="./css/viewStrategy.css">
<link rel="stylesheet" type="text/css" href="./css/common.css">
<script src="js/jQuery1.7.1.js" type="text/javascript"></script>
<script src="js/jquery.qqFace.js" type="text/javascript"></script>
<script src="js/ssp-vs.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>

<body>
 <!--网站头部:页头和导航-->
 <!--顶部-->
   <div class="hz-top">
      <div class="w1100 bc tl pr  h35  hz-logReg">
         <!-- 系统消息、联系我们-->
         <span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
         <span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
         <!--登陆|注册-->
         <div class="fr">
            <a href="javascript:selectedNav('login.jsp')">
            	<%=session.getAttribute("nickName") %>
            </a>  
            <a href="#">|</a>  
            <a href="ExitAction')">退出           	
            </a>
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
 <!-- 网站主体 浅灰色背景-->
 <div class="bd-main w fwr">
   <!--主体上部：策略共享内容展示区-->
   <div class="w1100 bd-main-content mt15">
      <div class="bd-main-top">
         <div class="f20 w90 fl ml15 fb" style="color:#e44545;">策略共享</div>
      </div>
      <!-- 灰色框包含头像和策略 -->
      <div class="bd-main-con m10"> 
         <!--策略共享左侧：头像区-->
         <div id="bd-main-headimg" class="fl">
            <div class="ml20">
               <a href="javaScript:selectedNav('personalidea.jsp');"> 
                 <img id="bd-my-head" class="mt30" src="" title="访问我的空间" alt="头像">
               </a> 
            </div>
            <div style="text-align:center;"><a id="bd-main-myname" href="javaScript:selectedNav('personalidea.jsp');" class=" f16 mt10" title="昵称xxx">昵称彼此次卡机地的</a></div>
         </div>
         <!--策略共享右侧整体-->
         <div class="bd-main-right fr">
            <div class="bd-main-title ">
               <span id="bd-main-tit" class="f20 pl10">策略标题在此</span>
               <span id="bd-main-time" class="f16"></span>
            </div>
            <!--策略共享中心：策略展示及代码列表区-->
            <div id="bd-main-body">
               <div id="bd-main-ss" class="fl">
                <!-- TODO添加策略内容 -->
                <textarea id="bd-edit-div" class="p10 f16" onfocus="this.blur()" >
				</textarea>          
               </div>
               <div id="bd-main-codelist" class="fr">
                  <div id="bd-cl" class="h40 f20 pl10">代码列表</div>
                  <div class="bd-file-author pl10">
                    <span>文件名</span>
                    <span class="fr mr15">作者</span>
                  </div>
                  <dl id="parentCode" class="f16"> 
                    
                  </dl>
               </div>
            </div>
            <!--策略共享底部：评论赞区-->             <!-- 评论个数 -->
            <div class="h30 f16 ml10">
               <span class="bd-main-cp bd-main-cmtcount">评论:<i id="bd-cmt-total">10</i></span>
               <a href="javascript:void()" class="bd-main-jb pr5">举报</a>        
               <span id="bd-main-mng">
               		管理
                  <ul id="bd-edit-rev" class="tac">
	               	<li id="bd-edit">编辑</li>
	               </ul>
               </span>
               <div id="bd-cancel" class="bd-button w50 mt5 fr tac">取消</div>
               <div id="bd-sure" class="bd-button w50 mt5 mr15 fr tac">确定</div>
            </div>
         </div>      
      </div>
      <!-- 主体底部：资源评论区 -->
      <div class="bd-main-cmt ml10">
         <div class="bd-main-cmt-top f20">
            <p class="fb">资源评论</p> 
         </div>            
         <ul class="bd-cmt-blocks">
            
         </ul>
      </div>
      <!-- 查看资源代码我的评论区域 -->
      <div class="bd-my-cmt m10">
         <p class="mt10 f20 fb">我要评论</p>
         <div class="bd-mycmt-con ml100">
            <textarea id="bd-mycmt-text" style="overflow:hidden;" class="f16" placeholder="我来说两句..."></textarea>
         </div>
         <div>
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

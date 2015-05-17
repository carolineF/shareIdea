<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@page import="com.shareIdea.factory.DAOFactory,com.shareIdea.po.Userbaseinfo"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
  <!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
   <title>个人空间-完善信息之修改头像</title>
</head>
<link rel="stylesheet" type="text/css" href="./css/base.css">
<link rel="stylesheet" type="text/css" href="./css/ssp-changeHead.css">
<link rel="stylesheet" type="text/css" href="./css/common.css">
<script type="text/javascript" src="js/jQuery1.7.1.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/changeHead.js"></script>
<body>
<%
	int userId = Integer.parseInt((String)session.getAttribute("userId"));
	Userbaseinfo user = DAOFactory.getIUserbaseinfoDAOInstance().getUserAllMsgById(userId);
%>
 <!--网站头部:页头和导航-->
  <!--顶部-->
    <div class="hz-top">
      <div class="w1100 bc tl pr  h35  hz-logReg">
        <!-- 系统消息、联系我们-->
        <span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
        <span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
        <!--用户昵称|退出-->
        <div class="fr">
          <a href="#">绿茶紫忆</a>  
          <a href="#">|</a>  
          <a href="#">退出</a>
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
        <a href="javaScript:selectedNav('ideaInfo_initPageIdeaMsg');" id="hz-navSt">个人中心</a>
        <a href="javaScript:selectedNav('/shareIdeal/UserBaseInfo_showInformation.action');" id="hz-navPers" class="hz-selectNav">个人资料</a>
        <a href="javaScript:selectedNav('UserFriendMsgAction');" id="hz-navUpload">通讯录</a>
        <a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload">上传策略</a>
      </div>
    </div>
<!--网站主体浅灰色背景-->
<div class="bd-main w">
  <!--完善信息修改头像 主体-->
  <div class="bd-main-ch w1100 mt10 mb10">
    <!-- 修改头像主体 左侧 -->
    <div class="bd-ch-left bg h500 fl">
      <div class="bd-ch-title h40 f20">头像设置</div>
      <div class="bd-ch-img">
        <div class="bd-l-img fl" id="localImag">
        	<img  id="preview"  class="mt50 ml20" src="./image/forHeadImg.jpg">
        </div>
        <div class="bd-r-sub fr">
          <form action="uploadImage" method="post" enctype="multipart/form-data">
              <input type="file" name="ufile" class="mt50 ml50" 
              onchange="javascript:setImagePreview();" id="doc" size="34">
              <input type="text" id="filePath" class="mt50" id="bd-browseText" readonly="true" />
              <input type="button" class="mt50 bd-bro-btn" id="bd-browse"  value="浏览" />
              <p class="m20">请选择png,jpg,bmp,gif图片格式</p>
              <input type="submit" class="bd-sub-btn w80 h30 tac f20 ml50 mt20" id="bd-img-upload" value="上传" />
          </form>
        </div>
      </div>
      <div class="bd-question">
        <b class="bd-que-tit m5">常见问题</b>
        <p class="ml5">头像上传不成功？</p>
        <p class="ml10 f14">目前只支持JPG、PNG、GIF类型的图片上传。查看图片类型的方法：您使用鼠标右键点击图片，在下拉菜单中选择“属性”。同时，您还需要确认上传的图片不大于5MB。图片大小的查看方法与图片</p>
      </div>
    </div>
    <!-- 修改头像主体 右侧 -->
    <div  class="bd-ch-right bg h500 ml10 fr">
      <img class="ml50 mt50 mb10 oldImage" id="imghead" style="width:145px;height:160px;" src="<%=user.getHeadimage()%>">
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

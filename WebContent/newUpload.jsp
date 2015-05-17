<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- <meta http-equiv="x-ua-compatible" content="ie=emulateie7" /> -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>上传策略</title>
	<link rel="stylesheet" type="text/css" href="./css/base.css">
	<link rel="stylesheet" type="text/css" href="./css/common.css">
	<link rel="stylesheet" type="text/css" href="./css/SSP_newupload.css">
	
	<script src="./js/jquery-1.8.3.min.js" type="text/javascript"></script>
  <!--  <script src="./js/jQuery1.7.1.js" type="text/javascript"></script> -->
	<script src="./js/SSP_newupload.js" type="text/javascript"></script>
	<script src="./js/common.js" type="text/javascript"></script>
</head>
<body >
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
					<a  href="ExitAciton">退出</a>
					<!-- 退出当前登录，注销session并跳转到主页 -->
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
				<a href="javaScript:selectedNav('ideaInfo');" id="hz-navSt">个人中心</a>
				<a href="javaScript:selectedNav('UserBaseInfo_showInformation.action');" id="hz-navPers">个人资料</a>
				<a href="javaScript:selectedNav('UserFriendMsgAction');" id="hz-navUpload">通讯录</a>
				<a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload" class="hz-selectNav">上传策略</a>
			</div>
		</div>
		<!--主体部分-->
		<div class="f-main-content bc mt30">
			<!--填写策略信息区域-->
			<form name="myForm" id="f-myForm" action="/shareIdea/UploadIdea_insertIdeaInfo.action" enctype="multipart/form-data" method="post">
				<div class="f-content-left">
					<ul class="f20 bc f-content-change">
						<li id="f-change-stra" class="f-modify tac">上传策略</li>
						<li id="f-change-code" class="f-modify tac" style="color:black">上传代码</li>
					</ul>
					<input id="uploadMethod" style="display:none" name="ideaUploadMode.uploadMethod" value=""/>

					<div class="bc f-content-left-mess">
						<div class="f-content-change f-content-mes">
							<label class="f-content-topic-span">所属分类：</label>
							<select id="ideaSort1" name="ideaUploadMode.firstCategory" class="ideaSort1">
								<option>现货</option>
								<option>期货</option>
								<option>期权</option>
								<option>外汇</option>
								<option>组合</option>
							</select>
							<select id="ideaSort2" name="ideaUploadMode.secondCategory" class="ideaSort1">
								<option>通用</option>
								<option>高频</option>
							</select>
							<select id="ideaSort3" name="ideaUploadMode.thirdCategory" style="width:80px;" class="ideaSort1">
								<option>保守型</option>
								<option>稳健型</option>
								<option>激励型</option>
								<option>权变型</option>
							</select>
							<select id="ideaSort4" name="ideaUploadMode.forthCategory" style="width:90px;" class="ideaSort1">
								<option>十万以下</option>
								<option>10~100万</option>
								<option>100~1000万</option>
								<option>1000万~1亿</option>
								<option>1亿以上</option>
							</select>
						</div>
						<div class="f-content-change f-content-mes">
							<label id="ideaTitleLabel" class="f-content-topic-span">策略标题：</label>
							<input type="text" errorMsg="策略标题不能为空！" name="ideaUploadMode.ideaTitle"
							 id="ideaTitle" class="onloadBtn" />
							 <span class="errorMsg" id="ideaTitleError"></span>
							 <input id="HiddenIdeaId" value ="" name="ideaUploadMode.ideaId" style="display:none" />
						</div>
						<div class="f-content-change f-content-mes">
							<label class="f-content-topic-span">关键词：</label>
							<input type="text" errorMsg="每个关键词之间用逗号分隔！" name="ideaUploadMode.ideaKeyWord" id="f-key-word" class="onloadBtn" />
							<span class="errorMsg" id="keyWordError"></span>
						</div>
						<div class="f-content-change f-content-mes">
							<label class="f-content-topic-span">权限：</label>
							<select class="onloadBtn" name="ideaUploadMode.ideaAuthority" id="f-lim">
								<option>所有人</option>
								<option>所有好友</option>
								<option>部分好友</option>
								<option>个人策略</option>
							</select>
						</div>
						<input id="hiddenObj" name="ideaUploadMode.friendList" style="display:none" />
						<div class="f-content-change f-content-mes">
							<label class="f-content-topic-span">资源分：</label>
							<!--修改的地方-->
							<input id="SourceText" errorMsg="" type="text" name="ideaUploadMode.codeScore" class="onloadBtnText" onclick="this.select()">
							<span id="information" class="errorMsg" style="display:none">您输入的有误，请重新输入！</span>
							<!--
							<select id="f-resource" class="onloadBtn">
								<option>0分</option>
								<option>3分</option>
								<option>6分</option>
								<option>9分</option>
							</select>
							-->
						</div>
						<div class="f-content-upload">
							<div class="f-content-upload-button" onclick="ClickInputFile()">上传文件</div>
							<span id='textfield' class='txt'>请选择上传文件</span>
							<span id='textsize' class='txt'>文件大小不超过2M</span>
							 	<input type="file" name="ideaUploadMode.fileField" class=" file" id="fileField" onchange="checkfile(event);"/> 
						</div>
						<input id="fileSize" name="ideaUploadMode.fileSize" style="display:none" />
						<div class="f-content-change f-content-mes f-content-propoty">
							<input id="agreeUpload" type="checkbox" value="同意上传协议" />
							<span>同意上传协议</span>
						</div>
						<div class="f-content-change f-content-mes">
						    <input class="f-content-ok" id="uploadSubmit" type="button" value="确定" />
						</div>
					</div>
				</div>
				<!--策略编辑或显示部分-->
				<div class="f-content-right">
					<label class="f-content-right-show"></label>
					<textarea id="IdeaShow" name="ideaUploadMode.ideaContent" style="overflow:hidden;"class="f-content-right-area"></textarea>
				</div>
			</form>
		</div>
		<!--通讯录部分-->
		<div  class="friendBorder">
			<div id="address">选择好友</div>
				<span style="font-size:18px;">
				<input id="f-selectAll" tabindex=-1 class="mqAll checkSelect" type="checkbox" />全选
				<div id="sure" class="mqAll addressListBtn">确定</div>
				<div id="cancle" class="mqAll addressListBtn">取消</div>
				</span>
			<div id="friendList">
				<!--全部联系人列表-->
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
<script type="text/javascript">
  function ClickInputFile() {
   document.getElementById("fileField").click(); 
}
</script>
</html>
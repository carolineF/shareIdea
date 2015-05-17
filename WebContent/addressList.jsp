<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!--修改的地方-->
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>通讯录</title>
		<script src="js/jQuery1.7.1.js" type="text/javascript"></script>
		<script src="js/addressListScript.js" type="text/javascript"></script>
		<script src="js/common.js" type="text/javascript"></script>
		<link href="css/addressListStyle.css" rel="stylesheet" type="text/css"/>
		<link href="css/base.css" rel="stylesheet" type="text/css"/>
		<link href="css/common.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="css/animate.css">
	</head>	
	<body>
	<script type="text/javascript" >
		<%
			String json = (String)request.getAttribute("json");
			String[] j=null;
			try{
				j=json.split("<");
			}catch(Exception e){
				System.out.println(e);
			}
		%>
		<%=j[0]%>
		<%=j[1]%>
	</script>
		<!--顶部-->
   <div class="hz-top">
      <div class="w1100 bc tl pr  h35  hz-logReg">
         <!-- 系统消息、联系我们-->
         <span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
         <span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
         <!--登陆|注册-->
         <div class="fr">
            <a href="javascript:selectedNav('login.jsp')"><%=session.getAttribute("nickName")%></a>  
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
			<a href="javaScript:selectedNav('ideaInfo');" id="hz-navSt">个人中心</a>
			<a href="javaScript:selectedNav('UserBaseInfo_showInformation');" id="hz-navPers">个人资料</a>
			<a href="javaScript:selectedNav('UserFriendMsgAction');" id="hz-navUpload" class="hz-selectNav">通讯录</a>
			<a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload">上传策略</a>
		</div>
	 </div>
		<div id="content">
			<div id="substance">
				<div id="friendList">
					<div id="address">通讯录</div>
				</div>
				<div id="addressNewRailing">
					<div class="addressListBar" id="createFirends">新建联系人</div>
					<div class="addressListBar" id="deleteGroups">删除分组</div>
					<div class="addressListBar" id="addGroups">新建分组</div>
				</div>
				<!--通讯录的主体内容-->
				<div class="addressMain" id="mainAddress">
					<div class="addressMainBar">
						<!-- <input id="all-checkbox" class="checkbox fb tac" type="checkbox"/>&nbsp; -->
						<span class="fb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头像</span>
						<span class="nickName fb">昵称</span>
						<span class="nickName fb">策略总数</span>
						<span class="nickName fb">操作</span>
						<table id="mqTable" border="0">
						</table>	
				         
				         <div class="w h50">
				            <div class="hz-pageUp pr tac w500" style="margin-top:10px;">
				         </div>
			         </div>
					</div>
				</div>

				<div class="addressBarContext  animated  rubberBand" id="createFirend" style="display:none">
					<div>新建联系人</div>
					<span id="tips" style="display:block;width:200px;height:30px;font-size:16px;color:red;margin:0px;padding:0px;
					text-align:center;line-height:30px;margin:0px auto;"></span>
					<span>昵称</span><input type="text" id="friendnickName"/>
					<span>分组</span>
					<select id="sortGroup">
					<%
						ArrayList<String> groupName =(ArrayList<String>) request.getAttribute("groupName");
						for(String gName:groupName){
					%>
						<option><%=gName %></option>
					<%
						}
					%>	
					</select>
					<label class="barBtn" id="submitAddFriend">确定</label>
					<label class="barBtn" id="cancelAddFriend">取消</label>
				</div>
				<div class="addressBarContext animated  tada" id="deleteGroup" style="display:none;">
					<div>删除分组</div>
					<span id="tipdelate" style="display:block;width:200px;height:30px;font-size:16px;color:red;margin:0px;padding:0px;
					text-align:center;line-height:30px;margin:0px auto;"></span>
					<span>分组</span><!--修改-->
					<select id="sortGroup" class="delteGroupName">
						<% 
						for(String gName:groupName){
							if(!gName.equals("全部")){
							%>
								<option><%=gName %></option>
							<%
							}
						}
						%>
					</select>
					<label class="barBtn" id="delteGroupBtn">确定</label>
					<label class="barBtn" id="cancelDelete">取消</label>
				</div>
				<div class="addressBarContext animated  tada"  id="addGroup"  style="display:none;">
					<div>新建分组</div>
					<span id="tipsCreateGroup" style="display:block;width:200px;height:30px;font-size:16px;color:red;margin:0px;padding:0px;
					text-align:center;line-height:30px;margin:0px auto;"></span>
					<span>名称</span><input type="text" id="newGroupName"/>
					<label class="barBtn" id="addGroupBtn">确定</label>
					<label class="barBtn" id="cancelAddGroup">取消</label>
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
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.shareIdea.factory.DAOFactory,com.shareIdea.po.Userbaseinfo" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
    <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">              
	    <title>个人中心</title>
	    <link rel="stylesheet" type="text/css" href="css/base.css"> 
	    <link rel="stylesheet" type="text/css" href="css/personSpace.css"> 
	    <link rel="stylesheet" type="text/css" href="css/common.css"> 
	    <script type="text/javascript" src="js/jQuery1.7.1.js"></script>
	    <script type="text/javascript" src="js/personSpace.js"></script>
	    <script type="text/javascript" src="js/common.js"></script>
    </head>
    <body>
    <%
    Userbaseinfo user=null;
    try{
    	user=DAOFactory.getIUserbaseinfoDAOInstance().getUserAllMsgById(Integer.parseInt((String)session.getAttribute("userId")));
    }catch(Exception e){
    	e.printStackTrace();
    }
    
    String ideaMsg = (String)request.getAttribute("ideaMsg");
    %>
    <script type="text/javascript" >
		var jsonBlock = <%=ideaMsg%>;
    //基本信息、页数数据
    var baseInfo = {
      account:"<%=user.getUserEmail()==null?"":user.getUserEmail()%>",
      userName:"<%=user.getNickName()%>",
      tel:"<%=user.getPhoneNumber()==null?"":user.getPhoneNumber()%>",
      ideaCount:"<%=user.getUserIdeaNo()%>",
      friendCount:"<%=user.getUserFriendNo() %>",
      score:"<%=user.getUserScore()%>",
      lastLogin:"<%=user.getLastLoginTime()%>",
    }
    
    function skip(){ 
   		 window.location="/shareIdea/UserBaseInfo_showInformation.action";
   	}
    </script>
       <!--顶部-->
		<div class="hz-top">
			<div class="w1100 bc tl pr  h35  hz-logReg">
				<!-- 系统消息、联系我们-->
				<span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
				<span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
				<!--用户昵称|退出-->
				<div class="fr">
					<a href="#"><%=user.getNickName() %></a>  
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
				<a href="javaScript:slectedNav('ideaInfo_initPageIdeaMsg');" id="hz-navSt" class="hz-selectNav">个人中心</a>
				<a href="#" onclick="skip();"  id="hz-navPers" >个人资料</a>
				<a href="javaScript:selectedNav('UserFriendMsgAction');" id="hz-navUpload">通讯录</a>
				<a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload">上传策略</a>
			</div>
		</div>
	    <!--网站主体-->	
	    <div class="zh-main w">
	      <div class="zh-m w1100 bc">
	      	<div class="zh-main-header">
	      		<div class="zh-main-img w100 h100 bc pa"></div>
	      			<table border="0" class="zh-main-t1 pa w300">
	      				<tr class="zh-main-tr">
	      					<td class="zh-main-td1">账号： </td>
	      					<td id="account" class="zh-main-td3"></td>
	      				</tr>
	      				<tr class="zh-main-tr">
	      					<td class="zh-main-td1">昵称： </td>
	      					<td id="userName" class="zh-main-td3"></td>
	      				</tr>
	      				<tr class="zh-main-tr">
	      					<td class="zh-main-td1">手机号码： </td>
	      					<td id="tel" class="zh-main-td3"></td>
	      				</tr>
	      				<tr class="zh-main-tr">
	      					<td colspan="2">
	      					<a id="complete-info" href="#" onClick="skip();" class="w80 zh-main-btn1 f16 tac">完善信息</a>
	      					</td>
	      				</tr>
	      			</table>
	      		  <table border="0" class="zh-main-t2 pa w100">
	      			<tr class="zh-main-tr">
	      				<td class="zh-main-td2">策略：</td>
	      				<td id="ideaCount" class="zh-main-td3"></td>
	      			</tr>
	      			<tr class="zh-main-tr">
	      				<td class="zh-main-td2">好友：</td>
	      				<td id="friendCount" class="zh-main-td3"></td>
	      			</tr>
	      			<tr class="zh-main-tr">
	      				<td class="zh-main-td2">积分：</td>
	      				<td id="score" class="zh-main-td3"></td>
	      			</tr>
	      		  </table>
	      		<div class="zh-main-r pa w400">
	      			<table border="0" class="zh-main-t3 p3" >
	      				<tr class="zh-main-tr">
	      					<td class="zh-main-td1">上次登录： </td>
	      					<td id="lastLogin" class="zh-main-td3"></td>
	      				</tr>
	      				<tr class="zh-main-tr">
	      					<td class="zh-main-td1">账户安全度： </td>
	      					<td class="zh-main-td3">
	      						<div class="zh-progress1 fl"></div>
                                <div class="zh-progress2 fl"></div>
	      					</td>
	      				</tr>
	      				<tr class="zh-main-tr">
	      					<td colspan="2">
	      					  <a href="javaScript:selectedNav('changePassword.jsp');" class="w80 zh-main-btn1 f16 tac" >修改密码</a>
	      					</td>
	      				</tr>
	      			</table>
	      		</div>
	      	</div>
	      	<!--主体的导航部分-->
	      	<div class="zh-main-nav h80 pa">
	      		<a href="#" id="my-strategy" class="zh-nav-mo f20 fb tac ml30 fl">我的策略</a>
	      		<a href="#" id="my-code" class="zh-nav-mo f20 fb tac ml30 fl">我的代码</a>
	      		<a href="#" id="firends-strategy" class="zh-nav-mo f20 fb tac ml30 fl">好友策略</a>
	      	</div>
	      	<div class="zh-main-sort h200 pa">
	      	<!--三级分类-->
	      	  <!-- 左侧一级分类选择区 -->
				<ul class="left fl ml10" id="ideaSort">
					<li id="allSort">全部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li id="nowGoods" class="first-rank">
							现货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
							<ul class="second-sort">
								<li>
									通用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="现货" name="现货>>通用>>">保守型</li>
										<li mq="现货" name="现货>>通用>>">稳健型</li>
										<li mq="现货" name="现货>>通用>>">激励型</li>
										<li mq="现货" name="现货>>通用>>">权变型</li>
									</ul>
								</li>
								<li>
									高频&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="现货" name="现货>>高频>>">保守型</li>
										<li mq="现货" name="现货>>高频>>">稳健型</li>
										<li mq="现货" name="现货>>高频>>">激励型</li>
										<li mq="现货" name="现货>>高频>>">权变型</li>
									</ul>
								</li>
							</ul>
						</li>
						<li id="futrueGoods" class="goods">
							期货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
							<ul>
								<li>
									通用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="期货" name="期货>>通用>>">保守型</li>
										<li mq="期货" name="期货>>通用>>">稳健型</li>
										<li mq="期货" name="期货>>通用>>">激励型</li>
										<li mq="期货" name="期货>>通用>>">权变型</li>
									</ul>
								</li>
								<li>
									高频&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="期货" name="期货>>高频>>">保守型</li>
										<li mq="期货" name="期货>>高频>>">稳健型</li>
										<li mq="期货" name="期货>>高频>>">激励型</li>
										<li mq="期货" name="期货>>高频>>">权变型</li>
									</ul>
								</li>
							</ul>
						</li>
						<li id="futruePower" class="goods">
							期权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
							<ul>
								<li>
									通用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="期权" name="期权>>通用>>">保守型</li>
										<li mq="期权" name="期权>>通用>>">稳健型</li>
										<li mq="期权" name="期权>>通用>>">激励型</li>
										<li mq="期权" name="期权>>通用>>">权变型</li>
									</ul>
								</li>
								<li>
									高频&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="期权" name="期权>>高频>>">保守型</li>
										<li mq="期权" name="期权>>高频>>">稳健型</li>
										<li mq="期权" name="期权>>高频>>">激励型</li>
										<li mq="期权" name="期权>>高频>>">权变型</li>
									</ul>
								</li>
							</ul>
						</li>
						<li id="foreign" class="goods">
							外汇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
							<ul>
								<li>
									通用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="外汇" name="外汇>>通用>>">保守型</li>
										<li mq="外汇" name="外汇>>通用>>">稳健型</li>
										<li mq="外汇" name="外汇>>通用>>">激励型</li>
										<li mq="外汇" name="外汇>>通用>>">权变型</li>
									</ul>
								</li>
								<li>
									高频&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="外汇" name="外汇>>高频>>">保守型</li>
										<li mq="外汇" name="外汇>>高频>>">稳健型</li>
										<li mq="外汇" name="外汇>>高频>>">激励型</li>
										<li mq="外汇" name="外汇>>高频>>">权变型</li>
									</ul>
								</li>
							</ul>
						</li>
						<li id="group" class="goods">
							组合&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
							<ul>
								<li>
									通用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="组合" name="组合>>通用>>">保守型</li>
										<li mq="组合" name="组合>>通用>>">稳健型</li>
										<li mq="组合" name="组合>>通用>>">激励型</li>
										<li mq="组合" name="组合>>通用>>">权变型</li>
									</ul>
								</li>
								<li>
									高频&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>
									<ul>
										<li mq="组合" name="组合>>高频>>">保守型</li>
										<li mq="组合" name="组合>>高频>>">稳健型</li>
										<li mq="组合" name="组合>>高频>>">激励型</li>
										<li mq="组合" name="组合>>高频>>">权变型</li>
									</ul>
								</li>
							</ul>
						</li>
				</ul>
	      	</div>
	      	<!--策略展示区-->
	      	<div class="zh-main-right pa">
	      		<div class="zh-main-rf h50">
	      			<input id="all-checkbox" class="zh-main-allChoice ml30 mt20 f20" type="checkbox"/>全选
	      			<a href="#" id="delete" class="zh-main-onload zh-main-delete w40 tac fr f16 pa">删除</a>
	      			<a href="javaScript:selectedNav('newUpload.jsp');" class="zh-main-upload fr tac f16 fb mr30 pa">上传文件</a>
	      		</div>
	      		<div class="personal-idea-label m10">所属分类：全部</div>
	      		<!--我的代码-->
	      		<div id="code" style="display:none">
	      		  <div class="zh-myCode">
	      			<span class="zh-myFile tac f16 fb fl">文件名</span>
	      			<span class="zh-cd f16 fb tac fr">点击量</span>
	      			<span class="zh-cd f16 fb tac fr">下载次数</span>
	      			<span class="zh-cd f16 fb tac fr">评论次数</span>
	      			<span class="zh-cd f16 fb tac fr">大小</span>
	      		  </div>
	      		  <div  class="zh-showCode ">
	      		  	<table border="0" class="zh-showTr" id="codeMsgParent">
	      		  		
	      		  	</table>
	      		  </div>
	      		</div>
	      		<!--我的策略、好友策略-->
	      		<div id="strategy">
	      			<!-- 策略显示表格 -->
					<ul id="ideaBlock">
					</ul>

					<!-- 翻页栏 -->
         <div id="mqPage" class="w h50">
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
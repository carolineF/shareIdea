<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.shareIdea.po.Userbaseinfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<html>
    <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">   
	    <meta http-equiv="X-UA-Compatible" content="IE=7" />
	    <title>个人资料</title>
	    <link rel="stylesheet" type="text/css" href="css/base.css"> 
	    <link rel="stylesheet" type="text/css" href="css/personMsg.css"> 
	    <link rel="stylesheet" type="text/css" href="css/common.css"> 
	    <script type="text/javascript" src="js/jQuery1.7.1.js"></script>
	    <script type="text/javascript" src="js/common.js"></script>
	    <script type="text/javascript" src="js/userInfo_ssx.js"> </script>
    </head>
    <script type="text/javascript">
     //安全退出
     function safeExit(){
    	  var r=confirm("您确认要退出当前页面吗？");
    	  if (r==true){
    		   window.location="personSpace.jsp";
    	    }
    	  else{
    	       window.location = window.location="/shareIdeal/UserBaseInfo_showInformation.action";;
    	    }
     }
     function sendToEmail(){
    	 var dataJson = {
    		 email : $("#checkedEmail").val()
    	 }
    	 $.ajax({
				type: "POST",
				dataType: "json",
				async: true,
				url: "shareIdea/SendEmail_execute.action",
				data: dataJson,
				success: function(data){
					var JsonObj = data;
					SecCode = JsonObj.code;
				},
				error:function(data){
					// 验证码失败处理
					alert("验证码发送失败！您查看您的邮箱是否存在！");
				}
			}); 
     }
      function tosubmit(){
  	     var myform=document.getElementById("peopleAdress");
  	     myform.submit();  
       }
      /*手机验证*/
      function tosubmitT(){
    	  var myform=document.getElementById("modifyTelC");
     	   myform.submit(); 
      }
      /*邮箱验证*/
      function tosubmitM(){
    	  $("#checkedEmail").blur();
    	  if( $("#checkEmail").text()==""){
    		  return null;
    	  }
    	  var myform = document.getElementById("modifyMailC");
    	  var dataJson = {
 	    		 secCode : $("#submitChecked").val()
 	    	 }
 	    	 $.ajax({
 					type: "POST",
 					dataType: "json",
 					async: true,
 					url: "shareIdea/UserBaseInfo_checkSecCode.action",
 					data: dataJson,
 					success:function(data){
 						if(data.isRight == true){
 							myform.submit();
 						}else{
 							$("#submitChecked").val(" ");
 							$("#submitChecked").focus();
 							alert("验证码有误，请重新输入！");
 						}
 					},
 					error:function(data){
 						alert("您输入的验证码有误，请重新输入！");
 					}
 				}); 
      }
      //得到action中的数据
         <%request.getAttribute("information");
    	  System.out.println(request.getAttribute("information"));
    	  
    	%>
    	var baseInformation = (<%=request.getAttribute("information")%>);
    	//初始化界面信息
    	function initInformation(){
    	    $("#mqUserName").attr("value",baseInformation.userName == "null" ? " ": baseInformation.userName);
    	    $("#userSexDiv").html(baseInformation.userSex == "null" ? " ":(baseInformation.userSex ==true?"男":"女"));
    	    $("#myJob").attr("value",baseInformation.userJob == "null" ? " ": baseInformation.userJob);
    	    $("#myEmail").attr("value",baseInformation.userEmail  == "null" ? " ": baseInformation.userEmail);
    	    $("#myTel").attr("value",baseInformation.userNumber  == "null" ? " ": baseInformation.userNumber);
    	    $("#mqAddress").html(baseInformation.userAddress  == "null" ? " ": baseInformation.userAddress);
    	    $("#mqTextarea").attr("value",baseInformation.userSelfInformation  == "null" ? " ": baseInformation.userSelfInformation);
    	    $("#headImag").attr("src",baseInformation.userImg);
    	    $.address("slc1","slc2","slc3");
    	}
     </script>
     <script type="text/javascript" src="js/personMsg.js">
     </script>
    <body>
    <!--顶部-->
		<div class="hz-top">
			<div class="w1100 bc tl pr  h35  hz-logReg">
				<!-- 系统消息、联系我们-->
				<span class="hz-topIcon1 fl"></span><a href="#" class="fl">联系我们</a>
				<span class="hz-topIcon2 fl"></span><a href="#" class="fl">系统消息</a>
				<!--用户昵称|退出-->
				<div class="fr">
					<a href="#"><%=session.getAttribute("nickName") %></a>  
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
				<a href="javaScript:selectedNav('ideaInfo');" id="hz-navSt">个人中心</a>
				<a href="#" id="hz-navPers" class="hz-selectNav" onclick="skip();">个人资料</a>
				<a href="javaScript:selectedNav('UserFriendMsgAction');" id="hz-navUpload">通讯录</a>
				<a href="javaScript:selectedNav('newUpload.jsp');" id="hz-navUpload">上传策略</a>
			</div>
		</div>
	    <!--网站主体-->	
	    <div class="zh-main w">
	    <div class="zh-m w1100 bc">
	      <!--手机/邮箱验证-->
	       <form id="modifyTelC" action="/shareIdea/UserBaseInfo_updateMsgTel.action">
	       <div id="zh-checked1" class="zh-main-mobile bc fl" style="display:none;">
	      	<span id="zh-tel" class="zh-telphone ml100 fl">手机号码:</span>
	      	 <input class="zh-text w200 fl ml10 mt50 f16" type="text" name="user.phoneNumber"/>
	      	 <span class="ml100 fl" style="margin-top:30px;">验证码:</span>
	      	<input class="zh-textc w100 fl mt10 f16" type="text"/>
	      	<span class="zh-repeate fl f14 tac" onclick="sendToEmail()">发送验证码</span>
	      	 <a href="#" class="zh-submit tac f16 fb" onclick="tosubmitT()">提交验证码</a>
	      	</div>
	       </form>
	       <form id="modifyMailC" action="/shareIdea/UserBaseInfo_updateMsgMail.action" >
	         <div id="zh-checked2" class="zh-main-mobile bc fl" style="display:none;">
	      	  <span id="zh-tel" class="zh-telphone ml100 fl">邮箱:</span>
	      	  <input id="checkedEmail" class="zh-text w200 fl ml10 mt50 f16" type="text" name="user.userEmail"/>
	      	  <span id="checkEmail" class="f14" style="position:absolute;top:350px;display:none" ></span>
	      	  <span class="ml100 fl" style="margin-top:30px;">验证码:</span>
	      	  <input id="submitChecked" class="zh-textc w100 fl mt10 f16" type="text"/>
	      	  <span id="sendEmailSecCode" class="zh-repeate fl f14 tac" onclick="sendToEmail()">发送验证码</span>
	      	 <a href="#"class="zh-submit tac f16 fb" onclick="tosubmitM()">提交验证码</a>
	      	 </div>
	       </form>
	      <!--个人资料-->
	        <form id="peopleAdress" action="/shareIdea/UserBaseInfo_updateMsg.action" method="post">
	        <div id="zh-mydata" class="zh-main-m fl mt20 ml30"> 	 
	      	  <div class="div">
	      	  <a href="#" class="modifyDetail fr mr20 mt10 f16 fb">修改个人资料</a>
	      	  </div>
	      	    <table border="0" class="zh-main-table">
	      	      <tr class="zh-main-tr">
	      	  	    <td class="zh-main-td f20 fb tar">昵称 : </td>
	      	  	    <td class="f20">
	      	  		<input id="mqUserName" type="text" class="zhUserName zh-main-tdt" value="菁陌"style="border:none;background:white"  onclick="this.select()"  name="user.nickName" />
	      	  	    </td>
	      	      </tr>  
	      	      <tr class="zh-main-tr">
	      	  	    <td class="zh-main-td f20 fb tar">性别 : </td>
	      	  	    <td class="f20"> 
	      	  	      <div id="userSexDiv" class="ml20 zh-userSex bc" style="border:0px solid #CCC;color:#545454;top:200px;font-size:18px;padding-top:23px;"></div>
	      	  	      <input type="radio" class="ml20 sex1" checked="checked" style="border:0;display:none" name="user.userSex" value="true"/>
	      	  	      <span id="sex1" class="ml20" style="color:#545454 ;display:none">男</span>
				      <input type="radio" class="ml20 sex2" 
				      style="border:0;display:none" name="user.userSex" value="false"/>
				      <span id="sex2" class="ml20" style="display:none">女</span>
				      <br>	
	      	  	    </td>
	      	      </tr>
	      	      <tr class="zh-main-tr">
	      	  	    <td class="zh-main-td f20 fb tar">职业 : </td>
	      	  	    <td>
	      	  	      <input id="myJob" type="text" class="zh-main-tdt"
	      	  	      style="border:0;background:white" value="" onclick="this.select()" name="user.userProf"/>
	      	  	      <span id="warn-job" class="f14" style="display:none" >请输入职业信息</span>
	      	  	      <span id="error-job" class="f14" style="display:none" >您输入的信息有误，请重新输入</span> 
	      	  	      <img id="i-job" src="image/01.png" style="display:none">
	      	  	    </td>
	      	      </tr>
	      	      <tr class="zh-main-tr">
	      	  	    <td class="zh-main-td f20 fb tar">邮箱 : </td>
	      	  	    <td>
	      	  	      <input id="myEmail" type="text" class="zh-main-tdt" 
	      	  	      style="border:0;background:white" value="" onclick="this.select()"name="user.userEmail"/> 
	      	  	      <span id="warn-mail" class="f14" style="display:none" >请输入6-18位作为邮箱</span>
	      	  	      <span id="error-mail" class="f14" style="display:none" >您输入的信息有误，请重新输入</span>
	      	  	     <img id="i-mail" src="image/01.png" style="display:none">
	      	  	      <font id="f-mail" color="red" style="display:none">*</font>
	      	  	    </td>
	      	      </tr> 
	      	      <tr class="zh-main-tr">
	      	  	    <td class="zh-main-td f20 fb tar">手机号码 : </td>
	      	  	    <td>
	      	  	      <input id="myTel" type="text" class="zh-main-tdt"
	      	  	      style="border:0;background:white" value="" onclick="this.select()" name="user.phoneNumber" /> 
	      	  	      <span id="warn-tel" class="f14" style="display:none" >请输入11位有效的手机号码</span>
	      	  	      <span id="error-tel" class="f14" style="display:none" >您输入的信息有误，请重新输入</span>
	      	  	      <img id="i-tel" src="image/01.png" style="display:none">
	      	  	      <font id="f-tel" color="red" style="display:none">*</font>
	      	  	    </td>
	      	      </tr>
	      	      <tr class="zh-main-tr">
	      	  	    <td class="zh-main-td f20 fb tar">所在地 : </td>
	      	  	    <td>
	      	  	    <!--修改的地方-->
	      	  	      <div id="mqAddress" class="location ml20"></div>
	      	  	      <select id="slc1" class="zh-main-tds ml20 f16" style="display:none" name="user.userAddress">
						<option >-- 请选择 --</option>	
						<option>陕西省</option>	
						<option>四川省</option>	
					  </select>
					  <select id="slc2" class="zh-main-tds f16" style="display:none" name="user.userAddress">
						<option>-- 请选择 --</option>	
						<option>西安市</option>	
						<option>宝鸡市</option>	
						<option>北京市</option>	
						<option>上海市</option>	
						<option>南京市</option>		
					  </select>
					  <select id="slc3" class="zh-main-tds f16" style="display:none" name="user.userAddress">
						<option>-- 请选择 --</option>	
						<option>扶风县</option>	
						<option>岐山县</option>	
						<option>眉县</option>	
						<option>太白县</option>	
					  </select>
	      	  	    </td>
	      	      </tr>
	      	     
	      	      <tr class="zh-main-tr">
		      	  	<td class="zh-main-td f20 fb tar pt15" valign="top">自我介绍 :</td>
		      	  	<td class="mt20">
		      	  		<textarea id="mqTextarea" class="ml20 check zh-main-intro mt15" style="border:0;resize:none;background:white" name="user.userIntroduct">  
						</textarea><br>	
		      	  	</td>
	      	  	  </tr>
	      	      <tr class="zh-main-tr">
	      	  	    <td colspan="2" align="center">
	      	  		    <a id="submit" class="zh-modify fl ml100 w70 f20 tac" style="display:none" onclick="tosubmit();">提交</a>
	      	  		    <a id="delete" class="zh-delete fl w70 f20 tac" style="display:none">取消</a> 
	      	  	    </td>
	      	      </tr>
	      	    </table>
	      	    </div>
	      	    </form>
	      	</div>
	       <div>
	      	    <div class="zh-subbar  pa">
	      	     <!--暂时先这样，以后将a标签和span标签结合实现按钮-->
	      	      <div class="zh-sub-h" id="img">
	      	      	<img id="headImag" style="width:100%;height:100%;" src=""/>
	      	      </div>
	      	      <span id="modify-img" class="zh-sub-btn mt50 f16 fb fl tac">修改头像</span>
	      	      <span id="modify-tel" class="zh-sub-btn ml5 mt50 f16 fb fl tac">修改手机</span><br/>
	      	      <span id="modify-mail" class="zh-sub-btn mt10 f16 fb fl tac">修改邮箱</span>
	      	      <span id="modify-exit" class="zh-sub-btn ml5 mt10 f16 fb fl tac" onclick="safeExit();">安全退出</span>
	            </div>
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
</html>
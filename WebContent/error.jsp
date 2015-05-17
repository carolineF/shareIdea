<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0022)http://blog.csdn.net/electroniXtar/ -->
<!--<html>
    <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">   
	    <meta http-equiv="X-UA-Compatible" content="IE=7" />
	    <title>错误提示页面</title>
	    
	    <h2 style="text-align:center;">内部出现异常，请检测后重新登录</h2>
    </body>	
</html>
<html>
	<head><title>出现错误</title></head>
	<body>
	<h1 style="text-align:center;">错误提示</h1>
     <h1>错误nei'ro：</h1><%=exception%>
	</body>
</html>-->
<html>
    <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">   
	    <meta http-equiv="X-UA-Compatible" content="IE=7" />
	    <title>错误提示页面</title>
	    <style type="text/css">
	    .span{
	    	position:absolute;
	    	display:inline-block;
	    	width:100px;
	    	height:30px;
	    	line-height: 30px;
	    	background:#e44545;
	    	color:white;
	    	margin-top:320px;
	    	font-size: 18px;
	    	font-weight: bold;
	    	text-align:center;
	    	margin-left: 130px;
	    }
	    .span:hover{
	    	cursor: pointer;
	    }
	    .posibility{
	    	position:absolute;
	    	display:inline; 
	    	color:black;
	        margin-top:200px;
	        margin-left:40px;
	    }
	    .method{
	    	position:absolute;
	    	margin-left:270px;
	    	color:#ADADAD;
	    	font-size:18px;
	    	margin-top:50px;
	    }
	    .prompt{
	    	position:absolute;
	    	display:inline;
	        color:#e44545;
	        margin-top:80px;
	        margin-left:30px;
	    }
	    .content{
	    	position:absolute;
	    	display:inline;
	    	color:black;
	    	margin-top:140px;
	    	margin-left:30px;
	    }
	    </style>
	</head>
    <body>
	    <div style="width:800px;height:500px;margin:0 auto;">
	    	<img src="./image/logo.png" style="margin-top:100px;">
	    	<h1 class="prompt">错误提示：</h1>
	    	<h3 class="content">后台异常或者资源不存在</h3>
	    	<h2 class="posibility">可能原因:</h2>
	    	<ul class="method">
	    		<li>请检查地址是否输入有误</li>
	    		<li>请检查你输入的内容是否有效</li>
	    		<li>请重新访问</li>
	    	</ul>
	    	<span  class="span">联系我们</span>
	    </div>
    </body>	
</html>
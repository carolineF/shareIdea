var maxsize = 2*1024*1024;//2M
var errMsg = "上传的附件文件不能超过2M！！！";
var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。";
var  browserCfg = {};
var ua = window.navigator.userAgent;
if (ua.indexOf("MSIE")>=1){
	browserCfg.ie = true;
}else if(ua.indexOf("Firefox")>=1){
	browserCfg.firefox = true;
}else if(ua.indexOf("Chrome")>=1){
	browserCfg.chrome = true;
}
//第一类分类的数量
var firstCount = 3;

$(function(){
	$.ssp={};          
	$.ssp.fe={};   

	$.ssp.fe.init=function(){
	//准备数据并生成通讯录列表
    $.ajax({
		type: "POST",
		dataType: "json",
		url: "/shareIdea/UserFriendMsg_getFriendList.action",
		success: function(data){
			var addressListJson = eval(data.root);
			var friendList = document.getElementById("friendList");
			createAddressList(friendList,addressListJson,true);
		},
    	error: function(){
    		alert("通讯录信息获取异常，为保证操作安全请您刷新界面！");
    	}
	 });
 
		/*点击上传代码和上传策略的切换
		相关数据需要从后台获取*/
		$("#f-change-stra").click(function(){
			$(this).css({
				backgroundColor:"#e44545",
			}).css('color','white');
			initUpload(true);
		})
		$("#f-change-code").click(function(){
			$(this).css({
				backgroundColor:"#e44545",
			}).css('color','white');
			initUpload(false);
		})
		
		/*选择部分好友滑出通讯录*/
		$("#f-lim").bind("change", function(){
			if($("#f-lim :selected").text() == "部分好友")
			{
				$(".friendBorder").animate({
					left:"780px",
					display:"inline"
				},1000);
				$("#friendList").animate({
					left:"780px",
					display:"inline"
				},1000);
				$("#f-lim").attr("disabled","true");
			}else if($("#f-lim :selected").text() == "所有好友"){
				var lists =  $(".lyt-allPeople:first").children();
				var str="";
				for(var i=0;i<lists.length;i++){
					alert($(lists[i]).html());
					str+=$(lists[i]).attr("userId")+" ";
				}
				$("#hiddenObj").attr("value", str);
			}else{
				$("#cancle").trigger("click");
				$("#hiddenObj").attr("value", "");
				//console.log("取消选中的好友了"+$("#hiddenObj").attr("value"));
			}
		});
		$("#ideaSort2").change(function(){ 
			var sort2 = $(this).val();
			if(sort2=="高频"){
				$("#ideaSort4").empty();
				var op1 = document.createElement("option");
				$(op1).text("1万以下");
				var op2 = document.createElement("option");
				$(op2).text("1~10万");
				var op3 = document.createElement("option");
				$(op3).text("10~100万");
				var op4 = document.createElement("option");
				$(op4).text("100~1000万");
				var op5 = document.createElement("option");
				$(op5).text("1000万以上");
				document.getElementById('ideaSort4').appendChild(op1);
				document.getElementById('ideaSort4').appendChild(op2);
				document.getElementById('ideaSort4').appendChild(op3);
				document.getElementById('ideaSort4').appendChild(op4);
				document.getElementById('ideaSort4').appendChild(op5);
			}else{ 
				$("#ideaSort4").empty();
				var op1 = document.createElement("option");
				$(op1).text("10万以下");
				var op2 = document.createElement("option");
				$(op2).text("10~100万");
				var op3 = document.createElement("option");
				$(op3).text("100~1000万");
				var op4 = document.createElement("option");
				$(op4).text("1000~1亿");
				var op5 = document.createElement("option");
				$(op5).text("1亿以上");
				document.getElementById('ideaSort4').appendChild(op1);
				document.getElementById('ideaSort4').appendChild(op2);
				document.getElementById('ideaSort4').appendChild(op3);
				document.getElementById('ideaSort4').appendChild(op4);
				document.getElementById('ideaSort4').appendChild(op5);
			}
		});
		//点击全选，选中所有好友
		$("#f-selectAll").click(function(){
			var selectAll = $(this).attr("checked");
			//alert($(this).prop('tagName'));
			if(selectAll == "checked"){
      				$(".checkSelect").attr("checked","true");
					$(".lyt-li").css("background-color","#ccc");
     		    }
     			else{
          			$(".checkSelect").removeAttr("checked");
      				$(".lyt-li").css("background-color","#eee");
    			}
		})
		
		$("#sure").click(function(){
			//收回通讯录部分，并准备所有被选中的好友名单
			//所有类名称为.lyt-li的背景为选中色的好友
			if(checkName()){
				$(".friendBorder").animate({
					left:"1350px",
					display:"inline"
				},1000);
				$("#friendList").animate({
					left:"1350px",
					display:"inline"
				},1000);
				$("#f-lim").removeAttr("disabled");	
			}else{
				alert("请选择好友！");
			}
		});
		$("#cancle").click(function(){
			//收回通讯录部分，并将权限设置为重新选择好友权限
			$(".friendBorder").animate({
					left:"1350px",
					display:"inline"
				},1000);
				$("#friendList").animate({
					left:"1350px",
					display:"inline"
				},1000);
			$(".checkSelect").removeAttr("checked");
      		$(".lyt-li").css("background-color","#eee");
      		$("#f-lim option:first").attr("selected","selected");
      		 $(".lytSpan2").trigger("click");
      		 $("#f-lim").removeAttr("disabled");
		});
		/*点击选择后检测上传文件的大小*/
		$("#f-change-stra").click();
	}  
	
	/*修改的地方资源分的上限设置*/
    $("#SourceText").bind('blur',function(){
    	var regt = /^(0|[1-9]\d*)$/;
        if ($(this).val() > 10 || !regt.test($(this).val())) {
        	$("#information").html('您输入的有误，请重新输入！');
        	$("#SourceText").attr("errorMsg","您输入的有误，请重新输入！");
        	$("#information").css('display','inline-block');
    	}else{
    		$("#SourceText").attr("errorMsg","");
    		$("#information").css('display','none');
    	}
    }).bind('focus',function(){
    	//$("#information").css('display','none'); 
    	$("#information").css('display','inline-block');
		$("#information").html('请输入0到10以内的数据，且选择上传文件！');
    });
    //检测策略标题
    $("#ideaTitle").bind("blur",function(){
    	var textValue = $(this).attr("value"); 
    	var ideaTitleLabel = $("#ideaTitleLabel").html().split("：")[0];
    	var str =new RegExp(/^[A-Z0-9]{10}$/);
    	if(textValue === ""){
    		$(this).attr("errorMsg",ideaTitleLabel+"不能为空！");
    	}else{
    		if(ideaTitleLabel === "策略标题" && textValue.length>15){
    			$(this).attr("errorMsg","策略标题不能超过十五个字，请检查你的输入！");
    		}else if(ideaTitleLabel === "策略编号" && !str.test(textValue)){
    			$(this).attr("errorMsg","策略编号只能由十个数字和大写字母组成，请检查你的输入！");
    		}else{
    			$(this).attr("errorMsg","");
    			if($("#uploadMethod").attr("value") == "上传代码"){
    				//根据策略编号查找策略的分类、关键词和策略内容并填充到页面
    				var dataJson = {
    						"ideaNum": textValue
    				}
    				$.ajax({
    					type: "POST",
    					dataType: "json",
    					data: dataJson,
    					url: "/shareIdea/IdeaInfo_getIdeaByIdeanum.action",
    					beforeSend:function(){
    						$("#IdeaShow").removeAttr("readOnly");
    					},
    					success: function(data){
    						console.log(data);
    						//将得到的值填写到页面中
    						$("#IdeaShow").val(data.ideaContent);//策略内容
    						$("#IdeaShow").attr("readOnly",true);
    						//策略分类
    						var category = data.ideaCatogry.split(",");
    						$("#ideaSort1").val(category[0]);
    						$("#ideaSort2").val(category[1]);
    						$("#ideaSort3").val(category[2]);
    						$("#ideaSort4").val(category[3]);
    						//策略关键字
    						$("#f-key-word").removeAttr("disabled");
    						$("#f-key-word").val(data.ideaKeyWord);
    						$("#f-key-word").attr("disabled",true);
    						//策略Id
    						$("#HiddenIdeaId").attr("value",data.ideaId);
    					},
    					error: function(data){
//    						alert("没查到数据，请检查策略编号是否正确！");
    						$("#ideaTitle").attr("errorMsg","该策略编号不存在，请核实后再试！");
//    						$("#ideaTitle").focus();
    					}
    				});
    			}
    		}
    	}
    	$("#ideaTitleError").html($("#ideaTitle").attr("errorMsg"));
    }).bind("focus",function(){
    	$("#ideaTitleError").html("请输入十五个字以内的策略标题！");
    	if($("#uploadMethod").attr("value") == "上传代码"){
    		$("#ideaTitleError").html("请输入要查找的策略编号！");
    		var ideaCategory = $('#ideaSort1 option:selected').val()+","+$('#ideaSort2 option:selected') .val()+
    		","+$('#ideaSort3 option:selected').val()+","+$('#ideaSort4 option:selected') .val();
    		console.log(ideaCategory);
    		var dataJson = {
    				"ideaCategory":ideaCategory
    		}
		}
    });
    //做提交表单前的验证处理
    $("#uploadSubmit").click(function(){
    	if(checkBeforSubmit($("#uploadMethod").val()==="上传策略"?true:false)){
    		//提交表单
    		document.myForm.submit();
    	}else{
    		alert("请填写完成后再提交！")
    	}
    });
    //检测关键字
    $("#f-key-word").bind("blur",function(){
    	var keyWord = $(this).attr("value");
    	keyWord = keyWord.replace(/[ ]/g,"");
    	console.log(keyWord);
    	$(this).attr("value",keyWord);
    	var keyWordList = keyWord.split(/[,，]/);
    	console.log(keyWordList);
    	for(var i=0; i<keyWordList.length; i++){
	    	if(keyWordList[i].length>30){
	    		$(this).attr("errorMsg","每个关键词的长度不要超过15个字哦！");
	    	}else{
	    		$(this).attr("errorMsg","");
	    	}
    	}
    	$("#keyWordError").html($("#f-key-word").attr("errorMsg"));
    }).bind("focus",function(){
    	$("#keyWordError").html("每个关键词请用逗号(,)隔开！");
    });
   
	//所属分类 一级分类点击事件
	$.ssp.fe.init();
});
function checkName(){
	var persons = new Array();
	var firendStr = "";
	persons = $(".lyt-li");
	for(var i = 0; i < persons.length;i++){
		if(getBg(persons[i]) =="#cccccc"){
			firendStr += $(persons[i]).attr("userId")+" ";
		}
	}
	$("#hiddenObj").attr("value", firendStr);
	console.log($("#hiddenObj").val());
	if(firendStr !== ""){
		return true;
	}
	return false;
}
//提交前检测信息
function checkBeforSubmit(flag){
	if(($("#ideaTitle").val()=="")||!($("#agreeUpload").attr("checked")=="checked") ||
				$("#ideaTitle").attr("errorMsg")!==""||$("#IdeaShow").val()==""){
		return false;
	}
	//资源分不为空的情况下，上传文件不能为空
	if($("#SourceText").val()!=""){
		if($("#textfield").html()=="请选择上传文件"){
			return false;
		}
	}
	if(flag){
		//上传策略
		//判断所有Input的errorMsg都不为空
		if($("#SourceText").attr("errorMsg")!==""){
			return false;
		}
	}else{
		//上传代码
		//资源分不能为空，上传文件不能为空
		if($("#SourceText").val()==""||$("#SourceText").attr("errorMsg")!==""){
			return false;
		}
	}
	return true;
}
//得到背景颜色的方法
function getBg(element)  
{ 
  var rgbToHex=function(rgbarray,array){  
      if (rgbarray.length < 3) return false;  
      if (rgbarray.length == 4 && rgbarray[3] == 0 && !array) return 'transparent';  
      var hex = [];  
      for (var i = 0; i < 3; i++){  
        var bit = (rgbarray[i] - 0).toString(16);  
        hex.push((bit.length == 1) ? '0' + bit : bit);  
      }  
      return array ? hex : '#' + hex.join('');  
    }  

  if (typeof element == "string") element = document.getElementById(element);  
  if (!element) return;  
  cssProperty = "backgroundColor";  
  mozillaEquivalentCSS = "background-color";  
  if (element.currentStyle)  
    var actualColor = element.currentStyle[cssProperty];  
  else  
  {  
    var cs = document.defaultView.getComputedStyle(element, null);  
    var actualColor = cs.getPropertyValue(mozillaEquivalentCSS).match(/\d{1,3}/g);  
 
    actualColor = (actualColor) ? rgbToHex(actualColor) : "transparent";  
  }  
  if (actualColor == "transparent" && element.parentNode) 
    return arguments.callee(element.parentNode);  
  if (actualColor == null)  
    return "#ffffff";  
  else  
    return actualColor;  
}  

//检查上传文件是否超过2M
function checkfile(event){
	try{
	 	var obj_file = $("#fileField").get(0).files[0];
	 	var filesize = 0;
	 	var fileName = event.target || window.event.srcElement;
	 	//document.getElementById('textsize').innerHTML=this.size>1024 ? (this.size>1024*1024 ? this.size+'MB':this.size+'kB'):this.size+'B';
	 	if(browserCfg.firefox || browserCfg.chrome || browserCfg.ie){
	 		filesize = obj_file.size;
	 	}else{
	 		alert(tipMsg);
	   	return;
	 	}
	 	if(filesize==-1){
	 		alert(tipMsg);
	 		return;
	 	}else if(filesize>maxsize){
	 		alert(errMsg);
	 		return;
		}else{
			//显示文件名称
			document.getElementById('textfield').innerHTML = fileName.value.substr(fileName.value.lastIndexOf('\\')+1);
			//显示文件大小
			var realFilePath = filesize>1024 ? (filesize>1024*1024 ? Math.ceil(filesize/1024/1024)+
					'MB':Math.ceil(filesize/1024)+'kB'):Math.ceil(filesize)+'B';
			document.getElementById("textsize").innerHTML = realFilePath;
			$("#fileSize").attr("value",realFilePath);
				
	 		return;
		}
	}catch(e){
		alert(e);
	}
}
//处理上传策略和上传代码的切换,flag为true表示上传策略，false表示上传代码
function initUpload(flag){
	if(flag){
		$("#f-change-code").css("background-color","#E7E7E7").css('color','black');
		$("#f-key-word").removeAttr("disabled");
		$("#f-resource").attr("disabled","true");
		$("#f-lim").removeAttr("disabled");
		$("#ideaSort1").removeAttr("disabled");
		$("#ideaSort2").removeAttr("disabled");
		$("#ideaSort3").removeAttr("disabled");
		$("#ideaSort4").removeAttr("disabled");
		$("#ideaTitleLabel").html("策略标题：");
		//策略输入区为输入
		$(".f-content-right-show").html("策略编辑");
		$("#uploadMethod").attr("value","上传策略");
		$("#IdeaShow").removeAttr("readOnly");
		//清空权限对应的好友
		$("#hiddenObj").attr("value","");
	}else{
		$("#f-change-stra").css("background-color","#E7E7E7").css('color','black');
		$("#f-key-word").attr("disabled","true");
		$("#f-resource").removeAttr("disabled");
		$("#f-lim").attr("disabled","true");
		$("#ideaSort1").attr("disabled","true");
		$("#ideaSort2").attr("disabled","true");
		$("#ideaSort3").attr("disabled","true");
		$("#ideaSort4").attr("disabled","true");
		$("#ideaTitleLabel").html("策略编号：");
		//策略展示区为显示
		$("#uploadMethod").attr("value","上传代码");
		$(".f-content-right-show").html("策略展示");
		$("#IdeaShow").attr("readOnly","true");
	}
	$("#ideaSort1").val(0);
	$("#ideaSort2").val(0);
	$("#ideaSort3").val(0);
	$("#ideaSort4").val(0);
	$("#ideaTitle").val("");
	$("#f-key-word").val("");
	$("#f-lim").val(0);
	$("#IdeaShow").val("");
	$("#f-key-word").attr("errorMsg","");
	$("#ideaTitle").attr("errorMsg","");
}

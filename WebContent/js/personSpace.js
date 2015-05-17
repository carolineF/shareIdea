$(document).ready(function(){ 
    operation();
    showIdeaBlock();
    showInfo();
});

var flog=0;       //记录操作状态  0 表示 我的策略  1表示好友策略  2 表示我的代码


function showCodeMsg(){
	$("#codeMsgParent").empty();
	 for(var i=0;i<codeMsg.length;i++){
		 createCodeMsg(codeMsg[i]['codeName'],codeMsg[i]['codeSize'],codeMsg[i]['codeCommentNo'],codeMsg[i]['codeDownNo'],codeMsg[i]['codeClickNo'],codeMsg[i]['codeId']); 
	 }
}

//显示策略块
function showIdeaBlock(){
	$(ideaBlock).empty();
  for(var i in jsonBlock.Block){
    var tit = jsonBlock.Block[i].title;
    var constr = jsonBlock.Block[i].content;
    var userName = jsonBlock.Block[i].author;
    var con = jsonBlock.Block[i].count;
    var id=jsonBlock.Block[i].id;
    var userId=jsonBlock.Block[i].userId;
    if(flog==1){
    	createIdeaBlock(ideaBlock,tit,constr,userName,con,false,id,userId);
    }else{
    	createIdeaBlock(ideaBlock,tit,constr,userName,con,true,id,userId)
    }  
   }
  if(jsonBlock.maxBlockCount <= 6){  
        $("#mqPage").hide(1);
    }
    else{
        $("#mqPage").show(1);
        clickPage(jsonBlock.pageCount);
        /*鼠标滑过操作*/
        $(".lyt-sub-btn").mouseover(function(){
         $(this).css('color','red');
        }).mouseout(function(){
            $(this).css('color','black');
        });
    }  
}

//显示用户基本信息
function showInfo(){
  $("#account").html(baseInfo.account);
  $("#userName").html(baseInfo.userName);
  $("#tel").html(baseInfo.tel);
  $("#ideaCount").html(baseInfo.ideaCount);
  $("#friendCount").html(baseInfo.friendCount);
  $("#score").html(baseInfo.score);
  $("#lastLogin").html(baseInfo.lastLogin);
}

function flushPageIdeaMsg(pageNum){ 
	if(pageNum==-1){
		pageNum=jsonBlock["pageCount"];
	}
	
	var sortNameList = $(".personal-idea-label").text();
	var sortName = sortNameList.split("：");
	var sn = sortName[1].replace(/>>/g,",");
	
	if(sn.split(",")[0]=="全部"){
		sn="";
	}
	var urlStr="";
	if(flog==0){
		urlStr = "IdeaInfo_flushIdeaMsg.action";
	}else if(flog==1){
		urlStr = "IdeaInfo_getFriendIdea.action";
	}else{
		urlStr = "CodeInfo_getPersonSpaceCodeMsg.action";
	}
	
	var json= {ideaSortName:sn,pageSize:'6',pageNo:pageNum};
	if(flog==2){
		json="";
	}
	console.log(urlStr);
	$.ajax({                         
		type: "POST",
		url: urlStr,
		data: json,
		dateType:"json",
		cache:false,
		success: function(data){
			console.log("进来了");
			console.log(data);
			if(flog==2){
				codeMsg =data["str"];
				showCodeMsg();
			}else{
				jsonBlock = data;
				showIdeaBlock();
			}
			console.log("执行成功了");
		},
		error:function(){
			alert("执行错误！");
		}
	});
}

function createCodeMsg(name,size,commentNo,downNo,clickNo,codeid){
	var trObj = document.createElement("tr");
	$(trObj).attr("codeid",codeid).css("cursor","pointer");
	
	var tdObj1 = document.createElement("td");
	$(tdObj1).addClass('headCodeMsg');
	var spanobj = document.createElement("span");
	$(spanobj).text(name);
	$(spanobj).attr("codeid",codeid)

	$(spanobj).bind("click",function(){
		window.location  = "CodeInfo_getViewCodeMsg?codeId="+codeid;
	});
	
	var tdObj2 = document.createElement("td");
	$(tdObj2).addClass('codeMsg').css("marginLeft",'80px').text(size);
	var tdObj3 = document.createElement("td");
	$(tdObj3).addClass('codeMsg').text(commentNo);
	var tdObj4 = document.createElement("td");
	$(tdObj4).addClass('codeMsg').text(commentNo);
	var tdObj5 = document.createElement("td");
	$(tdObj5).addClass('codeMsg').text(clickNo);
	
	var inputObj = document.createElement("input");
	$(inputObj).addClass("zh-code-all mr10 fl").attr({
		"name":'checkbox1',"type":"checkbox"
	}).css("marginTop",'14px');
	tdObj1.appendChild(inputObj);
	tdObj1.appendChild(spanobj);
	trObj.appendChild(tdObj1);
	trObj.appendChild(tdObj2);
	trObj.appendChild(tdObj3);
	trObj.appendChild(tdObj4);
	trObj.appendChild(tdObj5);
	
	document.getElementById("codeMsgParent").appendChild(trObj);
}


/*个人空间*/
function operation(){
    /*点击下载按钮*/
    $("#download").click(function(){
      alert("您确定要下载吗？");
      /*此处需后台处理*/
       $(".zh-main-rs").each(function(){
       $(".zh-main-rs1").css('display','block');
       $(".zh-main-rs3").css('display','none');
      });
    });
    /*点击删除按钮*/
    $("#delete").click(function(){
      /*这块还需要判断是否选中项*/
    	if(flog!=2){
    	var ideaIds="";
    	$("input[name='mqCheckbox']:checked").each(function(){  
    		 var ideaId = $(this).parent().parent().attr('ideaid');
    		 ideaIds+=ideaId+",";
    	})
    	var jsondata={
    		deleteIds:ideaIds
    	}
    	$.ajax({  
    		type: "POST",
    		url: "IdeaInfo_deleteIdeaMsg.action",
    		data: jsondata,
    		success: function(){
    			window.location.reload(true);   
    		},
    		error:function(){
    			alert("执行错误！");
    		}
    	});
    	}else{
    		var codeids="";
        	$("input[name='checkbox1']:checked").each(function(){  
        		 var codeid = $(this).parent().parent().attr('codeid');
        		 var codemsg = $(this).parent().parent().remove();
        		 codeids+=codeid+",";
        	});
        	var jsondata={
        		deleteIds:codeids
        	}
        	$.ajax({  
        		type: "POST",
        		url: "CodeInfo_deleteCodeMsg.action",
        		data: jsondata,
        		success: function(data){
        			$(codemsg).remove();   
        		},
        		error:function(){
        			alert("执行错误！");
        		}
        	});
    	}
    });
    /*鼠标滑过策略展示块*/
    $(".zh-main-rs").mouseover(function(){
    $(".zh-main-rs").each(function(index){
      $(".zh-main-rs1").eq(index).css('display','none');
      $(".zh-main-rs3").eq(index).css('display','block');
    });
    });
    /*全选*/
    $("#all-checkbox").click(function(){
       $("input[type='checkbox'][name^='checkbox1']").attr("checked", this.checked);
    }); 
    /*二级菜单的显示与隐藏*/
    $(".zh-myFirst").has("ul").mouseover(function(){
        $(this).children("ul").css('display','block');
    }).mouseout(function(){
        $(this).children("ul").css('display','none');
    }); 
    /*三级菜单的显示与隐藏*/
     $(".zh-mySecond").has("ul").mouseover(function(){
        $(this).children("ul").css('display','block');
    }).mouseout(function(){
        $(this).children("ul").css('display','none');
    });
    /*我的代码、我的策略、好友策略之间的转化*/
     $("#my-strategy").bind("click",function(){
        $("#strategy").css('display','block');
        $("#code").css('display','none');
        $("#my-strategy").css({color:'white',background:'#e44545'});
        $("#firends-strategy").css({color:'#000000',background:'#DFDFDF'});
        $("#my-code").css({color:'#000000',background:'#DFDFDF'});
        flog=0;
        $("#delete").show();
        $("#all-checkbox").show();
        flushPageIdeaMsg(1);
    });
     $("#firends-strategy").bind("click",function(){
        $("#strategy").css('display','block');
        $("#code").css('display','none'); 
        $("#firends-strategy").css({color:'white',background:'#e44545'});
        $("#my-strategy").css({color:'#000000',background:'#DFDFDF'});
        $("#my-code").css({color:'#000000',background:'#DFDFDF'});
        flog=1;
        $("#delete").hide();
        $("#all-checkbox").hide();
        flushPageIdeaMsg(1);
    });
     $("#my-code").bind("click",function(){
        $("#strategy").css('display','none');
        $("#code").css('display','block');
        $("#my-code").css({color:'white',background:'#e44545'});
        $("#my-strategy").css({color:'#000000',background:'#DFDFDF'});
        $("#firends-strategy").css({color:'#000000',background:'#DFDFDF'});
        flog=2;
        $("#delete").show();
        $("#all-checkbox").show();
        flushPageIdeaMsg(1);
        
    }); 
     
     
     
}

	
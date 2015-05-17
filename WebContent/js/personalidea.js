$(document).ready(function(){ 
	showIdeaBlock();
  	showInfo();
  	$("#addFriend").click(function(){console.log("whet");
	var t = this;
	var json = {
			friendName:baseInfo.userName,
			groupName:"全部"
	}
	$.ajax({                         
		type: "POST",
		url: "FriendOperation_addFriend",
		data: json,
		dateType:"json",
		cache:false,
		success: function(data){
			$(t).hide();
		},
		error:function(){
			alert("执行错误！");
		}
	});
  	});
});
//显示策略块
function showIdeaBlock(){
	for(var i in jsonBlock.Block){
		var tit = jsonBlock.Block[i].title;
		var constr = jsonBlock.Block[i].content;
		var userName = jsonBlock.Block[i].author;
		var con = jsonBlock.Block[i].count;
		var id = jsonBlock.Block[i].ideaId;
		createIdeaBlock(ideaBlock,tit,constr,userName,con,false,id);
	}
	if(jsonBlock.maxBlockCount <= 9){   
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
	$("#mqHeadImage").attr("src",baseInfo.headImageUrl);
	$("#name").html(baseInfo.userName);
	$("#job").html(baseInfo.userJob);
	$("#idea").html(baseInfo.ideaCount);
	$("#fans").html(baseInfo.userFens)
}


$(document).ready(function(){ 
	var initJson = {};
	var friendArray = [];
	for(var i=0;i<jsonFriend['colFriend'].length;i++){
		if(jsonFriend['colFriend'][i]['groupName']=="全部"){
			friendArray.push(jsonFriend['colFriend'][i]);
		}
	}
	initJson={
			maxFriend:friendArray.length,
			pageCount:parseFloat(friendArray.length)/6,
			colFriend:friendArray
	}
    showFriend(initJson);
});

//显示好友显示列表
function showFriend(json){
    var parent = $("#mqTable");
    $(parent).empty();
    for(var i in json.colFriend){
            var headImage = json.colFriend[i].headImage;
            var name = json.colFriend[i].name;
            var ideaCon = json.colFriend[i].ideaCount;
            createFriend(parent,headImage,name,ideaCon);
        }
    if(json.maxFriendCount <= 6){   
        $("#mqPage").hide(1);
    }
    else{
        $("#mqPage").show(1);
        clickPage(json.pageCount);
        /*鼠标滑过操作*/
        $(".lyt-sub-btn").mouseover(function(){
         $(this).css('color','red');
        }).mouseout(function(){
            $(this).css('color','black');
        });
    }  
}

//生成好友显示列表
function createFriend(parent,headImage,name,ideaCount){
    //添加行元素
    var tr = document.createElement("tr");
    $(tr).addClass("tableTr");
    $(parent).append(tr);

    //添加头像列
    var td = document.createElement("td");
    $(td).addClass("headImg");
    $(td).addClass("tac");
    tr.appendChild(td);
    
    //添加头像image
    var image = document.createElement("img");
    $(image).attr("src",headImage);
    td.appendChild(image);

    //添加名称列
    var tdName = document.createElement("td");
    $(tdName).html(name);
    $(tdName).addClass("headNick");
    $(tdName).addClass("tac");
    tr.appendChild(tdName);

    //添加策略总数列
    var ideaCon = document.createElement("td");
    $(ideaCon).html(ideaCount);
    $(ideaCon).addClass("headNick");
    $(ideaCon).addClass("tac");
    tr.appendChild(ideaCon);

    //添加操作列
    var tdOperate = document.createElement("td");
    $(tdOperate).addClass("delete");
    $(tdOperate).addClass("tac");
    tr.appendChild(tdOperate);

    //添加删除
    var mqDelete = document.createElement("span");
    $(mqDelete).html("删除").click(function(){
    	//通过Ajax传递数据到后台删除该好友！成功后删除页面元素
    	var jsondata = {
    		deleteFriendName:name
    	};
    	$.ajax({
			type:'POST',
			url:'/shareIdea/FriendOperation_deleteFriend.action',
			data:jsondata,
			dataType:'json',
			async:true,
			success:function(data){
				alert(data.msg);
				location.reload(true);
			}
    	});
    });
    $(mqDelete).addClass("font");
    tdOperate.appendChild(mqDelete);
}
$(function(){
    //准备数据并生成通讯录列表
    var friendList = document.getElementById("friendList");
    createAddressList(friendList,addressListJson,false);
    //翻页页数为7
    $("#.addressBarContext div").css("paddingBottom","0px")
    //新建联系人 新分组 删除分组点击事件
    $('#createFirends').bind('click',function(){
    	$("#friendnickName").val(""); 
    	$('#createFirend').show();
    	$("#tips").text("");
    	$('#deleteGroup').hide();
    	$('#addGroup').hide();
        $("#mainAddress").hide();
    });
   $('#deleteGroups').bind('click',function(){ 
    	$('#createFirend').hide();
    	$('#deleteGroup').show();
    	$('#addGroup').hide();
        $("#mainAddress").hide();
    });
    $('#addGroups').bind('click',function(){
    	$("#newGroupName").val("");
    	$('#createFirend').hide();
    	$('#deleteGroup').hide();
    	$('#addGroup').show();
        $("#mainAddress").hide();
    });
     /*分组的点击事件*/
    $(".lytSpan1").bind('click',function(){
        $('#mainAddress').show();
        $('#createFirend').hide();
        $('#deleteGroup').hide();
        $('#addGroup').hide(); 
    });
    $(".lytSpan2").bind('click',function(){
        $('#mainAddress').show();
        $('#createFirend').hide();
        $('#deleteGroup').hide();
        $('#addGroup').hide(); 
    });
    $("#all-checkbox").click(function(){
      $("input[type='checkbox'][name^='checkbox1']").attr("checked", this.checked);
    });
    
    $("#submitAddFriend").click(function(){ 
    	var name = $("#friendnickName").val();
    	var sortGroup = $("#sortGroup").val();
    	var jsondata = { 
    			"friendName":name,
    			"groupName":sortGroup
    	}
    	
    	$.ajax({
			type:'POST',
			url:'/shareIdea/FriendOperation_addFriend.action',
			data:jsondata,
			dataType:'json',
			async:true,
			success:function(data){
				var d = data.msg;
				d=="添加成功"?$("#tips").css("color","green"):$("#tips").css("color","red");
				$("#tips").text(d);
				if(d=="添加成功"){
					setTimeout(function(){
						$("#createFirend").hide();
						location.reload(true);
					},2000);
				}
			},
		});
    });
    
    $("#delteGroupBtn").click(function(){ 
    	var GroupName = $(".delteGroupName").val();
    	var jsondata = {
    		delteGroupName:GroupName
    	}
    	$.ajax({
			type:'POST',
			url:'/shareIdea/FriendOperation_deleteGroupName.action',
			data:jsondata,
			dataType:'json',
			async:true,
			success:function(data){
				var d = data.msg;
				d=="删除成功"?$("#tipdelate").css("color","green"):$("#tipdelate").css("color","red");
				$("#tipdelate").text(d);
				if(d=="删除成功"){
					setTimeout(function(){
						$("#delteGroupBtn").hide();
						location.reload(true);
					},2000);
				}
			},
    	});
    });
    
    $("#addGroupBtn").click(function(){ 
    	var groupName = $("#newGroupName").val();
    	var jsondata = {
    		newGroupName:groupName
    	};
    	$.ajax({
			type:'POST',
			url:'/shareIdea/FriendOperation_createGroup.action',
			data:jsondata,
			dataType:'json',
			async:true,
			success:function(data){
				var d = data.msg;
				d=="添加成功"?$("#tipsCreateGroup").css("color","green"):$("#tipsCreateGroup").css("color","red");
				$("#tipsCreateGroup").text(d);
				if(d=="添加成功"){
					setTimeout(function(){
						$("#addGroup").hide();
						location.reload(true);
					},2000);
				}
			},
    	});
    });
    
    $("#cancelDelete").click(function(){ 
    	$("#deleteGroup").hide();
    });
    $("#cancelAddFriend").click(function(){ 
    	$("#createFirend").hide();
    });
});

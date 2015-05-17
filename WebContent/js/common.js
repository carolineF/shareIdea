function createIdeaBlock(parent,title,content,author,count,flag,id,userId){
		//创建li
		var strategyLi = document.createElement("li");
		$(strategyLi).addClass("mqBlock");
		//创建外部div
		var block = document.createElement("div");
		$(block).addClass("ideaBorder");
		$(block).attr("ideaid",id);
		$(block).bind("click",function(){
			window.location = "viewStrategy_viewStrategy.action?ideaId="+id;
		})
		if(flag){
			//创建标题外大框
			var titleBorder = document.createElement("ul");
			$(titleBorder).addClass("titleBorder");

			//创建单选按钮
			var titleCheckbox = document.createElement("input");
			$(titleCheckbox).attr("type","checkbox");
			$(titleCheckbox).attr("name","mqCheckbox");
			$(titleCheckbox).addClass("titleCheckbox");
			$(titleCheckbox).click(function(event){
				event.stopPropagation();
			});
		}
		

		//创建标题
		var Title = document.createElement("li");
		if(title.length > 7){
			title = title.substr(0,7);
			title = title + "...";
		}
		$(Title).html(title);
		$(Title).addClass("ideaTitle");
		if(flag){
			$(Title).addClass("dieaTitleTrue");
		}
		else{
			$(Title).addClass("dieaTitleFalse");
		}

		

		//创建内容div
		var contentBorder = document.createElement("div");
		$(contentBorder).addClass("content");
		$(contentBorder).attr("name","mqContentName");

		//创建空格span
		var blank = document.createElement("span");
		$(blank).html("");
		$(blank).addClass("contentSpan");

		//创建内容span
		var contentSpan = document.createElement("span");
		if(content.length > 106){
			content = content.substr(0,106) + "...";
		}
		$(contentSpan).html(content);
		$(contentSpan).addClass("contentSpan");

		//创建作者点击量div
		var authorBorder = document.createElement("div");
		$(authorBorder).addClass("author")

		//创建作者a标签
		var authorA = document.createElement("a");
		$(authorA).html(author).attr("href","IdeaInfo_showOtherSpaceIdea?viewUserId="+userId);
		$(authorA).addClass("authorA");
		$(authorA).addClass("ml5");
		//创建点击量span
		var countSpan = document.createElement("span");
		$(countSpan).addClass("fr");
		var countStr = "点击量：" + count;
		$(countSpan).html(countStr);

		//选中添加背景图片
		if(flag){
			//点击一个策略块添加图片
			$(titleCheckbox).bind("click",function(){
				var attribute = $(this).attr("checked");
				if(attribute == "checked"){
					$(contentBorder).addClass("selectContent");
				}
				else{
					$(contentBorder).removeClass("selectContent")
				}
			})

			//点击全选按钮给策略块添加图片
			$("#all-checkbox").click(function(){
      			var attribute = $(this).attr("checked");
      			var contentArray = $("div[name='mqContentName']");
      			if(attribute == "checked"){
      				$("input[name='mqCheckbox']").attr("checked","true");
					$(contentArray).addClass("selectContent");
     		    }
     			else{
          			$("input[name='mqCheckbox']").removeAttr("checked");
      					$(contentArray).removeClass("selectContent");
    			}
   			 }); 
		}

		//完成父子关系appendChild
		parent.appendChild(strategyLi);
		strategyLi.appendChild(block);
		if(flag){
			block.appendChild(titleBorder);
			titleBorder.appendChild(Title);
			titleBorder.appendChild(titleCheckbox);
		}
		else{
			block.appendChild(Title);
		}
		
		block.appendChild(contentBorder);
		contentBorder.appendChild(blank);
		contentBorder.appendChild(contentSpan);
		block.appendChild(authorBorder);
		authorBorder.appendChild(authorA);
		authorBorder.appendChild(countSpan);
	}

var colorFlog = 0;
function createAddressList(parent,groupNameJson,flag){
	for(var i = 0; i<groupNameJson.length; i++){
		//创建ul
		var addressUl = document.createElement("ul");
		$(addressUl).addClass("lyt-address group");

		//创建第一个分组名
		if (flag) {
			var selectGroup1 = document.createElement("input");
			$(selectGroup1).attr("type","checkbox");
			$(selectGroup1).addClass("checkSelect")
			addressUl.appendChild(selectGroup1);
		};
		var span1 = document.createElement("span");
		span1.innerHTML = "&#9654" + groupNameJson[i].groupName;
		$(span1).addClass("lytSpan1");
		//创建第二个分组名
		var span2 = document.createElement("span");
		span2.innerHTML = "▼" + groupNameJson[i].groupName;
		$(span2).addClass("lytSpan2");
		//创建该分组下的好友列表
		var divList = document.createElement("div");
		$(divList).addClass("lyt-allPeople");
		var list = new Array();
		var userIdList = new Array();
		list = groupNameJson[i].groupList.split(":");
		userIdList = groupNameJson[i].userIds.split(":");
		for(var j=0; j<list.length;j++){
			var friendName = document.createElement("a");
			friendName.innerHTML = list[j];
			$(friendName).attr("name","mqFriend");
			$(friendName).addClass("lyt-li lyt-lii tac");
			$(friendName).attr("userId",userIdList[j]);
			if(!flag)
				$(friendName).attr("href","IdeaInfo_showOtherSpaceIdea?viewUserId="+userIdList[j]);
			divList.appendChild(friendName);
		}
		//添加元素到父类中
		parent.appendChild(addressUl);
		addressUl.appendChild(span1);
		addressUl.appendChild(span2);
		addressUl.appendChild(divList);
		//点击昵称选择好友
		if(flag){
			//完成背景色的切换
			$(".lyt-li").unbind("click");
			$(".lyt-li").bind("click",function(){
				var friendEl = $(this);
				if(colorFlog == 1){ 
					$(this).css("background-color","#eee")
					colorFlog=0;
				}else{ 
					$(this).css("background-color","#ccc")
					colorFlog=1;
				}
			});
			 $(".lytSpan1").bind("click",function(){
		    	    var $this = $(this).nextAll();
		    		$(this).nextAll().show("slow");
		    		$(this).hide();
		    		$(this).next().show();
		    });
				//点击分组全选该分组下的好友
			 $(".checkSelect").click(function(){
					var groupSelect = $(this).attr("checked");
					if(groupSelect == "checked"){
						alert("as");
						$(this).next().next().next().children(".lyt-li").css("background-color","#ccc");
					}else{
		      			$(this).next().next().next().children(".lyt-li").css("background-color","#eee");
		    		}
				});
		}else{
			 
			 $(".lytSpan1").bind("click",function(){
		    	    var $this = $(this).nextAll();
		    	    console.log("事件被触发  在common.js                     ");
		    	    //得到当前对象的分类名称  在jsonFriend 里找到相关分类的数据  放到一个新的里面
		    	   var name = $(this).text().substr(1);
		    	   
		    	    var col = [];
		    	    for(var i=0;i<jsonFriend['colFriend'].length;i++){
		    	    	console.log(jsonFriend['colFriend'][i]['groupName']== name);
			    	    if(jsonFriend['colFriend'][i]['groupName'] == name){
			    	    	col.push(jsonFriend['colFriend'][i]);
			    	    }
			    	}
		    	    var jsonData = {maxFriendCount:col.length,pageCount:Math.ceil(col.length/parseFloat(6)),
		    	    		colFriend:col};
		    	    showFriend(jsonData);
		    		$(this).nextAll().show("slow");
		    		$(this).hide();
		    		$(this).next().show();
		    });
		}
		// 通讯录部分/*鼠标滑过好友分组时*/
		 $(".lytSpan2").bind("click",function(){
	    	    var $this = $(this).nextAll();
	    		$(this).nextAll().hide("slow");
	    		$(this).hide();  
	    		$(this).prev().show();
	    });

		$('#selectAll').bind('click',function(){ 
			if($(this).attr('checked') === 'checked'){ 
				$('.selected').attr('checked','true');
			}else{ 	
				$('.selected').removeAttr('checked');;
			}
		});
		$('#friendList').bind('click',function(){ 
			$('#unshroud').show();
		});
	
	}
}
$(document).ready(function(){ 

	jQuery.ssp={};          
	jQuery.ssp.mq_personalidea={};   
	$.ssp.mq_personalidea=function(){  
		$("li").has("ul").mouseover(function(){
			$(this).children("ul").css("display","block");
		}).mouseout(function(){
			$(this).children("ul").css("display","none");
		});

		//点击全部分类
		$("#allSort").addClass("checkSort").bind("click",function(){
			$(".personal-idea-label").html("所属分类：全部");
			$(".checkSort").removeClass("checkSort");
			$(this).addClass("checkSort");
			flushPageIdeaMsg(1)
		});
		

		//点击三级分类
		$(".left ul li ul li").bind("click",function(){
			var a = $(this).html();
			$(".left ul").css("display","none");
			$(".checkSort").removeClass("checkSort");	
			var mqName = $(this).attr("name");
			var mqId = $(this).attr("mq");
			if(mqId == "现货"){
				$("#nowGoods").addClass("checkSort");
			}
			else if(mqId == "期货"){
				$("#futrueGoods").addClass("checkSort");
			}
			else if(mqId == "期权"){
				$("#futruePower").addClass("checkSort");
			}
			else if(mqId == "外汇"){
				$("#foreign").addClass("checkSort");
			}
			else if(mqId == "组合"){
				$("#group").addClass("checkSort");
			}
			$(".personal-idea-label").html("所属分类：" + mqName + a);
			flushPageIdeaMsg(1)
		})

	}
	
	$.ssp.mq_personalidea(); 
	//点击作者  
	$(".authorA").bind("click",function(){
		window.location.href = "personalidea.jsp";
	})
	

});
function  selectedNav(name){
	//添加区别登录与否的部分
	window.location=name;
};

//显示与隐藏：首页、上一页、尾页、下一页
function showHiddenBtn(number){
	var nowBtn = $(".bd-nowBtn");  //得到当前按钮
    var nowPage = parseInt(nowBtn.text()); 
    // 如果当前页是第一页，“上一页”和“首页”不显示
    if(nowPage==1){
    	$("#hz-firstPage").css("display","none");
    	$("#hz-lastPage").css("display","none");
    	$("#hz-nextPage").css("display","inline-block");
    	$("#finalPage").css("display","inline-block");
    }else if(nowPage==number){//如果当前是最后一页，“下一页”和“尾页”不显示
    	$("#hz-firstPage").css("display","inline-block");
    	$("#hz-lastPage").css("display","inline-block");
    	$("#hz-nextPage").css("display","none");
    	$("#finalPage").css("display","none");
    }else{
    	$("#hz-firstPage").css("display","inline-block");
    	$("#hz-lastPage").css("display","inline-block");
    	$("#hz-nextPage").css("display","inline-block");
    	$("#finalPage").css("display","inline-block");
    }

    if(number==0){
    	$(".hz-pageUp").css("display","none");
    }
    else if(number==1){//如果只有1页
    	$("#hz-firstPage").css("display","none");
    	$("#hz-lastPage").css("display","none");
    	$("#hz-nextPage").css("display","none");
    	$("#finalPage").css("display","none");
    	$(".hz-pageUp a").each(function(index,domEle){
    		if(index>0){
    			$(".hz-pageUp a").eq(index).css("display","none");
    		}
    	});
    }else if(number<5 && number>1){
    	$(".hz-pageUp a").each(function(index,domEle){
    		if(index>=number-1){
    			$(".hz-pageUp a").eq(index+1).css("display","none");
    		}
    	});
    }
}

// 翻页按钮 :参数number表示总页数
function clickPage(number){
	showHiddenBtn(number);
    //遍历hz-pageUp下的所有a标签
    $(".hz-pageUp a").bind("click",function(){
    	$(".hz-pageUp a").removeClass("bd-nowBtn");
    	$(this).addClass("bd-nowBtn");
    	var n = $(this).text();
    	showHiddenBtn(number);
    	flushPageIdeaMsg(n);
    });
    //点击首页
    $("#hz-firstPage").bind("click",function(){
    	$(".hz-pageUp a").removeClass("bd-nowBtn");
    	$($(".hz-pageUp a").get(0)).addClass("bd-nowBtn");
		$(".hz-pageUp a").each(function(index,domEle){
			$(".hz-pageUp a").eq(index).html(index+1);
		});
    	showHiddenBtn(number); 
    	flushPageIdeaMsg(1);
    });
    //点击尾页
    $("#finalPage").bind("click",function(){
    	$(".hz-pageUp a").removeClass("bd-nowBtn");
    	if(number<5){
    		$($(".hz-pageUp a").eq(number-1)).addClass("bd-nowBtn");
    	}else{
    		$($(".hz-pageUp a").last()).addClass("bd-nowBtn");
    	}
    	$(".hz-pageUp a").each(function(index,domEle){
    		if(number>5){
    			$(".hz-pageUp a").eq(index).html(number-4+index);	
    		}
    	});
    	showHiddenBtn(number); 
    	flushPageIdeaMsg(-1);
    });
    // 点击：上一页
    $("#hz-lastPage").bind("click",function(){
    	var nowBtn = $(".bd-nowBtn");  //得到当前按钮
    	var nowPage = parseInt(nowBtn.html());//得到当前按钮的值
    	var firstBtn = $(".hz-pageUp a").first();//得到第1个按钮
    	var firstPage = parseInt(firstBtn.html());
    	$(".hz-pageUp a").removeClass("bd-nowBtn");
        $(nowBtn).prev("a").addClass("bd-nowBtn");
        if (nowPage>1 && firstPage==nowPage){
	    	$(".hz-pageUp a").each(function(index,domEle){
	    		if(index==0){
	    			$(".hz-pageUp a").eq(index).html(nowPage-1);
	    			$(".hz-pageUp a").eq(index).addClass("bd-nowBtn")
	    		}else{
	    			$(".hz-pageUp a").eq(index).html(nowPage+index-1);
	    		}
	    	});
    	}; 
        showHiddenBtn(number);  
        flushPageIdeaMsg(nowPage-1);
    });
    // 点击：下一页
    $("#hz-nextPage").bind("click",function(){
    	var nowBtn = $(".bd-nowBtn");  //得到当前按钮
    	var nowPage = parseInt(nowBtn.html());//得到当前按钮的值
    	var lastBtn = $(".hz-pageUp a").last();//得到第5个按钮
    	var lastPage = parseInt(lastBtn.html());
    	$(".hz-pageUp a").removeClass("bd-nowBtn");
        $(nowBtn).next("a").addClass("bd-nowBtn");
    	if (nowPage<number && lastPage==nowPage){
	    	$(".hz-pageUp a").each(function(index,domEle){
	    		if(index==4){
	    			$(".hz-pageUp a").eq(index).html(nowPage+1);
	    			$(".hz-pageUp a").eq(index).addClass("bd-nowBtn")
	    		}else{
	    			$(".hz-pageUp a").eq(index).html(nowPage-3+index);
	    		}   		
	    	});
    	};    	
        showHiddenBtn(number);
        
        flushPageIdeaMsg(nowPage+1);
    });
}

    /*创建评论块儿,参数分别为头像、昵称、评论内容、评论者编号、评论时间*/
    function createCmtBlock(headUrl,name,content,num,time){

        var bdCmtBlock = $('<li></li>');     //创建一个父评论块儿
        bdCmtBlock.addClass("bd-cmt-block"); //给父评论块儿添加样式bd-cmt-block
    
        /*创建头像*/
        var bdCmtImg = $('<div></div>');     //创建一个子元素1，即左侧头像
        bdCmtImg.addClass("fl");
        var img = $("<img/>");//子元素1创建一个元素即孙子元素1，即头像
        // img.attr("src","./image/head.png");
        img.attr("id","bd-cmt-img");
        img.attr("src",headUrl);
        img.addClass("m10").addClass("w80").addClass("h80");
        bdCmtImg.append(img);             //把孙子元素1添加到子元素1
       
        var bdCmtRight = $('<div></div>');//创建子元素2，即评论块儿右侧区域
        bdCmtRight.addClass("bd-cmt-right").addClass("fr");
        bdCmtBlock.append(bdCmtRight).append(bdCmtImg);    //把子元素2添加到父元素评论块儿

        var bdCmtRtop = $('<div></div>'); //给右侧区域即子元素2创建元素即孙子2，即右侧顶部
        bdCmtRtop.addClass("bd-cmt-rtop").addClass("f16");
       
        /*创建评论内容*/
        var bdOtherCmt = $('<p></p>');    //给右侧区域即子元素2创建元素即孙子3，及评论内容
        bdOtherCmt.addClass("bd-other-cmt");
        bdOtherCmt.attr("id","bd-oth-cmt");
        bdOtherCmt.html(content);
        bdCmtRight.append(bdCmtRtop).append(bdOtherCmt);

        /*创建昵称*/
        var bdOthername = $('<div></div>');//给孙子2创建元素重孙子1，即昵称
        bdOthername.addClass("fl");
        var bdLink = $('<a></a>');
        bdLink.attr("id","bd-oth-name");
        bdLink.html(name);
        bdLink.addClass("f14").addClass("bd-othername");
        bdLink.attr("href","personalidea.html");//点击昵称跳转至个人空间
        bdOthername.append(bdLink);
        
        /*创建评论者编号*/
        var bdCmtFloor = $('<div></div>');//给孙子2创建元素重孙子3，即评论者编号
        bdCmtFloor.addClass("fr").addClass("mr20");
        bdCmtFloor.attr("id","bd-cmt-floor");
        bdCmtFloor.html(num+"#");

        /*创建评论时间*/
        var bdCmtTime = $('<div></div>');//给孙子2创建元素重孙子2，即评论时间
        bdCmtTime.addClass("fr").addClass("mr20");
        bdCmtTime.attr("id","bd-cmt-time");
        bdCmtTime.html(time);
        bdCmtRtop.append(bdCmtTime).append(bdOthername).append(bdCmtFloor);     
      
        $(".bd-cmt-blocks").append(bdCmtBlock);//把评论块儿添加到最外层评论包含块儿
    }

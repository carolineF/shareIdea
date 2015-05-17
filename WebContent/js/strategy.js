$(document).ready(function(){ 
	$.ssp.huazi();
	showIdeaBlock();
	showHotSearch();

})

function showHotSearch(){
	for(var i in jsonSearch.num){
		var str = jsonSearch.num[i].hotStr;
		var parent = $("#hotSearch");
		creatHotSearch(parent,str);
	}
}

function creatHotSearch(hotS,hotSearchContent){
	var liBlock = document.createElement("li");
	$(liBlock).bind("click",function(){
		window.location.href = "search.html";
	});
	var ABlock = document.createElement("a");
	$(ABlock).html(hotSearchContent);

	//构建父子关系
	$(hotS).append(liBlock);
	liBlock.appendChild(ABlock);
}

function showIdeaBlock(){
	$(ideaBlock).empty();
	
	for(var i in jsonBlock.Block){
		var tit = jsonBlock.Block[i].title;
		var constr = jsonBlock.Block[i].content;
		var userName = jsonBlock.Block[i].author;
		var con = jsonBlock.Block[i].count;
		var id = jsonBlock.Block[i].id;
		var userId = jsonBlock.Block[i].userId;
		createIdeaBlock(ideaBlock,tit,constr,userName,con,false,id,userId);
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

jQuery.ssp={};          
	jQuery.ssp.huazi={};   
	$.ssp.huazi=function(){   
		//隐藏期货的二级三级分类信息
		
		//给搜索输入框添加效果。获得焦点边框变亮。失去则返回。          证券
		$('#hz-searchText').focus(function(){
			$(this).css({background:"#fff"});
			$('#hz-searchBorder').css('border','2px solid rgba(255,0,0,1)');
		});
		$('#hz-searchText').blur(function(){ 
			$(this).css({background:"#ccc"}); 
			$('#hz-searchBorder').css('border','2px solid rgba(255,0,0,0.8)');
		});
		//给搜索输入框添加效果。获得焦点边框变亮。失去则返回。          期货
		$('#hz-FsearchText').focus(function(){
			$(this).css({background:"#fff"});
			$('#hz-FsearchBorder').css('border','2px solid rgba(255,0,0,1)');
		});
		$('#hz-FsearchText').blur(function(){ 
			$(this).css({background:"#ccc"}); 
			$('#hz-FsearchBorder').css('border','2px solid rgba(255,0,0,0.8)');
		});

		//搜索事件1
		$('#hz-FsearchBtn').bind('click',function(){
			var keyWord = $('input[name=search1]').val();  //输入框获得的关键字
			window.open('search.html');
		});
		//搜索事件2
		$('#hz-searchBtn').bind('click',function(){
			var keyWord = $('input[name=search2]').val(); 
//			var jsonData = {ideaSortName:keyWord+" ",pageNo:pageNum,pageSize:9}
//			console.log("数据准备完毕开始发送ajax");
//			
//			$.ajax({
//				type:'POST',
//				url:'/shareIdea/IdeaInfo_getStrategyIdeaMsg.action',
//				data:jsonData,
//				dataType:'json',
//				async:true,
//				success:function(data){
//					jsonBlock = data;
//					showIdeaBlock();
//					//location.reload(true);
//				},
//				error:function(){
//					console.log("ajax执行失败");
//				}
//	    	});
		});

		$("#hz-security").click(function(){ 
			$(".hz-checkSort1").removeClass("hz-checkSort1");
			$(this).addClass("hz-checkSort1");
			flushPageIdeaMsg(1);
		});
		$("#hz-futures").click(function(){ 
			$(".hz-checkSort1").removeClass("hz-checkSort1");
			$(this).addClass("hz-checkSort1");
			flushPageIdeaMsg(1);
		});
		$("#hz-CFT").click(function(){ 
			$(".hz-checkSort1").removeClass("hz-checkSort1");
			$(this).addClass("hz-checkSort1");
			flushPageIdeaMsg(1);
		});
		$("#hz-fu").click(function(){ 
			$(".hz-checkSort1").removeClass("hz-checkSort1");
			$(this).addClass("hz-checkSort1");
			flushPageIdeaMsg(1);
		});
		$("#hz-CT").click(function(){ 
			$(".hz-checkSort1").removeClass("hz-checkSort1");
			$(this).addClass("hz-checkSort1");
			flushPageIdeaMsg(1);
		});
	
		$("#hz-chose2Deg2").click(function(){ 
			$("#hz-chose2Deg1").removeClass("hz-select2Deg");
			$(this).addClass("hz-select2Deg");
			
			$("#hz-sort5").text("1万以下");
			$("#hz-sort6").text("1到10万");
			$("#hz-sort7").text("10到100万");
			$("#hz-sort8").text("100到1000万");
			$("#hz-sort9").text("1000万以上");
			flushPageIdeaMsg(1);
		});
		$("#hz-chose2Deg1").click(function(){ 
			$("#hz-chose2Deg2").removeClass("hz-select2Deg");
			$(this).addClass("hz-select2Deg");
			
			$("#hz-sort5").text("10万以下");
			$("#hz-sort6").text("10到100万");
			$("#hz-sort7").text("100到1000万");
			$("#hz-sort8").text("1000万到一亿");
			$("#hz-sort9").text("一亿以上");
			flushPageIdeaMsg(1);
		});
		//以下是所有三级分类项的点击事件处理
		$('.hz-tr2 ul li').click(function(){
			var value = $(this).attr("flag");
			if(value=='1'){ 
				$('#hz-sortType').removeClass('hz-sort3CheckedAll');
			}else{ 
				$('#hz-hasType').removeClass('hz-sort3CheckedAll');
			}
			$(".hz-tr2 ul li[flag="+value+"]").removeClass('hz-sort3Checked');
			$(this).addClass('hz-sort3Checked');
			flushPageIdeaMsg(1);
		});
		
		$('.hz-Ftr2 ul li').click(function(){
			var value = $(this).attr("flag");
			if(value=='3'){ 
				$('#hz-FsortType').removeClass('hz-sort3CheckedAll');
			}else if(value=='4'){ 
				$('#hz-FhasType').removeClass('hz-sort3CheckedAll');
			}else{ 
				$('#hz-Fvariety').removeClass('hz-sort3CheckedAll');
			}
			$(".hz-Ftr2 ul li[flag="+value+"]").removeClass('hz-sort3Checked');
			$(this).addClass('hz-sort3Checked');
			flushPageIdeaMsg(1);
		});
		//点击全部的点击事件
		$('#hz-sortType').click(function(){ 
			$(".hz-tr2 ul li[flag='1']").removeClass('hz-sort3Checked');
			$(this).addClass('hz-sort3CheckedAll');
			flushPageIdeaMsg(1);
		});
		$('#hz-hasType').click(function(){ 
			$(".hz-tr2 ul li[flag='2']").removeClass('hz-sort3Checked');
			$(this).addClass('hz-sort3CheckedAll');
			flushPageIdeaMsg(1);
		});
		$('#hz-FsortType').click(function(){ 
			$(".hz-Ftr2 ul li[flag='3']").removeClass('hz-sort3Checked');
			$(this).addClass('hz-sort3CheckedAll');
		});
		$('#hz-FhasType').click(function(){ 
			$(".hz-Ftr2 ul li[flag='4']").removeClass('hz-sort3Checked');
			$(this).addClass('hz-sort3CheckedAll');
		});
		$('#hz-Fvariety').click(function(){ 
			$(".hz-Ftr2 ul li[flag='5']").removeClass('hz-sort3Checked');
			$(this).addClass('hz-sort3CheckedAll');
		});
	}
	
	
	function flushPageIdeaMsg(pageNum,name){ 
		console.log("进入ajax请求了");
		var name1=$(".hz-checkSort1").text();
		var name2=$(".hz-select2Deg").text();
		var name3="";
		$(".hz-sort3Checked").each(function(){
			name3+=$(this).text()+"%";
		});
		var sortName ="";
		if(name3!=""){
			sortName = name1+","+name2+", %"+name3;	
		}else{
			sortName = name1+","+name2;
		}
		if(pageNum==-1){
			pageNum=jsonBlock["pageCount"];
		}
		console.log("准备好的参数："+sortName);
		var jsonData = {ideaSortName:sortName,pageNo:pageNum,pageSize:9}
		console.log("数据准备完毕开始发送ajax");
		
		$.ajax({
			type:'POST',
			url:'/shareIdea/IdeaInfo_getStrategyIdeaMsg.action',
			data:jsonData,
			dataType:'json',
			async:true,
			success:function(data){
				jsonBlock = data;
				showIdeaBlock();
				
				$(".bd-nowBtn").removeClass("bd-nowBtn");
				$("#hz-page1").addClass("bd-nowBtn");
			},
			error:function(){
				console.log("ajax执行失败");
			}
    	});
	}

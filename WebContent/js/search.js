 $(document).ready(function(){ 
 	 // /*准备和传递创建策略块的参数*/
   //  var ideaBlock = document.getElementById("ideaBlock");
   //  var constr = "我们的祖国是花园，0269园里花朵真鲜艳，和暖的阳光照耀着我们，每个人脸上都笑开颜，娃哈哈娃哈哈，每个人脸上都笑开颜，大姐姐你呀快快来，小弟弟你也莫躲开，手拉着手儿唱起那歌儿，我们的生活多愉快，娃哈哈...空间发呆卡夫卡的首付";
   //  var tit = "金属量化投资"
   //  var length = 12;
   //  if(constr.length > 100){
   //    constr = constr.substr(0,100);
   //    constr = constr + "...";
   //  }
   //  var userName = "妙强";
   //  var con = "10000";
   //  for(var i = 0;i < length;i++){
   //    createIdeaBlock(ideaBlock,tit,constr,userName,con);
   //  }

	
	
	$.ssp.huazi();
	showIdeaBlock();
});


//显示策略块
function showIdeaBlock(){
	$("#searchTag").html(jsonBlock.searchTag);
	for(var i in jsonBlock.Block){
		var tit = jsonBlock.Block[i].title;
		var constr = jsonBlock.Block[i].content;
		var userName = jsonBlock.Block[i].author;
		var con = jsonBlock.Block[i].count;
		createIdeaBlock(ideaBlock,tit,constr,userName,con);
	}
	if(jsonBlock.maxBlockCount <= 12){  
		alert(jsonBlock.maxBlockCount); 
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
	//给搜索输入框添加效果。获得焦点边框变亮。失去则返回。
	$('#hz-searchText').focus(function(){
		$(this).css({background:"#fff"});
		$('#hz-search').css('border','2px solid rgba(255,0,0,1)');
	});
	$('#hz-searchText').blur(function(){ 
		$(this).css({background:"#ccc"}); 
		$('#hz-search').css('border','2px solid rgba(255,0,0,0.8)');
	});
}
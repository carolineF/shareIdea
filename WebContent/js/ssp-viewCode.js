/*************************************************************
* 功能                    查看资源-代码V2    
* 函数列表（按顺序列表）  
**************************************************************/

$(document).ready(function(){ 
    shwoIdeaInfomation();
    showComment();
    $("pre").addClass("prettyprint linenums");
    prettyPrint();
    addDownLoadEvent();
});
function addDownLoadEvent(){
	$("#bd-load-btn").click(function(){
		var codeId = jsonCodeInformation["codeId"];
		var fileName = jsonCodeInformation["codeName"];
		$("input[name=codeId]").val(codeId);
		$("input[name=fileName]").val(fileName);
		document.downCode.submit();
	});
}

function showComment(){
  for(var i in jsonCommentInfomation.commentContent){
    var headUrl = jsonCommentInfomation.commentContent[i].headUrl;
    var name = jsonCommentInfomation.commentContent[i].name;
    var content = jsonCommentInfomation.commentContent[i].content;
    var num = jsonCommentInfomation.commentContent[i].num;
    var time = jsonCommentInfomation.commentContent[i].time;

    createCmtBlock(headUrl,name,content,num,time);
  }
  if(jsonCommentInfomation.maxBlockCount <= 10){   
        $("#mqPage").hide(1);
    }
    else{
        $("#mqPage").show(1);
        clickPage(jsonCommentInfomation.pageCount);
        /*鼠标滑过操作*/
        $(".lyt-sub-btn").mouseover(function(){
         $(this).css('color','red');
        }).mouseout(function(){
            $(this).css('color','black');
        });
    } 
  //createCmtBlock();
}

function shwoIdeaInfomation(){
  $("#bd-cf-name").html(jsonCodeInformation.codeName);
  $("#bd-upload-time").html("上传时间：" + jsonCodeInformation.upLoadTime);
  $("#bd-upload-name").html("昵称：" + jsonCodeInformation.author);
  $("#bd-upload-name").attr("title",jsonCodeInformation.author);
  $("#bd-upload-time").html("上传时间：" + jsonCodeInformation.upLoadTime);
  $("#score").html(jsonCodeInformation.codeScore + "分");
  $("#capacity").html(jsonCodeInformation.codeCapacity);
  $("#downLoadCount").html(jsonCodeInformation.downLoadCount + "次");
  $("#bd-cmt-total").html(jsonCodeInformation.CommentCount);
  $("#parentIdea").html(jsonCodeInformation.parentIdea);
  
}


 
  $(function(){
        // 字数统计
        $("#bd-mycmt-text").keyup(function(event){
          var len = $(this).val().length;   
          if(len==0||len>100){
            alert("评论字数应在0~100之间");
          }    
          $("#bd-font-count").html(len+"/100");
        }); 
  
        //展示评论块儿个数
        // showComment();
       
        // TODO 点击 翻页按钮
        //clickPage(7);

        //点击“提交”,将评论内容提交到服务器
        $("#bd-submit").click(function(){
            if($("#bd-mycmt-text").val()!=""){
            	var codeCommentContent = $("#bd-mycmt-text").val();
            	var codeId = jsonCodeInformation["codeId"];
            	var jsondata = {
            			"codeCommentContent":codeCommentContent,
            			"codeId":codeId
            	};
            	$.ajax({
        			type:'POST',
        			url:'CodeInfo_addCodeComment.action',
        			data:jsondata,
        			dataType:'json',
        			async:true,
        			success:function(data){
        				window.location.reload(true);   
        			}
            	});
            }else{
               alert("评论内容不能为空！");
               $("#bd-mycmt-text").focus();
               return false;
            }
         });
  });
      
   
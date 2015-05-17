/*************************************************************
* 功能                    查看资源-策略V2    
* 函数列表（按顺序列表）  
**************************************************************/

$(document).ready(function(){ 
    showComment();
    showIdea();
    showCode();
});

function clickBlock(){
	alert("asdfadsfadsfasdf");
}
function showCode(){
  var parent = $("#parentCode");
  for(var i = 0; i < jasonCode.code.length;i++){
    createCodeList(parent,jasonCode.code[i].codeName,jasonCode.code[i].codeAuthor);
  }
}

function createCodeList(parent,name,author){
  var dd = document.createElement("dd");
  $(dd).addClass("f16");
  $(dd).addClass("pl10");
  $(parent).append(dd);

  var codeName = document.createElement("a");
  $(codeName).attr("id","bd-file-name");
  $(codeName).attr("href","");
  $(codeName).attr("title",name);
  $(codeName).addClass("bd-filename");
  $(codeName).addClass("w150");
  $(codeName).addClass("fl");
  $(codeName).html(name);
  dd.appendChild(codeName);

  var codeAuthor = document.createElement("a");
  $(codeAuthor).attr("id","bd-author");
  $(codeAuthor).attr("href","");
  $(codeAuthor).addClass("bd-filename");
  $(codeAuthor).addClass("fr");
  $(codeAuthor).addClass("w50");
  $(codeAuthor).attr("title",author);
  $(codeAuthor).html(author);
  dd.appendChild(codeAuthor);
}

function showIdea(){
  $("#bd-main-tit").html(jsonCodeInformation.ideaTitle+"(策略编号：<b>"+jsonCodeInformation.ideaNum+"</b>)");
  $("#bd-main-time").html("上次修改时间：" + jsonCodeInformation.upLoadTime);
  $("#bd-edit-div").text(jsonCodeInformation.ideaContent);
  $("#bd-cmt-total").html(jsonCodeInformation.commentCon);
  $("#bd-main-myname").html(jsonCodeInformation.author);
  $("#bd-my-head").attr("src",jsonCodeInformation.headImage);
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

      $(function(){
    	  var ideaValue="";
        //鼠标进入“管理”，出现“编辑\删除”列表，鼠标移出“管理”，“编辑\删除”列表隐藏
        $("#bd-main-mng").hover(function(){
         $("#bd-edit-rev").css("display","list-item");
          });

        // 点击“编辑”，1、“管理”变为“确定”和“取消”；2、策略展示文本框变为可编辑状态
        isClickShow("#bd-edit","hide");
        // 点击“确定”或“取消”，1、“管理”变为显示，“确定”或“取消”隐藏；2、策略展示文本框变为不可编辑状态
        isClickShow("#bd-sure","show");
        isClickShow("#bd-cancel","show");
        
        // 点击“确定”按钮，更改策略文本框内容，并数据库中的内容一并修改TODO
        $("#bd-sure").bind("click",function(){
          var  val = $("#bd-edit-div").val();
          console.log(val);
          var jsondata = {
        	  newIdea : val,
        	  ideaId:jsonCodeInformation["ideaId"]
          };
	          $.ajax({
		  			type:'POST',
		  			url:'IdeaInfo_modifyIdea.action',
		  			data:jsondata,
		  			dataType:'json',
		  			async:true,
		  			success:function(data){
		  				window.location.reload(true);
		  			}
	      	 });
        });

        // 点击“取消”按钮，将恢复策略文本框原来的内容(即数据库中的内容)
        $("#bd-cancel").bind("click",function(){
        	window.location.reload(true);
        });

        // 点击“评论”，清空评论文本框内容并获得焦点
        $(".bd-main-cmtcount").bind("click",function(){
            $("#bd-mycmt-text").focus();
        });
               
         $("#bd-submit").click(function(){
            if($("#bd-mycmt-text").val()!=""){
            	var codeCommentContent = $("#bd-mycmt-text").val();
            	var codeId = jsonCodeInformation["ideaId"];
            	var jsondata = {
            			"ideaCommentContent":codeCommentContent,
            			"IdeaId":codeId
            	};
            	$.ajax({
        			type:'POST',
        			url:'IdeaInfo_addIdeaComment.action',
        			data:jsondata,
        			dataType:'json',
        			async:true,
        			success:function(data){
        				window.location.reload(true);   
        			}
            	});
            }
            else{
               alert("评论内容不能为空！");
               $("#bd-mycmt-text").focus();
               return false;
            }
         });
      
      });
     
      function  isClickShow($sel,eveMethod){
        $($sel).bind("click",function(){
            //点击“确定”或“取消”
            if(eveMethod=="show"){
                $("#bd-main-mng").show();
                $("#bd-cancel").hide();
                $("#bd-sure").hide();
                // 策略文本框变为不可编辑
                $("#bd-edit-div").attr("onfocus","$('#bd-edit-div')[0].blur()");
            }
            else{//点击“编辑”
              $("#bd-main-mng").hide();
                $("#bd-cancel").show();
                $("#bd-sure").show();
                // 策略文本框变为可编辑，且获得焦点
                $("#bd-edit-div").removeAttr("onfocus");
                $("#bd-edit-div").focus();
            }
         });  
      }

$(document).ready(function(){ 
  mqClickfunc();
  initInformation();
});
/*个人资料*/
function mqClickfunc(){
    /*点击修改按钮*/
     $(peopleAdress.elements).attr("disabled",true);//修改的内容
    /*点击修改个人资料*/
     $(".modifyDetail").bind("click",function(){
        ($("#userSexDiv").html())==="男"? ($(':radio[name="user.userSex"]').eq(0).attr("checked",true)):($(':radio[name="user.userSex"]').eq(1).attr("checked",true));
        $(peopleAdress.elements).css('display','inline');
        $(peopleAdress.elements).attr('disabled',false);
        $("#mqUserName").attr('disabled',true);
        $("#myEmail").attr('disabled',true);
        $("#myTel").attr('disabled',true);
        $("#myJob").css({border:"1px solid #A9A9A9"});
        $("textarea").css({border:"1px solid #A9A9A9"});
        $("select").css({border:"1px solid #A9A9A9"});
        $("select").css('display','inline');
        $("input[type='radio']").css('display','inline');
        $("#submit").css('display','block'); 
        $("#delete").css('display','block');
        $("#modify").css('display','none');
        $("#sex1").css('color','black');
        $("#sex1").css('display','inline-block');
        $("#userSexDiv").css('display','none');
        $("#sex2").css('display','inline-block');
        $(".location").css('display','none');/*修改的地方*/
        var a1=$(".location").html().split(", ")[0];
        var a2=$(".location").html().split(", ")[1];
        var a3=$(".location").html().split(", ")[2];
        $("#slc1").val(a1);
        $("#slc2").val(a2);
        $("#slc3").val(a3);
    });
    $("#modify").bind("click",function(){
    	($("#userSexDiv").html())==="男"? ($(':radio[name="user.userSex"]').eq(0).attr("checked",true)):($(':radio[name="user.userSex"]').eq(1).attr("checked",true));
        $(peopleAdress.elements).css('display','inline');
        $(peopleAdress.elements).attr('disabled',false);
        $("#mqUserName").attr('disabled',true);
        $("#myEmail").attr('disabled',true);
        $("#myTel").attr('disabled',true);
        $("#myJob").css({border:"1px solid #A9A9A9"});
        $("textarea").css({border:"1px solid #A9A9A9"});
        $("select").css({border:"1px solid #A9A9A9"});
        $("select").css('display','inline');
        $("input[type='radio']").css('display','inline');
        $("#submit").css('display','block'); 
        $("#delete").css('display','block');
        $("#modify").css('display','none');
        $("#sex1").css('color','black');
        $("#sex1").css('display','inline-block');
        $("#userSexDiv").css('display','none');
        $("#sex2").css('display','inline-block');
        $(".location").css('display','none');/*修改的地方*/
        var a1=$(".location").html().split(", ")[0];
        var a2=$(".location").html().split(", ")[1];
        var a3=$(".location").html().split(", ")[2];
        $("#slc1").val(a1);
        $("#slc2").val(a2);
        $("#slc3").val(a3);
    });
    /*点击取消按钮*/
    $("#delete").bind("click",function(){
        $(peopleAdress.elements).css('display','inline');
        $(peopleAdress.elements).attr("disabled",true);
        $(peopleAdress.elements).css({border:"0px solid #A9A9A9"});
        $("select").css({border:"0px solid #A9A9A9"});
        $("#submit").css('display','none');
        $("#delete").css('display','none');
        $("#modify").css('display','block');
        $("#f-mail").css('display','none');
        $("#f-tel").css('display','none');
        $("#sex1").css('color','#545454');
        $("#sex1").css('display','none');
        $("#sex2").css('display','none');
        $("#slc1").css('display','none');
        $("#slc2").css('display','none');
        $("#slc3").css('display','none');
        $("#i-job").css('display','none');
        $("#myMail").css('background','white');
        $("#warn-mail").css('display','none');
        $("#error-mail").css('display','none');
        $("#warn-job").css('display','none');
        $("#error-job").css('display','none');
        $("#warn-tel").css('display','none');
        $("#error-tel").css('display','none');
        $("#myTel").css('background','white');
        $("#i-mail").css('display','none');
        $("#i-tel").css('display','none');
        $(".sex2").css('display','none');
        $(".sex1").css('display','none');
        $("#userSexDiv").css('display','inline');
       // $(".location").css('dispaly','none');
        $(".location").css('display','inline');/*修改的地方*/
        initInformation();
    });
    /*点击完善信息*/
    $("#complete-info").bind("click",function(){
        $("input:not(:button,:hidden)").prop("disabled", true);
    });
	/*所在地*/
	$("#slc1").change(function(){
		if (this.value != null){
			$("#slc2").show();
		}
	});
	$("#slc2").change(function(){
	    if(this.value != null){
	        $("#slc3").show();
	    }
    });
    /*修改头像、修改手机、修改邮箱、安全退出所涉及的跳转*/
	$("#modify-img").click(function(){
	    location.href = "changeHead.jsp";//location.href实现客户端页面的跳转 
	});
	$("#modify-exit").click(function(){
	    location.href = "#";//location.href实现客户端页面的跳转 
	});
	$("#modify-tel").bind("click",function(){
        $("#zh-mydata").css('display','none');
        $("#zh-checked1").css('display','block');
        $("#zh-checked2").css('display','none');
    });
    $("#modify-mail").bind("click",function(){
        $("#zh-mydata").css('display','none');
        $("#zh-checked1").css('display','none');
        $("#zh-checked2").css('display','block');
    });
    /*点击个人资料*/
    $("#myself").bind("click",function(){
    	$("#zh-mydata").css('display','block');
        $("#zh-checked").css('display','none');
    });
    /*点击手机号码文本框*/
    $("#myTel").bind("click",function(){
            $("#warn-tel").css('display','inline');
            $("#f-tel").css('display','none');
            $("#error-tel").css('display','none');
            $("#i-tel").css('display','nbone');
    }).blur(function(){
        var regt = /^1[3|4|5|8][0-9]\d{4,8}$/;
        if (!regt.test($("#myTel").val())) {
            $("#warn-tel").css('display','none');
            $("#f-tel").css('display','none');
            $("#error-tel").css('display','inline');
        }else{
            $("#i-tel").css('display','inline');
            $("#warn-tel").css('display','none');
        }
    });
     /*点击邮箱文本框*/
    $("#checkedEmail").blur(function(){
    	$("#checkEmail").text("");
         var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if (!reg.test($("#checkedEmail").val())) {
                $("#checkEmail").css('display','inline');
                }else{
                	var dataJson = {
                			accountNo:$("#checkedEmail").val()
                	}
                	 $.ajax({
      					type: "POST",
      					dataType: "json",
      					async: true,
      					url: "shareIdea/UserBaseInfo_checkAccount.action",
      					data: dataJson,
      					success:function(data){
      						if(data.isExist){
      							 $("#checkEmail").css('display','inline');
      							 $("#sendEmailSecCode").attr("disabled",true);
      							 $("#checkEmail").text("邮箱不可用");
      						}else{
      							 $("#sendEmailSecCode").removeAttr("disabled" );
      							$("#checkEmail").css('display','none');
      						}
      					}
      				});
                
                }
    });
     /*点击职业输入框*/
    $("#myJob").bind("click",function(){
            $("#warn-job").css('display','inline');
            $("#f-job").css('display','none');
            $("#error-job").css('display','none');
            $("#i-job").css('display','none');
    }).blur(function(){
        var regj = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
        if (!regj.test($("#myJob").val())) {
            $("#warn-job").css('display','none');
            $("#f-job").css('display','none');
            $("#error-job").css('display','inline');
        }else{
            $("#i-job").css('display','inline');
            $("#warn-job").css('display','none');
        }
    });
}


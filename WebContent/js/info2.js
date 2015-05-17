
var index=1;
var res = "";//最后的提交结果
var inde = [];
var i=0;
$(document).ready(function(){
	//点击提交按钮
	$(".mbtn1").bind("click",function(){
		res="";
		$("p").each(function(){
			var num="";
			var value="";
		
			num = $(this).attr("id");
			
			var mSpan = $(this).children("span");
			if(num==16){
				mSpan.each(function(){
					var mInput = $(this).children('input:checked');
					var mH4 = $(this).children('h4');
					mInput.each(function(){
						 value += $(this).attr("value"); 
						 mH4.each(function(){
							 var grade = $(this).children('input:checked');
							    value += grade.attr("value")+",";
						 });
					});
					
				});	
			}else{	
				mSpan.each(function(){
					var mInput = $(this).children('input:checked');
					mInput.each(function(){
						 value += $(this).attr("value")+","; //用“,”分隔多选项中的每个选项
					});
					
				});	
			}
			
			if(value!=""){
				inde[parseInt(num)-1]=1;
				value=value.substring(0,value.length-1);
				res+=num+":"+value+".";	//用“:”分割题目与答案，用‘.’分隔每个题目
			}
			
		});
		
		res = res.substring(0,res.length-1);//所有的选中内容，包括题号和答案
		console.log("前台数据==="+res);
		
		//TODO
		var arrRes = res.split(".");
		//alert("分割数组长度="+arrRes.length);
		var jsonData = {"resSurvey":res}
		if(arrRes.length!=35){
			var c=0;
			for(var i=0;i<=inde.length;i++){
				if(inde[i]!=1){
					c=i+1;
					alert("请完成第"+c+"个题目后提交");
					return ;
				}
			}
		}else{
			$.ajax({
				type:'POST',
				url:'/shareIdea/UserBaseInfo_submitSurvey.action',
				dataType:'json',
				data:jsonData,
				async:true,
				success:function(data){
					console.log("成功了");
					window.location="ideaInfo";
				},
				error:function(data){
					alert("后台运行异常");
					console.log(data);
				}
			});
		}
	});	
});


//第16个
function initItem16(){
	var selectNum = 1;
	var radios,grade;
	var items = ["风险性：","收益性：","流通性：","安全性："];
	var length = items.length;
	var divObj = document.createElement('div');
	$(divObj).addClass('testBlock');

	var pObj = document.createElement('p');
	$(pObj).text("16、您最关注投资产标的哪些属性？（1分为不关注，5分为非常关注）：").attr("id",index++);
	divObj.appendChild(pObj);

	for(var i=0;i<length;i++){
		var span = document.createElement('span');
		var input = document.createElement('input');
		var h4=document.createElement('h4');
		$(span).addClass("disBlock");
		$(input).attr("value",items[i]);
		$(h4).text(items[i]);
		for(var j=1;j<=5;j++){
			radios = document.createElement('input');
			$(radios).attr({"type":"radio","name":"m16Radio"+i,"value":j+"分"});
			$(radios).attr({"checked":true});//防止用户不选，默认选中
			grade = document.createElement('h4');
			$(grade).text(j+"分  ");
			h4.appendChild(radios);
			h4.appendChild(grade);
		}
		
		$(input).addClass("fd-radio").attr("selectNum",selectNum++);
		$(input).attr({"type":"checkbox"});

		span.appendChild(input);
		span.appendChild(h4);
		pObj.appendChild(span);		
		
	}

	document.getElementById('surveyDiv').appendChild(divObj);
}

function initItem1(title,item){ 
	var selectNum=1;
	
	var length = item.length;
	var divObj = document.createElement('div');
	$(divObj).addClass('testBlock');

	var pObj = document.createElement('p');
	$(pObj).text(title).attr("id",index++);
	divObj.appendChild(pObj);

	for(var i=0;i<length;i++){
	
		var span = document.createElement('span');
		var input = document.createElement('input');
		var h4=document.createElement('h4');
		$(span).addClass("disBlock");
		$(h4).text(item[i]);
		$(input).attr("value",item[i]);
	
		$(input).addClass("fd-radio").attr("selectNum",selectNum++);
		$(input).attr({"type":"radio","name":"mRadio"+(index-1)});
		$(input).attr({"checked":true});//测试时，默认选中
		
		span.appendChild(input);
		span.appendChild(h4);
		pObj.appendChild(span);				
	}
	document.getElementById('surveyDiv').appendChild(divObj);
}	


function initItem2(title,item){ 
	var selectNum=1;
		
	var length = item.length;
	var divObj = document.createElement('div');
	$(divObj).addClass('testBlock');

	var pObj = document.createElement('p');
	$(pObj).text(title).attr("id",index++);
	divObj.appendChild(pObj);

	for(var i=0;i<length;i++){
		var span = document.createElement('span');
		var input = document.createElement('input');
		var h4=document.createElement('h4');
		$(span).addClass("disBlock");
		$(input).attr("value",item[i]);
		$(h4).text(item[i]);
		$(input).addClass("fd-radio").attr("selectNum",selectNum++);
		$(input).attr({"type":"checkbox","name":"mCheckbox"+(index-1)});

		span.appendChild(input);
		span.appendChild(h4);
		pObj.appendChild(span);		
		
	}

	document.getElementById('surveyDiv').appendChild(divObj);
}	


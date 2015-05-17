
var index=1;
var inde = [];
var i=0;
var lastRes="";//最后的提交结果字符串
var lastResLength=0;//最后的提交结果数组长度
$(document).ready(function(){
	if(window.localStorage){
		//localStorage.removeItem("answerItem");
		var answerItem = localStorage.getItem("answerItem");
		console.log(answerItem);
		if(answerItem !==null){
			var questionList = answerItem.split(".");
			for(var item=0;item<questionList.length;item++){
				var answer = questionList[item].split(":");
				var s = document.getElementById(answer[0]);
				var Items = answer[1].split(",");
				for(var checkItem=0; checkItem<Items.length;checkItem++){
					if(answer[0]==16){
						//TODO 填充第十六题
						var question16 = Items[checkItem].split("：")
						console.log($(s).find("input[value=\""+question16[0]+"：\"]").val());
						console.log($(s).find("input[value=\""+question16[0]+"：\"]").next().find("input[value=\""+question16[1]+"\"]").val());
						$(s).find("input[value=\""+question16[0]+"：\"]").attr("checked",true);
						$(s).find("input[value=\""+question16[0]+"：\"]").next().find("input[value=\""+question16[1]+"\"]").attr("checked",true);
					}else{
					console.log($(s).find("input[value=\""+Items[checkItem]+"\"]").val());
					$(s).find("input[value=\""+Items[checkItem]+"\"]").attr("checked",true);
				}
			}
		}
	}
}

	var divD = document.getElementById("bodyPage");
	var clickOn = document.getElementById("click");
	var surveyDiv = document.getElementById("surveyDiv");
	var value = clickOn.innerHTML;
	var height = window.screen.height;
	var j = 0;
	surveyDiv.style["borderBottomWidth"]= 3 + "px";
	surveyDiv.style["borderBottomStyle"]="solid";
	surveyDiv.style["borderBottomColor"]="#eee";
	clickOn.onclick = function (){
				//alert(j);
				if(j == 0){
					check(0,13);
					j = j + 1;
				}else if(j == 1){
					check(13,28);
					j = j + 1;
				}else if(j == 2){
					check(28,35);
					j = j + 1;
				}
				if (divD.offsetTop > -(5.2*height)) {
					if(divD.offsetTop >= -(2.7*height)){
						window.scrollBy(0,-(2.6*height)); 
						surveyDiv.style["borderTopWidth"]= 3 + "px";
						surveyDiv.style["borderTopStyle"]="solid";
						surveyDiv.style["borderTopColor"]="#eee";
						divD.style.top = divD.offsetTop - 2010 + "px";
						surveyDiv.style.paddingTop = 70 + "px";
						surveyDiv.style["borderBottomWidth"]= 3 + "px";
						surveyDiv.style["borderBottomStyle"]="solid";
						surveyDiv.style["borderBottomColor"]="#eee";
					}
					if(divD.offsetTop < -(2.7*height)){
						clickOn.innerHTML = "提交";
						surveyDiv.style["borderTopWidth"]= 3 + "px";
						surveyDiv.style["borderTopStyle"]="solid";
						surveyDiv.style["borderTopColor"]="#eee";
						divD.style.top = divD.offsetTop - 60 + "px";
					}
				}
			}
});


function check(k,t){
	var res="";
		for(var i=k;i<t;i++){
			($("p").eq(i)).each(function(){
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
			
	 }
		var arrRes = res.split(".");
//		alert("arrRes.length-1==="+(arrRes.length-1));
		//判断每一页未填写的题目
		if((arrRes.length-1)==(t-k)){
			lastRes+=((k!=0)?".":"")+res;
			lastRes = lastRes.substring(0, lastRes.length-1);
			var lastResArr = lastRes.split(".");
			lastResLength = lastResArr.length-1;
			
			alert("累加lastRes===="+lastRes);
			if(window.localStorage){
				if($("#click").html() == "提交"){
					var jsonData = {"resSurvey":lastRes}
					$.ajax({
						type:'POST',
						url:'/shareIdea/UserBaseInfo_submitSurvey.action',
						dataType:'json',
						data:jsonData,
						async:true,
						success:function(data){
							console.log("成功了");
							localStorage.removeItem("answerItem");
							window.location="ideaInfo";
						},
						error:function(data){
							alert("后台运行异常");
							console.log(data);
						}
					});
				}
				localStorage.setItem("answerItem",lastRes);//存储最后的的数据
			}
//			alert("lastResLength"+lastResLength);
		}
		
		if((arrRes.length-1)!= (t-k)){
			var c=0;
			for(var i=k;i<=inde.length;i++){
				if(inde[i]!=1){	
					c=i+1;
					if(c<29){
						alert("请完成第"+c+"个题目后进入下一页");
					}else{
						alert("请完成第"+c+"个题目后提交");
					}
					
					$("#bodyDiv").get(0).offsetTop = $("#bodyDiv").offsetTop + 2070 + "px";
					return ;
				}
			}
		}	
}


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

	document.getElementById('bodyPage').appendChild(divObj);
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
		//$(input).attr({"checked":true});
		
		span.appendChild(input);
		span.appendChild(h4);
		pObj.appendChild(span);				
	}
	document.getElementById('bodyPage').appendChild(divObj);
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

	document.getElementById('bodyPage').appendChild(divObj);
}	


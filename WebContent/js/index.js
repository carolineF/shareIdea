var WINDOWWIDTH=1100;  //第一画布canvas的宽度
var WINDOWHEIGHT=570;//第一画布canvas的高度
var RADIUS=8;        //圆的半径
var MARGIN_LEFT=80;   //第一个数字在画布的左边30px处
var MARGIN_TOP=20;		//第一个数字在画布的定点下边30px处
var curShowTimeSeconds=0;                        //当前剩余的秒数

var balls=[];                                    //所有下落的小球
var colors=["#33b5e5","#0099cc","#aa66cc","#9933cc","#99cc00","#669900","#ffbb33","#ff8800","#ff4444","#cc00cc"];

window.onload=function(){ 
	WINDOWWIDTH=$(window).width();
	WINDOWHEIGHT=$(window).height(); 
	setTimeout(function(){
		$('.hz-top').hide();
		$('.hz-topLogoNav').hide();
		$('#animate2').hide();
		$('#animate3').hide();
		
		$('.hz-indexBody').animate({ 
			width: $(window).width()+'',
    		height: $(window).height()+'' 
		},'slow');
	},1000)
	setTimeout(function(){
		$('.hz-top').show();
		$('.hz-topLogoNav').show();
		$('#animate2').show();
		$('#animate3').show();

		$('.hz-indexBody').animate({ 
			width: WINDOWWIDTH,
    		height: WINDOWHEIGHT 
		},'slow'); 
		$('#canvas').hide('slow');
		$('#canvas1').hide('slow');
		$('.animated').addClass('animated swing');
	},4000);
	setTimeout(function(){ 
		$('#animate2').hide('slow');
		$('#animate3').addClass('animated flipInX');
	},8000);
	setTimeout(function(){ 
		window.location = 'strategy.jsp';
	},11000);
	initCanvas();
}

function initCanvas(){ 
	var canvas =  document.getElementById("canvas");
	var context = canvas.getContext("2d");
	var canvas1 =  document.getElementById("canvas1");
	var context1 = canvas1.getContext("2d");

	canvas.width=WINDOWWIDTH;
	canvas.height=WINDOWHEIGHT;

	canvas1.width=500;
	canvas1.height=300;

	context1.font="bold 35px Arial";

	var linearGrad = context1.createLinearGradient(0,0,800,0);
	linearGrad.addColorStop(0.0,'#9933cc');
	linearGrad.addColorStop(0.25,'#33b5e5');
	linearGrad.addColorStop(0.5,'#aa66cc');
	linearGrad.addColorStop(0.75,'#ff8800');
	linearGrad.addColorStop(1.0,'#0099cc');
	context1.fillStyle=linearGrad;
	context1.fillText("时间易碎，分享就在此刻!",35,35);

	curShowTimeSeconds = getCurTime(); //获得剩余的时间秒数目

	setInterval(function(){ 
		render(context);  //定时渲染
		update();         //定时更新
	},50);
}

function update(){ 
	var nextShowTimeSeconds = getCurTime();
	var nextHour = parseInt(nextShowTimeSeconds/3600);
	var nextMinutes = parseInt((nextShowTimeSeconds-nextHour*3600)/60);
	var nextSeconds = parseInt(nextShowTimeSeconds%60);
	
	var curHour = parseInt(curShowTimeSeconds/3600);
	var curMinutes = parseInt((curShowTimeSeconds-curHour*3600)/60);
	var curSeconds = parseInt(curShowTimeSeconds%60);

	if(nextSeconds != curSeconds){ 
		if(parseInt(curHour/10)!=parseInt(nextHour/10)){	//当时间发生变化时，添加小球 
			addBalls(MARGIN_LEFT+0,MARGIN_TOP,parseInt(curHour/10));
		}
		if(parseInt(curHour%10)!=parseInt(nextHour%10)){ 
			addBalls(MARGIN_LEFT+15*(RADIUS+1),MARGIN_TOP,parseInt(curHour/10));
		}

		if(parseInt(curMinutes/10)!=parseInt(nextMinutes/10)){ 
			addBalls(MARGIN_LEFT+39*(RADIUS+1),MARGIN_TOP,parseInt(curMinutes/10));
		}
		if(parseInt(curMinutes%10)!=parseInt(nextMinutes%10)){ 
			addBalls(MARGIN_LEFT+54*(RADIUS+1),MARGIN_TOP,parseInt(curMinutes%10));
		}
		
		if(parseInt(curSeconds/10)!=parseInt(nextSeconds/10)){ 
			addBalls(MARGIN_LEFT+78*(RADIUS+1),MARGIN_TOP,parseInt(curSeconds/10));
		}
		if(parseInt(curSeconds%10)!=parseInt(nextSeconds%10)){ 
			addBalls(MARGIN_LEFT+93*(RADIUS+1),MARGIN_TOP,parseInt(nextSeconds%10));
		}

		curShowTimeSeconds = nextShowTimeSeconds;         //更新时间点
	}
	
	updateBalls();
}

function updateBalls(){ //更新小球位置使其移动
	for(var i=0;i<balls.length;i++){
		balls[i].x += balls[i].vx; //x轴方向匀速运动
		balls[i].y += balls[i].vy; //y轴方向按照加速度变速运动
		balls[i].vy += balls[i].g;

		if(balls[i].y>=WINDOWHEIGHT-RADIUS){ //碰撞检测。球碰到下壁反弹，能量损失0.65倍
			balls[i].y = WINDOWHEIGHT-RADIUS;
			balls[i].vy =-balls[i].vy*0.65;
		}
	}
	var cnt=0;
	for(var i=0;i<balls.length;i++){ 
		if(balls[i].x+RADIUS>0 && balls[i].x-RADIUS<WINDOWWIDTH){ 
			balls[cnt++]=balls[i];
		}
	}
	while(balls.length>Math.min(300,cnt)){ 
		balls.pop();
	}
}

function addBalls(x,y,num){ //当时间数字发生变化的时候，添加一组小球。这组小球的初始位置就是数字点阵	
	for(var i=0; i<digt[num].length;i++){ 
		for(var j=0 ; j<digt[num][i].length;j++){ 
			if(digt[num][i][j]==1){ 
				var aBall={ //记录参数到balls
					x:x+j*2*(RADIUS+1)+(RADIUS+1),
					y:y+i*2*(RADIUS+1)+(RADIUS+1),
					g:1.5+Math.random(),
					vx:Math.pow(-1,Math.ceil(Math.random()*1000))*4,
					vy:-5,
					color:colors[Math.floor(Math.random()*colors.length)]
				};
				balls.push(aBall);
			}
		}
	}
}

function getCurTime(){ //当前时间减去结束时间的秒数即为当前剩余时间
	var curTime  = new Date();
	var ret = curTime.getHours()*3600+curTime.getMinutes()*60+curTime.getSeconds();

	return ret;
}

function render(context){     //渲染
	context.clearRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);            //刷新画布

	var hour = parseInt(curShowTimeSeconds/3600);          //当前剩余小时值
	var minutes = parseInt((curShowTimeSeconds-hour*3600)/60);
	var seconds = parseInt(curShowTimeSeconds%60);
	//初始化到画布
	renderDigt(MARGIN_LEFT,MARGIN_TOP,parseInt(hour/10),context);   
	renderDigt(MARGIN_LEFT+15*(RADIUS+1),MARGIN_TOP,parseInt(hour%10),context);
	renderDigt(MARGIN_LEFT+30*(RADIUS+1),MARGIN_TOP,10,context);

	
	renderDigt(MARGIN_LEFT+39*(RADIUS+1),MARGIN_TOP,parseInt(minutes/10),context);
	renderDigt(MARGIN_LEFT+54*(RADIUS+1),MARGIN_TOP,parseInt(minutes%10),context);
	renderDigt(MARGIN_LEFT+69*(RADIUS+1),MARGIN_TOP,10,context);
	renderDigt(MARGIN_LEFT+78*(RADIUS+1),MARGIN_TOP,parseInt(seconds/10),context);
	renderDigt(MARGIN_LEFT+93*(RADIUS+1),MARGIN_TOP,parseInt(seconds%10),context);
	//散落小球的初始化渲染
	for(var i=0;i<balls.length;i++){
		context.fillStyle=balls[i].color;

		context.beginPath();
		context.arc(balls[i].x,balls[i].y,RADIUS,0,2*Math.PI,true);
		context.closePath();

		context.fill();
	}

}
//实例化数字点阵
function renderDigt(x,y,num,context){ 
	context.fillStyle="#000";

	for(var i=0; i<digt[num].length;i++){ 
		for(var j=0 ; j<digt[num][i].length;j++){ 
			if(digt[num][i][j]==1){ 
				context.beginPath();
				context.arc(x+j*2*(RADIUS+1)+(RADIUS+1),y+i*2*(RADIUS+1)+(RADIUS+1),RADIUS,0,2*Math.PI);
				context.closePath();
				context.fill();
			}
		}
	}
}
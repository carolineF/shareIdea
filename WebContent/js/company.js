$(document).ready(function(){
	disPlayCompanyItem();
	login();
});
{	
	var JsonBlock={
		Block1:[
			{
				title: "1、贵公司是：",			
				content:["公募基金","私募基金","证券资产管理公司","保险资产管理公司","银行理财","信托公司",
						  "其他（请注明）："]
			},
			{
				title: "2、您在贵公司的职务是：",			
				content:["公司负责人（董事长、总经理等）","公司投资部门负责人（基金经理等）","公司投资具体操作人（操盘手等）",
						 "公司投资顾问（分析师等）"]
			},
			{
				title: "3、贵公司金融投资部门的规模为：",			
				content:["未设立专门金融投资部门","3人以下","3-8人","8-20人","20-50人","50人以上"]
			},
			{
				title: "4、贵公司参与金融投资的时间是：",			
				content:["1年及以下","1-5年","5-10年","10-15人","15年及以上"]
			},
			{
				title: "5、贵公司目前的总资本规模为：",			
				content:["500万以下","500万-2000万","2000万-1亿","1亿-5亿","5亿-20亿","20亿以上"]
			},
			{
				title: "6、贵公司目前的投资结构是（各项之和为100%）：",			
				content:["股票及股票基金：___%","债券及债券基金：___%","金融衍生品：___%","社保基金、保险产品等：___%",
					    "其他金融产品（请注明）：_____"]
			},
			{
				title: "7、贵公司最近3个月的金融投资收益如何：",			
				content:["亏损15%以上","亏损8%以上、15%以下","亏损3%以上、8%以下","亏损或盈利在3%以内","盈利在3%以上、8%以内",
						 "盈利在8%以上、15%以下","盈利在15%以下"]
			},
			{
				title: "8、贵公司最近一年的金融投资收益如何：",			
				content:["亏损30%以上","亏损15%以上、30%以内","亏损5%以上、15%以内","盈利或亏损5%以内","盈利5%以上、15%以内",
						 "盈利15%以上、30%以内","盈利30%以上"]	
			},
			{
				title: "9、贵公司最近3个月的金融投资最大回撤如何：",			
				content:["超过10%","超过7%小于10%","超过3%小于7%","小于3%"]	     
			},
			{
				title: "10、贵公司最近一年的金融投资最大回撤如何：",			
				content:["超过30%","超过20%小于30%","超过12%小于20%","超过6%小于12%","小于6%"]
			},
			{
				title: "11、贵公司进行投资的资金来源主要是：",			
				content:["自有资金","他人委托","借款或贷款（年利率3%以下）","借款或贷款（年利率3%-7%）","借款或贷款（年利率7%以上）"]		
			},
			{
				title: "12、贵公司面临的现金流压力如何：",			
				content:["现金流长期充裕，几乎没有压力","现金流短期没压力，长期压力较小","现金流长期有一定压力，需要一定的投资收益补充现金流",
						 "现金流短期有一定压力，需要流动性较强资产","现金流压力较大，随时需要将投资转化为现金"]	
			},
			{
				title: "13、贵公司投资的预期年收益为：",			
				content:["4%以下","4%-7%","7%-12%","12%-20%","20%-30%","30%以上"]	
			},
			{
				title: "14、贵公司投资的预期时间长度为：",			
				content:["3个月以下","3个月到6个月","6个月到一年","1年到2年","2年到5年",
					     "5年到10年","10年以上"]	
			},
			{
				title: "15、贵公司预计进行投资的规模为：",			
				content:["100万以下","100万-500万","500万-1000万","1000万-2000万","2000万-5000万","5000万以上"]
			},
			{
				title: "16、贵公司可以将多少比例的投资收益用于再投资：",			
				content:["所有投资收益","50%以上的投资收益","20%-50%的投资收益","20%以下的投资收益","所有的投资收益都不做再投资"]		
			},
			{
				title: "17、贵公司未来3年内预计达到的资产规模为：",			
				content:["500万以下","500万-2000万","2000万-1亿","1亿-5亿","5亿-20亿","20亿以上"]	
			},
			{
				title: "18、贵公司最关注投资产标的哪些属性？（分数越高，关注程度越高）",			
				content:["风险性： 1  2  3  4  5","收益性： 1  2  3  4  5","流通性： 1  2  3  4  5","安全性： 1  2  3  4  5"]		
			},
			{
				title: "19、贵公司在未来一年的风险承受能力为：",			
				content:["不低于预期收益","不低于同期银行一年期存款利率","本金不亏损","本金亏损不超过10%",
						 "本金亏损不超过20%","本金亏损20%以上"]	
			},
			{
				title: "20、贵公司接受新鲜事物的能力：",			
				content:["经常尝试新鲜事物","偶尔尝试新鲜食物","接受新鲜事物比较慢"]	
			},
			{
				title: "21、综合收益和风险，贵公司最愿意将资金投资到下面哪个方向：",			
				content:["非保本结构性产品（与货币、商品、利率等挂钩）","股票、股票型基金","股票与债券混合型产品",
	                     "债券、债券基金、货币市场基金","国债或存款","股指期货、期权","其他（请注明）："]
			},
			{
				title: "22、贵公司更喜欢哪种类型的交易策略：",			
				content:["完全对冲套利型","有较小的风险敞口型","完全无对冲保护型"]
			},
			{
				title: "23、贵公司能够接受有杠杆的交易？",			
				content:["不能接受有杠杆的交易","可以接受有2倍以下杠杆的交易","可以接受有2-5倍杠杆的交易",
						  "可以接受有2-10倍杠杆的交易","可以接受有10倍杠杆以上的交易"]

			},
			{
				title: "24、贵公司能否接受投资新的交易品种？",			
				content:["完全可以接受投资新的交易品种","无所谓是否投资新的交易品种","完全不能接受投资新的交易品种"]	
			},
			{
				title: "25、贵公司认为比较理想的可以考察投资绩效的时间段是多长？",			
				content:["10年以上","5年至10年","3年至5年","1至3年","1年以下"]	
			},
			{
				title: "26、如果运用新策略进行投资，贵公司更倾向于选择下列哪种：",			
				content:["有获得30%以上收益的可能，但也有损失30%本金的可能","投资本金可接受一定范围内波动，但预期收益在10%以内",
	                     "投资本金基本能得到保障，回报高于定期存款即可","不希望投资本金承担风险，接受的回报大约与定期存款一样"]	
			},
			{
				title: "27、出现亏损贵公司一般如何操作选择：",			
				content:["交易前设好止损并能够执行止损操作","虽然没有设好止损但实际操作中能够在一定范围内执行止损",
						 "开始不止损，并补仓摊低成本，跌到自己难以承受时再止损","不止损，继续持有等待解套"]	
			},
			{
				title: "28、贵公司在使用新策略的开始，是否接受先半人工的模拟策略，然后再实现计算机自动交易？",			
				content:["是","否"]	
			},
			{
				title: "29、在使用新的策略/交易方法进入市场交易时，贵公司可以用于交易的资金量是多少？",			
				content:["100万及以下","101万-500万","501万-1000万","1001万-5000万","5000万以上"]	
			}
		],
		Block2:[{
				title: "30、贵公司参与过的金融市场有（可多选）：",			
				content:["沪深股票市场","香港股票市场","新三板市场","国际股票市场","债券市场","外汇市场","股指期货市场",
				"期权市场","基金市场","其他（请注明）："]
			},
			{
				title: "31、贵公司希望参与的金融市场有（可多选）：",			
				content:["沪深股票市场","香港股票市场","新三板市场","国际股票市场","债券市场","外汇市场","股指期货市场",
				"期权市场","基金市场","其他（请注明）："]	
			},
			{
				title: "32、贵公司不希望进入的金融市场有（可多选）：",			
				content:["沪深股票市场","香港股票市场","新三板市场","国际股票市场","债券市场","外汇市场","股指期货市场",
				"期权市场","基金市场","其他（请注明）："]	
			},
			{
				title: "33、贵公司是否进行过量化交易？量化交易进行的市场有（可多选）：",			
				content:["未进行过量化交易","沪深股票市场","香港股票市场","新三板股票市场","国际股票市场",
					     "债券市场","外汇市场","股指期货","期权市场","基金市场"]	
			},
			{
				title: "34、贵公司是否进行过计算机交易？计算机交易进行的市场有（可多选）：",			
				content:["未进行过量化交易","沪深股票市场","香港股票市场","新三板股票市场","国际股票市场",
					     "债券市场","外汇市场","股指期货","期权市场","基金市场"]	
			},]
	}
}
//显示问题列表
function disPlayCompanyItem(){
	for (var i in JsonBlock.Block1) {
		var title=JsonBlock.Block1[i].title;
		var content=JsonBlock.Block1[i].content;
		initItem1(title,content);
	}
	for (var j in JsonBlock.Block2) {
		var title=JsonBlock.Block2[j].title;
		var content=JsonBlock.Block2[j].content;
		initItem2(title,content);
	}
}
function login(){
	var input1=document.createElement('input');
	$(input1).attr({"type":"button","value":"提交"});
	$(input1).addClass('mbtn1');
	document.getElementById('bodyPage').appendChild(input1);
	var input2=document.createElement('input');
	$(input2).attr({"type":"button","value":"取消"});
	$(input2).addClass('mbtn2');
	document.getElementById('bodyPage').appendChild(input2);
}


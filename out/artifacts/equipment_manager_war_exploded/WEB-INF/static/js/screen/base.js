function fnW(str) {
    var num;
    str >= 10 ? num = str : num = "0" + str;
    return num;
}

//中国地图开始
//var china_map =echarts.init(document.getElementById("china_map"),'macarons'); 
var china_map =echarts.init(document.getElementById("china_map"),'infographic'); 
//var china_map =echarts.init(document.getElementById("china_map"),'shine'); 


function randomData() {
		return Math.round(Math.random()*500);
	}
 
	var mydata = [
		{name: '北京',value: randomData() },{name: '天津',value: randomData() },
		{name: '上海',value: randomData() },{name: '重庆',value: randomData() },
		{name: '河北',value: randomData() },{name: '河南',value: randomData() },
		{name: '云南',value: randomData() },{name: '辽宁',value: randomData() },
		{name: '黑龙江',value: randomData() },{name: '湖南',value: randomData()},
		{name: '安徽',value: randomData() },{name: '山东',value: randomData() },
		{name: '新疆',value: randomData() },{name: '江苏',value: randomData() },
		{name: '浙江',value: randomData() },{name: '江西',value: randomData() },
		{name: '湖北',value: randomData() },{name: '广西',value: randomData() },
		{name: '甘肃',value: randomData() },{name: '山西',value: randomData() },
		{name: '内蒙古',value: randomData() },{name: '陕西',value: randomData()},
		{name: '吉林',value: randomData() },{name: '福建',value: randomData() },
		{name: '贵州',value: randomData() },{name: '广东',value: randomData() },
		{name: '青海',value: randomData() },{name: '西藏',value: randomData() },
		{name: '四川',value: randomData() },{name: '宁夏',value: randomData() },
		{name: '海南',value: randomData() },{name: '台湾',value: randomData() },
		{name: '香港',value: randomData() },{name: '澳门',value: randomData() }
	];
 
	var option = {
		//backgroundColor: '#FFFFFF',
		
		title: {
			text: '项目分布情况',
			textStyle:{color:'#fff'},
			//subtext: '纯属虚构',
			x:'center'
		},
		tooltip : {
			trigger: 'item'
		},
		visualMap: {
			show : false,
			x: 'left',
			y: 'bottom',
			//layoutCenter:['30%','30%'],
			splitList: [ 
				{start: 500, end:600},{start: 400, end: 500},
				{start: 300, end: 400},{start: 200, end: 300},
				{start: 100, end: 200},{start: 0, end: 100},
			],
			color: ['#ff0', '#ffff00', '#0E94EB','#6FBCF0', '#F0F06F', '#00CC00']
		},
		series: [{
			name: '项目分布情况',
			type: 'map',
			mapType: 'china', 
			roam: true,
			label: {
				normal: {
					show: false
				},
				emphasis: {
					show: false
				}
			},
			data:mydata
		}]
	};

china_map.setOption(option);
//中国地图结束







//获取当前时间
var timer = setInterval(function () {
    var date = new Date();
    var year = date.getFullYear(); //当前年份
    var month = date.getMonth(); //当前月份
    var data = date.getDate(); //天
    var hours = date.getHours(); //小时
    var minute = date.getMinutes(); //分
    var second = date.getSeconds(); //秒
    var day = date.getDay(); //获取当前星期几 
    var ampm = hours < 12 ? 'am' : 'pm';
    $('#time').html(fnW(hours) + ":" + fnW(minute) + ":" + fnW(second));
    $('#date').html('<span>' + year + '/' + (month + 1) + '/' + data + '</span><span>' + ampm + '</span><span>周' + day + '</span>')

}, 1000)


//违法犯罪人员分析占比，带边框效果的饼图
//var pie_fanzui =echarts.init(document.getElementById("pie_fanzui"),'macarons'); 
var pie_fanzui =echarts.init(document.getElementById("pie_fanzui"),'infographic'); 
option = {
    title : {
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['卖淫嫖娼','经侦嫌疑','重点人口','刑贞重点','吸毒人口'],
        textStyle: {color: '#fff'}
    },
    
	label: {
	     normal: {
	          textStyle: {
	                color: 'red'  // 改变标示文字的颜色
	          }
	     }
	},
    series : [
        {
            name: '违法犯罪人员分析',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'卖淫嫖娼'},
                {value:310, name:'经侦嫌疑'},
                {value:234, name:'重点人口'},
                {value:135, name:'刑贞重点'},
                {value:1548, name:'吸毒人口'}
            ],
          
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
            
        }
    ]
};
pie_fanzui.setOption(option);
//----------------------违法犯罪人员分析占比end---------------



//违法犯罪人员年龄分析占比，带边框效果的饼图
//var pie_age =echarts.init(document.getElementById("pie_age"),'macarons'); 
var pie_age =echarts.init(document.getElementById("pie_age"),'infographic'); 
//var pie_age =echarts.init(document.getElementById("pie_age"),'shine'); 
option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:['18-30','31-40','41-50','51-60','65岁以上','未标明'],
        textStyle: {color: '#fff'}
    },
    series: [
        {
            name:'违法犯罪人员年龄分布',
            type:'pie',
            radius: ['30%', '55%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '20',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:335, name:'18-30'},
                {value:310, name:'31-40'},
                {value:234, name:'41-50'},
                {value:135, name:'51-60'},
                {value:135, name:'65岁以上'},
                {value:1548, name:'未标明'}
            ]
        }
    ]
};
pie_age.setOption(option);
//----------------------违法犯罪人员年龄分析占比end---------------



//===================人口出入时间段统计=======================
//var line_time =echarts.init(document.getElementById("line_time"),'shine'); 
var line_time =echarts.init(document.getElementById("line_time"),'macarons'); 
//var line_time =echarts.init(document.getElementById("line_time"),'infographic'); 
var option = {
        // 给echarts图设置背景色
        //backgroundColor: '#FBFBFB',  // -----------> // 给echarts图设置背景色
        color: ['#7FFF00'],
        tooltip: {
            trigger: 'axis'
        },
       
		grid:{
                    x:40,
                    y:30,
                    x2:5,
                    y2:20
                    
                },
        calculable: true,


        xAxis: [{
             type: 'category',
        data: ['6:00-9:00', '10:00-12:00', '13:00-15:00', '16:00-20:00', '21:00-24:00'],
     axisLabel: {
			color: "#7FFF00" //刻度线标签颜色
			}
        }],
        yAxis: [{

            type: 'value',
            axisLabel: {
			color: "#7FFF00" //刻度线标签颜色
			}
        }],
        series: [{
            name: '人次',
            type: 'line',
            data: [800, 300, 500, 800, 300, 600],
            
        }]
    };


line_time.setOption(option);


//=========违法犯罪人员地区分布开始=======================
//var qufenbu_data =echarts.init(document.getElementById("qufenbu_data"),'shine'); 
//var qufenbu_data =echarts.init(document.getElementById("qufenbu_data"),'macarons'); 
var qufenbu_data =echarts.init(document.getElementById("qufenbu_data"),'infographic'); 
option = {
    color: ['#FADB71'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        x:30,
        y:10,
        x2:15,
        y2:20
    },
    xAxis : [
        {
            type : 'category',
            data : ['河北', '天津', '北京', '新疆', '内蒙', '宁夏', '海南'],
            axisTick: {
                alignWithLabel: true
            },
            axisLabel: {
			color: "#FADB71" //刻度线标签颜色
			}
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel: {
			color: "#FADB71" //刻度线标签颜色
			}
        }
    ],
    series : [
        {
            name:'地区分布',
            type:'bar',
            barWidth: '60%',
            data:[10, 52, 200, 334, 390, 330, 220]
        }
    ]
};

qufenbu_data.setOption(option);
//=========违法犯罪人员地区分布结束=======================

//------------------------------------------
var data_1={
    "code":0,
    "businessTypeAnalysis":{
        "orderNum":["39","77","96","41","24","17","10","15"],
        "categoryArr":["项目A","项目B","项目C","项目D","项目E","项目F","项目G","项目H"],
        "projectNum":["10.79","17.05","14.84","10.07","5.58","10.36","2.00","4.43"],
     //   "projectNum":["50","150","200","250","300","350","400","450"],
        "legendArr":["耗时时间","订单量"]
    }
};

var project_data =echarts.init(document.getElementById("project_data"),'infographic');
var maxOrder=Math.max.apply(null,data_1.businessTypeAnalysis.orderNum);
option1 = {
    title : {text:'',subtext:'',top:'3',right:'0'},
    tooltip: {trigger: 'axis'},
    grid: {left: '8%',right: '8%',bottom: '10%'},
    xAxis: {type: 'category',axisLine: {lineStyle: {color: '#57617B'}},axisLabel: {interval:0,textStyle: {color:'#fff',}},data: data_1.businessTypeAnalysis.categoryArr},
    yAxis:[
        {
            type: 'value',name: '',
            axisLine: {lineStyle: {color: '#57617B'}},
            axisLabel: {margin: 10,textStyle: {fontSize: 12},textStyle: {color:'#fff'},formatter:'{value}'},
            splitLine: {show: false}
        },
        {
            type: 'value',name: '',max:maxOrder+parseInt(maxOrder*0.2),
            axisLabel: {margin: 10,textStyle: {fontSize: 12},textStyle: {color:'#fff'},formatter:'{value}'},
            splitLine: {
                show: true,
                lineStyle:{
                    type:'dashed',
                    color: ['#25CEF3']
                }
            }
        }
    ],
    series: [
        {
            name:'项目数量',
            type:'line',
            yAxisIndex:0,
            smooth: false,
            symbolSize:5,
            lineStyle: { normal: {width: 2}},
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(230, 48, 123, 0.8)'
                    }, {
                        offset: 0.8,
                        color: 'rgba(230, 48, 123, 0)'
                    }], false),
                    shadowColor: 'rgba(0, 0, 0, 0.1)',
                    shadowBlur: 10
                }
            },
            itemStyle: {normal: { color: '#DA2F78'}},
            data:data_1.businessTypeAnalysis.projectNum
        },
        {
            name:'订单量',
            type:'bar',
            barWidth:12,
            yAxisIndex:1,
            itemStyle : {
                normal: {
                    barBorderRadius:[10, 10, 0, 0],
                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                        offset: 0,
                        color: "#4033F9"
                    }, {
                        offset: 0.8,
                        color: "#BA97F9"
                    }], false),
                    shadowColor: 'rgba(0, 0, 0, 0.1)',
                }
            },
            data:data_1.businessTypeAnalysis.orderNum
        }
    ]
};

project_data.setOption(option1);

//------------------------------------------
var data_2={
    "code":0,
    "businessTypeAnalysis":{
        "orderNum":["39","77","96","41","24","17","10","15"],
        "categoryArr":["项目A","项目B","项目C","项目D","项目E","项目F","项目G","项目H"],
        "projectNum":["10.79","17.05","14.84","10.07","5.58","10.36","2.00","4.43"],
        //   "projectNum":["50","150","200","250","300","350","400","450"],
        "legendArr":["耗时时间","订单量"]
    }
};
var project_data1 =echarts.init(document.getElementById("project_data1"),'infographic');
var maxOrder1=Math.max.apply(null,data_2.businessTypeAnalysis.orderNum);
option2 = {
    title : {text:'',subtext:'',top:'3',right:'0'},
    tooltip: {trigger: 'axis'},
    grid: {left: '8%',right: '8%',bottom: '10%'},
    xAxis: {type: 'category',axisLine: {lineStyle: {color: '#57617B'}},axisLabel: {interval:0,textStyle: {color:'#fff',}},data: data_2.businessTypeAnalysis.categoryArr},
    yAxis:[
        {
            type: 'value',name: '',
            axisLine: {lineStyle: {color: '#57617B'}},
            axisLabel: {margin: 10,textStyle: {fontSize: 12},textStyle: {color:'#fff'},formatter:'{value}'},
            splitLine: {show: false}
        },
        {
            type: 'value',name: '',max:maxOrder1+parseInt(maxOrder1*0.2),
            axisLabel: {margin: 10,textStyle: {fontSize: 12},textStyle: {color:'#fff'},formatter:'{value}'},
            splitLine: {
                show: true,
                lineStyle:{
                    type:'dashed',
                    color: ['#25CEF3']
                }
            }
        }
    ],
    series: [
        {
            name:'项目数量',
            type:'line',
            yAxisIndex:0,
            smooth: false,
            symbolSize:5,
            lineStyle: { normal: {width: 2}},
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(230, 48, 123, 0.8)'
                    }, {
                        offset: 0.8,
                        color: 'rgba(230, 48, 123, 0)'
                    }], false),
                    shadowColor: 'rgba(0, 0, 0, 0.1)',
                    shadowBlur: 10
                }
            },
            itemStyle: {normal: { color: '#DA2F78'}},
            data:data_2.businessTypeAnalysis.projectNum
        },
        {
            name:'订单量',
            type:'bar',
            barWidth:12,
            yAxisIndex:1,
            itemStyle : {
                normal: {
                    barBorderRadius:[10, 10, 0, 0],
                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                        offset: 0,
                        color: "#4033F9"
                    }, {
                        offset: 0.8,
                        color: "#BA97F9"
                    }], false),
                    shadowColor: 'rgba(0, 0, 0, 0.1)',
                }
            },
            data:data_2.businessTypeAnalysis.orderNum
        }
    ]
};

project_data1.setOption(option2);

//*******************************************************************

//时间选择器
var startV = '';
var endV = '';
laydate.skin('danlan');
var startTime = {
    elem: '#startTime',
    format: 'YYYY-MM-DD',
    min: '1997-01-01', //设定最小日期为当前日期
    max: laydate.now(), //最大日期
    istime: true,
    istoday: true,
    fixed: false,
    choose: function (datas) {
        startV = datas;
        endTime.min = datas; //开始日选好后，重置结束日的最小日期
    }
};
var endTime = {
    elem: '#endTime',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: laydate.now(),
    istime: true,
    istoday: true,
    fixed: false,
    choose: function (datas) {
        //        startTime.max = datas; //结束日选好后，重置开始日的最大日期
        endV = datas;
    }
};

laydate(startTime);
laydate(endTime);

//时间选择器
var startVs = '';
var endVs = '';
laydate.skin('danlan');
var startTimes = {
    elem: '#startTimes',
    format: 'YYYY-MM-DD',
    min: '1997-01-01', //设定最小日期为当前日期
    max: '2099-06-16', //最大日期
    istime: true,
    istoday: true,
    fixed: false,
    choose: function (datas) {
        startVs = datas;
        endTimes.min = datas; //开始日选好后，重置结束日的最小日期
        setQgData($('#barTypes').parent().parent(), 1);
    }
};
var endTimes = {
    elem: '#endTimes',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: laydate.now(),
    istime: true,
    istoday: true,
    fixed: false,
    choose: function (datas) {
        //        startTime.max = datas; //结束日选好后，重置开始日的最大日期
        endVs = datas;
        setQgData($('#barTypes').parent().parent(), 1);
    }
};

laydate(startTimes);
laydate(endTimes);




//更改日期插件的样式
function dateCss() {
    var arr = $('#laydate_box').attr('style').split(';');
    var cssStr =
        'position:absolute;right:0;';
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].indexOf('top') != -1) {
            cssStr += arr[i];
        }
    }

    $('#laydate_box').attr('style', cssStr);
}


var workDate;
var time = {
    elem: '#times',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: laydate.now() + 30,
    istime: true,
    istoday: true,
    fixed: false,
    choose: function (datas) {
        //        startTime.max = datas; //结束日选好后，重置开始日的最大日期
        workDate = datas;
    }
};

laydate(time);




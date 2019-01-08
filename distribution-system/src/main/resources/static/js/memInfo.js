var dom = document.getElementById("meminfo");
var myChart2 = echarts.init(dom);
var app = {};
option = null;
var base = +new Date();
var oneDay = 24 * 3600 * 1000;
var date2 = [];

var data4 = [0];
var now = new Date();
var data5 = [0];
var data6 = [0];

function formatTime(time) {
    if (time < 10) {
        return time = '0' + time;
    }
    return time;
}

function addData2(shift) {
    var hours = now.getHours();
    var minutes = now.getMinutes();
    var seconds = now.getSeconds();
    var nowFormat = [formatTime(hours), formatTime(minutes), formatTime(seconds)].join(':');

    $.get("http://localhost:8080/sys/mem", function (result) {
        // console.log(data)
        data4.push(result.totalMem)
        data5.push(result.freeMem)
        data6.push(result.usedMem)
    })

    date2.push(nowFormat);
    // data.push((Math.random() - 0.4) * 10 + data[data.length - 1]);
    // data2.push((Math.random() + 0.9) * 2 + data2[data2.length - 1]);
    if (shift) {
        date2.shift();
        data4.shift();
        data5.shift();
        data6.shift();
    }
    if (shift == null || shift == true) {
        now = new Date(+new Date(now) + 3000);
    }

}

for (var i = 1; i < 8; i++) {

    addData2();
}

option = {
    legend: {
        data: ['物理总内存', '物理剩余内存', '物理已使用内存']
    },
    title: {
        text: 'MEM-INFO'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7995'
            }
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: date2
    },
    yAxis: {
        boundaryGap: [0, '80%'],
        type: 'value'
    },
    series: [
        {
            name: '物理总内存(单位：kb）',
            type: 'line',
            stack: 'a',
            areaStyle: {
                normal: {
                    color: '#87951b'
                }
            },
            data: data4
        },
        {
            name: '物理剩余内存(单位：kb）',
            type: 'line',
            stack: 'b',
            areaStyle: {
                normal: {
                    color: '#52f71e'
                }
            },
            // lineSytle:{
            //     normal:{
            //         color: '#162795'
            //     }
            // },

            data: data5
        },
        {
            name: '物理已使用内存(单位：kb）',
            type: 'line',
            stack: 'c',
            areaStyle: {
                normal: {
                    color: '#950c0b'
                }
            },
            data: data6
        }
    ]
};
var begin = function () {
    addData2(true);
    myChart2.setOption({
        xAxis: {
            data: date2
        },
        series: [{
            name: '物理总内存(单位：kb）',
            data: data4
        },
            {
                name: '物理剩余内存(单位：kb）',
                data: data5
            },
            {
                name: '物理已使用内存(单位：kb）',
                data: data6
            }]
    });
}
$(begin)
setInterval(begin, 3000);

if (option && typeof option === "object") {
    myChart2.setOption(option, true);
}
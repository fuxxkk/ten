var dom = document.getElementById("meminfo");
var myChart2 = echarts.init(dom);
option = null;
var base = +new Date();
var date2 = [];

var data4 = [0];
var data5 = [0];
var data6 = [0];

function formatTime(time) {
    if (time < 10) {
        return time = '0' + time;
    }
    return time;
}

function addData2(shift) {

    if (!shift) {
        data4.push(0)
        data5.push(0)
        data6.push(0)
        date2.push('')
    } else {
        $.get("http://localhost:8080/sys/mem", function (result) {
            data4.push(result.totalMem)
            data5.push(result.freeMem)
            data6.push(result.usedMem)
            date2.push(result.date)
        })
    }

    if (shift) {
        date2.shift();
        data4.shift();
        data5.shift();
        data6.shift();
    }
}

for (var i = 1; i <= 30; i++) {
    addData2();
    if (i > 29) {
        addData2(true)
    }
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
setInterval(begin, 2000);

if (option && typeof option === "object") {
    myChart2.setOption(option, true);
}

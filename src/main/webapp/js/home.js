/**
 * Created by huanghanqian on 16/11/6.
 */
var vm = new Vue({
    el: '#app',
    ready: function () {
        $.post("/risk/getRisks", {"beginDate": getTheDate(-5), "endDate": getTheDate(0)},
            function(result){
                console.log(result);
                vm.$set('tds', result.list);
            }, "json");
    },
    data: {
        start: getTheDate(-5),
        end: getTheDate(0)
    },
//			computed: {
//				// 一个计算属性的 getter
//				tds: function () {
//					var myr="";
//					$.getJSON("/main/getMonitor", {"beginDate": getTheDate(-2),"endDate": getTheDate(0)}, function (result) {
//						myr= result;
//					});
//					return myr;
//				}
//			},
    methods: {
        rerun: function (index, monitor_id) {
            var button = $('#trs').children().eq(index).children().eq(9).children().eq(0);
            button.prop('disabled', true);
            button.html('重跑中<span class="dotting"></span>');
            $.ajax({
                url: "http://m.o2.qq.com/Api/rerunMonitor",
                // The name of the callback parameter, as specified by the YQL service
                jsonp: "callback",
                // Tell jQuery we're expecting JSONP
                dataType: "jsonp",
                // Tell YQL what we want and that we want JSON
                data: {
                    monitorID: monitor_id
                },
                // Work with the response
                success: function (response) {
                    console.log(response); // server response
                    vm.isupdate = (vm.isupdate == 0 ? 1 : 0);
                    button.html('重跑');
                    button.prop('disabled', false);
                }
            });
        }
    }
})


vm.$watch('tds',function(val){
    vm.$nextTick(function() {
        initTableCheckbox();
    });
})

var dateRange_begin = new pickerDateRange('date_begin', {
    isTodayValid: true,
    startDate: getTheDate(-5),
    isSingleDay: true,
    theme: 'ta',
    shortOpr: true,
    autoSubmit: true,
    stopToday: true,     //是否是允许选择今天之前的日期
    calendars: 1,
    success: function (obj) { //回调函数
        console.log($("#date_begin").text());
        vm.start = $("#date_begin").text();
    }
});

var dateRange_end = new pickerDateRange('date_end', {
    isTodayValid: true,
    startDate: getTheDate(0),
    isSingleDay: true,
    theme: 'ta',
    shortOpr: true,
    autoSubmit: true,
    stopToday: true,     //是否是允许选择今天之前的日期
    calendars: 1,
    success: function (obj) { //回调函数
        vm.end = $("#date_end").text();
    }
});

//获得days天后的日期 2012-01-01
function getTheDate(days) {
    var date = new Date();
    date.setDate(date.getDate() + days);
    var y = date.getFullYear();
    var m = (((date.getMonth() + 1) + "").length == 2) ? (date.getMonth() + 1) : ("0" + (date.getMonth() + 1));
    var d = (((date.getDate()) + "").length == 2) ? ((date.getDate()) + "") : ("0" + (date.getDate()));
    var theDate = y + "-" + m + "-" + d;
    return theDate;
}

function initTableCheckbox() {
    var $thr = $('table thead tr');
    /*“全选/反选”复选框*/
    var $checkAll = $thr.find('input');
    $checkAll.click(function(event){
        /*将所有行的选中状态设成全选框的选中状态*/
        $tbr.find('input').prop('checked',$(this).prop('checked'));
        /*并调整所有选中行的CSS样式*/
        if ($(this).prop('checked')) {
            $tbr.find('input').parent().parent().addClass('warning');
        } else{
            $tbr.find('input').parent().parent().removeClass('warning');
        }
        /*阻止向上冒泡，以防再次触发点击操作*/
        event.stopPropagation();
    });
    var $tbr = $('table tbody tr');
    /*点击每一行的选中复选框时*/
    $tbr.find('input').click(function(event){
        /*调整选中行的CSS样式*/
        $(this).parent().parent().toggleClass('warning');
        /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
        $checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.find('input').length ? true : false);
        /*阻止向上冒泡，以防再次触发点击操作*/
        event.stopPropagation();
    });
    /*点击每一行时也触发该行的选中操作*/
    $tbr.click(function(){
        $(this).find('input').click();
    });
}

$("#batchBtn").unbind().bind("click", function () {

});


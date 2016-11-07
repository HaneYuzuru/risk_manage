/**
 * Created by huanghanqian on 16/11/6.
 */
var vm = new Vue({
    el: '#app',
    ready: function () {
        $.post("/risk/getRisks", {"beginDate": getTheDate(-5), "endDate": getTheDate(0)},
            function(result){
                vm.$set('tds', result.list);
            }, "json");
    },
    data: {
        isUpdate: 0
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
        search: function () {
            $.post("/risk/getRisks", {"beginDate": $("#date_begin").text(), "endDate": $("#date_end").text()},
                function(result){
                    vm.$set('tds', result.list);
                }, "json");
        }
    }
})


vm.$watch('tds',function(val){
    vm.$nextTick(function() {
        initTableCheckbox();
    });
})

vm.$watch('isUpdate',function(val){
    $.post("/risk/getRisks", {"beginDate": $("#date_begin").text(), "endDate": $("#date_end").text()},
        function(result){
            vm.$set('tds', result.list);
        }, "json");
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

$("#submitRisk").unbind().bind("click", function () {
    var name=$('#riskname').val();
    var content=$('#content').val();
    var trigger=$('#trigger').val();
    if(name==null||name==""){
        $('#empTip').text("风险名不能为空");
        $('#empTip').show();
    }
    else if(content==null||content==""){
        $('#empTip').text("风险内容不能为空");
        $('#empTip').show();
    }
    else if(trigger==null||trigger==""){
        $('#empTip').text("触发条件不能为空");
        $('#empTip').show();
    }
    else{
        $('#empTip').hide();
        $.post("/risk/add", {"name": name,"content":content,"possibility":$('input:radio[name="optionsRadiosinline"]:checked').val(),"impact":$('input:radio[name="optionsRadiosinline1"]:checked').val(),"trigger":trigger,"followers":$("#options").val()},
            function(result){
                if(result['result']=="true"){
                    vm.isUpdate=(vm.isUpdate==0?1:0);
                }
                else{
                    alert(result['result']);
                }
            }, "json");
    }
});

$.post("/user/searchByName", {"name": ""},
    function(result){
        var list= result.list;
        var htm="";
        for(var i=0;i<list.length;i++){
            htm+='<option value="'+list[i]['id']+'">'+list[i]['name']+'</option>';
        }
        $('#options').html(htm);
        var height=31;
        if(list.length!=0){
            height=list.length*18+13;
        }
        $('#options').css("height",height+"px");
    }, "json");


/**
 * Created by huanghanqian on 16/11/8.
 */
$('#backBtn').unbind().bind("click",function(){
    history.back(-1);
});

var vm = new Vue({
    el: '#app',
    ready: function () {
        $.post("riskTrack/getRisktracks", {"beginDate": getTheDate(-5), "endDate": getTheDate(0),"riskID":$('#riskID').val()},
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
            $.post("riskTrack/getRisktracks", {"beginDate": $("#date_begin").text(), "endDate": $("#date_end").text(),"riskID":$('#riskID').val()},
                function(result){
                    vm.$set('tds', result.list);
                }, "json");
        },
        modify: function(index) {
            var risk=vm.tds[index];
            $('#modifyID').val(risk.id);
            $('#modifycontent').val(risk.description);
            var val=0;
            if(risk.status=="风险状态"){
                val=0;
            }
            else if(risk.status=="问题状态"){
                val=1;
            }
            else if(risk.status=="解决状态"){
                val=2;
            }
            $("input[type=radio][name=modifyoptionsRadiosinline][value="+val+"]").prop("checked",true);
            $("input[type=radio][name=modifyoptionsRadiosinline]").attr("disabled","disabled");
            $('#modifyModal').modal();
        }
    }
})


vm.$watch('tds',function(val){
    vm.$nextTick(function() {

    });
})

vm.$watch('isUpdate',function(val){
    $.post("riskTrack/getRisktracks", {"beginDate": getTheDate(-5), "endDate": getTheDate(0),"riskID":$('#riskID').val()},
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

$("#submitRisk").unbind().bind("click", function () {
    var content=$('#content').val();
    if(content==null||content==""){
        $('#empTip').text("跟踪描述不能为空");
        $('#empTip').show();
    }
    else{
        $('#empTip').hide();
        $.post("riskTrack/add", {"riskId":$('#riskID').val(),"description":content,"status":$('input:radio[name="optionsRadiosinline"]:checked').val()},
            function(result){
                if(result['result']=="true"){
                    vm.isUpdate=(vm.isUpdate==0?1:0);
                    $('#content').val("");
                    $("input[type='radio'][name='optionsRadiosinline'][value='0']").prop("checked",true);
                }
                else{
                    alert(result['result']);
                }
                $('#myModal').modal('hide');
            }, "json");
    }
});

$("#modifyRisk").unbind().bind("click", function () {
    var content=$('#modifycontent').val();
    if(content==null||content==""){
        $('#empTip2').text("跟踪描述不能为空");
        $('#empTip2').show();
    }
    else{
        $('#empTip2').hide();
        var id=$('#modifyID').val();
        $.post("riskTrack/update", {"id":id,"description":content},
            function(result){
                if(result['result']=="true"){
                    vm.isUpdate=(vm.isUpdate==0?1:0);
                }
                else{
                    alert(result['result']);
                }
                $('#modifyModal').modal('hide');
            }, "json");
    }
});




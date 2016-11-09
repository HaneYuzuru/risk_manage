/**
 * Created by huanghanqian on 16/11/8.
 */
$('#backBtn').unbind().bind("click",function(){
    history.back(-1);
});

var vm = new Vue({
    el: '#app',
    ready: function () {
        $.post("/riskTrack/getRisktracks", {"beginDate": getTheDate(-5), "endDate": getTheDate(0),"riskID":$('#riskID').val()},
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
            $.post("/riskTrack/getRisktracks", {"beginDate": $("#date_begin").text(), "endDate": $("#date_end").text(),"riskID":$('#riskID').val()},
                function(result){
                    vm.$set('tds', result.list);
                }, "json");
        },
        modify: function(index) {
            var risk=vm.tds[index];
            $('#modifyID').val(risk.id);
            $('#modifyname').val(risk.name);
            $('#modifycontent').val(risk.content);
            $("input[type=radio][name=modifyoptionsRadiosinline][value="+risk.possibility+"]").attr("checked",'checked');
            $("input[type=radio][name=modifyoptionsRadiosinline1][value="+risk.impact+"]").attr("checked",'checked');
            $('#modifytrigger').val(risk.trigger);
            $('#modifyoptions').val(risk.followerNames.split(","));
            $('#modifyModal').modal();
        }
    }
})


vm.$watch('tds',function(val){
    vm.$nextTick(function() {

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

$("#submitRisk").unbind().bind("click", function () {
    var content=$('#content').val();
    if(content==null||content==""){
        $('#empTip').text("风险内容不能为空");
        $('#empTip').show();
    }
    else{
        $('#empTip').hide();
        $.post("/riskTrack/add", {"content":content,"possibility":$('input:radio[name="optionsRadiosinline"]:checked').val(),"impact":$('input:radio[name="optionsRadiosinline1"]:checked').val(),"trigger":trigger,"followers":followers},
            function(result){
                if(result['result']=="true"){
                    vm.isUpdate=(vm.isUpdate==0?1:0);
                }
                else{
                    alert(result['result']);
                }
                $('#myModal').modal('hide');
            }, "json");
    }
});

$("#modifyRisk").unbind().bind("click", function () {
    var name=$('#modifyname').val();
    var content=$('#modifycontent').val();
    var trigger=$('#modifytrigger').val();
    if(name==null||name==""){
        $('#empTip2').text("风险名不能为空");
        $('#empTip2').show();
    }
    else if(content==null||content==""){
        $('#empTip2').text("风险内容不能为空");
        $('#empTip2').show();
    }
    else if(trigger==null||trigger==""){
        $('#empTip2').text("触发条件不能为空");
        $('#empTip2').show();
    }
    else{
        $('#empTip2').hide();
        var id=$("#modifyID").val();
        var ids=$("#modifyoptions").val();
        var followers=ids.join();
        $.post("/risk/update", {"id":id,"name": name,"content":content,"possibility":$('input:radio[name="modifyoptionsRadiosinline"]:checked').val(),"impact":$('input:radio[name="modifyoptionsRadiosinline1"]:checked').val(),"trigger":trigger,"followers":followers},
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




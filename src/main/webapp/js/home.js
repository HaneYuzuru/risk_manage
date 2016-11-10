/**
 * Created by huanghanqian on 16/11/6.
 */
$('#yonghu').removeClass('active');
$('#fengxian').addClass('active');
var userType=$('#hiddenType').val();
var vm = new Vue({
    el: '#app',
    ready: function () {
        $.post("/risk_manage-release-1.0-SNAPSHOT/risk/getRisks", {"beginDate": getTheDate(-5), "endDate": getTheDate(0)},
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
            $.post("/risk_manage-release-1.0-SNAPSHOT/risk/getRisks", {"beginDate": $("#date_begin").text(), "endDate": $("#date_end").text()},
                function(result){
                    vm.$set('tds', result.list);
                }, "json");
        },
        modify: function(index) {
            var risk=vm.tds[index];
            $('#modifyID').val(risk.id);
            $('#modifyname').val(risk.name);
            $('#modifycontent').val(risk.content);
            $("input[type=radio][name=modifyoptionsRadiosinline][value="+risk.possibility+"]").prop("checked",true);
            $("input[type=radio][name=modifyoptionsRadiosinline1][value="+risk.impact+"]").prop("checked",true);
            $('#modifytrigger').val(risk.trigger);
            $('#modifyoptions').val(risk.followerNames.split(","));
            $('#modifyModal').modal();
        },
        delete:function(index){
            if(confirm("确定要删除该风险吗?")==true){
                var riskid=vm.tds[index].id;
                $.post("/risk_manage-release-1.0-SNAPSHOT/risk/delete", {"idStr":riskid},
                    function(result){
                        if(result['result']=="true"){
                            vm.isUpdate=(vm.isUpdate==0?1:0);
                        }
                        else{
                            alert(result['result']);
                        }
                    }, "json");
            }
        },
        track:function(index){
            var id=vm.tds[index].id;
            window.location.href="riskTrack?riskID="+id;
        }
    }
})


vm.$watch('tds',function(val){
    vm.$nextTick(function() {
        if(userType==1){
            initTableCheckbox();
        }
        removeNotFollowers();
    });
})

vm.$watch('isUpdate',function(val){
    $.post("/risk_manage-release-1.0-SNAPSHOT/risk/getRisks", {"beginDate": $("#date_begin").text(), "endDate": $("#date_end").text()},
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
        $("input[type='radio'][name='optionsRadiosinline'][value='高']").prop("checked",true);
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
        var ids=$("#options").val();
        var followers="";
        if(ids!=null){
            followers=ids.join();
        }
        $.post("/risk_manage-release-1.0-SNAPSHOT/risk/add", {"name": name,"content":content,"possibility":$('input:radio[name="optionsRadiosinline"]:checked').val(),"impact":$('input:radio[name="optionsRadiosinline1"]:checked').val(),"trigger":trigger,"followers":followers},
            function(result){
                if(result['result']=="true"){
                    vm.isUpdate=(vm.isUpdate==0?1:0);
                    $('#riskname').val("");
                    $('#content').val("");
                    $("input[type='radio'][name='optionsRadiosinline'][value='高']").prop("checked",true);
                    $("input[type='radio'][name='optionsRadiosinline1'][value='高']").prop("checked",true);
                    $('#trigger').val("");
                    $('#options').val(null);
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
        $.post("/risk_manage-release-1.0-SNAPSHOT/risk/update", {"id":id,"name": name,"content":content,"possibility":$('input:radio[name="modifyoptionsRadiosinline"]:checked').val(),"impact":$('input:radio[name="modifyoptionsRadiosinline1"]:checked').val(),"trigger":trigger,"followers":followers},
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



$.post("/risk_manage-release-1.0-SNAPSHOT/user/searchByName", {"name": ""},
    function(result){
        var list= result.list;
        var htm="";
        for(var i=0;i<list.length;i++){
            htm+='<option value="'+list[i]['name']+'">'+list[i]['name']+'</option>';
        }
        $('#options').html(htm);
        $('#modifyoptions').html(htm);
        var height=31;
        if(list.length!=0){
            height=list.length*18+13;
        }
        $('#options').css("height",height+"px");
        $('#modifyoptions').css("height",height+"px");
    }, "json");


$("#batchBtn").unbind().bind("click", function () {
    $("#ltip").hide();
    var trs=$('table tbody tr').find('input:checked').parent().parent();
    if(trs.length==0){
        $("#ltip").show();
    }
    else{
        $("#ltip").hide();
        var ids=new Array();
        for(var i=0;i<trs.length;i++){
            var id=trs.eq(i).children().eq(11).text();
            ids.push(id);
        }
        if(confirm("确定要批量删除这些风险吗?")==true){
            $.post("/risk_manage-release-1.0-SNAPSHOT/risk/delete", {"idStr":ids.join()},
                function(result){
                    if(result['result']=="true"){
                        vm.isUpdate=(vm.isUpdate==0?1:0);
                    }
                    else{
                        alert(result['result']);
                    }
                }, "json");
        }
    }

});

function removeNotFollowers(){
    $('#trs').children().each(function(i,n){
        var tr = $(n)
        var followers=tr.children().eq(9).text();
        var btns=tr.children().eq(10);
        if(userType==0){
            followers=tr.children().eq(8).text();
            btns=tr.children().eq(9);
        }
        var fo=followers.split(",");
        var username=$('#hiddenUsername').val();
        var isIn=false;
        for(var i=0;i<fo.length;i++){
            if(fo[i]==username){
                isIn=true;
                break;
            }
        }
        if(isIn==false){
            btns.children().eq(0).replaceWith("<label style='width:52px;'></label>");
        }
    });
}

<#setting number_format="#">
<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/logo.png">
    <!-- CSS -->
    <title>风险管理首页</title>

</head>
<body>
<#include "header.ftl"/>
<link rel="stylesheet" href="css/dateRange.css">
<div class="body" style="min-height:510px;" id="app">
    <div class="container-fluid">
        <div class="row" style="margin-top:-55px;">
            <div class="col-sm-12 col-md-12 main">
                <!-- page inner nav -->
                <nav class="navbar navbar-default" id="app-navbar" style="top:10px;">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="top:7px;">开始日期</label>

                            <div class="col-sm-1">
                                <div class="ta_date" style="margin-top:12px;margin-left:-15px;width:110px;">
                                    <span class="date_title form-control-input" style="border-right:0px;" id="date_begin" v-model="start"></span>
                                </div>
                            </div>
                            <label class="col-sm-1 control-label" style="top:7px;margin-left:-20px;">结束日期</label>

                            <div class="col-sm-1">
                                <div class="ta_date" style="margin-top:12px;margin-left:-15px;width:110px;">
                                    <span class="date_title form-control-input" style="border-right:0px;" id="date_end" v-model="end"></span>
                                </div>
                            </div>

                            <button v-on:click="search()" type="button" class="col-sm-1 btn btn-sm btn-default" style="background-color:#E6E6E6;padding:3px 3px;margin-left:40px;top:11px;">
                                <span class="glyphicon glyphicon-search"></span> 查询
                            </button>

                            <span id="errorTip" style="display: none;color:red;position:relative;left:20px;top:14px;">开始日期不能晚于结束日期!</span>

                        </div>
                    </form>
                </nav>
                <!--/.nav-->


            </div>
            <!--/.main -->
        </div>
        <!--/.row -->
    <#if type==1 !>
        <div class="row">
            <div class="col-sm-12 col-md-12 main" style="padding-bottom: 0px;">
                <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" >
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 增加
                </button>
                <button type="button" class="btn btn-default btn-sm" id="batchBtn">
                    <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> 批量删除
                </button>
                <span id="ltip" style="display:none;color:red;position:relative;left:5px;top:2px;">您未选择任何风险</span>
            </div>
        </div>
    </#if>

        <div class="row" >
            <div class="col-sm-12 col-md-12 main" style="padding-top:10px;">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="checkAll" name="checkAll" /></th>
                        <th>修改时间</th>
                        <th>风险名</th>
                        <th>风险内容</th>
                        <th>可能性</th>
                        <th>影响程度</th>
                        <th>触发器</th>
                        <th>风险状态</th>
                        <th>提交者</th>
                        <th>跟踪者</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="trs">
                    <tr v-for="td in tds">
                        <td><input type="checkbox" name="checkItem" /></td>
                        <td>{{td.gmtModified}}</td>
                        <td>{{td.name}}</td>
                        <td>{{td.content}}</td>
                        <td>{{td.possibility}}</td>
                        <td>{{td.impact}}</td>
                        <td>{{td.trigger}}</td>
                        <td>{{td.status}}</td>
                        <td>{{td.committerName}}</td>
                        <td>{{td.followerNames}}</td>
                        <td>
                        <#if type==0 !>
                            <button v-on:click="rerun($index,td.monitor_id)" type="button"
                                    class="btn btn-default btn-xs"
                                    style="padding:1px 10px;font-size:15px;margin-top:-2px;margin-bottom:0px;">跟踪
                            </button>
                        </#if>
                        <#if type==1 !>
                            <button v-on:click="rerun($index,td.monitor_id)" type="button"
                                    class="btn btn-default btn-xs"
                                    style="padding:1px 10px;font-size:15px;margin-top:-5px;margin-bottom:-2px;">跟踪
                            </button>
                            <button v-on:click="modify($index)" type="button"
                                    class="btn btn-default btn-xs"
                                    style="padding:1px 10px;font-size:15px;margin-top:-5px;margin-bottom:-2px;">修改
                            </button>
                            <button v-on:click="delete($index)" type="button"
                                    class="btn btn-default btn-xs"
                                    style="padding:1px 10px;font-size:15px;margin-top:-5px;margin-bottom:-2px;">删除
                            </button>
                        </#if>
                        </td>
                        <td style="display: none;">{{td.id}}</td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <!--/.main -->
        </div>
        <!--/.row -->
    </div>
    <!--/.container-fluid -->


</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    增加风险
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="addForm">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" style="margin-left:-10px;">风险名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="riskname" name="name"
                                   placeholder="风险名...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">风险内容</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" cols="20" name="content" id="content"></textarea>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:-5px;">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">可能性</label>
                        <div class="col-sm-10">
                            <label class="checkbox-inline" style="margin-left:-20px;">
                                <input type="radio" name="optionsRadiosinline" id="optionsRadios3"
                                       value="高" checked> 高
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="optionsRadiosinline" id="optionsRadios4"
                                       value="中"> 中
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="optionsRadiosinline" id="optionsRadios4"
                                       value="低"> 低
                            </label>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:-5px;">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">影响程度</label>
                        <div class="col-sm-10">
                            <label class="checkbox-inline" style="margin-left:-20px;">
                                <input type="radio" name="optionsRadiosinline1" id="optionsRadios3"
                                       value="高" checked> 高
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="optionsRadiosinline1" id="optionsRadios4"
                                       value="中"> 中
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="optionsRadiosinline1" id="optionsRadios4"
                                       value="低"> 低
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" style="margin-left:-10px;">触发器</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="trigger" name="trigger"
                                   placeholder="触发条件...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" style="margin-left:-10px;">跟踪者</label>
                        <div class="col-sm-10">
                            <select multiple class="form-control" style="height:103px;" id="options" name="followers">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <span id="empTip" style="float:left;color:red;margin-left:10px;display: none;">不能为空</span>
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="submitRisk">
                    提交风险
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" >
                    修改风险
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" style="margin-left:-10px;">风险名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="modifyname"
                                   placeholder="风险名...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">风险内容</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" cols="20" id="modifycontent"></textarea>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:-5px;">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">可能性</label>
                        <div class="col-sm-10">
                            <label class="checkbox-inline" style="margin-left:-20px;">
                                <input type="radio" name="modifyoptionsRadiosinline"
                                       value="高" checked> 高
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="modifyoptionsRadiosinline"
                                       value="中"> 中
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="modifyoptionsRadiosinline"
                                       value="低"> 低
                            </label>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:-5px;">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">影响程度</label>
                        <div class="col-sm-10">
                            <label class="checkbox-inline" style="margin-left:-20px;">
                                <input type="radio" name="modifyoptionsRadiosinline1"
                                       value="高" checked> 高
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="modifyoptionsRadiosinline1"
                                       value="中"> 中
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="modifyoptionsRadiosinline1"
                                       value="低"> 低
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" style="margin-left:-10px;">触发器</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="modifytrigger"
                                   placeholder="触发条件...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" style="margin-left:-10px;">跟踪者</label>
                        <div class="col-sm-10">
                            <select multiple class="form-control" style="height:103px;" id="modifyoptions">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <span id="empTip2" style="float:left;color:red;margin-left:10px;display: none;">不能为空</span>
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <input type="hidden" id="modifyID" value=""/>
                <button type="button" class="btn btn-primary" id="modifyRisk">
                    修改风险
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#include "footer.ftl"/>
<script src="js/vue.min.js"></script>
<script src="js/dateRange.js"></script>
<script src="js/user.js"></script>
</body>
</html>
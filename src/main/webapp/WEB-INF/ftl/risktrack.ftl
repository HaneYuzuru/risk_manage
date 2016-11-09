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
        <input type="hidden" id="riskID" value="${riskID!}" />
        <div class="row" style="margin-top:-45px;">
            <div class="col-sm-12 col-md-12 main">
                <div class="form-group" style="background-color:#666666;font-size:17px;color:white;border-radius:10px;padding:10px 20px;height:46px;">
                    <span class="col-sm-2 col-md-2">风险名称 : ${riskVO.getName()!}&nbsp;&nbsp;</span>
                    <span class="col-sm-2 col-md-2">内容 : ${riskVO.getContent()!}&nbsp;&nbsp;</span>
                    <span class="col-sm-2 col-md-2">可能性 : ${riskVO.getPossibility()!}&nbsp;&nbsp;</span>
                    <span class="col-sm-2 col-md-2">影响程度 : ${riskVO.getImpact()!}&nbsp;&nbsp;</span>
                    <span class="col-sm-2 col-md-2">触发器 : ${riskVO.getTrigger()!}&nbsp;&nbsp;</span>
                    <span class="col-sm-2 col-md-2">当前状态 : ${riskVO.getStatus()!}</span>
                </div>
            </div>
        </div>
        <div class="row" style="position:relative;top:-5px;">
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
        <div class="row">
            <div class="col-sm-12 col-md-12 main" style="padding-bottom: 0px;">
                <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" >
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 增加
                </button>
                <button type="button" class="btn btn-default btn-sm" id="backBtn">
                    <span class="glyphicon glyphicon-share-alt"></span> 返回
                </button>
            </div>
        </div>

        <div class="row" style="margin-bottom:20px;">
            <div class="col-sm-12 col-md-12 main" style="padding-top:10px;">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>创建时间</th>
                        <th>风险状态</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="trs">
                    <tr v-for="td in tds">
                        <td>{{td.riskId}}</td>
                        <td v-if="td.status=='风险状态'"><span style="background-color:#FF9966;color:white;padding:3px 6px;border-radius: 5px;">{{td.status}}</span></td>
                        <td v-if="td.status=='问题状态'"><span style="background-color:#FF7777;color:white;padding:3px 6px;border-radius: 5px;">{{td.status}}</span></td>
                        <td v-if="td.status=='解决状态'"><span style="background-color:#99CC66;color:white;padding:3px 6px;border-radius: 5px;">{{td.status}}</span></td>
                        <td>{{td.description}}</td>
                        <td>
                            <button v-on:click="modify($index)" type="button"
                                    class="btn btn-default btn-xs"
                                    style="padding:1px 10px;font-size:15px;margin-top:-2px;margin-bottom:0px;">修改
                            </button>
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
                    增加风险跟踪
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="addForm">
                    <div class="form-group" style="margin-top:-5px;">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">风险状态</label>
                        <div class="col-sm-10">
                            <label class="checkbox-inline" style="margin-left:-20px;">
                                <input type="radio" name="optionsRadiosinline" id="optionsRadios3"
                                       value="0" checked> 风险状态
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="optionsRadiosinline" id="optionsRadios4"
                                       value="1"> 问题状态
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="optionsRadiosinline" id="optionsRadios4"
                                       value="2"> 解决状态
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">描述信息</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" cols="20" name="content" id="content"></textarea>
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
                    提交跟踪
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
                    <div class="form-group" style="margin-top:-5px;">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">风险状态</label>
                        <div class="col-sm-10">
                            <label class="checkbox-inline" style="margin-left:-20px;">
                                <input type="radio" name="modifyoptionsRadiosinline"
                                       value="0" checked> 风险状态
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="modifyoptionsRadiosinline"
                                       value="1"> 问题状态
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="modifyoptionsRadiosinline"
                                       value="2"> 解决状态
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label" style="margin-left:-10px;">描述信息</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" cols="20" name="modifycontent" id="modifycontent"></textarea>
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
                    修改跟踪
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#include "footer.ftl"/>
<script src="js/vue.min.js"></script>
<script src="js/dateRange.js"></script>
<script src="js/risktrack.js"></script>
</body>
</html>
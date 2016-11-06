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
<div class="body" style="min-height:510px;">
    <div class="container-fluid">
        <div class="row" style="margin-top:-55px;">
            <div class="col-sm-12 col-md-12 main">
                <!-- page inner nav -->
                <nav class="navbar navbar-default" id="app-navbar" style="top:60px;">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="top:7px;">开始日期</label>

                            <div class="col-sm-1">
                                <div class="ta_date" style="margin-top:12px;margin-left:-15px;width:93px;">
                                    <span class="date_title form-control-input" style="border-right:0px;" id="date_begin" v-model="start"></span>
                                </div>
                            </div>
                            <label class="col-sm-1 control-label" style="top:7px;margin-left:-20px;">结束日期</label>

                            <div class="col-sm-1">
                                <div class="ta_date" style="margin-top:12px;margin-left:-15px;width:93px;">
                                    <span class="date_title form-control-input" style="border-right:0px;" id="date_end" v-model="end"></span>
                                </div>
                            </div>

                            <button type="button" class="col-sm-1 btn btn-sm btn-default" style="background-color:#E6E6E6;padding:3px 3px;margin-left:40px;top:11px;">
                                <span class="glyphicon glyphicon-search"></span> 查询
                            </button>

                        </div>
                    </form>
                </nav>
                <!--/.nav-->


            </div>
            <!--/.main -->
        </div>
        <!--/.row -->
        <div class="row" style="margin-top:50px;">
            <div class="col-sm-12 col-md-12 main" style="padding-bottom: 0px;">
                <button type="button" class="btn btn-default btn-sm" id="batchBtn">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 增加
                </button>
                <button type="button" class="btn btn-default btn-sm" id="batchBtn">
                    <span class="glyphicon glyphicon-minus" aria-hidden="true"></span> 批量删除
                </button>
                <span id="ltip" style="display:none;color:red;position:relative;left:5px;top:2px;">您未选择任何风险</span>
            </div>
        </div>
        <div class="row" id="app" >
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
                        <th>提交者</th>
                        <th>跟踪者</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="trs">
                    <tr v-for="td in tds">
                        <td><input type="checkbox" name="checkItem" /></td>
                        <td>{{td.gmt_create}}</td>
                        <td>{{td.name}}</td>
                        <td>{{td.content}}</td>
                        <td>{{td.possibility}}</td>
                        <td>{{td.impact}}</td>
                        <td>{{td.trigger}}</td>
                        <td>{{td.committer}}</td>
                        <td>{{td.followers}}</td>
                        <td>
                            <button v-on:click="rerun($index,td.monitor_id)" type="button" class="btn btn-default btn-xs"
                                    style="padding:1px 10px;margin-top:-5px;margin-bottom:-2px;">查看
                            </button>
                            <button v-on:click="rerun($index,td.monitor_id)" type="button" class="btn btn-default btn-xs"
                                    style="padding:1px 10px;margin-top:-5px;margin-bottom:-2px;">修改
                            </button>
                            <button v-on:click="rerun($index,td.monitor_id)" type="button" class="btn btn-default btn-xs"
                                    style="padding:1px 10px;margin-top:-5px;margin-bottom:-2px;">删除
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
<#include "footer.ftl"/>
<script src="js/vue.min.js"></script>
<script src="js/dateRange.js"></script>
<script src="js/home.js"></script>
</body>
</html>
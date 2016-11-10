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
    <link rel="icon" href="../img/logo.png">
    <!-- CSS -->
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="../css/form-elements.css">

    <title>风险管理注册页面</title>

</head>
<body>
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box" style="margin-left:25%;margin-top:-60px;">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>注册用户</h3>
                            <p>请输入您的用户名,密码和角色</p>
                        </div>
                        <div class="form-top-right">
                            <img alt="logo" style="width:71px;height:71px;" src="../img/logo2.png">
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="/risk_manage-release-1.0-SNAPSHOT/login/submitRegister" method="post" class="registration-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">用户名</label>
                                <input type="text" name="form-firstname" placeholder="用户名..."
                                       class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">密码</label>
                                <input type="password" name="form-password" placeholder="密码..."
                                       class="form-password form-control" id="form-password">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-repeat-password">确认密码</label>
                                <input type="password" name="form-repeat-password" placeholder="确认密码..."
                                       class="form-repeat-password form-control" id="form-repeat-password">
                            </div>
                            <div class="form-group">
                                <label class="checkbox-inline">
                                    <input type="radio" style="margin-left:-19px;" name="optionsRadiosinline" id="optionsRadios1" value="0" checked>&nbsp;&nbsp;普通开发人员
                                </label>
                                <label class="checkbox-inline">
                                    <input type="radio" name="optionsRadiosinline" id="optionsRadios2" value="1">&nbsp;&nbsp;项目经理
                                </label>
                                <span id="tip" style="float:right;position:relative;top:1px;color:red;display: none;">用户名已存在</span>
                            </div>
                            <button type="button" id="registerBtn" class="btn">注册</button>
                            <button type="button" class="btn" id="backBtn" style="float:right">返回</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="../js/jquery/jquery-1.11.1.min.js"></script>
<script src="../js/jquery/jquery.backstretch.min.js"></script>
<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/register.js"></script>
</body>
</html>
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
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/form-elements.css">

    <title>风险管理系统页面</title>

</head>
<body>
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>欢迎光临风险管理系统</h3>
                            <p>请输入您的用户名和密码</p>
                        </div>
                        <div class="form-top-right">
                            <img alt="logo" style="width:71px;height:71px;" src="img/logo2.png">
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="/login/testlogin" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" id="userID" name="form-username" placeholder="用户名..." class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" id="password" name="form-password" placeholder="密码..." class="form-password form-control" id="form-password">
                            </div>
                            <button type="button" id="loginBtn" class="btn">登录</button>
                            <button type="button" class="btn" id="registerBtn" style="float:right">注册</button>
                            <span id="tip" style="float:left;position:relative;top:1px;color:red;display: none;">用户名已存在</span>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>
<script src="js/jquery/jquery-1.11.1.min.js"></script>
<script src="js/jquery/jquery.backstretch.min.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>
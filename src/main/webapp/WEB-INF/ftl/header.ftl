<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="css/header.css">
<header class="header">
    <nav class="navbar navbar-custom navbar-fixed-top">
        <div class="container">
            <div class="row">
                <div class="hidden-xs hidden-sm col-md-3 col-lg-3 logo-custom">
                    <div class="navbar-header navbar-left">
                        <a class="navbar-brand-mc" href="#">
                            <img src="img/logo3.png" alt="logo" width="74%" />
                        </a>
                    </div>
                </div>
                <div class="span08">
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#"><span class="glyphicon glyphicon-th-large"></span> 风险条目</a></li>
                        <li><a href="store"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>

                    </ul>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn dropdown-toggle btn-custom" data-toggle="dropdown"><span id="username">${username!}</span> <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right th" role="menu">
                        <li><a href="login/out">注销用户</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</header>
<script src="js/jquery/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
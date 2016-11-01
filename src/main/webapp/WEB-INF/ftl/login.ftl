<#setting number_format="#">
<#import "/spring.ftl" as spring/>
<html>
<head>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>dashBoard</title>
</head>
<body>
<#include "header.ftl"/>
<form action="/user/login" method="POST">
    UserId: <input type="text" id="userId" name="userId"
                    <#if userId!>value=${userId!}</#if>
        /><br>
    Password: <input type="password" name="password"
                 <#if password!>value=${password!}</#if>
        /><br>
    <input type="submit" value="登录"/>
</form>

<!--use include to include another ftl file content in this file.-->
<#include "footer.ftl"/>
</body>
</html>
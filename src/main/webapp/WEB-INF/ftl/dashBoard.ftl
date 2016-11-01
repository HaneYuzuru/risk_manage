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
<#if loginMessage!>
<div>
    <h1> ${loginMessage!}</h1>
</div>
</#if>
<!--use include to include another ftl file content in this file.-->
<#include "footer.ftl"/>
</body>
</html>
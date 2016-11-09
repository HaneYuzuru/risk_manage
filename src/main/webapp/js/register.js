
jQuery(document).ready(function() {

    /*
     Fullscreen background
     */
    $.backstretch("../img/bg.jpg");

    /*
     Form validation
     */

    $('#registerBtn').click(function(){
        $.ajaxSettings.async=false;
        var isEmpty=false;
        $('.registration-form').find('input[type="text"], input[type="password"], textarea').each(function() {
            if( $(this).val() == "" ) {
                $(this).addClass('input-error');
                isEmpty=true;
            }
            else {
                $(this).removeClass('input-error');
            }
        });
        if (isEmpty == false) {
            $.ajax({
                url: '/risk_manage-release-1.0-SNAPSHOT/login/testRegister',
                type: 'POST', //GET
                async: false,    //或false,是否异步
                data: {
                    username: $('#form-username').val()
                },
                timeout: 5000,    //超时时间
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success: function (res) {
                    console.log(res);
                    if(res['result']=="false"){
                        $('#tip').text("用户名已存在");
                        $('#tip').show();
                        return false;
                    }
                    else{
                        $('#tip').hide();
                        if($('#form-password').val()!=$('#form-repeat-password').val()){
                            $('#tip').text("两次密码输入不一致");
                            $('#tip').show();
                            return false;
                        }
                        else{
                            $(".registration-form").submit();
                        }
                    }
                }
            })
        }
        $.ajaxSettings.async=true;

    });


});

$("#backBtn").click(function(){
    history.back(-1);
});


$("#loginBtn").click(function(){
    var userID=$("#userID").val();
    var password=$("#password").val();
    if(userID==""||userID==null||password==""||password==null){
        alert("请输入完整的登录信息！");
    }
    else{
        $.ajax({
            url:'api/testLogin',
            data:{
                userID : userID ,
                password: password
            },
            type:'POST',
            dataType:'json',
            async: false,
            success:function(data) {
                if(data.loginResult=="success"){
                    window.location.href="home";
                }
                else if(data.loginResult=="notExists"){
                    alert("用户名不存在！");
                }
                else{
                    alert("密码错误！");
                }
            },
            error:function(){
                alert("登录失败");
            }

        });
    }
})


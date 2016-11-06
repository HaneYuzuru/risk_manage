
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("img/bg.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });

	$('#loginBtn').click(function(){
		var isEmpty=false;
		$('.login-form').find('input[type="text"], input[type="password"], textarea').each(function() {
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
				url: '/login/testlogin',
				type: 'POST', //GET
				async: true,    //或false,是否异步
				data: {
					username: $('#userID').val(),
					password: $('#password').val()
				},
				timeout: 5000,    //超时时间
				dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
				success: function (res) {
					if(res['result']==0){
						$('#tip').hide();
						window.location.href="";
					}
					else{
						if(res['result']==1){
							$('#tip').text("密码错误");
						}
						else{
							$('#tip').text("用户名不存在");
						}
						$('#tip').show();
					}
				}
			})
		}

	});



});

$("#registerBtn").click(function(){
	window.location.href="login/register";
})

    
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
    

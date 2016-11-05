
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
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
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
    


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
					if(res['result']=="true"){
						$('#tip').hide();
						window.location.href="risk";
					}
					else{
						$('#tip').text(res['result']);
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


    

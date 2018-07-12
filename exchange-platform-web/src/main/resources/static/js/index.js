$(function(){
		xrVal();
		function xrVal(){
			 if(getCookie('username')!=''&&getCookie('password')!=''){
		      $("#username").val(getCookie('username'));
		      $("#password").val(getCookie('password'));
		      $("#remember-me").attr("checked",'true');
			} 
		}
		$("#bt").click(function(){
			console.log("bt")
			var username = $('#username').val();
			var password = $('#password').val();
			var data = {username:username,password:password};
			$.post("/login",data,function(result){ 
				/*  if(result.mssage='error'){
					 layer.alert("账号或密码错误") 
				}else{ */ 
					if($("#remember-me").is(':checked')){
						 setCookie('username',username,7); //保存帐号到cookie，有效期7天
					     setCookie('password',password,7); //保存密码到cookie，有效期7天
					}else{
						delCookie('username');
						delCookie('password');
					}
					window.location.href="admin.html"
 				/*  } */
			}) 
		});
		 //设置cookie
		 function setCookie(name,value,day){
			    var date = new Date();
			    date.setDate(date.getDate() + day);
			    document.cookie = name + '=' + value + ';expires='+ date;
		 };
		 //获取cookie
		 function getCookie(name){
		    var reg = RegExp(name+'=([^;]+)');
		    var arr = document.cookie.match(reg);
		    if(arr){
		      return arr[1];
		    }else{
		      return '';
		    }
		 };
		//删除cookie
		 function delCookie(name){
			 setCookie(name,"", -1);
		 }
})
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
    <title>优就业-线医疗管理系统</title>
	<meta charset="UTF-8">
	<link rel="icon" href="Images/logo_favicon.ico" type="image/x-icon" />
   <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="Js/jquery.validate.js"></script>
    <script type="text/javascript" src="Js/messages_zh.js"></script>
    <style type="text/css">
        body {
            padding-top: 140px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            font-family: "微软雅黑";
            background-color: buttonhighlight;
        }

        .form-signin {
            max-width: 600px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            background: rgba(255,255,255,0.5);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
            font-size: 24px;
            margin-left: 90px;
        }
        
        .form-signin .form-signin-heading{
        	margin-bottom: 10px;
            font-size: 24px;
            margin-left: 200px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
		
		
		#message{
			font-size: 14px;
			color:red;
			margin-left: 40px;
		}
		
		.input-block-level{
			width: 300px;
			margin-left: 40px;
		}
		.input-medium{
			margin-left: 40px;
		}
		.code_images{
			width: 115px;
			height: 35px;
			margin-top: -15px;
			margin-left: 10px;
		}
		.error{
			color: red;
			font-size: 14px;
		}
		
    </style>  
</head>
<body>
<div class="container">	
    <form class="form-signin" method="post" action="user">
    	<input type="hidden" name="method" value="regist">
        <h2 class="form-signin-heading" >管理员注册</h2>
                        姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
		<input type="text" name="name" class="input-block-level" placeholder="账号">
		<br/>
                        密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
        <input id="password" type="password" name="password" class="input-block-level" placeholder="密码">
        <br/>
                       确认密码：<input type="password" name="password2" class="input-block-level" placeholder="确认密码">
        <br/>
                        用&nbsp;&nbsp;户&nbsp;&nbsp;名：<input type="text" id="username" name="username" class="input-block-level" placeholder="用户名">
        <span id="username_msg"></span><br/>
                        邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input type="text" id="email" name="email" class="input-block-level" placeholder="邮箱">
        <span id="email_msg"></span><br/>               
        <p style="text-align: center;">
        <input id="regist" type="button" value="注册" name="login" class="btn btn-large btn-info" style="width: 100px;"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="reset" type="reset" value="清空" name="login" class="btn btn-large btn-info" style="width: 100px;"/>
        </p>
    </form>
</div>

<script type="text/javascript">
	$("#regist").click(function(){
		if(username_flag&&email_falg){
		$("form").submit();
	}else{
		alert("请填写有效信息！");
	}
	});
	var username_flag=false, email_falg=false;
//用户名非空验证
$("#username").blur(function(){
	//获取用户名
	var username=$(this).val();
	if($.trim(username)==""){
		$("#username_msg").text("用户名不能为空").css("color","red");
		username_flag=false;
		return;
	}
	//向ajax后台发送验证请求
	$.ajax({
		url:"user",
		data:{"method":"checkUserName","username":username},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data){
				$("#username_msg").text("该用户被注册").css("color","red");
				username_flag=false;
			}else{
				$("#username_msg").text("√").css("color","green");
				username_flag=true;
			}
		}
	})
})
	//邮箱验证,非空，唯一
$("#email").blur(function(){
	//获取用户名
	var email=$(this).val();
	if($.trim(email)==""){
		$("#email_msg").text("邮箱不能为空").css("color","red");
		email_falg=false;
		return;
	}
	//格式验证
	var reg= /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	if(!reg.test(email)){
		$("#email_msg").text("该邮箱格式不对").css("color","red");
		email_falg=false;
		return;
	}

	//向ajax后台发送验证请求
	$.ajax({
		url:"user",
		data:{"method":"checkEmailName","email":email},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data){
				$("#email_msg").text("该邮箱被注册过").css("color","red");
				email_falg=false;
			}else{
				$("#email_msg").text("√").css("color","green");
				email_falg=true;
			}
		}
	})
});
	$("form").validate({
		rules:{
			"name":{
				"required":true
			},
			"password":{
				"required":true,
				"rangelength":[6,16]
			},
			"password2":{
				"required":true,
				"rangelength":[6,16],
				"equalTo":"#password"
			}
		},
		messages:{
			"name":{
				"required":"用户名不能为空"
			},
			"password":{
				"required":"密码不能为空",
				"rangelength":"密码长度必须在6-16字符之间"
			},
			"password2":{
				"required":"密码不能为空",
				"rangelength":"密码长度必须在6-16字符之间",
				"equalTo":"两次密码不一致"
			}
		},
			errorElement:"span"
	})
		
</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    <title>二维码签到系统登录首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="./Css/init.css">
	
	<style type="text/css">
	*{
    margin:0px;
    padding: 0px;
    }
    
	body {
	background: #f5f2f2;
	width: 100%;
	height: 100%;
	background-image: url("./Images/main.jpg");
	}
	
	.logo{
	   width: 80%;
	   margin:0 auto;
	}
	
   .container {
	border-radius: 8px;
	width: 420px;
	height: 300px;
	position:absolute;
	left:50%;
    top:50%;
    margin-top:-170px;
    margin-left:-210px;
    text-align:center;
    background: rgba(0, 0, 0, 0.3);
	}
	
	.account {    
		margin-top:10%;
		width: 100%;
	}
	
	.password {
		margin-top: 15px;
		width: 100%;
	}
	
	#account,#password{
	    width: 60%;
		height:35px;
		background-color:transparent;
		border-color:#fff; 
		
	}
	
	.submit .login{
	  width: 60%;
	  height:35px;
	  margin-top: 20px;
	}
	
	:-moz-placeholder { 

	    color: #fff;  
	}
	::-moz-placeholder { 

	    color: #fff;
	}
	
	input:-ms-input-placeholder,
	textarea:-ms-input-placeholder {
	    color: #fff;
	}
	input::-webkit-input-placeholder,
	textarea::-webkit-input-placeholder {
	    color: #fff;
	}
	
	.pass-image{
	 position:absolute;
	 display:inline-block;
	 left:40px;
	 width: 35px;
     height:35px;
	}
    .account_image{
     position:absolute;
     display:inline-block;
    /*  background-image: url("./Images/user1.png"); */
	 width: 35px;
     height:35px;
     left:40px;
    }
    
    .login-font{
      display:inline-block;
      margin-top:20px;
      font-size: 30px;
      font-family: 楷体;
      color:#abc;
    }
	</style>
	
  </head>
  
  <body>
	<div class="container">
	    <span class="login-font">二维码签到后台管理</span>
		<form id="from1" action="/SignedScanDemo/Login/Login" method="post">
		<!-- <div class="logo"><img alt="" src="/Hospital/img/logo.png"></div> -->
			<div class="account">
				<span class="account_image"><img  src="./Images/user1.png"></span> 
				<input id="account" type="text" name="username" placeholder="账号" >
			</div>
			<div class="password">
			<!-- 	<span class="pass-image"></span>  -->
				<span class="pass-image"><img  src="./Images/edit1.png"></span> 
				<input id="password" type="password" name="password" placeholder="密码">
			</div>
		    <div class="submit">
				<input class="btn btn-primary login" type="submit" value="登录">
			</div>
		</form>
	</div>
   </body>
</html>

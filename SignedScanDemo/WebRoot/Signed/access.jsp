<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<%--     <base href="<%=basePath%>"> --%>
    
    <title>非管理员扫码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
     span{
     font-size:50px;
     color:red;
     position:absolute;
     display:block;
     margin: 0 auto;
     top:40%;
     left:15%;
     }
    </style>
  </head>
  
  <body>
    <span>非管理员扫描，扫描无效，请找管理员扫码！</span>
  </body>
</html>

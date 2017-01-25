<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'containt.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/SignedScanDemo/Css/init.css">
	
  </head>
  
        <frameset cols ="420,*"   border="2" framespacing="0">
	        <!--  <frame src=""  scrolling="no" name="floorFrame" noresize="noresize" > -->
	         <frame src="/SignedScanDemo/left.jsp"  scrolling="auto"  name ="leftFrame" noresize="noresize" >
	         <frame src="/SignedScanDemo/selectinfo/selectinfo"  scrolling="auto" name="rightFrame"  noresize="noresize" >
	        
	    </frameset>
</html>

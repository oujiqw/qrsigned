<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>二维码签到后台管理</title>
 <!--  <script type="text/javascript" src="/Hospital/js/jquery-2.1.1.js"></script> -->
  <link href="" rel="stylesheet">
  <script src="/SignedScanDemo/Js/jquery-2.1.1.js"></script>
 <!--  <link rel="stylesheet" type="text/css" href="/Hospital/tree/css/main.css"> -->
  <!-- <script type="text/javascript">
  function displayTime(){
     var elt=document.getElementById("clock");
     var now=new Date();
     elt.innerHTML=now.toLocaleTimeString();
     setTimeout(displayTime,1000);
   }
  window.onload=displayTime;
  </script> -->
  <style type="text/css">
   body{
   background:#eef;
   }
   
   .exitLogin{
   position:absolute;
   right:50px;
   }
   .icon-user{
   position:absolute;
   right:110px;
   }
  </style>
  </head>
  <body>
  <div  class="title">
     <!--  <span class="time"><i class="icon-time"></i>&nbsp;时间: <span id="clock"></span></span> -->
      <span class="content">欢迎登录二维码签到后台管理系统!</span>
      <span class="login">
           <span class="icon-user"><i class="icon-user user"></i>&nbsp;登录用户：<c:out value="${userName}"></c:out>&nbsp;&nbsp;</span>
           <i class="icon-remove  exit exitLogin"></i><a class="exitLogin" href="/SignedScanDemo/exit/exit"  target="_parent">退出登录</a>
      </span>
  </div>
  </body>
</html>

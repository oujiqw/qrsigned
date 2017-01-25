<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%--   <base href="<%=basePath%>"> --%>
    
    <title>用户管理列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
    th,td{width:150px;border:2px solid gray;text-align:cneter;padding:2px 10px;}
    talbe{border-collapse:collapse;}
    body{text-align:center;}
    a{text-decoration:none}
    </style>
    <script src="/SignedScanDemo/Js/jquery-2.1.1.js"></script>
    <script type="text/javascript">
    $(document).ready(
    function(){
	    $(".send").on("click",function(){
		           var childrenLength=this.childNodes.length;
		           if(childrenLength>1){
		           
		           var qq=this.childNodes[1].value;
		           var rqposition=this.childNodes[3].innerHTML;
		           $.ajax({
						 type:"post",
					     async:true,
					     url:"/SignedScanDemo/email/sendEmail",
					     dataType:'json',//返回的值以json格式解释
					     data: {qqnumber:qq,username:rqposition},
					     success:function(data){
					     alert("邮件发送成功!");
					     }
					});
		          }
	    });	
    });
    </script>
    <style type="text/css">
    body{
    background:#DFDFFF;
    }
    .list{
    height:90%;
    width:100%;
    background-color: #DFDFFF;
    }

    .list-head{
    border-style:dotted solid double solid; 
    }
    .list-head h2{
    font-family:华文楷体;
    color:#000;
    }
    /* a:nth-child(1){
    font-size: 22px;
    } */
    
    .list-content-title{
     height:30px;
     width:100%;
    }
    .list-content-title span{
    display: inline-block;
    margin: 5px 0px 0px 25px;
    }
    
    table{
    border:solid 1px #add9c0;}
    th,tr,td{
    border: 1px solid gray;
    }
    .signed{
    width:5px;
    }
    .sendMail{
    width:90px;
    }
    .sex{
    width:30px;
    }
    .delete{
    width:100px;
    }
    .reset{
    width:100px;
    }
    
    .send{
    /* background:rgba(0,0,0,0); */
    border: 1px solid gray;
    cursor:pointer;
    color:green;
    }
    </style>
  </head>
  <body>
    <div class="list">
      <div class="list-head"><h2>用户信息列表</h2></div>
      <div class="list-content-title">
      <span><a href="/SignedScanDemo/selectinfo/selectinfo?pageNo=${pageNo}">返回首页</a></span><span>目前是第<c:out value="${pageNo}"/>页</span>

      <c:if test="${pageNo-1>0}">
      <a href="/SignedScanDemo/selectinfo/selectinfo?pageNo=${pageNo-1}">上一页</a></span>   
      </c:if>
      <c:if test="${pageNo-1<=0}">
		   <span>上一页</span>
      </c:if> 
      <c:if test="${pageNo+1>pageCount}">
		   <span>下一页</span>
      </c:if>
      <c:if test="${pageNo+1<=pageCount}">
		   <a href="/SignedScanDemo/selectinfo/selectinfo?pageNo=${pageNo+1}">下一页</a></span>
      </c:if>
      <span><a href="/SignedScanDemo/selectinfo/selectinfo?pageNo=${pageCount}">最后一页</a></span><span>共有<c:out value="${pageCount}"/>页</span>
      </div>
      <div class="list-content">
	        <c:if test="${pageCount>0}">
		    <table>
		    <tr><th>用户编号</th><th>用户名</th><th class="sex">性别</th><th class="signed">是否签到</th>
		    <th class="sendMail">发二维码至QQ邮箱</th><th>QQ邮箱</th><th class="delete">删除</th><th class="reset">重设</th></tr>
		    <c:forEach items="${userlist}" var="user">
		    <%-- ${user.qrposition}${user.flagsinged} --%>
		    <tr><td style="text-align: center">${user.userid}</td><td style="text-align: center">${user.username}</td>
		    <td class="sex" style="text-align: center">${user.sex}</td>
		    <td class="signed"><c:if test="${user.flagsinged==0}">否</c:if><c:if test="${user.flagsinged==1}">是</c:if></td>
		    <td class="sendMail" style="text-align: center">
		    <span class="send">发送<input type="hidden" name="value" value="${user.qqnumber}">
		    <span style="display:none">${user.username}</span>
		    </span></td>
		    <td>${user.qqnumber}@qq.com</td>
		    <td style="text-align: center" class="delete"><a href="/SignedScanDemo/delete/delete?userid=${user.userid}">删除</a></td>
		    <td style="text-align: center" class="reset"><a href="/SignedScanDemo/reset/reset?userid=${user.userid}">重设</a></td>
		    </tr>
		    </c:forEach>
		    </table>
		    </c:if>
		    <c:if test="${pageCount==0}">
		    <p>目前没有数据！</p>
		    </c:if>
	  </div> 
    </div>
   
  </body>
</html>

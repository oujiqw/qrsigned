<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    
    <title>createQR</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
    body{
    background:#DFDFFF;
    }
    .name-div,.sex-div,.position-div,.telphone-div,.qq-div,.companyName-div,.companyAddr-div{
    margin:15px 0px 0px 100px;
    width:200px;
    height:50px;
    }
    .QR{
    width:200px;
    height:30px;
    }
    .QR-div{
    margin:15px 0px 0px 100px;
    width:200px;
    height:30px;
    }
    .QR-input{
    width:200px;
    height:30px;
    }
    </style>
    <script src="/SignedScanDemo/Js/jquery-2.1.1.js"></script>
    <script type="text/javascript">
    var qqflag=0,nameflag=0;//
       $(function(){
       
        $('#create-btn').click(function(){ 
     	   if($('#name').val()){
     		 nameflag=1;    		
     	   }
            if(qqflag&&nameflag){
        		$.ajax({
 		           	url:"/SignedScanDemo/saveinfo/saveuser",
 		          	type:'post',
 		          	data:{userName:$('#name').val(),userSex:$('#sex').val(),companyName:$('#companyName-input').val(),
 		          		position:$('#position').val(),telphone:$('#telphone').val(),
 		          		qqNumber:$('#qq-input').val(),companyAddr:$('#addr').val()},
 		          	dataType:'json',
 		          	success:function(data){
 		                if(data=="1"){
 		                	  alert("二维码生成成功！");
 		                	  parent.rightFrame.location.href="/SignedScanDemo/selectinfo/selectinfo";
 		                	  location.href="/SignedScanDemo/left.jsp";
 		                }
 		                else{
 		                	  alert("二维码生成失败！");
 		                	  location.href='';//跳转到错误界面或注册页面，页面待开发
 		                }
 		          	}
 		        })
        	}else if(qqflag==0){
                alert("QQ号输入有误！");
        	}else if(nameflag==0){
        		alert("名字不能为空！");
        	}
  	    });
        
        $("#qq-input").on("blur",function(){
        	 
       	  var qq=$("#qq-input").val();
       	  var qqlength=qq.length;
       	  if(qqlength==0){
       	      $('#qq-span').html('<font></font>');
       		  alert("QQ号不能为空！");
       	  }else{
       		/*  qqflag=1; */
       		  $.ajax({
    	             type:"post",
    	             async:true,
    	             url:"/SignedScanDemo/qqcheck/qqcheck",
    	             dataType:'json',//返回的值以json格式解释
    	             data:{qqnumber:qq},
    	             success:function(data){
    		              if(data==1){
    		            	  $('#qq-span').html('<font style = "color:green">QQ账号存在！</font>');
    		            	  qqflag=1;
    		              }else if(data==2){
    		            	  $('#qq-span').html('<font></font>');
    		            	  qqflag=1;
    		              }else{
    		            	  $('#qq-span').html('<font style = "color:red">该QQ账号不存在！</font>');
    		            	  qqflag=0;
    		              }
    	             }
    	             
    	          });	  
       	  }
  	          
  	     });
        
      });
    
       
     
    </script>
  </head>
  
  <body>
        <div class="QR">
			<form action="" method="post">
			<div class="name-div">
				<label class="name-lable" style="text-align:right">姓名:</label>
				<div class="name-input-div">
				<input type="text" id="name" name="userName" placeholder="姓名" class="QR" value="">
				</div>
			</div>
			<div class="sex-div">
				<label class="sex-label" style="text-align:right">性别:</label>
				<div class="sex-input-div">
				<input type="text" id="sex" name="userSex" placeholder="男或女" class="QR" value="">
				</div>
			</div>
			<div class="companyName-div">
				<label class="companyName-name" style="text-align:right">公司名称:</label>
				<div class="companyName-input-div">
				<input type="text" id="companyName-input" name="companyName" placeholder="公司名称" class="QR" value="">
				</div>
			</div>
			<div class="position-div">
				<label class="position-label" style="text-align:right">职位:</label>
				<div class="position-input-div">
				<input type="text" id="position" name="position" placeholder="职务" class="QR" value="">
				</div>
			</div>
			<div class="telphone-div">
				<label class="telphone-lable" style="text-align:right">手机:</label>
				<div class="telphone-input-div">
				<input type="text" id="telphone" name="telphone" placeholder="手机" class="QR" value="">
				</div>
			</div>
			<div class="qq-div">
				<label class="qq-lable" style="text-align:right">QQ号:</label>
				<div class="qq-input-div">
				<input style="display:inline-block" type="text" id="qq-input" name="qqNumber" placeholder="qq号" class="QR" value="">
				<span id="qq-span" style="display:inline-block"></span>
				</div>
			</div>
			<!-- <div class="mail-div">
				<label class="mail-lable" style="text-align:right">邮箱:</label>
				<div class="mail-input-div">
				<input type="text" id="email-input" name="email" placeholder="邮箱" class="QR" value="">
				</div>
			</div> -->
			
			<div class="companyAddr-div">
				<label class="companyAddr-label" style="text-align:right">公司地址:</label>
				<div class="companyAddr-input-div">
				<input type="text" id="addr" name="companyAddr" placeholder="公司地址" class="QR" value="">
				</div>
			</div>
			<div class="QR-div">
				<div class="QR-input-div"  style="text-align:center">
					<button type="submit" class="QR-input" id="create-btn" onclick="return false">生成二维码</button>
				</div>
			</div>
			</form>
		</div>
  </body>
</html>

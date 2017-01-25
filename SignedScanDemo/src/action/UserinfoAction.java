package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import qrbean.UsersBean;
import util.JsonUtil;
public class UserinfoAction extends SuperAction{
  
private static final long serialVersionUID = 1L;

    /**
     * 计算出有多少条记录，然后进行分页
     * @return
     * @throws SQLException
     */
	public int getPageCount() throws SQLException{
	  Connection conn=null;
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	  int recordCount=0,t1=0,t2=0;
	  try {
		  conn=Dbconnection.getConn();//连接数据库
		  String sql="select * from qr_users";
		  ps=conn.prepareStatement(sql);
		  rs=ps.executeQuery();
		  while (rs.next()) {
		  recordCount++;
		}
		  t1=recordCount%12;//除10取余数
		  t2=recordCount/12;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	}   
	  rs.close();
	  ps.close();
      conn.close();
      /*System.out.print("pageCount");
      System.out.print(t2+1);*/
	  return t1==0?t2:t2+1;//返回分页的数目
	  }
    
	/**
	 * 用listUser（）方法返回所有的用户数据
	 * @param pageNo
	 * @return
	 * @throws SQLException
	 */
	public List<UsersBean> listUser(int pageNo) throws SQLException{
	  Connection conn=null;
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	  int pageSize=12;
	  int startRecno=(pageNo-1)*pageSize;
	  ArrayList<UsersBean> userList=new ArrayList<UsersBean>();//实例化一个数组集合类
	  conn=Dbconnection.getConn();//连接数据库
	  String sql="select * from qr_users order by id  desc limit ?,?";
	  ps=conn.prepareStatement(sql);
	  ps.setInt(1, startRecno);
	  ps.setInt(2, pageSize);
	  rs=ps.executeQuery();
	  while(rs.next()){     //从数据库取出用户ID，用户名，用户 性别
	  UsersBean user=new  UsersBean();
	  user.setUserid(rs.getString(1));
	  user.setUsername(rs.getString(2));
	  user.setSex(rs.getString(3));  
	  user.setQqnumber(rs.getString(7));
	  user.setQrposition(rs.getString(9));
	  user.setFlagsinged(rs.getString(10));
	  System.out.print(rs.getString(9));
	  userList.add(user);//把查询到的数据添加到userlist集合中
	  }	
	  System.out.print(userList);//测试，输出userlist集合的内容
	  rs.close();
	  ps.close();
	  conn.close();
	  return userList;//返回取到的数据
	}
	
	public void selectPage() throws SQLException{
	    int pageNo=1;
	    int pageSize=5;
	    String strPageNo=request.getParameter("pageNo");
	    /*String strPageNo="1";*/
	    if(strPageNo!=null){
	    	pageNo=Integer.parseInt(strPageNo);	
	    }
	try {
		List<UsersBean>userlist=listUser(pageNo);//调用listUser(int pageNo)方法获取用户数据
		request.setAttribute("userlist", userlist);//把用户数据保存到request对象中
		Integer pageCount=new Integer(getPageCount());//调用getPageCount()方法获取分页数，保存到
		request.setAttribute("pageCount", pageCount);	
		request.setAttribute("pageNo",pageNo);
		/*RequestDispatcher rd=request.getRequestDispatcher("/Userlist/list.jsp");*/
		request.getRequestDispatcher("/Userlist/list.jsp").forward(request, response); 
		System.out.print(session.getAttribute("pageCount"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
	}
	
	/**
	 * 保存用户信息方法saveUserinfo(),同时生成二维码并保存到文件夹中
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void saveUserinfo() throws SQLException, IOException{
	   /*response.setContentType("text/html;charset=utf-8");
	   PrintWriter out=response.getWriter();*/
	   int influ;
	   Connection conn=null;
	   PreparedStatement ps=null;
	   ResultSet rs=null;
	   String filePathL=null;
	   String userName=request.getParameter("userName");//获取用户名
	   String sex=request.getParameter("userSex");
	   String companyName=request.getParameter("companyName");
	   String position=request.getParameter("position");
	   String telphone=request.getParameter("telphone");
	   String qqNumber=request.getParameter("qqNumber");
	   /*int telphone=Integer.parseInt(request.getParameter("telphone"));
	   int qqNumber=Integer.parseInt(request.getParameter("qqNumber"));*/
	   String companyAddr=request.getParameter("companyAddr");//获取公司地址
	   
	   StringBuffer content = new StringBuffer("http://139.199.19.232:8080/SignedScanDemo/signed/signed?username="+userName);
	   /*content.append("V").append("\n");
	   content.append("用户名：").append(userName).append("\n");
	   content.append("公司名称：").append(companyName).append("\n");
	   content.append("联系号码：").append(telphone).append("\n");
	   content.append("职位：").append(position).append("\n");
	   content.append("QQ:").append(qqNumber).append("@qq.com").append("\n");
	   content.append("公司地址：").append(companyAddr).append("\n");*/
	  /* content.append("提交：").append("http://139.199.19.232:8080/SignedScanDemo/").append("\n");*/
	   /*content.append("END1:VCARD1").append("\n");*/
	   response.setContentType("image/jpeg");
	   String fileName = userName+ ".jpg"; 
	   try {
			 /*response.setHeader("Content-Disposition", "attachment;"  
		                + " filename="  
		                + new String(fileName.getBytes(), "utf-8")); 
			 OutputStream os = response.getOutputStream();  */
			 String root = request.getSession().getServletContext().getRealPath("/");
			/* String path = request.getContextPath();
			 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			 System.out.print(basePath);*/
			 CreateqrAction.encode(content.toString(),root+"/Logos/logo.png",root+"/QrImages",true,userName);
			/* File filePath=new File(root+"/QrImages"+"/"+fileName);*/
			 System.out.print("filePath.toString=");
			 filePathL = "http://localhost:8080/SignedScanDemo/QrImages/"+fileName;
			 /*filePathL=filePath.toString();*/
	   } catch (Exception e) {
			e.printStackTrace();
	   }
	   
	   conn=Dbconnection.getConn();//连接数据库
	   String sql = "insert into qr_users(username,sex,companyname,position,telphone,qqnumber,companyaddr,qrposition)"
	   		+ " values('"+userName+"','"+sex+"','"+companyName+"','"+position+"','"+telphone+"','"+qqNumber+"','"+companyAddr+"','"+filePathL+"')";
	   ps = conn.prepareStatement(sql);
	   influ=ps.executeUpdate();
	   try{
			if(influ>0){
				/*out.print("二维码生成成功！");*/
				System.out.print("数据插入成功！");
				JsonUtil.writeToResponse("1");
			}else{
				System.out.print("数据插入失败！");
			}
	   }catch(Exception e){
			e.printStackTrace();
			   /*out.print("二维码生成失败！");*/
	   }
	   ps.close();
	   conn.close();  
	}
	
	public void delete() throws SQLException{
		String userid=request.getParameter("userid");
	    Connection conn=null;
	    PreparedStatement ps=null;
	    String sql="delete from qr_users where id='"+userid+"'";
	    conn=Dbconnection.getConn();//连接数据库
	    ps=conn.prepareStatement(sql);
	    try {
			int a=ps.executeUpdate();
			System.out.print("userid=");
			System.out.print(userid);
			if(a>0){
			selectPage();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ps.close();
	    conn.close(); 
	}
	
	public void reset() throws SQLException{
		String userid=request.getParameter("userid");
	    Connection conn=null;
	    PreparedStatement ps=null;
	    conn = Dbconnection.getConn();
	    String sql = "update qr_users set flagsinged='0' where id='"+userid+"'";
	    ps= conn.prepareStatement(sql);
	    int count=ps.executeUpdate();
	    if(count>0){
	    selectPage();     		    	
	    }
	    ps.close();
		conn.close();
	}
}

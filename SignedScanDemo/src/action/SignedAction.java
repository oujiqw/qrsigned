package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import db.Dbconnection;

public class SignedAction extends SuperAction{
	 /**
	  * 管理员登录后扫码的方法signed()
	  * @return
	  * @throws IOException
	  * @throws ServletException
	  * @throws SQLException
	  */
      public String signed() throws IOException, ServletException, SQLException{
    	  String username=request.getParameter("username");
    	  if(session.getAttribute("username")!=null){//检测管理员是否已登录
    		    Connection conn=null;
    		    PreparedStatement ps=null;
    		    conn = Dbconnection.getConn();
    		    String sql = "update qr_users set flagsinged='1' where username='"+username+"'";
    		    ps= conn.prepareStatement(sql);
    		    int count=ps.executeUpdate();
    		    if(count>0){
    		   	request.setAttribute("name", username);
        		request.getRequestDispatcher("/Signed/success.jsp").forward(request, response);     		    	
    		    }else{
            	response.sendRedirect("Signed/failure.jsp");	//签到失败转跳页面
    		    }
    			/*response.sendRedirect("/SignedScanDemo/login.jsp");*/
    			ps.close();
    			conn.close();
    	  }
    	  else{//非管理员扫码处理
    		  /*JsonUtil.writeToResponse(session.getAttribute("username"));*/ 
    		  response.sendRedirect("/SignedScanDemo/Signed/access.jsp");
    	  }
		     return null;
      }
}

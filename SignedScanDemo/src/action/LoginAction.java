package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Dbconnection;

public class LoginAction extends SuperAction{
	 private Connection conn;
	 private PreparedStatement pstmt;
	 private ResultSet rs;
	 
	 public String login() throws SQLException{
			/*response.setContentType("text/html");*/
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			session.setAttribute("username",username);
			session.setAttribute("password",password);
			System.out.print("username=");
			System.out.print(username);
			String sql = "select * from qr_admins where userName='"+username+"' and password='"+password+"'";
			conn = Dbconnection.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			try {
				if (rs.next()) {
					session.setAttribute("userName", username);
					return "success";
				} else {
					return "false";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs.close();
			pstmt.close();
			conn.close();
			return null;
	 }
	 
	 /**
	  * exit方法控制退出登录
	 * @throws IOException 
	  */
    public void exit() throws IOException{
    	session.removeAttribute("userName");
    	System.out.print("userName=");
    	System.out.print(session.getAttribute("userName"));
    	response.sendRedirect("/SignedScanDemo/login.jsp");
    }

}

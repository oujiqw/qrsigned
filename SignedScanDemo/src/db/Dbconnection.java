package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
	private static String dbUrl="jdbc:mysql://localhost:3306/qrsigned?characterEncoding=UTF-8";
	private static String dbUser="root";
	private static String dbPassword="qwc525431";
	private static String jdbcName="com.mysql.jdbc.Driver";
	
	//数据库连接
	public static Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(jdbcName);
		}
		catch(Exception e){}
		try{
			conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
		}
		catch(SQLException ex){}
		return conn;		
	}

}

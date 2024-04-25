package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conn {

	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected ResultSet rs = null;
	
	protected void getConn() {
		try{
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
		String user = "campus_24SW_FS_p1_3";
		String password= "smhrd3";
		conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	protected void getClose() {
		try {
			if(rs != null) rs.close(); 
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


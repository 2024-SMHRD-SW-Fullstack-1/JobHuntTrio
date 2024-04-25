package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class StatDAO {

	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected ResultSet rs = null;
	
	public void getConn() {
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
	
	public void getClose() {
		try {
			if(rs != null) rs.close(); 
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int listening(String uId) {
		int row = 0;
		try {
		getConn();
		String sql = "UPDATE STAT_TB SET CS=CS+1 where MEMBER_ID = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, uId);
		row = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			getClose();
		}
		return row;
	}
	
	public int study(String uId) {
		int row = 0;
		try {
		getConn();
		String sql = "UPDATE STAT_TB SET ALGORITHM=ALGORITHM+1, HEALTH=HEALTH-20 where MEMBER_ID = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, uId);
		row = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			getClose();
		}
		return row;
	}
	
	public int snack(String uId) {
		int row = 0;
		try {
		getConn();
		String sql = "UPDATE STAT_TB SET HEALTH=HEALTH+20 where MEMBER_ID = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, uId);
		row = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			getClose();
		}
		return row;
	}

}

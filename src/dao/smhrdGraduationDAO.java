package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import dto.smhrdGraduationDTO;

public class smhrdGraduationDAO{
	
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
		
	// 회원가입
	public int join(smhrdGraduationDTO dto) {

		int row = 0;
		
		try {
			getConn();
			String sql = "INSERT INTO MEMBER_TB (MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_GENDER, MEMBER_AGE) VALUES (?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getGender());
			psmt.setInt(5, dto.getAge());
			row = psmt.executeUpdate();
			
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 있는 id 입니다.");
		}catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			getClose();
		}
		return row;
	}
	
	//회원탈퇴
	public int delete(smhrdGraduationDTO dto) {
			
			int row = 0;
			try {
				getConn();
				
				String sql = "DELETE FROM MEMBER_TB WHERE MEMBER_ID=? AND MEMBER_PW=?";
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPw());
				
				row = psmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				getClose();
			}
			return row;
		}
	
	
}

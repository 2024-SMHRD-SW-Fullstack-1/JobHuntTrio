package dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import dto.MemberDTO;
import dto.StatDTO;

public class MemberDAO extends Conn {
		
	// 로그인 
		public String login(MemberDTO dto) {
			String uId = null;
			try {
				getConn();
				String sql = "SELECT * FROM MEMBER_TB WHERE MEMBER_ID= ? AND MEMBER_PW= ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPw());
				rs = psmt.executeQuery();
				if(rs.next()) {
					uId = rs.getString(1);
				}
			} catch (Exception e) {
					e.printStackTrace();
				} finally {
					getClose();
				}
			return uId;
		}

	// 회원가입
	public int join(MemberDTO dto) {

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
	public int delete(MemberDTO dto) {
			
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

	
	



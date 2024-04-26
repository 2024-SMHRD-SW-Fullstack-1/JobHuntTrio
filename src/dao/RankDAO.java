package dao;

import java.sql.SQLIntegrityConstraintViolationException;

import dto.MemberDTO;

public class RankDAO extends Conn {
	
	public int join(MemberDTO dto) {

		int row = 0;
		try {
			getConn();
			String sql = "SELECT 2";
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
	
	
	
	
	
	
	
}

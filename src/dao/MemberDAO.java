package dao;

import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import dto.MemberDTO;
import dto.StatDTO;

<<<<<<< HEAD
=======
<<<<<<<< HEAD:src/dao/smhrdGraduationDAO.java
public class smhrdGraduationDAO {

	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected ResultSet rs = null;

	protected void getConn() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String user = "campus_24SW_FS_p1_3";
			String password = "smhrd3";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void getClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
			if (rs.next()) {
				uId = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return uId;
	}

========
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164
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
<<<<<<< HEAD
=======
>>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164:src/dao/MemberDAO.java
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164
	// 회원가입
	public int join(MemberDTO dto) {

		int row = 0;
<<<<<<< HEAD
		
=======

>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164
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
<<<<<<< HEAD
			
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 있는 id 입니다.");
		}catch (Exception e) {
			e.printStackTrace(); 
=======

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 있는 id 입니다.");
		} catch (Exception e) {
			e.printStackTrace();
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164
		} finally {
			getClose();
		}
		return row;
	}
<<<<<<< HEAD
	
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
=======

	// 회원탈퇴
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
<<<<<<<< HEAD:src/dao/smhrdGraduationDAO.java
		return row;
	}

	// 캐릭터정보출력
	public StatDTO SelectInpo(String id) {

		StatDTO dto = new StatDTO();
		try {
			getConn();

			String sql = "SELECT * FROM STAT_TB WHERE MEMBER_ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next()) {
				dto = new StatDTO(rs.getString("MEMBER_ID"), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return dto;
	}

	public int createU(StatDTO dto) {

		int row = 0;

		try {
			getConn();
			String sql = "INSERT INTO STAT_TB (MEMBER_ID, NICKNAME) VALUES (?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getNickname());
			row = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return row;
	}

	

}
========
	
	
}
>>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164:src/dao/MemberDAO.java
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164

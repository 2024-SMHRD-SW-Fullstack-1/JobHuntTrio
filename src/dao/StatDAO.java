package dao;

import java.sql.ResultSetMetaData;

import dto.StatDTO;

<<<<<<< HEAD
public class StatDAO extends Conn {

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

	public int listening(String uId) {
		int row = 0;
		try {
			getConn();
			String sql = "UPDATE STAT_TB SET CS=CS+10 where MEMBER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uId);
			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
=======
public class StatDAO extends Conn{
	
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
						if(rs.next()) {
							dto = new StatDTO(rs.getString("MEMBER_ID"), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7),rs.getInt(8));
						}
						
					} catch (Exception e) {
							e.printStackTrace();
						} finally {
							getClose();
						}
					return dto;
			}
		
		// 캐릭터생성
		public int createU(StatDTO dto) {
		
			int row = 0;
			
			try {
				getConn();
				String sql = "INSERT INTO STAT_TB (MEMBER_ID, NICKNAME) VALUES (?, ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getNickname());
				row = psmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				getClose();
			}
			return row;
		}
	
		// 수업듣기
	public int listening(String uId) {
		int row = 0;
		try {
		getConn();
		String sql = "UPDATE STAT_TB SET CS=CS+10, HEALTH=HEALTH-20 where MEMBER_ID = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, uId);
		row = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace(); 
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164
		} finally {
			getClose();
		}
		return row;
	}
<<<<<<< HEAD

	public int study(String uId) {
		int row = 0;
		try {
			getConn();
			String sql = "UPDATE STAT_TB SET ALGORITHM=ALGORITHM+10, HEALTH=HEALTH-20 where MEMBER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uId);
			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
=======
	
	// 공부하기
	public int study(String uId) {
		int row = 0;
		try {
		getConn();
		String sql = "UPDATE STAT_TB SET ALGORITHM=ALGORITHM+10, HEALTH=HEALTH-20 where MEMBER_ID = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, uId);
		row = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace(); 
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164
		} finally {
			getClose();
		}
		return row;
	}
<<<<<<< HEAD

	public int snack(String uId) {
		int row = 0;
		try {
			getConn();
			String sql = "UPDATE STAT_TB SET HEALTH=HEALTH+20 where MEMBER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uId);
			row = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
=======
	
	// 간식먹기
	public int snack(String uId) {
		int row = 0;
		try {
		getConn();
		String sql = "UPDATE STAT_TB SET HEALTH=HEALTH+15 where MEMBER_ID = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, uId);
		row = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace(); 
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164
		} finally {
			getClose();
		}
		return row;
	}
<<<<<<< HEAD

	public void dayPlus(String uId) {
		int row = 0;
=======
	
	// 늦잠자기
	public void overSleep(String uId) {
		int row=0;
		try {
			getConn();
			String sql = "UPDATE STAT_TB SET HEALTH = HEALTH+30 where MEMBER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uId);
			row = psmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				getClose();
			}
	}
	
	// 다음날
	public void dayPlus(String uId) {
		int row=0;
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164
		try {
			getConn();
			String sql = "UPDATE STAT_TB SET DAY=DAY+1 where MEMBER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uId);
			row = psmt.executeUpdate();
<<<<<<< HEAD
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
	}

	// 시험 합격시 자격증 획득
	public int license(String uId) {
		int row = 0;
		try {
			getConn();
			String sql = "UPDATE STAT_TB SET license = license+1  WHERE MEMBER_ID = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uId);
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
			}catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				getClose();
			}
	}
	

}
>>>>>>> 9239e2a4677488486a346a567e3a87bf569b0164

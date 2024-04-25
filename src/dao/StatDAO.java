package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class StatDAO extends Conn{
	
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
	
	public void dayPlus(String uId) {
		int row=0;
		try {
			getConn();
			String sql = "UPDATE STAT_TB SET DAY=DAY+1 where MEMBER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uId);
			row = psmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				getClose();
			}
	}
	

}

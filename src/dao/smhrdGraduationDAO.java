package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class smhrdGraduationDAO {

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
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
	
	
	
}

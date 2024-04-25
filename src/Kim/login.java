package Kim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dao.smhrdGraduationDAO;

public class login extends Conn{

	public static void main(String[] args) {


	      Scanner sc = new Scanner(System.in);

	      System.out.print("ID를 입력해주세요 >> ");
	      String id = sc.next();
	      System.out.print("PW를 입력해주세요 >> ");
	      String pw = sc.next();

	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      String nick = null;

	      try {
	         Class.forName("oracle.jdbc.OracleDriver");
	         String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
	         String user = "campus_24SW_FS_p1_3";
	         String password = "smhrd3";
	         conn = DriverManager.getConnection(url, user, password);

	         String sql = "SELECT * FROM MEMBER_TB WHERE MEMBER_ID = ? AND MEMBER_PW = ?";

	         psmt = conn.prepareStatement(sql);

	         psmt.setString(1, id);
	         psmt.setString(2, pw);

	         rs = psmt.executeQuery();

	         if (rs.next()) {
	            String uName = rs.getString("MEMBER_NAME");
	            System.out.println(uName + "님 환영합니다~");

	            String sql2 = "SELECT * FROM STAT_TB WHERE NICKNAME = ?";

	            psmt = conn.prepareStatement(sql2);

	            psmt.setString(1, nick);

	            rs = psmt.executeQuery();

	            if (rs.next()) {
	               System.out.print("닉네임을 설정해주세요 >> ");
	               nick = sc.next();

	               String sql3 = "INSERT INTO STAT_TB VALUES (?,?,?,?,?,?,?) ";

	               psmt = conn.prepareStatement(sql3);

	               psmt.setString(1, id);
	               psmt.setString(2, nick);
	               psmt.setString(3, "100");
	               psmt.setString(4, "100");
	               psmt.setString(5, "100");
	               psmt.setString(6, "100");
	               psmt.setString(7, "0");

	               int row = psmt.executeUpdate();

	            } else {
	               System.out.print("갓생살기에 도전하시겠습니까? (Y/N) ");
	               String Answer = sc.next();
	               if (Answer.equals("Y") || Answer.equals("y")) {
	                  System.out.println("갓생살기 도전!");
	               } else {
	                  System.out.println("갓생살기 실패!!");
	               }

	            }

	         } else {
	            System.out.println("로그인이 실패했습니다.");
	            System.out.println("아이디나 비밀번호를 확인해주세요.");
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
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

	   }

	}
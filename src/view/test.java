package view;

import java.util.Scanner;

import dao.smhrdGraduationDAO;
import dto.smhrdGraduationDTO;

public class test {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[1]로그인 [2]회원가입 [3]게임목표 [4]회원탈퇴 [5]게임종료");
		System.out.println("번호 입력 : ");
		int input = sc.nextInt();
		if(input == 1) {
			System.out.print("id");
			String id =sc.next();
			System.out.print("pw");
			String pw =sc.next();
			System.out.print("name");
			String name =sc.next();
			System.out.print("성별 남/여");
			String gender =sc.next();
			System.out.print("age");
			int age = sc.nextInt();			
			smhrdGraduationDAO dao = new smhrdGraduationDAO();
			smhrdGraduationDTO dto = new smhrdGraduationDTO(id, pw, name, gender, age);
			int row = dao.join(dto);
			if(row >0) {
				System.out.println("가입완료");
			}
		}else if(input == 4){
			System.out.print("id: ");
			String id = sc.next();
			System.out.print("pw: ");
			String pw = sc.next();
			
			smhrdGraduationDAO dao = new smhrdGraduationDAO();
			smhrdGraduationDTO dto = new smhrdGraduationDTO(id, pw);
			int row = dao.delete(dto);
			if(row >0) {
				System.out.println("계정 삭제 완료");
			}
		}
		
		
		
		
		
		
		
		
	}

}

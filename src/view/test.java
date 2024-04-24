package view;

import java.util.Scanner;

import dao.smhrdGraduationDAO;
import dto.MemberDTO;
import dto.StatDTO;

public class test {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[1]로그인 [2]회원가입 [3]캐릭터정보 [4]회원탈퇴 [5]게임종료");
		System.out.println("번호 입력 : ");
		int input = sc.nextInt();
		if(input == 2) {
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
			MemberDTO dto = new MemberDTO(id, pw, name, gender, age);
			int row = dao.join(dto);
			if(row >0) {
				System.out.println("가입완료");
			}
		}else if(input == 3){
			String id = "TEST2";
			smhrdGraduationDAO dao = new smhrdGraduationDAO();
			 StatDTO dto = dao.SelectInpo(id);
			 System.out.println(dto.getNickname()+"님의 정보");
			 System.out.println("지능 : "+dto.getIntellect());
			 System.out.println("CS : "+dto.getCs());
			 System.out.println("알고리즘 : "+dto.getAlgorithm());
			 System.out.println("건강 : "+dto.getHealth());
			 System.out.println("자격증 갯수 : "+dto.getLicense());
			 
		}else if(input == 4){
			System.out.print("id: ");
			String id = sc.next();
			System.out.print("pw: ");
			String pw = sc.next();
			
			smhrdGraduationDAO dao = new smhrdGraduationDAO();
			MemberDTO dto = new MemberDTO(id, pw);
			int row = dao.delete(dto);
			if(row >0) {
				System.out.println("계정 삭제 완료");
			}
		}
		
		
		
		
		
		
		
		
	}

}

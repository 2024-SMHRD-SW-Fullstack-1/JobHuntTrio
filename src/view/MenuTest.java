package view;

import java.util.Scanner;

import dao.smhrdGraduationDAO;
import dto.MemberDTO;

public class MenuTest {

	public static void main(String[] args) {
			
		Scanner sc = new Scanner(System.in);
		smhrdGraduationDAO dao = new smhrdGraduationDAO();
		
		
		String uId = null;
		System.out.println("인트로");
		
		while(true) {
			System.out.println("[1]로그인 [2]회원가입 [3]회원탈퇴 [4]게임종료");
			System.out.println("선택>>");
			int input = sc.nextInt();
			if(input==1) {
				System.out.print("ID입력: ");
				String id = sc.next();
				System.out.print("PW입력: ");
				String pw = sc.next();
				MemberDTO dto = new MemberDTO(id, pw);
				uId = dao.login(dto);
				if(uId!=null) {
					System.out.println(uId+"님이 로그인하셨습니다.");
					break;
				}else {
					System.out.println("아이디나 비밀번호가 틀렸습니다.");
				}
			}else if(input ==2) {
				System.out.println("회원가입완료");
			}else if(input ==3) {
				System.out.println("회원탈퇴완료");
			}else if(input ==4) {
				System.out.println("게임을 종료합니다.");
				break;
			}else {
				System.out.println("다른 번호를 입력해주세요.");
			}
		}
		
		if(uId!=null) {
			String uNick = dao.SelectInpo(uId).getNickname();
			if(uNick!=null) {
				System.out.println("이전 플레이 내역이 있어 이어서 진행합니다.");
			}else {
				System.out.println("이전 플레이 내역이 없어 캐릭터를 생성합니다.");
			}
		}
		
		while(uId !=null) {
			
			System.out.println("[1]로그인 [2]회원가입 [3]회원탈퇴 [4]게임종료");
			break;
			
		}
		
		
		
	}

}

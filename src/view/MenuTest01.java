package view;

import java.util.Scanner;

import MusicController.MusicController;
import dao.StatDAO;
import dao.MemberDAO;
import dto.MemberDTO;
import dto.StatDTO;

public class MenuTest01  {

	public static void main(String[] args) {
			
		Scanner sc = new Scanner(System.in);
		MemberDAO dao = new MemberDAO();
		MusicController mc = new MusicController();
		MainAscci mf = new MainAscci();
		String uId = null;
		mf.intro();
		
		
		mf.gameIntro();
		while(true) {
			//기본메뉴
			System.out.println("====================================  메인 메뉴 ==================================== ");
			System.out.println("[1]로그인\t\t[2]회원가입\t\t[3]회원탈퇴\t\t[4]게임종료");
			System.out.print("선택>>");
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
				MemberDTO dto = new MemberDTO(id, pw, name, gender, age);
				int row = dao.join(dto);
				if(row >0) {
					System.out.println("가입완료");
				}
			}else if(input ==3) {
				System.out.println("회원탈퇴완료");
			}else if(input ==4) {
				System.out.println("게임을 종료합니다.");
				break;
			}else {
				System.out.println("다른 번호를 입력해주세요.");
			}
		}
		
		StatDAO stat = new StatDAO();
		if(uId!=null) {
			String uNick = stat.SelectInpo(uId).getNickname();
			if(uNick!=null) {
				System.out.println("이전 플레이 내역이 있어 이어서 진행합니다.");
			}else {
				System.out.println("이전 플레이 내역이 없어 캐릭터를 생성합니다.");
				System.out.println("닉네임 입력 : ");
				String nick =sc.next();
				StatDTO dto = new StatDTO(uId, nick);
				stat.createU(dto);
				mf.gameIntro();
			}
		}
		mc.play();
		menuLoop:
		//게임메뉴
		while(uId !=null) {
			StatDTO dto = stat.SelectInpo(uId);
			System.out.println(dto.getDay()+"일차 갓생력");
			System.out.println("CS: "+dto.getCs());
			System.out.println("알고리즘"+dto.getAlgorithm());
			System.out.println("건강"+dto.getHealth());
			System.out.println("자격증"+dto.getLicense());
			System.out.println("[1]"+dto.getDay()+"일차 진행 [2]게임종료");
			int choose = sc.nextInt();
			if(choose == 2) {
				mc.stop();
				break menuLoop;
			}
			for(int i=1; i<=2; i++) {
				System.out.println("[1]수업듣기 [2]독학하기 [3]시험보기 [4]간식먹기 [5]늦잠자기");
				int input = sc.nextInt();
				if(input==1) {
					System.out.println("수업을 듣습니다.");
					
					if(stat.listening(uId)>0) {
						System.out.println("수업을 들었습니다.");
					}
				}else if(input==2) {
					System.out.println("혼자 공부합니다.");		
					if(stat.study(uId)>0) {
						System.out.println("공부를 했습니다.");
					}
				}else if(input==3) {
					System.out.println("시험을봅니다.");								
				}else if(input==4) {
					System.out.println("간식먹기");		
					if(stat.snack(uId)>0) {
						System.out.println("간식을 먹었습니다.");
					}
				}else if(input==5) {
					System.out.println("늦잠자기");																
				}
			}
			System.out.println("하루가 지났습니다.");
			stat.dayPlus(uId);
		}
	}

}

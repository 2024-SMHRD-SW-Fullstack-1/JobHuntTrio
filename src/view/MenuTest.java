package view;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import dao.QuizDAO;
import dto.QuizDTO;
import dao.MemberDAO;
import dao.StatDAO;
import dto.MemberDTO;
import dto.StatDTO;

public class MenuTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		MemberDAO dao = new MemberDAO();
		Random random = new Random();

		String uId = null;
		System.out.println("인트로");

		while (true) {
			System.out.println("[1]로그인 [2]회원가입 [3]회원탈퇴 [4]게임종료");
			System.out.print("선택>> ");
			int input = sc.nextInt();
			if (input == 1) {
				System.out.print("ID를 입력해주세요 : ");
				String id = sc.next();
				System.out.print("PW를 입력해주세요 : ");
				String pw = sc.next();
				MemberDTO dto = new MemberDTO(id, pw);
				uId = dao.login(dto);
				if (uId != null) {
					System.out.println(uId + "님이 환영합니다.");
					break;
				} else {
					System.out.println("아이디나 비밀번호가 틀렸습니다.");
				}
			} else if (input == 2) {
				System.out.print("id를 입력해주세요 >> ");
				String id = sc.next();
				System.out.print("pw를 입력해주세요 >> ");
				String pw = sc.next();
				System.out.print("name을 입력해주세요 >> ");
				String name = sc.next();
				System.out.print("성별 남/여을 입력해주세요 >> ");
				String gender = sc.next();
				System.out.print("age를 입력해주세요 >> ");
				int age = sc.nextInt();
				MemberDTO dto = new MemberDTO(id, pw, name, gender, age);
				int row = dao.join(dto);
				if (row > 0) {
					System.out.println("가입완료");
				}
			} else if (input == 3) {
				System.out.print("id를 입력해주세요 >> ");
				String id = sc.next();
				System.out.print("pw를 입력해주세요 >> ");
				String pw = sc.next();
				MemberDTO dto = new MemberDTO(id, pw);
				int row = dao.delete(dto);
				if (row > 0) {
					System.out.println("회원탈퇴완료");
				}
			} else if (input == 4) {
				System.out.println("게임을 종료합니다.");
				break;
			} else {
				System.out.println("다른 번호를 입력해주세요.");
			}
		}

		StatDAO stat = new StatDAO();
		if (uId != null) {
			String uNick = stat.SelectInpo(uId).getNickname();
			if (uNick != null) {
				System.out.println("이전 플레이 내역이 있어 이어서 진행합니다.\n");
			} else {
				System.out.println("이전 플레이 내역이 없어 캐릭터를 생성합니다.");
				System.out.print("플레이 할 닉네임을 입력해주세요 : ");
				String nick = sc.next();
				StatDTO dto = new StatDTO(uId, nick);
				stat.createU(dto);
				System.out.println(stat.SelectInpo(uId).getNickname() + "님 갓생살기에 도전하시겠습니까?(Y/N)");
				String answer = sc.next();
				if (answer.equals("Y") || answer.equals("y")) {
					
				} else {
					System.out.println("갓생보단 잠이 최고야 ... 잠에 들었습니다.");
					uId = null;
					// game over 아스키코드
				}
			}
		}

		while (uId != null) {

			StatDTO dto = stat.SelectInpo(uId);
			System.out.println(dto.getDay() + "일차 갓생력");
			System.out.println("CS: " + dto.getCs());
			System.out.println("알고리즘" + dto.getAlgorithm());
			System.out.println("지능" + dto.getIntellect());
			System.out.println("건강" + dto.getHealth());
			System.out.println("자격증" + dto.getLicense());
			if(dto.getCs()>=30&&dto.getHealth()>=70&&dto.getAlgorithm()>=30&&dto.getLicense()>=1) {
				System.out.println(dto.getDay()+"일만에 수료");
				break;
			}
			for (int i = 1; i <= 2; i++) {
				System.out.println("[1]수업듣기 [2]독학하기 [3]시험보기 [4]간식먹기 [5]늦잠자기");
				int input = sc.nextInt();
				if (input == 1) {
					System.out.println("수업을 듣습니다.");
					if (stat.listening(uId) > 0) {
						System.out.println("수업을 들었습니다.(cs + 1)");
					}
				} else if (input == 2) {
					System.out.println("혼자 공부합니다.");
					if (stat.study(uId) > 0) {
						System.out.println("공부를 했습니다.(알고리즘 +1 체력-20");
					}
				} else if (input == 3) {
					Quiz quiz = new Quiz();
					quiz.uId = uId;

					System.out.println("시험 난이도는 학습 능력에 따라 다르게 출제됩니다");
					System.out.print("시험을 선택해주세요\n[1]자격증 시험 [2]코딩테스트\n");
					int TestSelect = sc.nextInt();
					if (TestSelect == 1) {
						System.out.println("자격증 시험을 선택하셨습니다");
						System.out.println("[1]정보처리기사 [2]sqld");
						int license = sc.nextInt();
						if (license == 1) {
							System.out.println("정보처리기사 시험을 시작하겠습니다");
							quiz.jq();
						} else if (license == 2) {
							System.out.println("sql 시험을 시작하겠습니다");
							quiz.sqld();
						}
					} else if (TestSelect == 2) {
						System.out.println("코딩 테스트를 진행합니다");
						quiz.codingtest();
					}
				} else if (input == 4) {
					System.out.println("간식먹기");
					if (stat.snack(uId) > 0) {
						System.out.println("간식을 먹었습니다.");
					}
				} else if (input == 5) {
					System.out.println("늦잠자기");
				}
			}
			System.out.println("하루가 지났습니다.");
			stat.dayPlus(uId);
		}

	}

}

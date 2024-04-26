package view;

import java.util.ArrayList;
import java.util.Scanner;

import MusicController.MusicController;
import dao.MemberDAO;
import dao.StatDAO;
import dto.MemberDTO;
import dto.StatDTO;

public class MenuTest3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MemberDAO memberDAO = new MemberDAO();
		StatDAO statDAO = new StatDAO();
		MusicController musicController = new MusicController();
		MainAscci mainAscii = new MainAscci();
		String uId = loginOrRegister(sc, memberDAO);
		

		playGame(uId, sc, statDAO, musicController, memberDAO);
	}

	private static String loginOrRegister(Scanner sc, MemberDAO memberDAO) {
		MusicController musicController = new MusicController();

		MainAscci mainAscii = new MainAscci();
		musicController.play();

		mainAscii.intro();
		mainAscii.intro2();
		String uId = null;
		
		while (uId == null) {
			System.out.println("====================================  메인 메뉴 ==================================== ");
			System.out.println("[1]로그인\t\t[2]회원가입\t\t[3]회원탈퇴\t\t[4]게임종료");
			System.out.print("선택>> ");
			int input = sc.nextInt();

			switch (input) {
			case 1:
				uId = login(sc, memberDAO);
				break;
			case 2:
				uId = register(sc, memberDAO);
				break;
			case 3:
				uId = delete(sc, memberDAO);
				break;
			case 4:
				System.out.println("게임을 종료합니다.");
				break;
			default:
				System.out.println("다른 번호를 입력해주세요.");
			}
		}
		return uId;
	}

	private static String login(Scanner sc, MemberDAO memberDAO) {
		while (true) {
			System.out.print("ID를 입력해주세요 : ");
			String id = sc.next();
			System.out.print("PW를 입력해주세요 : ");
			String pw = sc.next();
			MemberDTO dto = new MemberDTO(id, pw);
			String uId = memberDAO.login(dto);
			if (uId != null) {
				System.out.println(uId + "님이 환영합니다.");
				return uId; // 올바른 정보가 입력되었을 때 바로 반환
			} else {
				System.out.println("아이디나 비밀번호가 틀렸습니다.");
				System.out.print("다시 시도하시겠습니까? (Y/N): ");
				String retry = sc.next();
				if (!retry.equalsIgnoreCase("Y")) {
					// 사용자가 다시 시도하지 않을 경우
					return null; // null 반환하여 상위 메서드로 종료 신호 전달
				}
			}
		}
	}

	private static String register(Scanner sc, MemberDAO memberDAO) {
		while (true) {
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
			int row = memberDAO.join(dto);
			if (row > 0) {
				System.out.println("가입완료");
				return id;
			} else {
				System.out.println("회원가입에 실패했습니다.");
				System.out.print("다시 시도하시겠습니까? (Y/N): ");
				String retry = sc.next();
				if (!retry.equalsIgnoreCase("Y")) {
					// 사용자가 다시 시도하지 않을 경우
					return null; // null 반환하여 상위 메서드로 종료 신호 전달
				}
			}
		}
	}

	private static String delete(Scanner sc, MemberDAO memberDAO) {
		while (true) {
			System.out.print("id를 입력해주세요 >> ");
			String id = sc.next();
			System.out.print("pw를 입력해주세요 >> ");
			String pw = sc.next();
			MemberDTO dto = new MemberDTO(id, pw);

			int row = memberDAO.delete(dto);
			if (row > 0) {
				System.out.println("회원탈퇴완료");
				return null; // 회원탈퇴가 완료되면 메인 메뉴로 돌아감
			} else {
				System.out.println("아이디나 비밀번호가 틀렸습니다.");
				System.out.print("다시 시도하시겠습니까? (Y/N): ");
				String retry = sc.next();
				if (!retry.equalsIgnoreCase("Y")) {
					// 사용자가 다시 시도하지 않을 경우
					return null; // null 반환하여 상위 메서드로 종료 신호 전달
				}
			}
		}
	}

	private static void playGame(String uId, Scanner sc, StatDAO statDAO, MusicController musicController,
			MemberDAO dao) {
		MainAscci mainAscii = new MainAscci();

		// 플레이어의 게임 상태 확인
		if (uId != null) {
			String uNick = statDAO.SelectInpo(uId).getNickname();
			if (uNick != null) {
				System.out.println("이전 플레이 내역이 있어 이어서 진행합니다.");
			} else {
				System.out.println("이전 플레이 내역이 없어 캐릭터를 생성합니다.");
				System.out.print("닉네임 입력 : ");
				String nick = sc.next();
				StatDTO dto = new StatDTO(uId, nick);
				statDAO.createU(dto);
				System.out.println(statDAO.SelectInpo(uId).getNickname() + "님 갓생살기에 도전하시겠습니까?(Y/N)");
				String answer = sc.next();
				if (answer.equals("Y") || answer.equals("y")) {
					mainAscii.gameIntro();


				} else {
					System.out.println("갓생보단 잠이 최고야 ... 잠에 들었습니다.");
					uId = null;
					mainAscii.gameover();
				}
			}
		}
		while (uId != null) {
			StatDTO dto = statDAO.SelectInpo(uId);
			if (dto.getHealth() <= 0) {
				System.out.println("과로사로 수료하지 못 했습니다.");
				musicController.stop();
				mainAscii.gameover();
				int row = dao.gameover(uId);
				break;
			}
			if (statDAO.SelectInpo(uId).getAlgorithm() >= 70 && statDAO.SelectInpo(uId).getCs() >= 40) {

				System.out.println(dto.getDay() + "일차만에 수료 성공! " + statDAO.SelectInpo(uId).getNickname() + "님의 앞 날을 응원합니다!");
				mainAscii.gameclear();
				
				ArrayList<StatDTO> rank = statDAO.rank();
				for(int i=0; i<rank.size(); i++) {
					System.out.println((i+1)+"위 \t"+rank.get(i).getNickname()+"님"+rank.get(i).getDay()+"일만에 수료!");
				}
				break;
			}

			printPlayerStats(dto);
			System.out.println("[1]" + dto.getDay() + "일차 진행  [2]저장하고 게임종료하기");
			int choose = sc.nextInt();


			if (choose == 2) {
				musicController.stop();
				break;
			} 

			processPlayerActions(sc, statDAO, uId);
			System.out.println("하루가 지났습니다.");
			statDAO.dayPlus(uId);
		}
	}

	private static void printPlayerStats(StatDTO dto) {

		System.out.println(dto.getDay() + "일차 갓생력");
		System.out.println("CS: " + dto.getCs());
		System.out.println("알고리즘" + dto.getAlgorithm());
		System.out.println("건강" + dto.getHealth());
		System.out.println("자격증" + dto.getLicense());
	}

	private static void processPlayerActions(Scanner sc, StatDAO statDAO, String uId) {
		MainAscci mainAscii = new MainAscci();

		for (int i = 1; i <= 2; i++) {
			System.out.println("[1]수업듣기 [2]코딩테스트 [3]시험보기 [4]간식먹기 [5]늦잠자기");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				mainAscii.study();
				takeClass(statDAO, uId);
				break;
			case 2:
				mainAscii.ct();
				ct(statDAO, sc, uId);
				break;
			case 3:
				mainAscii.test();
				i = takeExam(sc, uId, statDAO, i);
				break;
			case 4:
				mainAscii.snack();
				eatSnack(statDAO, uId);
				break;
			case 5:
				mainAscii.sleep();
				sleepLate(statDAO, uId);
				break;
			default:
				System.out.println("올바른 선택을 해주세요.");
			}
		}
	}

	private static void takeClass(StatDAO statDAO, String uId) {
		System.out.println("수업을 듣습니다.");
		if (statDAO.listening(uId) > 0) {
			System.out.println("수업을 들었습니다.(cs + 10)");
		}
	}

	private static void ct(StatDAO statDAO, Scanner sc, String uId) {
		Quiz quiz = new Quiz();

		System.out.println("시험 난이도는 학습 능력에 따라 다르게 출제됩니다");
		System.out.println("코딩 테스트를 진행합니다");
		quiz.codingtest(uId);
	}

	private static int takeExam(Scanner sc, String uId, StatDAO statDAO, int i) {
		Quiz quiz = new Quiz();

		System.out.println("시험을 선택해주세요");
		System.out.println("[1]정보처리기사 [2]SQLD");
		int license = sc.nextInt();
		if (license == 1) {
			if (statDAO.SelectInpo(uId).getAlgorithm() < 40) {
				System.out.println("코딩테스트을 더 공부하세요");
				i -= 1;
			} else {

				System.out.println("정보처리기사 시험을 시작하겠습니다");
				System.out.println("아.. 조금만 더 보고올걸 \n");
				quiz.jq(uId);
			}
		} else if (license == 2) {
			if (statDAO.SelectInpo(uId).getCs() < 40) {
				System.out.println("수업을 더 듣고오세요");
				i -= 1;
			} else {
				System.out.println("SQLD 시험을 시작하겠습니다");
				System.out.println("후...떨린다 ... \n");
				quiz.sqld(uId);
			}
		} else {
			System.out.println("올바른 선택을 해주세요.");
		}
		return i;
	}

	private static void eatSnack(StatDAO statDAO, String uId) {
		System.out.println("간식을 먹습니다.");
		if (statDAO.snack(uId) > 0) {
			System.out.println("달고다디단 밤양갱을 먹었습니다~ (체력 +20)");
		}
	}

	private static void sleepLate(StatDAO statDAO, String uId) {
		System.out.println("늦게 자기");
		if (statDAO.sleepLate(uId) > 0) {
			System.out.println("아 유튜브 조금만 더 보고 자야겠다...(체력 -20)");
		}
	}

}
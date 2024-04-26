package view;

import java.util.Scanner;

import MusicController.MusicController;
import dao.MemberDAO;
import dao.StatDAO;
import dto.MemberDTO;
import dto.StatDTO;

public class MenuTest2 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MemberDAO memberDAO = new MemberDAO();
        StatDAO statDAO = new StatDAO();
        MusicController musicController = new MusicController();
        MainAscci mainAscii = new MainAscci();
        String uId = loginOrRegister(sc, memberDAO);
        if (uId == null) {
            System.out.println("로그인 또는 회원가입에 실패했습니다. 프로그램을 종료합니다.");
            return;
        }
        mainAscii.intro();
        playGame(uId, sc, statDAO, musicController);
    }

    private static String loginOrRegister(Scanner sc, MemberDAO memberDAO) {
        while (true) {
            System.out.println("[1]로그인\t\t[2]회원가입\t\t[3]회원탈퇴\t\t[4]게임종료");
            System.out.print("선택>> ");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    return login(sc, memberDAO);
                case 2:
                    return register(sc, memberDAO);
                case 3:
                    System.out.println("회원탈퇴완료");
                    break;
                case 4:
                    System.out.println("게임을 종료합니다.");
                    return null;
                default:
                    System.out.println("다른 번호를 입력해주세요.");
            }
        }
    }

    private static String login(Scanner sc, MemberDAO memberDAO) {
        System.out.print("ID입력: ");
        String id = sc.next();
        System.out.print("PW입력: ");
        String pw = sc.next();
        MemberDTO dto = new MemberDTO(id, pw);
        String uId = memberDAO.login(dto);
        if (uId != null) {
            System.out.println(uId + "님이 로그인하셨습니다.");
        } else {
            System.out.println("아이디나 비밀번호가 틀렸습니다.");
        }
        return uId;
    }

    private static String register(Scanner sc, MemberDAO memberDAO) {
        System.out.print("id: ");
        String id = sc.next();
        System.out.print("pw: ");
        String pw = sc.next();
        System.out.print("name: ");
        String name = sc.next();
        System.out.print("성별 남/여: ");
        String gender = sc.next();
        System.out.print("age: ");
        int age = sc.nextInt();
        MemberDTO dto = new MemberDTO(id, pw, name, gender, age);
        int row = memberDAO.join(dto);
        if (row > 0) {
            System.out.println("가입완료");
            return id;
        } else {
            System.out.println("회원가입에 실패했습니다.");
            return null;
        }
    }

    private static void playGame(String uId, Scanner sc, StatDAO statDAO, MusicController musicController) {
        // 플레이어의 게임 상태 확인
        if (uId != null) {
            String uNick = statDAO.SelectInpo(uId).getNickname();
            if (uNick != null) {
                System.out.println("이전 플레이 내역이 있어 이어서 진행합니다.");
            } else {
                System.out.println("이전 플레이 내역이 없어 캐릭터를 생성합니다.");
                System.out.println("닉네임 입력 : ");
                String nick = sc.next();
                StatDTO dto = new StatDTO(uId, nick);
                statDAO.createU(dto);
            }
        }
        musicController.play();
        while (uId != null) {
            StatDTO dto = statDAO.SelectInpo(uId);
            printPlayerStats(dto);
            System.out.println("[1]" + dto.getDay() + "일차 진행 [2]수료하기 [3]게임종료");
            int choose = sc.nextInt();
            if (choose == 3) {
                musicController.stop();
                loginOrRegister(sc, null);
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
        for (int i = 1; i <= 2; i++) {
            System.out.println("[1]수업듣기 [2]독학하기 [3]시험보기 [4]간식먹기 [5]늦잠자기");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    takeClass(statDAO, uId);
                    break;
                case 2:
                    studyAlone(statDAO, uId);
                    break;
                case 3:
                    takeExam(sc, uId);
                    break;
                case 4:
                    eatSnack(statDAO, uId);
                    break;
                case 5:
                    sleepLate();
                    break;
                default:
                    System.out.println("올바른 선택을 해주세요.");
            }
        }
    }

    private static void takeClass(StatDAO statDAO, String uId) {
        System.out.println("수업을 듣습니다.");
        if (statDAO.listening(uId) > 0) {
            System.out.println("수업을 들었습니다.");
        }
    }

    private static void studyAlone(StatDAO statDAO, String uId) {
        System.out.println("혼자 공부합니다.");
        if (statDAO.study(uId) > 0) {
            System.out.println("공부를 했습니다.");
        }
    }

    private static void takeExam(Scanner sc, String uId) {
        Quiz quiz = new Quiz();

        System.out.println("시험 난이도는 학습 능력에 따라 다르게 출제됩니다");
        System.out.print("시험을 선택해주세요\n[1]자격증 시험 [2]코딩테스트\n");
        int testSelect = sc.nextInt();
        if (testSelect == 1) {
            System.out.println("자격증 시험을 선택하셨습니다");
            System.out.println("[1]정보처리기사 [2]SQLD");
            int license = sc.nextInt();
            if (license == 1) {
                System.out.println("정보처리기사 시험을 시작하겠습니다");
                quiz.jq();
            } else if (license == 2) {
                System.out.println("SQLD 시험을 시작하겠습니다");
                quiz.sqld();
            } else {
                System.out.println("올바른 선택을 해주세요.");
            }
        } else if (testSelect == 2) {
            System.out.println("코딩 테스트를 진행합니다");
            quiz.codingtest();
        } else {
            System.out.println("올바른 선택을 해주세요.");
        }
    }

    private static void eatSnack(StatDAO statDAO, String uId) {
        System.out.println("간식을 먹습니다.");
        if (statDAO.snack(uId) > 0) {
            System.out.println("간식을 먹었습니다.");
        }
    }

    private static void sleepLate() {
        System.out.println("늦게 자기");
        // 늦게 자는 로직 구현
    }
}
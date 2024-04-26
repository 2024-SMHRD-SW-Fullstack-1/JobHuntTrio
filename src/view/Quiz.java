package view;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import dao.MemberDAO;
import dao.QuizDAO;
import dao.StatDAO;
import dto.QuizDTO;
import dto.StatDTO;

public class Quiz {
	private QuizDAO quizDAO = new QuizDAO();
	private StatDAO stat = new StatDAO();
	String uId;

	public void jq(String uId) {
		List<QuizDTO> quizzes = selectQuizzesForjq();
		processQuiz(quizzes, uId);
	}

	public void sqld(String uId) {
		List<QuizDTO> quizzes = selectQuizzesForSQLD();
		processQuiz(quizzes, uId);
	}
	
	public void codingtest(String uId) {
		List<QuizDTO> quizzes = selectQuizzesForCODDINGTEST();
		processQuiz2(quizzes, uId);
	}

	private List<QuizDTO> selectQuizzesForjq() {
		StatDTO dto = stat.SelectInpo(uId);
		return quizDAO.readQuizzesFromFile("quiz/jt1.txt");
	}

	private List<QuizDTO> selectQuizzesForSQLD() {
		StatDTO dto = stat.SelectInpo(uId);
			return quizDAO.readQuizzesFromFile("quiz/sql1.txt");
	}
	
	private List<QuizDTO> selectQuizzesForCODDINGTEST() {
		return quizDAO.readQuizzesFromFile("quiz/ct1.txt");
	}

	private void processQuiz(List<QuizDTO> quizzes, String uId) {
		if (quizzes == null) {
			System.out.println("퀴즈 파일을 불러올 수 없습니다.");
			return;
		}

		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		StatDTO dto = stat.SelectInpo(uId);

		QuizDTO randomQuiz = quizzes.get(random.nextInt(quizzes.size()));

		System.out.println("문제: " + randomQuiz.getQuestion());
		for (int j = 0; j < randomQuiz.getOptions().length; j++) {
			System.out.println((j + 1) + ". " + randomQuiz.getOptions()[j]);
		}
		System.out.print("정답 : ");
		int choice = sc.nextInt();
		if (choice == randomQuiz.getCorrectOption() + 1) {
			stat.license(uId);
			System.out.println("시험에 합격하셨습니다!");
		} else {
			System.out.println("시험에 불합격하셨습니다ㅠㅠ");
		}
	}
	
	private void processQuiz2(List<QuizDTO> quizzes, String uId) {
		if (quizzes == null) {
			System.out.println("퀴즈 파일을 불러올 수 없습니다.");
			return;
		}

		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		StatDTO dto = stat.SelectInpo(uId);
		StatDAO statDAO = new StatDAO();

		QuizDTO randomQuiz = quizzes.get(random.nextInt(quizzes.size()));

		System.out.println("문제: " + randomQuiz.getQuestion());
		for (int j = 0; j < randomQuiz.getOptions().length; j++) {
			System.out.println((j + 1) + ". " + randomQuiz.getOptions()[j]);
		}
		System.out.print("정답 : ");
		int choice = sc.nextInt();
		if (choice == randomQuiz.getCorrectOption() + 1) {
			statDAO.study(uId);
			System.out.println("코딩테스트에 통과하셨습니다! (알고리즘 + 10, 건강 - 20)");
		} else {
			System.out.println("코딩테스트를 틀렸습니다 .. ");
		}
	}
}
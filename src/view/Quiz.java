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

	public void jq() {
		List<QuizDTO> quizzes = selectQuizzesForAlgorithm();
		processQuiz(quizzes);
	}

	public void sqld() {
		List<QuizDTO> quizzes = selectQuizzesForSQLD();
		processQuiz(quizzes);
	}

	private List<QuizDTO> selectQuizzesForAlgorithm() {
		StatDTO dto = stat.SelectInpo(uId);
		if (dto.getAlgorithm() <= 30 && dto.getCs() <= 30) {
			return quizDAO.readQuizzesFromFile("C:\\Users\\SMHRD\\Desktop\\미니프로젝트 퀴즈\\jt1.txt");
		} else if (dto.getAlgorithm() > 30 && dto.getAlgorithm() <= 60 && dto.getCs() > 30 && dto.getCs() <= 60) {
			return quizDAO.readQuizzesFromFile("C:\\Users\\SMHRD\\Desktop\\미니프로젝트 퀴즈\\jt2.txt");
		} else if (dto.getAlgorithm() > 60 && dto.getCs() > 60) {
			return quizDAO.readQuizzesFromFile("C:\\Users\\SMHRD\\Desktop\\미니프로젝트 퀴즈\\jt3.txt");
		}
		return null;
	}

	private List<QuizDTO> selectQuizzesForSQLD() {
		StatDTO dto = stat.SelectInpo(uId);
		if (dto.getIntellect() < 30 && dto.getCs() < 30) {
			return quizDAO.readQuizzesFromFile("C:\\Users\\SMHRD\\Desktop\\미니프로젝트 퀴즈\\sql1.txt");
		} else if (dto.getIntellect() <= 60 && dto.getCs() <= 60) {
			return quizDAO.readQuizzesFromFile("C:\\Users\\SMHRD\\Desktop\\미니프로젝트 퀴즈\\sql2.txt");
		} else if (dto.getIntellect() > 60 && dto.getCs() > 60) {
			return quizDAO.readQuizzesFromFile("C:\\Users\\SMHRD\\Desktop\\미니프로젝트 퀴즈\\sql3.txt");
		}
		return null;
	}

	private void processQuiz(List<QuizDTO> quizzes) {
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
}
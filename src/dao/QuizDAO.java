package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dto.QuizDTO;

public class QuizDAO {
	public List<QuizDTO> readQuizzesFromFile(String filename) {
		List<QuizDTO> quizzes = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String question = line;
				String[] options = new String[4];
				for (int i = 0; i < 4; i++) {
					options[i] = br.readLine();
				}
				int correctOption = Integer.parseInt(br.readLine()) - 1; // 인덱스는 0부터 시작하므로 1을 빼줌
				quizzes.add(new QuizDTO(question, options, correctOption));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return quizzes;
	}
}

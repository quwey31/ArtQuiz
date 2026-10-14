package quiz;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InMemoryQuestionStorage implements QuestionStorage {
    private List<Question> questions;
    private int currentQuestion = 0;

    public InMemoryQuestionStorage() {
        questions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("questions.txt"));

            for (String line : lines) {
                if (line.contains("|")) {
                    String[] parts = line.split("\\s*\\|\\s*", 2);
                    String questionText = parts[0].trim();
                    String answer = parts[1].trim();
                    questions.add(new Question(questionText, answer));
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении questions.txt: " + e.getMessage());
        }
    }

    @Override
    public Question getQuestion() {
        Question question = questions.get(currentQuestion);
        currentQuestion++;
        if (currentQuestion >= questions.size()) {
            currentQuestion = 0;
        }
        return question;
    }

    @Override
    public int size() {
        return questions.size();
    }
}
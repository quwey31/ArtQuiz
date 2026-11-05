package quiz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InMemoryQuestionStorage implements QuestionStorage {
    private List<Question> questions;
    private int currentQuestion = 0;

    public InMemoryQuestionStorage() {
        questions = new ArrayList<>();
        loadQuestions("easy"); // по умолчанию грузим легкие
    }

    @Override
    public void loadQuestions(String difficulty) {
        questions.clear();
        currentQuestion = 0;

        String fileName = switch (difficulty.toLowerCase()) {
            case "medium" -> "questions_medium.txt";
            case "hard" -> "questions_hard.txt";
            default -> "questions_easy.txt";
        };

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                if (line.contains("|")) {
                    String[] parts = line.split("\\s*\\|\\s*", 2);
                    questions.add(new Question(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении " + fileName + ": " + e.getMessage());
        }
    }

    @Override
    public Question getQuestion() {
        if (questions.isEmpty()) {
            return new Question("Вопросы не загружены", "Нет ответа");
        }
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

    @Override
    public List<Question> getAllQuestions() {
        return questions;
    }

    public String getQuestionsAsJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(questions);
    }
}
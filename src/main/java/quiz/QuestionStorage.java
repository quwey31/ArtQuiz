package quiz;

import java.util.List;

public interface QuestionStorage {
    Question getQuestion();
    int size();
    void loadQuestions(String difficulty);
    List<Question> getAllQuestions();
}

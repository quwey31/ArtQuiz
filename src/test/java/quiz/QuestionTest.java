package quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {

    @Test
    void getQuestionReturnsQuestion() {
        Question question = new Question("Сколько будет 2 + 2?", "4");

        assertEquals("Сколько будет 2 + 2?", question.getQuestion());
    }

    @Test
    void getAnswerReturnsAnswer() {
        Question question = new Question("Сколько будет 2 + 2?", "4");

        assertEquals("4", question.getAnswer());
    }
}

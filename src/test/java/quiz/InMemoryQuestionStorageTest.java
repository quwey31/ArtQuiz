package quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryQuestionStorageTest {

    @Test
    void getQuestionReturnsFirstQuestion() {
        QuestionStorage storage = new InMemoryQuestionStorage();

        Question question = storage.getQuestion();

        assertEquals("Сколько будет 2 + 2?", question.getQuestion());
        assertEquals("4", question.getAnswer());
    }

    @Test
    void getQuestionReturnsQuestionsInOrder() {
        QuestionStorage storage = new InMemoryQuestionStorage();

        Question firstQuestion = storage.getQuestion();
        Question secondQuestion = storage.getQuestion();
        Question thirdQuestion = storage.getQuestion();
        Question fourthQuestion = storage.getQuestion();

        assertEquals("Сколько будет 2 + 2?", firstQuestion.getQuestion());
        assertEquals("Столица Франции?", secondQuestion.getQuestion());
        assertEquals("Сколько планет в Солнечной системе?", thirdQuestion.getQuestion());
        assertEquals("Какой язык программирования мы изучаем?", fourthQuestion.getQuestion());
    }

    @Test
    void afterLastQuestionStorageReturnsFirstQuestion() {
        QuestionStorage storage = new InMemoryQuestionStorage();

        storage.getQuestion();
        storage.getQuestion();
        storage.getQuestion();
        storage.getQuestion();

        Question question = storage.getQuestion();

        assertEquals("Сколько будет 2 + 2?", question.getQuestion());
    }
}
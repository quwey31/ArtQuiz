package quiz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryQuestionStorageTest {

    @Test
    void loadQuestionsSwitchesDifficulty() {
        InMemoryQuestionStorage storage = new InMemoryQuestionStorage();

        storage.loadQuestions("easy");
        int easySize = storage.size();
        assertTrue(easySize > 0, "Легкий уровень не должен быть пустым");

        storage.loadQuestions("hard");
        int hardSize = storage.size();
        assertTrue(hardSize > 0, "Сложный уровень не должен быть пустым");

                Question easyQ = new InMemoryQuestionStorage().getQuestion(); // По умолчанию easy
        storage.loadQuestions("hard");
        Question hardQ = storage.getQuestion();

        assertNotEquals(easyQ.getQuestion(), hardQ.getQuestion(), "Вопросы разных уровней не должны совпадать");
    }

    @Test
    void getQuestionReturnsQuestionsInOrderAndLoops() {
        InMemoryQuestionStorage storage = new InMemoryQuestionStorage();
        storage.loadQuestions("easy");

        int total = storage.size();
        assertTrue(total > 1, "Для теста нужно хотя бы 2 вопроса в easy.txt");

        Question firstQuestion = storage.getQuestion();

        for (int i = 1; i < total; i++) {
            storage.getQuestion();
        }

        // После последнего должен снова пойти первый (цикл замкнулся)
        Question afterLast = storage.getQuestion();
        assertEquals(firstQuestion.getQuestion(), afterLast.getQuestion(), "После последнего вопроса должен вернуться первый");
    }

    @Test
    void getQuestionsAsJsonReturnsCorrectFormat() {
        InMemoryQuestionStorage storage = new InMemoryQuestionStorage();
        storage.loadQuestions("easy");

        String json = storage.getQuestionsAsJson();

        assertTrue(json.startsWith("["), "JSON должен начинаться с квадратной скобки");
        assertTrue(json.endsWith("]"), "JSON должен заканчиваться квадратной скобкой");

        assertTrue(json.contains("\"question\":"), "В JSON должно быть поле question");
        assertTrue(json.contains("\"answer\":"), "В JSON должно быть поле answer");
    }
}
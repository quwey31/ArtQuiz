package quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {

    @Test
    void getQuestionReturnsQuestion() {
        Question question = new Question("На полотне изображены четыре бурых медвежонка, которые резвятся на поваленном дереве в густом сосновом бору.\" +\n" +
                "                        \" Сквозь утренний туман пробиваются первые лучи солнца, освещая лесную чащу.\"", "Утро в сосновом лесу");

        assertEquals("На полотне изображены четыре бурых медвежонка, которые резвятся на поваленном дереве в густом сосновом бору.\" +\n" +
                "                        \" Сквозь утренний туман пробиваются первые лучи солнца, освещая лесную чащу.\"", question.getQuestion());
    }

    @Test
    void getAnswerReturnsAnswer() {
        Question question = new Question("На полотне изображены четыре бурых медвежонка, которые резвятся на поваленном дереве в густом сосновом бору.\" +\n" +
                "                        \" Сквозь утренний туман пробиваются первые лучи солнца, освещая лесную чащу.\"", "Утро в сосновом лесу");

        assertEquals("Утро в сосновом лесу", question.getAnswer());
    }
}
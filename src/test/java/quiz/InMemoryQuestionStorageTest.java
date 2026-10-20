package quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryQuestionStorageTest {

    @Test
    void getQuestionReturnsFirstQuestion() {
        QuestionStorage storage = new InMemoryQuestionStorage();

        Question question = storage.getQuestion();

        assertEquals("На полотне изображены четыре бурых медвежонка, которые резвятся на поваленном дереве в густом сосновом бору." +
                " Сквозь утренний туман пробиваются первые лучи солнца, освещая лесную чащу.", question.getQuestion());
        assertEquals("Утро в сосновом лесу", question.getAnswer());
    }

    @Test
    void getQuestionReturnsQuestionsInOrder() {
        QuestionStorage storage = new InMemoryQuestionStorage();

        Question firstQuestion = storage.getQuestion();
        Question secondQuestion = storage.getQuestion();
        Question thirdQuestion = storage.getQuestion();
        Question fourthQuestion = storage.getQuestion();

        assertEquals("На полотне изображены четыре бурых медвежонка, которые резвятся на поваленном дереве в густом сосновом бору." +
                " Сквозь утренний туман пробиваются первые лучи солнца, освещая лесную чащу.", firstQuestion.getQuestion());
        assertEquals("Огромная морская волна нависает над обломком мачты, за который из последних сил держатся несколько потерпевших" +
                        " кораблекрушение моряков. На горизонте занимается рассвет, дающий людям слабую надежду на спасение.", secondQuestion.getQuestion());
        assertEquals("Три могучих всадника в доспехах на конях стоят на страже русской земли, внимательно всматриваясь вдаль. За их " +
                        "спинами — холмистая степь и грозовое небо, а под ногами шумит ковыль.", thirdQuestion.getQuestion());
        assertEquals("На картине изображена молодая женщина в элегантном тёмном платье и шляпе с пером, сидящая в коляске у ресторана." +
                        " Её лицо выражает надменность и загадочность, а взгляд устремлён прямо на зрителя.",  fourthQuestion.getQuestion());
    }

    @Test
    void afterLastQuestionStorageReturnsFirstQuestion() {
        QuestionStorage storage = new InMemoryQuestionStorage();

        int totalQuestions = storage.size(); // ← узнаем из хранилища

        for (int i = 0; i < totalQuestions; i++) {
            storage.getQuestion();
        }

        Question question = storage.getQuestion();

        assertEquals("На полотне изображены четыре бурых медвежонка, которые резвятся на поваленном дереве в густом сосновом бору." +
                " Сквозь утренний туман пробиваются первые лучи солнца, освещая лесную чащу.", question.getQuestion());
    }
}
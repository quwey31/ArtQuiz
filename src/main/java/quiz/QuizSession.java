package quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizSession {
    private List<Question> pendingQuestions;   // вопросы, которые еще не задавали
    private final List<Question> answeredQuestions; // вопросы, на которые уже ответили
    private Question currentQuestion;
    private boolean isPlaying = false;

    public QuizSession(List<Question> questions) {
        this.pendingQuestions = new ArrayList<>(questions);
        this.answeredQuestions = new ArrayList<>();
    }

    public String startGame() {
        isPlaying = true;
        pendingQuestions.addAll(answeredQuestions);
        answeredQuestions.clear();

        if (pendingQuestions.isEmpty()) {
            return "Нет доступных вопросов для игры.";
        }

        currentQuestion = pendingQuestions.remove(0);
        return "Привет! Я ArtQuizBot.\n" +
                "Мои команды: /help, /skip, /easy, /medium, /hard, /exit, /art.\n" +
                "Вопрос:\n" + currentQuestion.getQuestion();
    }

    public String stopGame() {
        isPlaying = false;
        return "До свидания!";
    }

    public String getHelp() {
        return "Справка:\n" +
                "/help - эта справка\n" +
                "/skip - пропустить вопрос\n" +
                "/art - случайная картина из Метрополитен-музея Нью-Йорка\n" +
                "/easy, /medium, /hard - уровни сложности\n" +
                "/exit - выход\n\n" +
                "Текущий вопрос:\n" + (currentQuestion != null ? currentQuestion.getQuestion() : "Игра не запущена");
    }

    public String skipQuestion() {
        if (!isPlaying || currentQuestion == null) {
            return "Игра не запущена. Напишите /start.";
        }

        pendingQuestions.add(currentQuestion); // возвращаем в конец

        if (pendingQuestions.isEmpty()) {
            return "Вопросы закончились! Вы молодец!";
        }

        currentQuestion = pendingQuestions.remove(0);
        return "Вопрос пропущен.\n\nСледующий вопрос:\n" + currentQuestion.getQuestion();
    }

    public String handleAnswer(String userAnswer) {
        if (!isPlaying || currentQuestion == null) {
            return "Игра не запущена. Напишите /start.";
        }

        if (userAnswer.equalsIgnoreCase(currentQuestion.getAnswer())) {
            answeredQuestions.add(currentQuestion);

            if (pendingQuestions.isEmpty()) {
                isPlaying = false;
                return "Молодец! Вы ответили на все вопросы!";
            }

            currentQuestion = pendingQuestions.remove(0);
            return "Молодец!\n\nСледующий вопрос:\n" + currentQuestion.getQuestion();
        } else {
            return "Неправильно! Попробуй ещё раз.\n" + currentQuestion.getQuestion();
        }
    }

    // смена уровня слодности
    public String changeLevel(List<Question> newQuestions) {
        this.pendingQuestions = new ArrayList<>(newQuestions);
        this.answeredQuestions.clear();
        this.isPlaying = false;
        this.currentQuestion = null;
        return "Уровень изменен! Напишите /start, чтобы начать.";
    }

    public String handleMessage(String message) {
        if (message.equals("/start")) return startGame();
        if (message.equals("/help")) return getHelp();
        if (message.equals("/skip")) return skipQuestion();
        if (message.equals("/exit")) return stopGame();
        return handleAnswer(message);
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
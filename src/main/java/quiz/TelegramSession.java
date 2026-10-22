package quiz;

public class TelegramSession {
    private final QuestionStorage storage;
    private Question currentQuestion;
    private boolean isPlaying = false;

    public TelegramSession(QuestionStorage storage) {
        this.storage = storage;
    }

    public String startGame() {
        isPlaying = true;
        currentQuestion = storage.getQuestion();
        return "Привет! Я ArtQuizBot.\n" +
                "Я задаю вопросы и проверяю твои ответы.\n" +
                "Команда /help — показать информацию.\n" +
                "Команда /exit — завершить работу.\n\n" +
                "Вопрос:\n" + currentQuestion.getQuestion();
    }

    public String stopGame() {
        isPlaying = false;
        return "До свидания!";
    }

    public String getHelp() {
        return "Я QuizBot.\n" +
                "Я задаю вопросы и проверяю твои ответы.\n" +
                "/help — показать эту информацию.\n" +
                "/exit — завершить программу.\n\n" +
                "Текущий вопрос:\n" + (currentQuestion != null ? currentQuestion.getQuestion() : "Игра не запущена");
    }

    public String handleAnswer(String userAnswer) {
        if (!isPlaying || currentQuestion == null) {
            return "Игра не запущена. Напишите /start, чтобы начать.";
        }

        if (userAnswer.equalsIgnoreCase(currentQuestion.getAnswer())) {
            currentQuestion = storage.getQuestion();
            return "Молодец!\n\nСледующий вопрос:\n" + currentQuestion.getQuestion();
        } else {
            return "Неправильно! Попробуй ещё раз.\n" + currentQuestion.getQuestion();
        }
    }
}
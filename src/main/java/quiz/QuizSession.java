package quiz;

public class QuizSession {
    private final QuestionStorage storage;
    private Question currentQuestion;
    private boolean isPlaying = false;

    public QuizSession(QuestionStorage storage) {
        this.storage = storage;
    }

    public String startGame() {
        isPlaying = true;
        currentQuestion = storage.getQuestion();
        return getHelp();
    }

    public String stopGame() {
        isPlaying = false;
        return "До свидания!";
    }

    public String getHelp() {
        String text = "Привет! Я ArtQuizBot.\n" +
                "Я задаю вопросы и проверяю твои ответы.\n" +
                "Я умею:\n" +
                "/help - справка\n" +
                "/skip - пропустить вопрос\n" +
                "/easy, /medium, /hard - выбрать уровень\n" +
                "/art - показать картину\n" +
                "/exit - выйти\n\n";
        if (isPlaying && currentQuestion != null) {
            text += "Текущий вопрос:\n" + currentQuestion.getQuestion();
        } else {
            text += "Игра не запущена. Напишите /start.";
        }
        return text;
    }

    public String skipQuestion() {
        if (!isPlaying || currentQuestion == null) {
            return "Игра не запущена. Напишите /start.";
        }
        currentQuestion = storage.getQuestion();
        return "Вопрос пропущен.\n\nСледующий вопрос:\n" + currentQuestion.getQuestion();
    }

    public String handleAnswer(String userAnswer) {
        if (!isPlaying || currentQuestion == null) {
            return "Игра не запущена. Напишите /start.";
        }

        if (userAnswer.equalsIgnoreCase(currentQuestion.getAnswer())) {
            currentQuestion = storage.getQuestion();
            return "Молодец!\n\nСледующий вопрос:\n" + currentQuestion.getQuestion();
        } else {
            return "Неправильно! Попробуй ещё раз.\n" + currentQuestion.getQuestion();
        }
    }

    public String changeLevel(String level) {
        storage.loadQuestions(level);
        if (isPlaying) {
            currentQuestion = storage.getQuestion();
            return "Уровень изменен на " + level + ".\n\nПервый вопрос:\n" + currentQuestion.getQuestion();
        } else {
            return "Уровень изменен на " + level + ". Напишите /start, чтобы начать.";
        }
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }
}
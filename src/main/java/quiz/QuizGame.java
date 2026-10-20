package quiz;

public class QuizGame {
    private final QuestionStorage storage;
    private final ConsoleView view;

    public QuizGame(QuestionStorage storage, ConsoleView view) {
        this.storage = storage;
        this.view = view;
    }

    public void start() {
        view.sentMessage(printWelcome());

        while (true) {
            Question question = storage.getQuestion();
            view.sentMessage(question.getQuestion());

            boolean answered = askUntilCorrect(question);
            if (!answered) {
                return;
            }
        }
    }

    private boolean askUntilCorrect(Question question) {
        while (true) {
            String response = view.readLine();

            if (response.equalsIgnoreCase("/help")) {
                view.sentMessage(printWelcome());
                view.sentMessage(question.getQuestion());
                continue;
            }

            if (response.equalsIgnoreCase("/exit")) {
                view.sentMessage(printGoodbye());
                view.close();
                return false;
            }

            if (response.equalsIgnoreCase(question.getAnswer())) {
                view.sentMessage(printCorrect());
                return true;
            } else {
                view.sentMessage(printWrong());
            }
        }
    }
    private String printWelcome() {
        return "Привет! Я ArtQuizBot.\n Я задаю вопросы и проверяю твои ответы.\n Команда /help — показать информацию обо мне.\n Команда /exit — завершить работу.\n";
    }

    private String printCorrect() {
        return "Молодец!";
    }

    private String printWrong() {
        return "Неправильно! Попробуй ещё раз.";
    }

    private String printGoodbye() {
        return "До свидания!";
    }
}
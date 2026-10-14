package quiz;

public class QuizGame {
    private final QuestionStorage storage;
    private final ConsoleView view;

    public QuizGame(QuestionStorage storage, ConsoleView view) {
        this.storage = storage;
        this.view = view;
    }

    public void start() {
        view.printWelcome();

        while (true) {
            Question question = storage.getQuestion();
            view.printQuestion(question.getQuestion());

            boolean answered = askUntilCorrect(question);
            if (!answered) {
                return; // пользователь ввел /exit
            }
        }
    }

    private boolean askUntilCorrect(Question question) {
        while (true) {
            String response = view.readLine();

            if (response.equalsIgnoreCase("/help")) {
                view.printWelcome();
                view.printQuestion(question.getQuestion());
                continue;
            }

            if (response.equalsIgnoreCase("/exit")) {
                view.printGoodbye();
                view.close();
                return false;
            }

            if (response.equalsIgnoreCase(question.getAnswer())) {
                view.printCorrect();
                return true;
            } else {
                view.printWrong();
            }
        }
    }
}
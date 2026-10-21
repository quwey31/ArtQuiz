package quiz;

public class ConsoleApp {
    public void start() {
        QuestionStorage storage = new InMemoryQuestionStorage();
        ConsoleView view = new ConsoleView();
        QuizGame game = new QuizGame(storage, view);
        game.start();
    }
}
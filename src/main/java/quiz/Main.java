package quiz;

public class Main {
    public static void main(String[] args) {
        QuestionStorage storage = new InMemoryQuestionStorage();
        ConsoleView view = new ConsoleView();
        QuizGame game = new QuizGame(storage, view);

        game.start();
    }
}
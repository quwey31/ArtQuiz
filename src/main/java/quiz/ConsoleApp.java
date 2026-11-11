package quiz;

import java.util.Scanner;

public class ConsoleApp {
    public void start() {
        QuestionStorage storage = new InMemoryQuestionStorage();
        QuizSession session = new QuizSession(storage);
        Scanner scanner = new Scanner(System.in);

        System.out.println(session.startGame());

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("/art")) {
                ArtworkApi api = new ArtworkApi();
                Artwork artwork = api.getRandomArtwork();
                if (artwork != null) {
                    System.out.println(artwork.title + "\nАвтор: " + artwork.artistDisplayName);
                    System.out.println("Ссылка на картинку: " + artwork.primaryImageSmall);
                } else {
                    System.out.println("Не удалось загрузить картину.");
                }
                continue;
            }

            String response;
            if (input.equals("/start")) {
                response = session.startGame();
            } else if (input.equals("/help")) {
                response = session.getHelp();
            } else if (input.equals("/skip")) {
                response = session.skipQuestion();
            } else if (input.equals("/exit")) {
                response = session.stopGame();
                System.out.println(response);
                break;
            } else if (input.equals("/easy")) {
                response = session.changeLevel("easy");
            } else if (input.equals("/medium")) {
                response = session.changeLevel("medium");
            } else if (input.equals("/hard")) {
                response = session.changeLevel("hard");
            } else {
                response = session.handleAnswer(input);
            }

            System.out.println(response);
        }
    }
}
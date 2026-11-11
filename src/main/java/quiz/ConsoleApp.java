package quiz;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    public void start() {
        QuestionStorage storage = new InMemoryQuestionStorage();
        storage.loadQuestions("easy");
        QuizSession session = new QuizSession(storage.getAllQuestions());
        Scanner scanner = new Scanner(System.in);

        System.out.println(session.startGame());

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("/art")) {
                ArtworkApi api = new ArtworkApi();
                Artwork artwork = api.getRandomArtwork();
                if (artwork != null) {
                    System.out.println("🎨 " + artwork.title + "\nАвтор: " + artwork.artistDisplayName);
                    System.out.println("Ссылка на картинку: " + artwork.primaryImageSmall);
                } else {
                    System.out.println("Не удалось загрузить картину.");
                }
                continue;
            }

            String response;
            if (input.equals("/easy") || input.equals("/medium") || input.equals("/hard")) {
                String level = input.substring(1);
                storage.loadQuestions(level);
                List<Question> newQuestions = storage.getAllQuestions();
                response = session.changeLevel(newQuestions);
            } else {
                response = session.handleMessage(input);
            }

            System.out.println(response);

            if (!session.isPlaying() && input.equals("/exit")) {
                break;
            }
        }
    }
}
package quiz;

import java.util.Scanner;
public class Main {
    static void main() {
        System.out.println("Привет! Я ArtQuizBot.");
        System.out.println("Я задаю вопросы и проверяю твои ответы.");
        System.out.println("Команда /help — показать информацию обо мне.");
        System.out.println("Команда /exit — завершить работу.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        QuestionStorage questionStorage = new InMemoryQuestionStorage();
        while (true) {
            Question question = questionStorage.getQuestion();
            System.out.println(question.getQuestion());

            while (true) {
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("/help")) {
                    System.out.println();
                    System.out.println("Я QuizBot.");
                    System.out.println("Я задаю вопросы и проверяю твои ответы.");
                    System.out.println("/help — показать эту информацию.");
                    System.out.println("/exit — завершить программу.");
                    System.out.println();
                    System.out.println(question.getQuestion());
                    continue;
                }
                if (response.equalsIgnoreCase("/exit")) {
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                }

                String correctAnswer = question.getAnswer();
                if (response.equalsIgnoreCase(correctAnswer)) {
                    System.out.println("Молодец!");
                    System.out.println();
                    break;
                } else {
                    System.out.println("Неправильно! Попробуй ещё раз.");
                }
            }
        }
    }
}

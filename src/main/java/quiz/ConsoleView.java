package quiz;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public void printWelcome() {
        System.out.println("Привет! Я ArtQuizBot.");
        System.out.println("Я задаю вопросы и проверяю твои ответы.");
        System.out.println("Команда /help — показать информацию обо мне.");
        System.out.println("Команда /exit — завершить работу.");
        System.out.println();
    }

    public void printQuestion(String question) {
        System.out.println(question);
    }

    public void printCorrect() {
        System.out.println("Молодец!");
        System.out.println();
    }

    public void printWrong() {
        System.out.println("Неправильно! Попробуй ещё раз.");
    }

    public void printGoodbye() {
        System.out.println("До свидания!");
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
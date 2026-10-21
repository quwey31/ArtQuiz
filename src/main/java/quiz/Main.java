package quiz;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("telegram")) {
            new TelegramApp().start();
        } else {
            new ConsoleApp().start();
        }
    }
}
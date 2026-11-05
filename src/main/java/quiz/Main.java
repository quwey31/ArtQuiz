package quiz;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("telegram")) {
            new TelegramApp().start();
        }
        else if (args.length > 0 && args[0].equals("console")) {
            new ConsoleApp().start();
        }
        else {
            new Thread(() -> new ConsoleApp().start()).start();
            new TelegramApp().start();
        }
    }
}
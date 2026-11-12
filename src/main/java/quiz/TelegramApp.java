package quiz;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramApp {
    public void start() {
        String botUsername = "TheArtQuizBot";
        String botToken = System.getenv("TELEGRAM_BOT_TOKEN");

        QuestionStorage storage = new InMemoryQuestionStorage();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ArtQuizBot(botUsername, botToken, storage));
            System.out.println("Telegram бот успешно запущен! Иди в телегу и пиши ему /start");
        } catch (TelegramApiException e) {
            System.out.println("Ошибка при запуске бота: " + e.getMessage());
        }
    }
}
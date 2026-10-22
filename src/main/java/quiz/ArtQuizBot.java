package quiz;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ArtQuizBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final QuestionStorage storage;
    private final Map<Long, TelegramSession> sessions = new HashMap<>();

    public ArtQuizBot(String botUsername, String botToken, QuestionStorage storage) {
        super(botToken);
        this.botUsername = botUsername;
        this.storage = storage;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        TelegramSession session = sessions.computeIfAbsent(chatId, id -> new TelegramSession(storage));

        String responseText;

        if (messageText.equals("/start")) {
            responseText = session.startGame();
        } else if (messageText.equals("/help")) {
            responseText = session.getHelp();
        } else if (messageText.equals("/skip")) {
            responseText = session.skipQuestion();
        } else if (messageText.equals("/exit")) {
            responseText = session.stopGame();
            sessions.remove(chatId);
        } else {
            responseText = session.handleAnswer(messageText);
        }

        sendMessage(chatId, responseText);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
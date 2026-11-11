package quiz;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ArtQuizBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final QuestionStorage storage;
    private final Map<Long, QuizSession> sessions = new HashMap<>();

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
        if (!update.hasMessage() || !update.getMessage().hasText()) return;

        long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        QuizSession session = sessions.computeIfAbsent(chatId, id -> new QuizSession(storage));

        if (messageText.equals("/art")) {
            ArtworkApi api = new ArtworkApi();

            Artwork artwork = api.getRandomArtwork();
            if (artwork != null) {
                 sendPhoto(chatId, artwork.primaryImageSmall, artwork.title + "\nАвтор: " + artwork.artistDisplayName);
            } else {
                sendMessage(chatId, "Не удалось загрузить картину.");
            }
            return;
        }

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
        } else if (messageText.equals("/easy")) {
            responseText = session.changeLevel("easy");
        } else if (messageText.equals("/medium")) {
            responseText = session.changeLevel("medium");
        } else if (messageText.equals("/hard")) {
            responseText = session.changeLevel("hard");
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

    private void sendPhoto(long chatId, String photoUrl, String caption) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(String.valueOf(chatId));
        photo.setPhoto(new InputFile(photoUrl));
        photo.setCaption(caption);
        try {
            execute(photo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
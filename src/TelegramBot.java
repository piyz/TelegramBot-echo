import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.Date;

public class TelegramBot extends TelegramLongPollingBot{

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){

            Message message = update.getMessage();
            Date currentDate = new Date();
            String message_txt = update.getMessage().getText();
            String message_data = currentDate.toString();

            if (message_txt.equals("/time")){
                sendReply(message,message_data);
            }else {
                sendReply(message,message_txt);
            }
        }
    }

    private void sendReply(Message message, String txt){
        SendMessage s = new SendMessage().setChatId(message.getChatId()).setText(txt);
        try {
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }
}

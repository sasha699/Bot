import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends TelegramLongPollingBot {

    static ArrayList<Object> objects2 = new ArrayList<>();
    static String link = "";

    public static void main(String[] args) throws TelegramApiException, IOException {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        Parser.main(args);

        botsApi.registerBot(new Main());

        System.out.println("Бот запущен");
    }

    public void sendMsg(String text, long Id) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(Id);

        sendMessage.setText(text);
        try{
            execute(sendMessage);
        }
        catch (TelegramApiException ignored){}
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message.hasText() && objects2.size() != 1 && message.getChatId() == -753070001) {
            switch (message.getText()) {
                case "/list":
                    link = objects2.get(objects2.size() - 1).Link;
                    sendMsg(link, -753070001);
                    objects2.remove(objects2.size() - 1);
                    break;
                case "/info":
                    sendMsg(String.valueOf(objects2.size() - 1), -753070001);
                    break;
                case "/Yes":
                    sendMsg(link,-1001516965127L);
                    break;
                default:
                    break;
            }
        } else {
            if(objects2.size() == 1 && "/Yes".equals(message.getText())){
                sendMsg(link, -1001516965127L);
            }

            else if ("/list".equals(message.getText()) && !link.equals("")) {
                sendMsg("Новых объявлений не найдено", -753070001);
            }
            else if (objects2.size() == 1 && "/info".equals(message.getText())) {
                sendMsg("0", -753070001);
            }
        }
    }

    public String getBotUsername() {
        return "Alerto";
    }

    public String getBotToken() {
        return "5468955104:AAHsnr0E0T-GXCkZHj6C62QEi-4p2oeWZ-c";
    }
}
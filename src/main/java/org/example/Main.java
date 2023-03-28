package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main extends TelegramLongPollingBot {
    // BandergusGoitKOIBot
    // 5919996960:AAFZEJTcllVfxd9iZnO_Gq-wcjoPhUR8-yo
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());
//        System.out.println("Hello world!");
    }

    @Override
    public String getBotUsername() {
        return "BandergusGoitKOIBot";
    }

    @Override
    public String getBotToken() {
        return "5919996960:AAFZEJTcllVfxd9iZnO_Gq-wcjoPhUR8-yo";
    }

    @Override

    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);
        if (update.hasMessage() && update.getMessage().getText().equals("/start")){
            SendMessage message = createMessages("Привіт !" );
            message.setChatId(chatId);
            attachButtons(message,Map.of(
                    "Слава Україні","glory_for_ukraine"
            ));
            sendApiMethodAsync(message);
        }

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("glory_for_ukraine")) {
                SendMessage message = createMessages("Героям Слава !");
                message.setChatId(chatId);
                attachButtons(message, Map.of(
                        "Слава Нації", "slava_for_ukraine"));
                sendApiMethodAsync(message);
            }
        }
            if (update.hasCallbackQuery()){
                if(update.getCallbackQuery().getData().equals("slava_for_ukraine")){
                    SendMessage message = createMessages("Смерть ворогам!");
                    message.setChatId(chatId);
                    sendApiMethodAsync(message);
                }
        }
    }

    public Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }

        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }

    public SendMessage createMessages(String text) {
        SendMessage message = new SendMessage();

        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8) );
        message.setParseMode("markdown");
//        message.setParseMode("markdown");
        return message;
    }
    public void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String( buttonName.getBytes(), StandardCharsets.UTF_8) );
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }
}
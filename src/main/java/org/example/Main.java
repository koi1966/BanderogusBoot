package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

//1)додайте в кінець файлу build.gradle ось цей фрагмент коду
//
//        compileJava.options.encoding = 'UTF-8'
//        tasks.withType(JavaCompile) {options.encoding = 'UTF-8'}
//
//        2)в методі attachButtons змініть
//        button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
//        на button.setText(buttonName);
//
//        3)а в методі createMessage змініть
//        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
//        на message.setText(text);
public class Main extends TelegramLongPollingBot {

    private Map<Long, Integer> levels = new HashMap<>();

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
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            // Send imege level 1
            sendImage("level-1", chatId);

            // Send message
            SendMessage messages = createMessages("Ґа-ґа-ґа!\n" +
                    "Вітаємо у боті біолабораторії «Батько наш Бандера».\n" +
                    "\n" +
                    "Ти отримуєш гусака №71\n" +
                    "\n" +
                    "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської худоби до рівня біологічної зброї, здатної нищити ворога. \n" +
                    "\n" +
                    "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\n" +
                    "✔️виконувати завдання\n" +
                    "✔️переходити на наступні рівні\n" +
                    "✔️заробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\n" +
                    "\n" +
                    "*Гусак звичайний.* Стартовий рівень.\n" +
                    "Бонус: 5 монет.\n" +
                    "Обери завдання, щоб перейти на наступний рівень");
            messages.setChatId(chatId);

            List<String> battons = Arrays.asList(
                    "Сплести маскувальну сітку (+15 монет)",
                    "Зібрати кошти патріотичними піснями (+15 монет)",
                    "Вступити в Міністерство Мемів України (+15 монет)",
                    "Запустити волонтерську акцію (+15 монет)",
                    "Вступити до лав тероборони (+15 монет)"
            );

            battons = getRandom3(battons);

            attachButtons(messages, Map.of(
                    battons.get(0), "level_1_task",
                    battons.get(1), "level_1_task",
                    battons.get(2), "level_1_task"
            ));
            sendApiMethodAsync(messages);
        }

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_1_task") && getLevel(chatId) == 1) {
                // increase level
                setLevels(chatId, 2);
                // Send image
                sendImage("level-2", chatId);
                // Send message
                SendMessage messages = createMessages("*Вітаємо на другому рівні! Твій гусак - біогусак.*\n" +
                        "Баланс: 20 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");
                messages.setChatId(chatId);

                List<String> battons = Arrays.asList(
                        "Зібрати комарів для нової біологічної зброї (+15 монет)",
                        "Пройти курс молодого бійця (+15 монет)",
                        "Задонатити на ЗСУ (+15 монет)",
                        "Збити дрона банкою огірків (+15 монет)",
                        "Зробити запаси коктейлів Молотова (+15 монет)"
                );

                battons = getRandom3(battons);

                attachButtons(messages, Map.of(
                        battons.get(0), "level_2_task",
                        battons.get(1), "level_2_task",
                        battons.get(2), "level_2_task"
                ));
                sendApiMethodAsync(messages);
            }
        }

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_2_task") && getLevel(chatId) == 2) {
                // increase level
                setLevels(chatId, 3);
                // Send image
                sendImage("level-3", chatId);
                // Send message
                SendMessage messages = createMessages("*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\n" +
                        "Баланс: 35 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");
                messages.setChatId(chatId);

                List<String> battons = Arrays.asList(
                        "Злітати на тестовий рейд по чотирьох позиціях (+15 монет)",
                        "Відвезти гуманітарку на передок (+15 монет)",
                        "Знайти зрадника та здати в СБУ (+15 монет)",
                        "Навести арту на орків (+15 монет)",
                        "Притягнути танк трактором (+15 монет)"
                );

                battons = getRandom3(battons);

                attachButtons(messages, Map.of(
                        battons.get(0), "level_3_task",
                        battons.get(1), "level_3_task",
                        battons.get(2), "level_3_task"));
                sendApiMethodAsync(messages);
            }
        }

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_3_task") && getLevel(chatId) == 3) {
                setLevels(chatId, 4);
                sendImage("level-4", chatId);

                SendMessage messages = createMessages("*Вітаємо на останньому рівні! Твій гусак - готова біологічна зброя - бандерогусак.*\n" +
                        "Баланс: 50 монет. \n" +
                        "Тепер ти можеш придбати Джавелін і глушити чмонь");
                messages.setChatId(chatId);

                List<String> battons = Arrays.asList(
                        "Купити Джавелін (50 монет)"
                );

                attachButtons(messages, Map.of(
                        battons.get(0), "level_4_task"
                        ));
                sendApiMethodAsync(messages);
            }
        }

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level_4_task") && getLevel(chatId) == 4) {
                setLevels(chatId, 5);
                SendMessage messages = createMessages("*Джавелін твій. Повний вперед!*");
                messages.setChatId(chatId);

                sendImage("final", chatId);

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
//        3)а в методі createMessage змініть
//        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
//        на message.setText(text);
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
//        message.setParseMode("markdown");
        return message;
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String buttonName : buttons.keySet()) {
//            2)в методі attachButtons змініть
//            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
//            на button.setText(buttonName);
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(Arrays.asList(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public void sendImage(String name, Long chatId) {
        SendAnimation animation = new SendAnimation();

        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("images/" + name + ".gif"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);
        executeAsync(animation);
    }

    public int getLevel(Long chatId) {
        return levels.getOrDefault(chatId, 1);
    }

    public void setLevels(Long chatId, int level) {
        levels.put(chatId, level);
    }

    public List<String> getRandom3(List<String> variants) {
        ArrayList<String> copy = new ArrayList<>(variants);
        Collections.shuffle(copy);
        return copy.subList(0, 3);

    }
}
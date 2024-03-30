package ua.javarush;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static ua.javarush.TelegramBotContent.*;
import static ua.javarush.TelegramBotUtils.*;

public class MyFirstTelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        // TODO: додай ім'я бота в лапки нижче
        return "tesseracterMegaUltraBot";
    }

    @Override
    public String getBotToken() {
        // TODO: додай токен бота в лапки нижче
        return "6628520145:AAF5ik0hJ4ETWPOMxG4t-KswvbqPK1xtuzU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {

            clearGlories(chatId); // just in case

            System.out.println("fist in process");
            sendMessageInChat(chatId, 0, "step_1_pic", STEP_1_TEXT, Map.of(
                    "Злам холодильника", "step_1_btn"));
            System.out.println("fist done " + update.getMessage().getChatId());

        }

        if (update.hasMessage() && update.getMessage().getText().contains("/send ")) {

            String mess = update.getMessage().getText();
            String[] idChat = mess.split(" ");

            sendApiMethodAsync(createMessage(Long.parseLong(idChat[1]), "Шо ти голова?",
                    Map.of("Start", "send_btn")));

        }

        else if (update.hasCallbackQuery()) {

            if (update.getCallbackQuery().getData().equals("send_btn")) {

                System.out.print("ID = ");
                System.out.println(chatId);
                sendApiMethodAsync(createMessage(chatId, "hahahah i got ur id, loh id: " + chatId));

            }


            //===================================(Холодос)=============================================
            if (update.getCallbackQuery().getData().equals("step_1_btn") && getGlories(chatId) == 0) {

                System.out.println("second in process");
                sendMessageInChat(chatId, 20, "step_2_pic", STEP_2_TEXT, Map.of(
                        "Взяти сосиску! +20 слави", "step_2_btn",
                        "Взяти рибку! +20 слави", "step_2_btn",
                        "Скинути банку з огірками! +20 слави", "step_2_btn"));
                System.out.println("second done");
            }
            //===================================(Пилосос)=============================================
            if (update.getCallbackQuery().getData().equals("step_2_btn") && getGlories(chatId) == 20) {

                System.out.println("third in process");
                sendMessageInChat(chatId, 20, "step_3_pic", STEP_3_TEXT, Map.of(
                        "Злам робота пилососа", "step_3_btn"));
                System.out.println("third done");
            }
            if (update.getCallbackQuery().getData().equals("step_3_btn") && getGlories(chatId) == 40) {

                System.out.println("4th in process");
                sendMessageInChat(chatId, 30, "step_4_pic", STEP_4_TEXT, Map.of(
                        "Відправити робопилосос за їжею! +30 слави", "step_4_btn",
                        "Проїхатися на робопилососі! +30 слави", "step_4_btn",
                        "Тікати від робопилососа! +30 слави", "step_4_btn"));
                System.out.println("4th done");
            }
            //===================================(Гопрошка)=============================================
            if (update.getCallbackQuery().getData().equals("step_4_btn") && getGlories(chatId) == 70) {

                System.out.println("5th in process");
                sendMessageInChat(chatId, 30, "step_5_pic", STEP_5_TEXT, Map.of(
                        "Одягнути та включити GoPro!", "step_5_btn"));
                System.out.println("5th done");
            }
            if (update.getCallbackQuery().getData().equals("step_5_btn") && getGlories(chatId) == 100) {

                System.out.println("6th in process");
                sendMessageInChat(chatId, 40, "step_6_pic", STEP_6_TEXT, Map.of(
                        "Бігати дахами, знімати на GoPro! +40 слави", "step_6_btn",
                        "З GoPro нападати на інших котів із засідки! +40 слави", "step_6_btn",
                        "З GoPro нападати на собак із засідки! +40 слави", "step_6_btn"));
                System.out.println("6th done");

            }
            //===================================(Пароль)=============================================
            if (update.getCallbackQuery().getData().equals("step_6_btn") && getGlories(chatId) == 140) {

                System.out.println("7th in process");
                sendMessageInChat(chatId, 30, "step_7_pic", STEP_7_TEXT, Map.of(
                        "Злам пароля", "step_7_btn"));
                System.out.println("7th done");

            }
            //===================================(Вихід)=============================================
            if (update.getCallbackQuery().getData().equals("step_7_btn") && getGlories(chatId) == 170) {

                System.out.println("8th in process");
                sendMessageInChat(chatId, 30, "step_8_pic", STEP_8_TEXT, Map.of(
                        "Вийти на подвір'я", "step_8_btn"));
                System.out.println("8th done");
            }
            if (update.getCallbackQuery().getData().equals("step_8_btn") && getGlories(chatId) == 200) {
                System.out.println("final in process");
                sendMessageInChat(chatId, 30, "final_pic", FINAL_TEXT, Map.of(
                        "Почати знову", "start_again"));
                System.out.println("final done");
            }
            if(update.getCallbackQuery().getData().equals("start_again")){
                clearGlories(chatId); // just in case

                System.out.println("fist in process");
                sendMessageInChat(chatId, 0, "step_1_pic", STEP_1_TEXT, Map.of(
                        "Злам холодильника", "step_1_btn"));
                System.out.println("fist done " + update.getMessage().getChatId());

            }
        }
    }

    private void sendMessageInChat(Long chatId, int glories, String pictures, String text, Map<String, String> buttons){
        System.out.println("Inside sendMessageInChat");
        addGlories(chatId, glories);

        SendPhoto photo = createPhotoMessage(chatId, pictures); // Photo
        executeAsync(photo);                                    // Send photo

        SendMessage message = createMessage(chatId, text, buttons);
        sendApiMethodAsync(message);
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
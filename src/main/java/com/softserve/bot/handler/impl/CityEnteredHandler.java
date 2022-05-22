package com.softserve.bot.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import com.softserve.bot.enums.ConversationState;
import com.softserve.bot.handler.UserRequestHandler;
import com.softserve.bot.helper.KeyboardHelper;
import com.softserve.bot.model.UserRequest;
import com.softserve.bot.model.UserSession;
import com.softserve.bot.service.TelegramService;
import com.softserve.bot.service.UserSessionService;

@Component
public class CityEnteredHandler extends UserRequestHandler {

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final UserSessionService userSessionService;

    public CityEnteredHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate())
                && ConversationState.WAITING_FOR_CITY.equals(userRequest.getUserSession().getState());
    }

    @Override
    public void handle(UserRequest userRequest) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.buildMenuWithCancel();
        if(isTextMessage(userRequest.getUpdate(),"Харків")) {
            telegramService.sendMessage(userRequest.getChatId(),
                    "Радий бачити людей з технологічної столиці України. Вражає ваша стійкість",
                    replyKeyboardMarkup);
        }
        if(isTextMessage(userRequest.getUpdate(),"Львів")) {
            telegramService.sendMessage(userRequest.getChatId(),
                    "О круто я тоже зі Львова. Файне місто для файних людей",
                    replyKeyboardMarkup);
        }
        if(isTextMessage(userRequest.getUpdate(),"Івано-Франківськ")) {
            telegramService.sendMessage(userRequest.getChatId(),
                    "О в мене родина звідти. Аж захотілось баношу;)",
                    replyKeyboardMarkup);
        }
        if(isTextMessage(userRequest.getUpdate(),"Херсон")) {
            telegramService.sendMessage(userRequest.getChatId(),
                    "Не можливо описати слова вашу мужність. Ну і смак ваших кавунів)",
                    replyKeyboardMarkup);
        }
        if(isTextMessage(userRequest.getUpdate(),"Київ")) {
            telegramService.sendMessage(userRequest.getChatId(),
                    "Ну тут й сказати нічого. Вольове місто сильних людей",
                    replyKeyboardMarkup);
        }
        if(isTextMessage(userRequest.getUpdate(),"Одеса")) {
            telegramService.sendMessage(userRequest.getChatId(),
                    "О Одеса-мама, перлина біля моря",
                    replyKeyboardMarkup);
        }
        String city = userRequest.getUpdate().getMessage().getText();

        UserSession session = userRequest.getUserSession();
        session.setCity(city);
        session.setState(ConversationState.WAITING_FOR_TEXT);
        userSessionService.saveSession(userRequest.getChatId(), session);
    }


    @Override
    public boolean isGlobal() {
        return false;
    }

}

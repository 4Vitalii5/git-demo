package com.softserve.bot.handler.impl;

import com.softserve.bot.handler.UserRequestHandler;
import com.softserve.bot.helper.KeyboardHelper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import com.softserve.bot.enums.ConversationState;
import com.softserve.bot.model.UserRequest;
import com.softserve.bot.model.UserSession;
import com.softserve.bot.service.TelegramService;
import com.softserve.bot.service.UserSessionService;

import java.util.List;

@Component
public class INeedHelpHandler extends UserRequestHandler {

    public static String INFORMATION = "Давайте дізнаємся щось про те звідки ви:";
    public static List<String> cities = List.of("Київ", "Львів","Івано-Франківськ","Харків","Одеса","Херсон");

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final UserSessionService userSessionService;

    public INeedHelpHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), INFORMATION);
    }

    @Override
    public void handle(UserRequest userRequest) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.buildCitiesMenu(cities);
        telegramService.sendMessage(userRequest.getChatId(),"Ваше місто?⤵️", replyKeyboardMarkup);

        UserSession userSession = userRequest.getUserSession();
        userSession.setState(ConversationState.WAITING_FOR_CITY);
        userSessionService.saveSession(userSession.getChatId(), userSession);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }

}

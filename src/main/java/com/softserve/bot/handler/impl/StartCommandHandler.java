package com.softserve.bot.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import com.softserve.bot.handler.UserRequestHandler;
import com.softserve.bot.helper.KeyboardHelper;
import com.softserve.bot.model.UserRequest;
import com.softserve.bot.service.TelegramService;

@Component
public class StartCommandHandler extends UserRequestHandler {

    private static String command = "/start";

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    public StartCommandHandler(TelegramService telegramService, KeyboardHelper keyboardHelper) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        ReplyKeyboard replyKeyboard = keyboardHelper.buildMainMenu();
        telegramService.sendMessage(request.getChatId(),
                "\uD83D\uDC4BПривіт! Я чат бот чим можу допомогти!",
                replyKeyboard);
        telegramService.sendMessage(request.getChatId(),
                "Я ще не дуже розумний то обирайте з меню нижче ⤵️");
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}

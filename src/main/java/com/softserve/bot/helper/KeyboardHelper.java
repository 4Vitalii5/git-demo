package com.softserve.bot.helper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

import static com.softserve.bot.constant.Constants.BTN_CANCEL;

/**
 * Helper class, allows to build keyboards for users
 */
@Component
public class KeyboardHelper {

    public ReplyKeyboardMarkup buildCitiesMenu(List<String> cities) {
        List<KeyboardButton> buttons = List.of(
                new KeyboardButton("Київ"),
                new KeyboardButton("Львів"),
                new KeyboardButton("Івано-Франківськ"),
                new KeyboardButton("Харків"),
                new KeyboardButton("Одеса"),
                new KeyboardButton("Херсон"));
        KeyboardRow row1 = new KeyboardRow(buttons);

        KeyboardRow row2 = new KeyboardRow(List.of(new KeyboardButton(BTN_CANCEL)));

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row1, row2))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildMainMenu() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Давайте дізнаємся щось про те звідки ви:");

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
//    public ReplyKeyboardMarkup buildMainMenu2() {
//        KeyboardRow keyboardRow = new KeyboardRow();
//        keyboardRow.add("Давайте дізнаємся щось про те звідки ви:");
//
//        return ReplyKeyboardMarkup.builder()
//                .keyboard(List.of(keyboardRow))
//                .selective(true)
//                .resizeKeyboard(true)
//                .oneTimeKeyboard(false)
//                .build();
//    }

    public ReplyKeyboardMarkup buildMenuWithCancel() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(BTN_CANCEL);

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
}

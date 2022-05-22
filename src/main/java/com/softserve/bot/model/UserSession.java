package com.softserve.bot.model;

import lombok.Builder;
import lombok.Data;
import com.softserve.bot.enums.ConversationState;

@Data
@Builder
public class UserSession {
    private Long chatId;
    private ConversationState state;
    private String city;
    private String text;
}

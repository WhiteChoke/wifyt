package com.whitechoke.wifyt.web.validate;

import com.whitechoke.wifyt.dto.message.MessageRequest;
import org.springframework.stereotype.Component;

@Component
public class ValidateMessage {

    public void validateMessage(MessageRequest request) {
        if (request.chatId() == null) {
            throw new IllegalArgumentException("Chat id cannot be empty");
        }
        if (request.senderId() == null) {
            throw new IllegalArgumentException("Sender id cannot be empty");
        }
        if (request.message() == null || request.message().isBlank()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
    }
}

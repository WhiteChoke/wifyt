package com.whitechoke.wifyt.web.validate;

import com.whitechoke.wifyt.dto.chat.ChatRequest;
import org.springframework.stereotype.Component;

@Component
public class ValidateChat {

    public void validatePersonalChat(ChatRequest chat) {

        if (chat.name() == null || chat.name().isBlank()) {
            throw new IllegalArgumentException("Chat name cannot be empty");
        }

        if (chat.participantList().size() != 2) {
            throw new IllegalArgumentException("Participant list length for personal chat should be 2");
        }

        var firstParticipant = chat.participantList().getFirst();
        var secondParticipant = chat.participantList().getLast();

        if (firstParticipant.equals(secondParticipant)){
            throw new IllegalArgumentException("Unable to create a chat with yourself");
        }
    }
}

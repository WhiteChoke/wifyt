package com.whitechoke.wifyt.web.validate;

import com.whitechoke.wifyt.dto.chat.ChatInterface;
import com.whitechoke.wifyt.enums.ChatType;
import org.springframework.stereotype.Component;

@Component
public class ValidateChat {

    public <T extends ChatInterface> void validateChat(T chat) {

        if (chat.name() == null || chat.name().isBlank()) {
            throw new IllegalArgumentException("Chat name cannot be empty");
        }

        if (chat.participantList() == null) {
            throw new IllegalArgumentException("Participants list cannot be null");
        }

        if (chat.participantList().isEmpty()) {
            throw new IllegalArgumentException("Impossible to create a chat without participants");
        }

        if (chat.chatType().equals(ChatType.PERSONAL)){
            validatePersonalChat(chat);
        }
    }

    private <T extends ChatInterface> void validatePersonalChat(T chat) {

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

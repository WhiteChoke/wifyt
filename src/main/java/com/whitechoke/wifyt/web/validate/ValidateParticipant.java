package com.whitechoke.wifyt.web.validate;

import com.whitechoke.wifyt.dto.participant.ParticipantRequest;
import org.springframework.stereotype.Component;

@Component
public class ValidateParticipant {

    public void validateParticipant(ParticipantRequest request) {
        if (request.userId() == null) {
            throw new IllegalArgumentException("User id cannot be empty");
        }
        if (request.role() == null) {
            throw new IllegalArgumentException("Participant role cannot be empty");
        }
        if (request.chatId() == null) {
            throw new IllegalArgumentException("Chat id cannot be empty");
        }
    }
}

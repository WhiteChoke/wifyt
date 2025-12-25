package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface MessageService {
    Message createMessage(Message messageToCreate);
    Message getMessageById(String id);
    Message updateMessage(String id, Message newMessage);
    void deleteMessageById(String id);
    void handleIncomingMessage(Long chatId, Message message);
}

package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.message.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Message createMessage(Message messageToCreate);
    Message getMessageById(String id);
    Message updateMessage(String id, Message newMessage);
    void deleteMessageById(String id);
    void handleIncomingMessage(Long chatId, Message message);
}

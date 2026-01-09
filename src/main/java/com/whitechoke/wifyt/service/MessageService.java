package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.message.Message;
import com.whitechoke.wifyt.dto.message.MessageRequest;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Message createMessage(MessageRequest messageToCreate);
    Message getMessageById(String id);
    Message updateMessage(String id, MessageRequest newMessage);
    void deleteMessageById(String id);
    void handleIncomingMessage(Long chatId, MessageRequest message);
}

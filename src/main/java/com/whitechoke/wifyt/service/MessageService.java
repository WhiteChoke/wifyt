package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Message createMessage(Message messageToCreate);
    Message getMessageById(Long id);
    Message updateMessage(Long id, Message newMessage);
    void deleteMessageById(Long id);
}

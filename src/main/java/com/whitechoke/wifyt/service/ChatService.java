package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.chat.Chat;
import com.whitechoke.wifyt.dto.chat.ChatRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    Chat createPersonalChat(ChatRequest chatToCreate);
    Chat createGroupChat(Long ownerId, ChatRequest chatToCreate);
    Chat getChatById(Long id);
    Chat updateChat(Long id, ChatRequest newChat);
    void deleteChatById(Long id);
    List<Chat> getUserChats(Long userId);
}

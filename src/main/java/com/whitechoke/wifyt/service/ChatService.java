package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Chat;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    Chat createPersonalChat(Chat chatToCreate);
    Chat createGroupChat(Long ownerId, Chat chatToCreate);
    Chat getChatById(Long id);
    Chat updateChat(Long id, Chat newChat);
    void deleteChatById(Long id);
    List<Chat> getUserChats(Long userId);
}

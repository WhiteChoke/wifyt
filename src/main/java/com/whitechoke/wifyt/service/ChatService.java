package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Chat;

import java.util.UUID;

public interface ChatService {
    Chat createChat(Chat chatToCreate);
    Chat getChatById(UUID id);
    Chat updateChat(UUID id, Chat newChat);
    void deleteChatById(UUID id);
}

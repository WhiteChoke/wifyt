package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Chat;

import java.util.UUID;

public interface ChatService {
    Chat createChat(Chat chatToCreate);
    Chat getChatById(Long id);
    Chat updateChat(Long id, Chat newChat);
    void deleteChatById(Long id);
}

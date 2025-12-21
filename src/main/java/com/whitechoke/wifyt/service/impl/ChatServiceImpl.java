package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.Chat;
import com.whitechoke.wifyt.dto.mapper.ChatMapper;
import com.whitechoke.wifyt.repository.ChatRepository;
import com.whitechoke.wifyt.service.ChatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;
    private final ChatRepository chatRepository;
    private final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Override
    public Chat createChat(Chat chatToCreate) {

        if (chatToCreate.id() != null || chatToCreate.createdAt() != null) {
            throw new IllegalArgumentException("Chat id and creation time should be empty");
        }
        if (chatToCreate.name() == null) {
            throw new IllegalArgumentException("Chat name cannot be empty");
        }

        var chat = chatMapper.toEntity(chatToCreate);
        chat.setCreatedAt(Instant.now());

        var createdChat = chatRepository.save(chat);

        log.info("created chat with id: {}", createdChat.getId());

        return chatMapper.toDomain(createdChat);
    }

    @Override
    public Chat getChatById(UUID id) {

        var chat = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + id));

        return chatMapper.toDomain(chat);
    }

    @Override
    public Chat updateChat(UUID id, Chat newChat) {

        var oldChat = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + id));

        if (newChat.id() != null || newChat.createdAt() != null) {
            throw new IllegalArgumentException("Chat id and creation time should be empty");
        }
        if (newChat.name() == null) {
            throw new IllegalArgumentException("Chat name cannot be empty");
        }

        var updatedChat = chatMapper.toEntity(newChat);
        updatedChat.setId(id);
        updatedChat.setCreatedAt(oldChat.getCreatedAt());

        chatRepository.save(updatedChat);

        log.info("Updated chat with id: {}", id);

        return chatMapper.toDomain(updatedChat);
    }

    @Override
    public void deleteChat(UUID id) {

        var chatToDelete = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + id));

        chatRepository.delete(chatToDelete);

        log.info("Deleted chat with id: {}", id);
    }
}

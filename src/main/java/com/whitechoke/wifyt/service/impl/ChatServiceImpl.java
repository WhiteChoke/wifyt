package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.Chat;
import com.whitechoke.wifyt.dto.Participant;
import com.whitechoke.wifyt.dto.mapper.ChatMapper;
import com.whitechoke.wifyt.enums.ChatType;
import com.whitechoke.wifyt.enums.UserRoles;
import com.whitechoke.wifyt.repository.ChatRepository;
import com.whitechoke.wifyt.service.ChatService;
import com.whitechoke.wifyt.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;
    private final ChatRepository chatRepository;
    private final ParticipantService  participantService;
    private final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Override
    public Chat createPersonalChat(Chat chatToCreate) {

        if (chatToCreate.id() != null || chatToCreate.createdAt() != null) {
            throw new IllegalArgumentException("Chat id and creation time should be empty");
        }
        if (chatToCreate.name() == null) {
            throw new IllegalArgumentException("Chat name cannot be empty");
        }
        if (chatToCreate.participantList().size() != 2) {
            throw new IllegalArgumentException("Participant list length for personal chat should be 2");
        }

        var chat = chatMapper.toEntity(chatToCreate);
        chat.setCreatedAt(Instant.now());
        chat.setType(ChatType.PERSONAL);

        var createdChat = chatRepository.save(chat);

        participantService.createParticipantsByUserIds(chatToCreate.participantList(), createdChat);

        log.info("created chat with id: {}", createdChat.getId());

        return chatMapper.toDomain(createdChat);
    }

    @Override
    public Chat getChatById(Long id) {

        var chat = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + id));

        return chatMapper.toDomain(chat);
    }

    @Override
    public Chat updateChat(Long id, Chat newChat) {

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
    public void deleteChatById(Long id) {

        var chatToDelete = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + id));

        chatRepository.delete(chatToDelete);

        log.info("Deleted chat with id: {}", id);
    }

    @Override
    public List<Chat> getUserChats(Long userId) {

        var chatList = chatRepository.getUserChats(userId);

        return chatList.stream().map(chatMapper::toDomain).toList();
    }

    @Override
    public Chat createGroupChat(Long ownerId, Chat chatToCreate) {

        if (chatToCreate.id() != null || chatToCreate.createdAt() != null) {
            throw new IllegalArgumentException("Chat id and creation time should be empty");
        }
        if (chatToCreate.name() == null) {
            throw new IllegalArgumentException("Chat name cannot be empty");
        }

        var chatToSave = chatMapper.toEntity(chatToCreate);
        chatToSave.setCreatedAt(Instant.now());
        chatToSave.setType(ChatType.GROUP);

        var createdChat = chatRepository.save(chatToSave);

        chatToCreate.participantList().remove(ownerId);

        participantService.createParticipantByUserId(ownerId, UserRoles.OWNER, createdChat);
        participantService.createParticipantsByUserIds(chatToCreate.participantList(), createdChat);

        return chatMapper.toDomain(createdChat);
    }
}

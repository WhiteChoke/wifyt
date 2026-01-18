package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.chat.Chat;
import com.whitechoke.wifyt.dto.chat.ChatRequest;
import com.whitechoke.wifyt.dto.mapper.ChatMapper;
import com.whitechoke.wifyt.enums.ChatType;
import com.whitechoke.wifyt.enums.UserRoles;
import com.whitechoke.wifyt.repository.ChatRepository;
import com.whitechoke.wifyt.service.ChatService;
import com.whitechoke.wifyt.service.ExtractDataFromAuth;
import com.whitechoke.wifyt.service.ParticipantService;
import com.whitechoke.wifyt.web.validate.ValidateChat;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    private final ValidateChat validate;
    private final ExtractDataFromAuth extractDataFromAuth;

    @Override
    @Transactional
    public Chat createPersonalChat(ChatRequest chatToCreate) {

        validate.validateChat(chatToCreate, ChatType.PERSONAL);

        var chat = chatMapper.toEntity(chatToCreate);
        chat.setCreatedAt(Instant.now());
        chat.setType(ChatType.PERSONAL);

        var createdChat = chatRepository.save(chat);

        participantService.createParticipantsByUserIds(chatToCreate.participantList(), createdChat.getId());

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
    @Transactional
    public Chat updateChat(Long id, ChatRequest newChat) {

        var oldChat = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + id));

        validate.validateChat(newChat, oldChat.getType());

        var updatedChat = chatMapper.toEntity(newChat);
        updatedChat.setId(id);
        updatedChat.setCreatedAt(oldChat.getCreatedAt());

        chatRepository.save(updatedChat);

        log.info("Updated chat with id: {}", id);

        return chatMapper.toDomain(updatedChat);
    }

    @Override
    @Transactional
    public void deleteChatById(Long id) {

        var chatToDelete = chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + id));

        chatRepository.delete(chatToDelete);

        log.info("Deleted chat with id: {}", id);
    }

    @Override
    public List<Chat> getUserChats() {

        var userId = extractDataFromAuth.getCurrentUserId();

        var chatList = chatRepository.getUserChats(userId);

        return chatList.stream().map(chatMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Chat createGroupChat(ChatRequest chatToCreate) {

        validate.validateChat(chatToCreate, ChatType.GROUP);
        var ownerId = extractDataFromAuth.getCurrentUserId();

        var chatToSave = chatMapper.toEntity(chatToCreate);
        chatToSave.setCreatedAt(Instant.now());
        chatToSave.setType(ChatType.GROUP);

        var createdChat = chatRepository.save(chatToSave);

        chatToCreate.participantList().remove(ownerId);
`
        participantService.createParticipantByUserId(ownerId, UserRoles.OWNER, createdChat.getId());
        participantService.createParticipantsByUserIds(chatToCreate.participantList(), createdChat.getId());

        return chatMapper.toDomain(createdChat);
    }
}

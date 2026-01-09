package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.message.Message;
import com.whitechoke.wifyt.dto.mapper.MessageMapper;
import com.whitechoke.wifyt.dto.message.MessageRequest;
import com.whitechoke.wifyt.repository.MessageRepository;
import com.whitechoke.wifyt.service.MessageService;
import com.whitechoke.wifyt.web.validate.ValidateMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class MassageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper mapper;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Logger logger = LoggerFactory.getLogger(MassageServiceImpl.class);
    private final ValidateMessage validate;

    @Override
    @Transactional
    public Message createMessage(MessageRequest messageToCreate) {

        validate.validateMessage(messageToCreate);

        var message = mapper.toEntity(messageToCreate);
        message.setCreated_at(Instant.now());

        var createdMessage =  messageRepository.save(message);

        return mapper.toDomain(createdMessage);
    }

    @Override
    public Message getMessageById(String id) {
        var message = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found message by id: " + id));

        return mapper.toDomain(message);
    }

    @Override
    @Transactional
    public Message updateMessage(String id, MessageRequest newMessage) {

        validate.validateMessage(newMessage);

        var oldMessage =  messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found message by id: " + id));

        var messageToUpdate = mapper.toEntity(newMessage);
        messageToUpdate.setId(oldMessage.getId());
        messageToUpdate.setCreated_at(oldMessage.getCreated_at());

        messageRepository.save(messageToUpdate);

        return mapper.toDomain(messageToUpdate);
    }

    @Override
    @Transactional
    public void deleteMessageById(String id) {
        var message = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found message by id: " + id));

        messageRepository.delete(message);
    }

    @Override
    @Transactional
    @Deprecated
    public void handleIncomingMessage(Long chatId, MessageRequest message) {

        var response = createMessage(message);

        simpMessagingTemplate.convertAndSend("/topic/chat/" + chatId, response);
    }
}

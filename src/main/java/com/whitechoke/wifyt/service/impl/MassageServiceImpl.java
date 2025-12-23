package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.Message;
import com.whitechoke.wifyt.dto.mapper.MessageMapper;
import com.whitechoke.wifyt.repository.MessageRepository;
import com.whitechoke.wifyt.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class MassageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper mapper;

    @Override
    public Message createMessage(Message messageToCreate) {
        if (
                messageToCreate.id() != null ||
                messageToCreate.createdAt() != null
        ) {
            throw new IllegalArgumentException("message id and creation time should be empty");
        }
        if (
                messageToCreate.senderId() == null ||
                messageToCreate.chatId() == null ||
                messageToCreate.message() == null
        ) {
            throw new IllegalArgumentException("sender id, chat id and message cannot be empty");
        }

        var message = mapper.toEntity(messageToCreate);
        message.setCreated_at(Instant.now());

        var createdMessage =  messageRepository.save(message);

        return mapper.toDomain(createdMessage);
    }

    @Override
    public Message getMessageById(Long id) {
        var message = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found message by id: " + id));

        return mapper.toDomain(message);
    }

    @Override
    public Message updateMessage(Long id, Message newMessage) {
        if (
                newMessage.id() != null ||
                newMessage.createdAt() != null
        ) {
            throw new IllegalArgumentException("message id and creation time should be empty");
        }
        if (
                newMessage.senderId() == null ||
                newMessage.chatId() == null ||
                newMessage.message() == null
        ) {
            throw new IllegalArgumentException("sender id, chat id and message cannot be empty");
        }

        var oldMessage =  messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found message by id: " + id));

        var messageToUpdate = mapper.toEntity(newMessage);
        messageToUpdate.setId(oldMessage.getId());
        messageToUpdate.setCreated_at(oldMessage.getCreated_at());

        messageRepository.save(messageToUpdate);

        return mapper.toDomain(messageToUpdate);
    }

    @Override
    public void deleteMessageById(Long id) {
        var message = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found message by id: " + id));

        messageRepository.delete(message);
    }
}

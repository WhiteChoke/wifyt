package com.whitechoke.wifyt.dto.mapper;

import com.whitechoke.wifyt.dto.Message;
import com.whitechoke.wifyt.entity.MessageEntity;
import com.whitechoke.wifyt.repository.ChatRepository;
import com.whitechoke.wifyt.repository.ParticipantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final ParticipantRepository participantRepository;
    private final ChatRepository chatRepository;

    public Message toDomain(MessageEntity entity) {
        return new Message(
                entity.getId(),
                entity.getSender().getId(),
                entity.getChat().getId(),
                entity.getMessage(),
                entity.getCreated_at()
        );
    }

    public MessageEntity toEntity(Message dto) {
        var sender = participantRepository.findById(dto.senderId())
                .orElseThrow(() -> new EntityNotFoundException("Not found participant by id: " + dto.senderId()));

        var chat = chatRepository.findById(dto.chatId())
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + dto.chatId()));

        return new MessageEntity(
                dto.id(),
                sender,
                chat,
                dto.message(),
                dto.createdAt()
        );
    }
}

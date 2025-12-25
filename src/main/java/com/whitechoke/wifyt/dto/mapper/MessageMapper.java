package com.whitechoke.wifyt.dto.mapper;

import com.whitechoke.wifyt.dto.Message;
import com.whitechoke.wifyt.entity.MessageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    public Message toDomain(MessageEntity entity) {
        return new Message(
                entity.getId(),
                entity.getSenderId(),
                entity.getChatId(),
                entity.getMessage(),
                entity.getCreated_at()
        );
    }

    public MessageEntity toEntity(Message dto) {
        return new MessageEntity(
                dto.id(),
                dto.senderId(),
                dto.chatId(),
                dto.message(),
                dto.createdAt()
        );
    }
}

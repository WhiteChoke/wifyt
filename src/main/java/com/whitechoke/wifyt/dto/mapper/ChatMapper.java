package com.whitechoke.wifyt.dto.mapper;

import com.whitechoke.wifyt.dto.chat.Chat;
import com.whitechoke.wifyt.dto.chat.ChatRequest;
import com.whitechoke.wifyt.entity.ChatEntity;
import com.whitechoke.wifyt.entity.ParticipantEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChatMapper {

    public Chat toDomain(ChatEntity entity) {

        List<Long> participantList = entity.getParticipantList() != null
                ? entity
                .getParticipantList()
                .stream()
                .map(ParticipantEntity::getId)
                .toList()
                : List.of();

        return new Chat(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getType(),
                participantList
        );
    }

    public ChatEntity toEntity(Chat dto) {

        return new ChatEntity(
                dto.id(),
                dto.name(),
                dto.createdAt(),
                dto.chatType()
        );
    }

    public ChatEntity toEntity(ChatRequest request) {

        var entity = new ChatEntity();
        entity.setName(request.name());
        entity.setType(request.chatType());

        return entity;
    }
}

package com.whitechoke.wifyt.dto.mapper;

import com.whitechoke.wifyt.dto.Chat;
import com.whitechoke.wifyt.entity.ChatEntity;
import com.whitechoke.wifyt.entity.ParticipantEntity;
import com.whitechoke.wifyt.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ChatMapper {

    private final ParticipantRepository participantRepository;

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

        List<ParticipantEntity> participantList = Optional
                .ofNullable(dto.participantList())
                .map(participantRepository::findAllById)
                .orElse(List.of());

        return new ChatEntity(
                dto.id(),
                dto.name(),
                dto.createdAt(),
                dto.chatType(),
                participantList
        );
    }
}

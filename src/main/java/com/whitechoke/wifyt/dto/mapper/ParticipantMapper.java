package com.whitechoke.wifyt.dto.mapper;

import com.whitechoke.wifyt.dto.Participant;
import com.whitechoke.wifyt.entity.ParticipantEntity;
import com.whitechoke.wifyt.repository.ChatRepository;
import com.whitechoke.wifyt.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ParticipantMapper {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public Participant toDomain(ParticipantEntity entity) {
        return new Participant(
                entity.getId(),
                entity.getUser().getId(),
                entity.getRole(),
                entity.getChat().getId()
        );
    }

    public ParticipantEntity toEntity(Participant dto) {

        var user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + dto.userId()));
        var chat = chatRepository.findById(dto.chatId())
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + dto.userId()));

        return new ParticipantEntity(
                dto.id(),
                user,
                dto.role(),
                chat
        );
    }
}

package com.whitechoke.wifyt.dto.mapper;

import com.whitechoke.wifyt.dto.participant.Participant;
import com.whitechoke.wifyt.dto.participant.ParticipantRequest;
import com.whitechoke.wifyt.entity.ParticipantEntity;
import com.whitechoke.wifyt.repository.ChatRepository;
import com.whitechoke.wifyt.repository.UserRepository;
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
        return new ParticipantEntity(
                dto.id(),
                dto.role()
        );
    }

    public ParticipantEntity toEntity(ParticipantRequest request) {
        var entity = new ParticipantEntity();
        entity.setRole(request.role());
        return entity;
    }
}

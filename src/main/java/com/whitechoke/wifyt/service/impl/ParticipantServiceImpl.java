package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.Participant;
import com.whitechoke.wifyt.dto.mapper.ParticipantMapper;
import com.whitechoke.wifyt.repository.ParticipantRepository;
import com.whitechoke.wifyt.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper mapper;

    @Override
    public Participant createParticipant(Participant participantToCreate) {

        if (participantToCreate.id() != null){
            throw new IllegalArgumentException("Participant id should be empty");
        }

        if (
                participantToCreate.userId() == null ||
                participantToCreate.role() == null ||
                participantToCreate.chatId() == null
        ) {
            throw new IllegalArgumentException("Participant role, chat id and user id cannot be empty");
        }

        var participant = mapper.toEntity(participantToCreate);

        var createdParticipant = participantRepository.save(participant);

        return mapper.toDomain(createdParticipant);
    }

    @Override
    public Participant getParticipantById(Long id) {

        var participant = participantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found participant by id: " + id));

        return mapper.toDomain(participant);
    }

    @Override
    public Participant updateParticipant(Long id, Participant newParticipant) {

        var oldParticipant = participantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found participant by id: " + id));

        if (newParticipant.id() != null){
            throw new IllegalArgumentException("Participant id should be empty");
        }

        var updatedParticipant = mapper.toEntity(newParticipant);
        updatedParticipant.setId(oldParticipant.getId());
        updatedParticipant.setChat(oldParticipant.getChat());
        updatedParticipant.setUser(oldParticipant.getUser());

        participantRepository.save(updatedParticipant);

        return mapper.toDomain(updatedParticipant);
    }

    @Override
    public void deleteParticipantById(Long id) {

        var participant = participantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found participant by id: " + id));

        participantRepository.delete(participant);
    }
}

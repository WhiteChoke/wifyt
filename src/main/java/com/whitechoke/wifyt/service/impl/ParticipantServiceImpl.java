package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.Participant;
import com.whitechoke.wifyt.dto.mapper.ParticipantMapper;
import com.whitechoke.wifyt.entity.ChatEntity;
import com.whitechoke.wifyt.entity.ParticipantEntity;
import com.whitechoke.wifyt.entity.UserEntity;
import com.whitechoke.wifyt.enums.UserRoles;
import com.whitechoke.wifyt.repository.ParticipantRepository;
import com.whitechoke.wifyt.repository.UserRepository;
import com.whitechoke.wifyt.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
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

    @Override
    public void createAndSaveParticipantByUserId(Long id, UserRoles role, ChatEntity chat) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + id));

        var participant = new ParticipantEntity(
                user,
                role,
                chat
        );

        participantRepository.save(participant);
    }

    @Override
    public void createAndSaveParticipantByUserId(List<Long> ids, ChatEntity chat) {

        var users = userRepository.findAllById(ids);
        List<ParticipantEntity> participantEntityList = new ArrayList<>();

        for  (UserEntity user : users) {
            participantEntityList.add(
                    new ParticipantEntity(
                            user,
                            UserRoles.MEMBER,
                            chat
                    )
            );
        }

        participantRepository.saveAll(participantEntityList);
    }
}

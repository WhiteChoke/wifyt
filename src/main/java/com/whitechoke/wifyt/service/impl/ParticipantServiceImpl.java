package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.participant.Participant;
import com.whitechoke.wifyt.dto.mapper.ParticipantMapper;
import com.whitechoke.wifyt.dto.participant.ParticipantRequest;
import com.whitechoke.wifyt.entity.ChatEntity;
import com.whitechoke.wifyt.entity.ParticipantEntity;
import com.whitechoke.wifyt.entity.UserEntity;
import com.whitechoke.wifyt.enums.UserRoles;
import com.whitechoke.wifyt.repository.ChatRepository;
import com.whitechoke.wifyt.repository.ParticipantRepository;
import com.whitechoke.wifyt.repository.UserRepository;
import com.whitechoke.wifyt.service.ParticipantService;
import com.whitechoke.wifyt.web.validate.ValidateParticipant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final ParticipantMapper mapper;
    private final ValidateParticipant validate;

    @Override
    @Transactional
    public Participant createParticipant(ParticipantRequest participantToCreate) {

        validate.validateParticipant(participantToCreate);

        var user = userRepository.findById(participantToCreate.userId())
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + participantToCreate.userId()));
        var chat = chatRepository.findById(participantToCreate.chatId())
                .orElseThrow(() -> new EntityNotFoundException("Not found chat by id: " + participantToCreate.userId()));

        var participant = mapper.toEntity(participantToCreate);
        participant.setUser(user);
        participant.setChat(chat);

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
    @Transactional
    public Participant updateParticipantRole(ParticipantRequest participant) {

        var participantToUpdate = participantRepository
                .getParticipantBy(participant.userId(), participant.chatId())
                .orElseThrow(() -> new EntityNotFoundException("Not found participant"));

        participantToUpdate.setRole(participant.role());

        var updatedParticipant = participantRepository.save(participantToUpdate);

        return mapper.toDomain(updatedParticipant);
    }

    @Override
    @Transactional
    public void deleteParticipantById(Long id) {

        var participant = participantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found participant by id: " + id));

        participantRepository.delete(participant);
    }

    @Override
    @Transactional
    public void createParticipantByUserId(Long id, UserRoles role, Long chatId) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + id));

        var chat = entityManager.find(ChatEntity.class, chatId);

        var participant = new ParticipantEntity(
                user,
                role,
                chat
        );

        participantRepository.save(participant);
    }

    @Override
    @Transactional
    public void createParticipantsByUserIds(List<Long> ids, Long chatId) {

        var users = userRepository.findAllById(ids);
        List<ParticipantEntity> participantEntityList = new ArrayList<>();

        var chat = entityManager.find(ChatEntity.class, chatId);

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

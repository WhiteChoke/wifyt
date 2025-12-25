package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Participant;
import com.whitechoke.wifyt.entity.ChatEntity;
import com.whitechoke.wifyt.enums.ChatType;
import com.whitechoke.wifyt.enums.UserRoles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantService {
    Participant createParticipant(Participant participantToCreate);
    Participant getParticipantById(Long id);
    Participant updateParticipant(Long id, Participant newParticipant);
    void deleteParticipantById(Long id);
    void createAndSaveParticipantByUserId(Long id, UserRoles role, ChatEntity chat);
    void createAndSaveParticipantByUserId(List<Long> ids, ChatEntity chat);
}

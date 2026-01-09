package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Participant;
import com.whitechoke.wifyt.enums.UserRoles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantService {
    Participant createParticipant(Participant participantToCreate);
    Participant getParticipantById(Long id);
    Participant updateParticipantRole(Participant newParticipant);
    void deleteParticipantById(Long id);
    void createParticipantByUserId(Long id, UserRoles role, Long chatId);
    void createParticipantsByUserIds(List<Long> ids, Long chatId);
}

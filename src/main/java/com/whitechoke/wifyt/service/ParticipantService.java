package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Participant;
import org.springframework.stereotype.Service;

@Service
public interface ParticipantService {
    Participant createParticipant(Participant participantToCreate);
    Participant getParticipantById(Long id);
    Participant updateParticipant(Long id, Participant newParticipant);
    void deleteParticipantById(Long id);
}

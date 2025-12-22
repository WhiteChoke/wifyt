package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.Participant;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ParticipantService {
    Participant createParticipant(Participant participantToCreate);
    Participant getParticipantById(UUID id);
    Participant updateParticipant(UUID id, Participant newParticipant);
    void deleteParticipantById(UUID id);
}

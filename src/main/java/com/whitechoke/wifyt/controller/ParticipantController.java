package com.whitechoke.wifyt.controller;

import com.whitechoke.wifyt.dto.participant.Participant;
import com.whitechoke.wifyt.dto.participant.ParticipantRequest;
import com.whitechoke.wifyt.service.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/participant")
@AllArgsConstructor
@CrossOrigin
public class ParticipantController {

    private final ParticipantService service;

    @PostMapping
    public ResponseEntity<Participant> createParticipant(@RequestBody ParticipantRequest participantToCreate) {

        var createdParticipant = service.createParticipant(participantToCreate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdParticipant);
    }

    @GetMapping("{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {

        var participant = service.getParticipantById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(participant);
    }

    @PutMapping()
    public ResponseEntity<Participant> updateParticipantById(@RequestBody ParticipantRequest participant) {

        var updatedParticipant = service.updateParticipantRole(participant);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedParticipant);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteParticipantById(@PathVariable Long id) {

        service.deleteParticipantById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

package com.whitechoke.wifyt.controller;

import com.whitechoke.wifyt.dto.Message;
import com.whitechoke.wifyt.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/message")
@AllArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message messageToCreate) {

        var createdMessage = service.createMessage(messageToCreate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdMessage);
    }

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {

        var message = service.getMessageById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(message);
    }

    @PutMapping("{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id,
                                           @RequestBody Message MessageToUpdate) {

        var updatedMessage = service.updateMessage(id, MessageToUpdate);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedMessage);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Message> deleteMessageByID(@PathVariable Long id) {

        service.deleteMessageById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

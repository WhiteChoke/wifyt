package com.whitechoke.wifyt.controller;

import com.whitechoke.wifyt.dto.Chat;
import com.whitechoke.wifyt.dto.User;
import com.whitechoke.wifyt.service.ChatService;
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

import java.util.List;

@RestController
@RequestMapping("api/v1/chat")
@AllArgsConstructor
@CrossOrigin
public class ChatController {
    
    private final ChatService service;

    @GetMapping("user/{id}")
    public ResponseEntity<List<Chat>> getUserChats(@PathVariable Long id){

        var chats = service.getUserChats(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(chats);
    }

    @PostMapping
    public ResponseEntity<Chat> createPersonalChat(@RequestBody Chat chatToCreate) {
        
        var createdChat = service.createPersonalChat(chatToCreate);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdChat);
    }

    @PostMapping("{ownerId}")
    public ResponseEntity<Chat> createGroupChat(@RequestBody Chat chatToCreate,
                                                @PathVariable Long ownerId) {

        var createdChat = service.createGroupChat(ownerId, chatToCreate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdChat);
    }

    @GetMapping("{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable Long id) {
        
        var chat = service.getChatById(id);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(chat);
    }

    @PutMapping("{id}")
    public ResponseEntity<Chat> updateChat(@PathVariable Long id,
                                           @RequestBody Chat ChatToUpdate) {

        var updatedChat = service.updateChat(id, ChatToUpdate);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedChat);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Chat> deleteChatByID(@PathVariable Long id) {

        service.deleteChatById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

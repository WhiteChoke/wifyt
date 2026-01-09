package com.whitechoke.wifyt.controller;

import com.whitechoke.wifyt.dto.message.MessageRequest;
import com.whitechoke.wifyt.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@AllArgsConstructor
@CrossOrigin
public class MessageController {

    private final MessageService service;

    @MessageMapping("/chat/{chatId}")
    public void handleIncomingMessage(@DestinationVariable Long chatId,
                                      @Payload MessageRequest message) {
            service.handleIncomingMessage(chatId, message);
    }
}

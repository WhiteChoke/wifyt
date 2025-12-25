package com.whitechoke.wifyt.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collation = "messages")
@AllArgsConstructor
public class MessageEntity {

    @Id
    private String id;
    private Long senderId;
    private Long chatId;
    // will soon be replaced by
    private String message;
    private Instant created_at;
}

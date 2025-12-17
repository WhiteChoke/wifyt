package com.whitechoke.wifyt.entity;

import com.whitechoke.wifyt.enums.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document
public class MessageStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Status status;
    private Instant updatedAt;
    private UUID userId;
    private UUID messageId;


}

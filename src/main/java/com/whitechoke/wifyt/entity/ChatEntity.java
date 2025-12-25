package com.whitechoke.wifyt.entity;

import com.whitechoke.wifyt.enums.ChatType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "chats")
@AllArgsConstructor
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ChatType type = ChatType.PERSONAL;

    @OneToMany(mappedBy = "chat")
    private List<ParticipantEntity> participantList;

    public ChatEntity() {};
}

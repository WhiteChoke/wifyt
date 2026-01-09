package com.whitechoke.wifyt.entity;

import com.whitechoke.wifyt.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "participants")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatEntity chat;

    public ParticipantEntity() {}

    public ParticipantEntity(UserEntity user, UserRoles role, ChatEntity chat) {
        this.user = user;
        this.role = role;
        this.chat = chat;
    }

    public ParticipantEntity(Long id, UserRoles role) {
        this.id = id;
        this.role = role;
    }
}

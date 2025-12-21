package com.whitechoke.wifyt.dto.mapper;

import com.whitechoke.wifyt.entity.ChatEntity;
import com.whitechoke.wifyt.enums.ChatType;
import com.whitechoke.wifyt.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChatMapperTest {

    @Mock
    ParticipantRepository participantRepository;
    @InjectMocks
    ChatMapper chatMapper;

    @Test
    void toDomain() {
        ChatEntity entity = new ChatEntity();
        entity.setId(UUID.randomUUID());
        entity.setName("test chat");
        entity.setType(ChatType.GROUP);
        entity.setCreatedAt(Instant.now());

        var dto = chatMapper.toDomain(entity);

        assertEquals(entity.getId(), dto.id());
        assertEquals(entity.getName(), dto.name());
        assertEquals(entity.getType(), dto.chatType());

    }

    @Test
    void toEntity() {
    }
}
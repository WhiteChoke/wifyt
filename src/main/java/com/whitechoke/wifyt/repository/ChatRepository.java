package com.whitechoke.wifyt.repository;

import com.whitechoke.wifyt.dto.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}

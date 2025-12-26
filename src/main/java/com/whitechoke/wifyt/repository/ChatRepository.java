package com.whitechoke.wifyt.repository;

import com.whitechoke.wifyt.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    @Query("""
        SELECT p.chat FROM ParticipantEntity p
                WHERE p.user.id = :userId
                """)
    List<ChatEntity> getUserChats(@Param("userId") Long userId);

}

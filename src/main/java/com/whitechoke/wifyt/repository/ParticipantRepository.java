package com.whitechoke.wifyt.repository;

import com.whitechoke.wifyt.entity.ParticipantEntity;
import com.whitechoke.wifyt.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    @Query("""
        SELECT p FROM ParticipantEntity p
                WHERE p.user.id = :userId
                AND p.chat.id = :chatId
            """)
    Optional<ParticipantEntity> getParticipantBy(@Param("userId") Long userId,
                                                 @Param("chatId") Long chatId);

}

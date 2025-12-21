package com.whitechoke.wifyt.repository;

import com.whitechoke.wifyt.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, UUID> {

}

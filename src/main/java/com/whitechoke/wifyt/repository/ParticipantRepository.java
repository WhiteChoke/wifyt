package com.whitechoke.wifyt.repository;

import com.whitechoke.wifyt.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

}
`
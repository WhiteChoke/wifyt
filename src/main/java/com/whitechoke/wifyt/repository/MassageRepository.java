package com.whitechoke.wifyt.repository;

import com.whitechoke.wifyt.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MassageRepository extends MongoRepository<MessageEntity, UUID> {
}

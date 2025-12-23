package com.whitechoke.wifyt.repository;

import com.whitechoke.wifyt.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassageRepository extends MongoRepository<MessageEntity, Long> {
}

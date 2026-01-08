package com.whitechoke.wifyt.dto.mapper;

import com.whitechoke.wifyt.dto.RequestToCreateUser;
import com.whitechoke.wifyt.dto.User;
import com.whitechoke.wifyt.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getCreatedAt()
        );
    }

    public UserEntity toEntity(User dto) {
        return new UserEntity(
                dto.id(),
                dto.username(),
                dto.phoneNumber(),
                dto.email(),
                dto.password(),
                dto.createdAt()
        );
    }

    public UserEntity toEntity(RequestToCreateUser request) {
        var entity = new UserEntity();
        entity.setUsername(request.username());
        entity.setPassword(request.password());
        entity.setEmail(request.email());

        return entity;
    }

}

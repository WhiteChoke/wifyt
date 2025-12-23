package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.User;
import com.whitechoke.wifyt.dto.mapper.UserMapper;
import com.whitechoke.wifyt.repository.UserRepository;
import com.whitechoke.wifyt.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User userToCreate) {
        if (userToCreate.id() != null || userToCreate.createdAt() != null) {
            throw new IllegalArgumentException("User id and creation time should be empty");
        }

        var userToSave = mapper.toEntity(userToCreate);
        userToSave.setCreatedAt(Instant.now());

        var createdUser = userRepository.save(userToSave);

        log.info("created new user with id: {}", createdUser.getId());

        return mapper.toDomain(createdUser);
    }

    @Override
    public User getUserById(Long id) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + id));

        return mapper.toDomain(user);
    }

    @Override
    public User updateUser(Long id, User userToUpdate) {

        if (userToUpdate.id() != null || userToUpdate.createdAt() != null) {
            throw new IllegalArgumentException("User id and creation time should be empty");
        }

        var oldUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + id));

        var updatedUser = mapper.toEntity(userToUpdate);
        updatedUser.setId(id);
        updatedUser.setCreatedAt(oldUser.getCreatedAt());

        userRepository.save(updatedUser);

        log.info("Updated user with id: {}", id);

        return mapper.toDomain(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {

        var userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + id));

        userRepository.delete(userToDelete);

        log.info("Deleted user with id: {}", id);
    }
}

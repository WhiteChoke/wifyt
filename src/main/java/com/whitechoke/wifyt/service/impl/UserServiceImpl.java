package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.dto.user.UserRequest;
import com.whitechoke.wifyt.dto.user.User;
import com.whitechoke.wifyt.dto.mapper.UserMapper;
import com.whitechoke.wifyt.repository.UserRepository;
import com.whitechoke.wifyt.service.UserService;
import com.whitechoke.wifyt.web.validate.ValidateUser;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final ValidateUser validate;

    @Override
    @Transactional
    public User createUser(UserRequest userToCreate) {

        validate.validateUser(userToCreate);

        var userToSave = mapper.toEntity(userToCreate);
        userToSave.setCreatedAt(Instant.now());

        var createdUser = userRepository.save(userToSave);

        logger.info("created new user with id: {}", createdUser.getId());

        return mapper.toDomain(createdUser);
    }

    @Override
    public User getUserById(Long id) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + id));

        return mapper.toDomain(user);
    }

    @Override
    @Transactional
    public User updateUser(Long id, UserRequest userToUpdate) {

        var oldUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + id));

        var updatedUser = mapper.toEntity(userToUpdate);
        updatedUser.setId(id);
        updatedUser.setCreatedAt(oldUser.getCreatedAt());

        userRepository.save(updatedUser);

        logger.info("Updated user with id: {}", id);

        return mapper.toDomain(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {

        var userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by id: " + id));

        userRepository.delete(userToDelete);

        logger.info("Deleted user with id: {}", id);
    }
}

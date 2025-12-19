package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    User createUser(User userToCreate);
    User getUserById(UUID id);
    User updateUser(UUID id, User userToUpdate);
    void deleteUserById(UUID id);
}

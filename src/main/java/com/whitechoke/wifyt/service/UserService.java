package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(User userToCreate);
    User getUserById(Long id);
    User updateUser(Long id, User userToUpdate);
    void deleteUserById(Long id);
}

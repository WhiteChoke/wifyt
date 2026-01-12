package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.user.impl.UserRequest;
import com.whitechoke.wifyt.dto.user.impl.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(UserRequest userToCreate);
    User getUserById(Long id);
    User updateUser(Long id, UserRequest userToUpdate);
    void deleteUserById(Long id);
}

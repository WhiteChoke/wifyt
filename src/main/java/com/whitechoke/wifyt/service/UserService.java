package com.whitechoke.wifyt.service;

import com.whitechoke.wifyt.dto.user.UserRequest;
import com.whitechoke.wifyt.dto.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(UserRequest userToCreate);
    User getUserById();
    User updateUser(Long id, UserRequest userToUpdate);
    void deleteUserById();
}

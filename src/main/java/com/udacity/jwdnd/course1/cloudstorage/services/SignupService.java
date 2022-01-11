package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    private UserMapper userMapper;

    public SignupService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int signup(User user) {
        int userId = userMapper.insertUser(user);
        return userId;
    }
}

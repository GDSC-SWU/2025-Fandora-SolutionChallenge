package com.group.Fandora.service;

import com.group.Fandora.entity.User;
import com.group.Fandora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String getUserNameById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getName();
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }
}
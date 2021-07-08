package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import com.ciembro.healthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUserDetails(UserToRegisterDto user){
      return validateUsername(user.getUsername())
              && validatePassword(user.getPassword())
              && validateEmail(user.getEmail());
    }

    private boolean validateUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return (user.isEmpty() && username != null && !username.isEmpty());
    }

    private boolean validatePassword(String password){
        return password != null && !password.isEmpty();
    }

    private boolean validateEmail(String email){
        return email != null && !email.isEmpty();
    }
}

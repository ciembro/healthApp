package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public void changeUserLocation(String location, String username) throws UserNotFoundException {
        User user = findByUsername(username);
        user.setLocation(location);
        save(user);
    }

}

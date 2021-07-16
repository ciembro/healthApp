package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(UserToRegisterDto userToRegisterDto){
        return new User(userToRegisterDto.getUsername(),
                userToRegisterDto.getLocation(),
                userToRegisterDto.getEmail(),
                userToRegisterDto.getPassword());
    }
}

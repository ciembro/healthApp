package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import com.ciembro.healthApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest()
class UserValidatorTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserValidator userValidator;

    @Test
    void testValidateUserWhenCorrectData(){
        //given
        UserToRegisterDto user = new UserToRegisterDto("new user", "user@mail.com", "pass");
        //when
        boolean isCorrect = userValidator.validateUserDetails(user);
        //then
        assertTrue(isCorrect);
    }

    @Test
    void testValidateUserWhenEmptyUsername(){
        //given
        UserToRegisterDto user = new UserToRegisterDto("", "user@mail.com", "pass");
        //when
        boolean isCorrect = userValidator.validateUserDetails(user);
        //then
        assertFalse(isCorrect);
    }

    @Test
    void testValidateUserWhenEmptyEmail(){
        //given
        UserToRegisterDto user = new UserToRegisterDto("user", "", "pass");
        //when
        boolean isCorrect = userValidator.validateUserDetails(user);
        //then
        assertFalse(isCorrect);
    }

    @Test
    void testValidateUserWhenEmptyPassword(){
        //given
        UserToRegisterDto user = new UserToRegisterDto("user", "user@mail.com", "");
        //when
        boolean isCorrect = userValidator.validateUserDetails(user);
        //then
        assertFalse(isCorrect);
    }

    @Test
    void testValidateUserWhenUsernameTaken(){
        //given
        UserToRegisterDto userToRegister = new UserToRegisterDto("reg user", "user@mail.com", "pass");
        User user = new User("reg user", "user@mail.com", "password");
        user = userRepository.save(user);
        //when
        boolean isCorrect = userValidator.validateUserDetails(userToRegister);
        //then
        assertFalse(isCorrect);
        //cleanup
        userRepository.deleteById(user.getId());
    }
}
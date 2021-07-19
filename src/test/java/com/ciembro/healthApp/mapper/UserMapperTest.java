package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void shouldMapToUser() {
        //given
        UserToRegisterDto dto = new UserToRegisterDto("user", "Krakow",
                "user@email.com", "password");
        //when
        User user = userMapper.mapToUser(dto);

        //then
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getLocation(), user.getLocation());
        assertEquals(dto.getPassword(), user.getPassword());
    }
}
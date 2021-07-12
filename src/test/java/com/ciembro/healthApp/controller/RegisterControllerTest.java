package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import com.ciembro.healthApp.mapper.UserMapper;
import com.ciembro.healthApp.repository.UserRepository;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    void testRegisterWhenValidData() throws Exception {
        //given
        User user =  new User("user", "user@email.com", "pass");
        user = userRepository.save(user);

        UserToRegisterDto userToRegister = new UserToRegisterDto("next user",
                "user@email.com",
                "pass");

        when(userMapper.mapToUser(userToRegister)).thenReturn
                (new User("next user", "user@email.com", "pass"));
        Gson gson = new Gson();
        String registrationDto = gson.toJson(userToRegister);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/v1/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(registrationDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));

        //cleanup
        userRepository.deleteById(user.getId());
    }

    @Test
    void testRegisterWhenUserAlreadyExists() throws Exception {
        //given
        User user =  new User("user", "user@email.com", "pass");
        user = userRepository.save(user);

        UserToRegisterDto userToRegister = new UserToRegisterDto("user",
                "user@email.com",
                "pass");

        when(userMapper.mapToUser(userToRegister)).thenReturn
                (new User("user", "user@email.com", "pass"));
        Gson gson = new Gson();
        String registrationDto = gson.toJson(userToRegister);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(registrationDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(false)));

        //cleanup
        userRepository.deleteById(user.getId());
    }

}
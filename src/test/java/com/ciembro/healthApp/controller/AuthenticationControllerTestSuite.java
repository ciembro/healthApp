package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.controller.AuthenticationController;
import com.ciembro.healthApp.domain.AuthenticationRequest;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.repository.UserRepository;
import com.ciembro.healthApp.security.MyUserDetailsService;
import com.ciembro.healthApp.security.domain.JwtUtil;
import com.ciembro.healthApp.security.domain.MyUserDetails;
import com.google.gson.Gson;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
//@WebMvcTest(AuthenticationController.class)
//load full app configuration
@AutoConfigureMockMvc
@SpringBootTest
public class AuthenticationControllerTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private  JwtUtil jwtTokenUtil;


    @Test
    public void testAuthenticationWhenLoginCredentialsCorrect() throws Exception {
        //given
        String password = passwordEncoder.encode("pass");
        User user = new User("user", "Krakow","user@email.com", password);
        user = userRepository.save(user);

        UserDetails userDetails = new MyUserDetails(user);
        when(userDetailsService
                .loadUserByUsername(user.getUsername())).thenReturn(userDetails);

        when(jwtTokenUtil.generateToken(userDetails)).thenReturn("token");

        Gson gson = new Gson();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("user", "pass");
        String jsonContent = gson.toJson(authenticationRequest);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.jwt", Matchers.is("token")));

        //cleanup
        userRepository.deleteById(user.getId());
    }

    @Test
    public void testAuthenticationWhenLoginCredentialsNotCorrect() throws Exception {

        Gson gson = new Gson();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("user", "pass");
        String jsonContent = gson.toJson(authenticationRequest);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isForbidden());
    }


}

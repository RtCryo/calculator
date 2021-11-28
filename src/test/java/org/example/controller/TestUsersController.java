package org.example.controller;

import org.example.config.SecurityConfig;
import org.example.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {
        TestConfig.class,
        UsersController.class,
        WithMockCustomUserSecurityContextFactory.class,
        SecurityConfig.class})
class TestUsersController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void usersGetControllerUnauthorized() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockCustomUser(roles = Role.USER)
    void usersGetControllerAsUser() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockCustomUser(roles = Role.ADMIN)
    void usersGetControllerAsAdmin() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

package org.example.controller;

import org.example.config.SecurityConfig;
import org.example.model.Role;
import org.example.service.MyUserDetailsService;
import org.example.service.UsersDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {
        TestUsersController.Config.class,
        UsersController.class,
        WithMockCustomUserSecurityContextFactory.class,
        SecurityConfig.class})
public class TestUsersController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void usersGetControllerUnauthorized() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().is3xxRedirection());
        this.mockMvc.perform(get("/userslist/getlist"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockCustomUser(roles = Role.USER)
    public void usersGetControllerAsUser() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get("/userslist/getlist"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockCustomUser(roles = Role.ADMIN)
    public void usersGetControllerAsAdmin() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<ul id = \"userslist\">")));
        this.mockMvc.perform(get("/userslist/getlist"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @TestConfiguration
    public static class Config{

        @MockBean
        @Qualifier(value = "myUserDetailsService")
        public MyUserDetailsService myUserDetailsService;
//
//        @MockBean
//        public ExpressionDaoService expressionDaoService;
//
//        @MockBean
//        public CalculationService calculationService;

        @MockBean
        public UsersDaoService usersDaoService;

    }
}

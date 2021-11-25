package org.example.controller;

import org.example.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LoginController.class)
@ContextConfiguration(classes = {
        TestConfig.class,
        SecurityConfig.class,
        LoginController.class})
class TestLoginController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginUnauthenticatedGetController() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

    @Test
    @WithMockUser(username = "bob")
    void loginAuthenticatedGetController() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("bob")));
    }

    @Test
    void loginUserIncorrectPostController() throws Exception {
        this.mockMvc.perform(formLogin("/login").user("qwerty").password("111"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login?error=true"));
    }
}

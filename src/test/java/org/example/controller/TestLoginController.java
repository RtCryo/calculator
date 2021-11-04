package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoginController.class)
@ContextConfiguration(classes = {TestLoginController.Config.class, WithMockCustomUserSecurityContextFactory.class})
public class TestLoginController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLoginControllerUnauthenticated() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("class=\"form-control\" placeholder=\"Username\"")));
    }

    @Test
    public void getLoginControllerAuthenticated() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Configuration
    static class Config{

    }
}

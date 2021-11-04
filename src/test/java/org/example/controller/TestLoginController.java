package org.example.controller;

import org.example.config.SecurityConfig;
import org.example.service.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoginController.class)
@ContextConfiguration(classes = {TestLoginController.Config.class, WithMockCustomUserSecurityContextFactory.class, SecurityConfig.class, LoginController.class})
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
    public void postLoginControllerUnauthenticated() throws Exception {
        this.mockMvc.perform(formLogin("/login").user("bob").password("111"))
                .andExpect(status().isOk());
    }

    @Configuration
    static class Config{

        @MockBean
        @Qualifier("myUserDetailsService")
        public MyUserDetailsService myUserDetailsService;

    }
}

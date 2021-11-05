package org.example.controller;

import org.example.config.SecurityConfig;
import org.example.service.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LoginController.class)
@ContextConfiguration(classes = {TestLoginController.Config.class, WithMockCustomUserSecurityContextFactory.class, SecurityConfig.class, LoginController.class})
public class TestLoginController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLoginControllerUnauthenticated() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("class=\"form-control\" id=\"username\" name=\"username\"")));
    }

    @Test
    @WithMockUser
    public void getLoginControllerAuthenticated() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/calc"));
    }

    @Test
    public void postLoginControllerUserInccorect() throws Exception {
        this.mockMvc.perform(formLogin("/login").user("qwerty").password("111"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/login?error=true"));
    }

    @Configuration
    static class Config{

        @MockBean
        @Qualifier("myUserDetailsService")
        public MyUserDetailsService myUserDetailsService;

    }
}

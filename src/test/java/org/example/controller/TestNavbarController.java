package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {NavbarController.class})
class TestNavbarController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userNameNavbarGetControllerUnauthorized() throws Exception {
        this.mockMvc.perform(get("/navbarRequest"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "bob")
    void userNameNavbarGetControllerAuthorized() throws Exception {
        this.mockMvc.perform(get("/navbarRequest"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("bob")));
    }

}

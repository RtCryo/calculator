package org.example.controller;

import org.example.config.SecurityConfig;
import org.example.service.CalculationService;
import org.example.service.ExpressionDaoService;
import org.junit.internal.Classes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
//@ContextConfiguration(classes = TestLoginController.Config.class)
@ContextConfiguration(classes = {TestLoginController.Config.class, CalcController.class, IndexController.class})
@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class TestLoginController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private ExpressionDaoService expressionDaoService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("r674574")));
    }

    @Test
    @WithMockUser("sdfsd")
    public void shouldReturnCalcMessage() throws Exception {
        Mockito.when(expressionDaoService.listToView(10)).thenReturn(Collections.EMPTY_LIST);
        this.mockMvc.perform(get("/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("6757")));
    }

    @Configuration
    static class Config{

        @MockBean
        private ExpressionDaoService expressionDaoService;

        @MockBean
        private CalculationService calculationService;

    }
}

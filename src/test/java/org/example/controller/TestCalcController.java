package org.example.controller;

import org.example.config.SecurityConfig;
import org.example.model.Expression;
import org.example.service.CalculationService;
import org.example.service.ExpressionDaoService;
import org.example.service.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalcController.class)
@ContextConfiguration(classes = {TestCalcController.Config.class, CalcController.class, SecurityConfig.class, WithMockCustomUserSecurityContextFactory.class})
public class TestCalcController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public ExpressionDaoService expressionDaoService;

    @Autowired
    public CalculationService calculationService;

    @Test
    @WithMockCustomUser
    public void calcGetController() throws Exception {
        this.mockMvc.perform(get("/calc")).andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    public void expressionListGetController() throws Exception {
        this.mockMvc.perform(get("/calc/expressions")).andExpect(status().isOk());
        Mockito.verify(expressionDaoService, Mockito.times(1)).listToView(10);
    }

    @Test
    @WithMockCustomUser
    public void expressionSavePostController() throws Exception {
        this.mockMvc.perform(post("/calc/expressions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":\"18\",\"expressionList\":\"9+1\",\"result\":\"10\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        Mockito.verify(expressionDaoService, Mockito.times(1)).expressionToSave(new Expression());
    }

    @Test
    @WithMockCustomUser
    public void processRequestPostController() throws Exception {
        this.mockMvc.perform(post("/calc/subtotal")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstVar", "18")
                .param("secondVar", "9")
                .param("operation", "+"))
                .andExpect(status().isOk());
        Mockito.verify(calculationService, Mockito.times(1)).calculate("18","9","+");
    }

    @TestConfiguration
    public static class Config{

        @MockBean
        @Qualifier(value = "myUserDetailsService")
        public MyUserDetailsService myUserDetailsService;

        @MockBean
        public ExpressionDaoService expressionDaoService;

        @MockBean
        public CalculationService calculationService;

    }

}

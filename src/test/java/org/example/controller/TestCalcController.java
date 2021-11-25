package org.example.controller;

import org.example.config.SecurityConfig;
import org.example.dto.ExpressionDTO;
import org.example.service.CalculationService;
import org.example.service.ExpressionDaoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalcController.class)
@ContextConfiguration(classes = {
        TestConfig.class,
        CalcController.class,
        SecurityConfig.class,
        WithMockCustomUserSecurityContextFactory.class})
class TestCalcController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public ExpressionDaoService expressionDaoService;

    @Autowired
    public CalculationService calculationService;

    @Test
    @WithMockCustomUser
    void calcGetController() throws Exception {
        this.mockMvc.perform(get("/calc")).andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    void expressionListGetController() throws Exception {
        this.mockMvc.perform(get("/calc")).andExpect(status().isOk());
        Mockito.verify(expressionDaoService, Mockito.times(1)).listToView(10);
    }

    @Test
    @WithMockCustomUser
    void expressionSavePostController() throws Exception {
        this.mockMvc.perform(put("/calc/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":\"18\",\"expressionList\":\"9+1\",\"result\":\"10\"}]"))
                .andExpect(status().isCreated());
        Mockito.verify(expressionDaoService, Mockito.times(1)).expressionToSave(new ExpressionDTO());
    }

    @Test
    @WithMockCustomUser
    void processRequestPostController() throws Exception {
        this.mockMvc.perform(post("/calc/subtotal")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstVar", "18")
                .param("secondVar", "9")
                .param("operation", "+"))
                .andExpect(status().isOk());
        Mockito.verify(calculationService, Mockito.times(1)).calculate("18","9","+");
    }

}

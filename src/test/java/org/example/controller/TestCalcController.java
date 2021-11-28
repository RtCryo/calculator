package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.SecurityConfig;
import org.example.dto.ExpressionDTO;
import org.example.service.CalculationService;
import org.example.service.ExpressionDaoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    private static final ObjectMapper mapper=new ObjectMapper();
    private static Map<String,String> expression;

    @BeforeAll
    public static void inizialize(){
        expression = new HashMap<>();
        expression.put("firstValue","18");
        expression.put("secondValue","9");
        expression.put("operation","+");
        expression.put("id","18");
        expression.put("expressionList","9 + 1 = 10");
        expression.put("result","10");
    }

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
        this.mockMvc.perform(put("/calc/expression/add")
                .content(mapper.writeValueAsString(expression))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        Mockito.verify(expressionDaoService, Mockito.times(1)).expressionToSave(
                new ExpressionDTO((long) 18, "9 + 1 = 10", "10", null, "18", "9", "+"));
    }

    @Test
    @WithMockCustomUser
    void processRequestPostController() throws Exception {
        this.mockMvc.perform(post("/calc/expression/calculate")
                .content(mapper.writeValueAsString(expression))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(calculationService, Mockito.times(1)).calculate("18","9","+");
    }
}

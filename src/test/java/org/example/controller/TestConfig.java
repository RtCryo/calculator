package org.example.controller;

import org.example.service.CalculationService;
import org.example.service.ExpressionDaoService;
import org.example.service.MyUserDetailsService;
import org.example.service.UsersDaoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestConfig {

    @MockBean
    @Qualifier(value = "myUserDetailsService")
    public MyUserDetailsService myUserDetailsService;

    @MockBean
    public UsersDaoService usersDaoService;

    @MockBean
    public ExpressionDaoService expressionDaoService;

    @MockBean
    public CalculationService calculationService;

}

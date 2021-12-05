package org.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class TestCalculationService {

    static CalculationService calculationService;

    @BeforeAll
    public static void inizialize(){
        calculationService = new CalculationService();
    }

    @Test
    void calculationPlus(){
        String[] actual = new String[5];
        actual[0] = calculationService.calculate("5","1","+");
        actual[1] = calculationService.calculate("1","8","+");
        actual[2] = calculationService.calculate("5.4","1.2","+");
        actual[3] = calculationService.calculate("-5","1","+");
        actual[4] = calculationService.calculate("-6","9","+");
        Assertions.assertArrayEquals(new String[]{"6", "9", "6.6", "-4", "3"},actual);
    }

    @Test
    void calculationMinus(){
        String[] actual = new String[5];
        actual[0] = calculationService.calculate("5","1","-");
        actual[1] = calculationService.calculate("1","8","-");
        actual[2] = calculationService.calculate("5.4","1.2","-");
        actual[3] = calculationService.calculate("-5","1","-");
        actual[4] = calculationService.calculate("-6","9","-");
        Assertions.assertArrayEquals(new String[]{"4", "-7", "4.2", "-6", "-15"},actual);
    }

    @Test
    void calculationMultiply(){
        String[] actual = new String[5];
        actual[0] = calculationService.calculate("5","1","*");
        actual[1] = calculationService.calculate("1","8","*");
        actual[2] = calculationService.calculate("5.4","1.2","*");
        actual[3] = calculationService.calculate("-5","1","*");
        actual[4] = calculationService.calculate("-6","9","*");
        Assertions.assertArrayEquals(new String[]{"5", "8", "6.48", "-5", "-54"},actual);
    }

    @Test
    void calculationDivision(){
        String[] actual = new String[5];
        actual[0] = calculationService.calculate("5","1","/");
        actual[1] = calculationService.calculate("1","8","/");
        actual[2] = calculationService.calculate("5.4","1.2","/");
        actual[3] = calculationService.calculate("-5","1","/");
        actual[4] = calculationService.calculate("-6","12","/");
        Assertions.assertArrayEquals(new String[]{"5", "0.125", "4.5", "-5", "-0.5"},actual);
    }

    @Test
    void calculationDivisionByZero(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                calculationService.calculate("5","0","/"));
    }

}

package org.example.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCalculationService {

    CalculationService calculationService;

    @Before
    public void inizialize(){
        calculationService = new CalculationService();
    }

    @Test
    public void calculationPlus(){
        String[] actual = new String[5];
        actual[0] = calculationService.calculate("5","1","+");
        actual[1] = calculationService.calculate("1","8","+");
        actual[2] = calculationService.calculate("5.4","1.2","+");
        actual[3] = calculationService.calculate("-5","1","+");
        actual[4] = calculationService.calculate("-6","9","+");
        Assert.assertArrayEquals(new String[]{"6", "9", "6,6", "-4", "3"},actual);
    }

    @Test
    public void calculationMinus(){
        String[] actual = new String[5];
        actual[0] = calculationService.calculate("5","1","-");
        actual[1] = calculationService.calculate("1","8","-");
        actual[2] = calculationService.calculate("5.4","1.2","-");
        actual[3] = calculationService.calculate("-5","1","-");
        actual[4] = calculationService.calculate("-6","9","-");
        Assert.assertArrayEquals(new String[]{"4", "-7", "4,2", "-6", "-15"},actual);
    }

    @Test
    public void calculationMultiply(){
        String[] actual = new String[5];
        actual[0] = calculationService.calculate("5","1","*");
        actual[1] = calculationService.calculate("1","8","*");
        actual[2] = calculationService.calculate("5.4","1.2","*");
        actual[3] = calculationService.calculate("-5","1","*");
        actual[4] = calculationService.calculate("-6","9","*");
        Assert.assertArrayEquals(new String[]{"5", "8", "6,48", "-5", "-54"},actual);
    }

    @Test
    public void calculationDivision(){
        String[] actual = new String[5];
        actual[0] = calculationService.calculate("5","1","/");
        actual[1] = calculationService.calculate("1","8","/");
        actual[2] = calculationService.calculate("5.4","1.2","/");
        actual[3] = calculationService.calculate("-5","1","/");
        actual[4] = calculationService.calculate("-6","12","/");
        Assert.assertArrayEquals(new String[]{"5", "0,125", "4,5", "-5", "-0,5"},actual);
    }

    @Test
    public void calculationDivisionByZero(){
        Assert.assertThrows(IllegalArgumentException.class, () ->
                calculationService.calculate("5","0","/"));
    }

}
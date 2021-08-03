package controller;

import service.CalculationService;

public class Main {
    public static void main(String[] args) {
        System.out.println(new CalculationService().calculate("9.2", "1.8", "+"));
    }
}

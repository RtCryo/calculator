package service;

import java.text.DecimalFormat;

public class CalculationService {
    public String calculate(String firstVar, String secondVar, String operation){
        System.out.println(">>>> x1 = " + firstVar + " | x2 = " + secondVar + " | op = " + operation);
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        double doubleFirstVar = Double.parseDouble(firstVar);
        double doubleSecondVar = Double.parseDouble(secondVar);
        double tempVar = 0;
        String result = "";
        switch (operation) {
            case "+": tempVar = (doubleFirstVar + doubleSecondVar);
                break;
            case "-": tempVar = (doubleFirstVar - doubleSecondVar);
                break;
            case "*": tempVar = (doubleFirstVar * doubleSecondVar);
                break;
            case "/": tempVar = (doubleFirstVar / doubleSecondVar);
                break;
            default: result = "operation not supported";
                break;
        }

        if(result.isEmpty()) {
            result = decimalFormat.format(tempVar);
        }
        System.out.println(result);
        return result;
    }
}

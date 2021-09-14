package org.example.service;

import org.springframework.stereotype.Service;
import java.text.DecimalFormat;

@Service
public class CalculationService {
    public String calculate(String firstVar, String secondVar, String operation){
        if (Integer.parseInt(secondVar) == 0 && operation.equals("/")){
            throw new IllegalArgumentException("Division by zero");
        }
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
        return result;
    }
}

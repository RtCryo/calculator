package org.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Component
@RequiredArgsConstructor
public class CalculationService {

    public String calculate(String firstVar, String secondVar, String operation){
        if (Double.parseDouble(secondVar.replace(",", ".")) == 0 && operation.equals("/")){
            throw new IllegalArgumentException("Division by zero");
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        DecimalFormatSymbols sym = DecimalFormatSymbols.getInstance();
        sym.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(sym);
        double doubleFirstVar = Double.parseDouble(firstVar.replace(",", "."));
        double doubleSecondVar = Double.parseDouble(secondVar.replace(",", "."));
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
package service;

public class CalculationService {
    public String calculate(String firstVar, String secondVar, String operation){
        System.out.println(">>>> x1 = " + firstVar + " | x2 = " + secondVar + " | op = " + operation);
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
            if ((int) tempVar == tempVar) {
                result = String.valueOf((int)tempVar);
            } else {
                result = String.format("%.5f",tempVar);
            }
        }
        System.out.println(result);
        return result;
    }
}

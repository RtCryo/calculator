package org.example.service;

import lombok.Data;
import org.example.dto.ExpressionDTO;
import org.example.model.Expression;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;

@Service
public class CalculationService {

    private final ExpressionDaoService expressionDaoService;

    public CalculationService(ExpressionDaoService expressionDaoService) {
        this.expressionDaoService = expressionDaoService;
    }

    public String calculate(String firstVar, String secondVar, String operation){
        if (Double.parseDouble(secondVar.replace(",", ".")) == 0 && operation.equals("/")){
            ExpressionClass.getExpressionClass().setLastButton("error");
            throw new IllegalArgumentException("Division by zero");
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
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

    public ExpressionDTO cancelExpression(){
        ExpressionClass.refreshClass();
        return ExpressionClass.getExpressionClass().getDTO();
    }

    public ExpressionDTO commaButton() {
        if(ExpressionClass.getExpressionClass().getResult().contains(",") && !ExpressionClass.getExpressionClass().getLastButton().equals("op")
                && !ExpressionClass.getExpressionClass().getLastButton().equals("enter")) {
            return ExpressionClass.getExpressionClass().getDTO();
        }
        if(ExpressionClass.getExpressionClass().getLastButton().equals("enter") ||
                ExpressionClass.getExpressionClass().getLastButton().equals("op") ||
                ExpressionClass.getExpressionClass().getLastButton().equals(""))
        {
            if(!ExpressionClass.getExpressionClass().isFirstVal) {
                ExpressionClass.getExpressionClass().setFirstVal("0", true);
            } else {
                ExpressionClass.getExpressionClass().setSecondVal("0", true);
            }
        }
        if (!ExpressionClass.getExpressionClass().isFirstVal) {
            ExpressionClass.getExpressionClass().setFirstVal(",", false);
            ExpressionClass.getExpressionClass().setResult(ExpressionClass.getExpressionClass().getFirstVal());
        } else {
            ExpressionClass.getExpressionClass().setSecondVal(",", false);
            ExpressionClass.getExpressionClass().setResult(ExpressionClass.getExpressionClass().getSecondVal());
        }
        ExpressionClass.getExpressionClass().setLastButton("comma");
        return ExpressionClass.getExpressionClass().getDTO();
    }

    public ExpressionDTO saveExpression(){
        if(ExpressionClass.getExpressionClass().getFirstVal() == null ||
                ExpressionClass.getExpressionClass().getSecondVal() == null ||
                ExpressionClass.getExpressionClass().getResult() == null ||
                ExpressionClass.getExpressionClass().getExpressionList() == null) {
            ExpressionClass.refreshClass();
            return ExpressionClass.getExpressionClass().getDTO();
        }

        if(ExpressionClass.getExpressionClass().getLastButton().equals("op")) {
            ExpressionClass.getExpressionClass().setSecondVal(
                    ExpressionClass.getExpressionClass().getFirstVal(), true
            );
        }

        ExpressionClass.getExpressionClass().setResult(calculate(
                ExpressionClass.getExpressionClass().getFirstVal(),
                ExpressionClass.getExpressionClass().getSecondVal(),
                ExpressionClass.getExpressionClass().getOperation()
        ));
        ExpressionClass.getExpressionClass().setExpressionList(" " +
                ExpressionClass.getExpressionClass().getSecondVal() + " = " +
                ExpressionClass.getExpressionClass().getResult(),false);

        expressionDaoService.expressionToSave(ExpressionClass.getExpressionClass().getDTO());

        ExpressionClass.getExpressionClass().setFirstVal(
                ExpressionClass.getExpressionClass().getResult(), true
        );

        ExpressionClass.getExpressionClass().isSave = true;

        return ExpressionClass.getExpressionClass().getDTO();
    }

    public ExpressionDTO tryAddNum(String num){
        if(isDouble(num)) {
            if (ExpressionClass.getExpressionClass().getResult().equals("0") && num.equals("0")) {
                return ExpressionClass.getExpressionClass().getDTO();
            }
            if (ExpressionClass.getExpressionClass().isSave) ExpressionClass.refreshClass();
            if (ExpressionClass.getExpressionClass().isNextNum()){
                ExpressionClass.getExpressionClass().isNextOperation = true;
                if(ExpressionClass.getExpressionClass().isFirstVal) {
                    ExpressionClass.getExpressionClass().setSecondVal(num, ExpressionClass.getExpressionClass().getLastButton().equals("op") || ExpressionClass.getExpressionClass().getLastButton().equals("error"));
                    ExpressionClass.getExpressionClass().setResult(
                            ExpressionClass.getExpressionClass().getSecondVal()
                    );
                } else {
                    ExpressionClass.getExpressionClass().setFirstVal(num);
                    ExpressionClass.getExpressionClass().setResult(
                            ExpressionClass.getExpressionClass().getFirstVal()
                    );
                }
            }
        }
        ExpressionClass.getExpressionClass().setLastButton("num");
        return ExpressionClass.getExpressionClass().getDTO();
    }

    public ExpressionDTO tryAddOperation(String op){
        if (isOperation(op)) {
            if(ExpressionClass.getExpressionClass().isNextOperation){
                if(ExpressionClass.getExpressionClass().isFirstVal){
                    if(!ExpressionClass.getExpressionClass().isSave){
                        ExpressionClass.getExpressionClass().setFirstVal(calculate(
                                ExpressionClass.getExpressionClass().getFirstVal(),
                                ExpressionClass.getExpressionClass().getSecondVal(),
                                ExpressionClass.getExpressionClass().getOperation()
                        ), true);
                        ExpressionClass.getExpressionClass().setSecondVal("");
                        ExpressionClass.getExpressionClass().setResult(ExpressionClass.getExpressionClass().getFirstVal());
                        ExpressionClass.getExpressionClass().setExpressionList( " " + ExpressionClass.getExpressionClass().getSecondVal() + " " + op, false);
                        ExpressionClass.getExpressionClass().isFirstVal = true;
                        ExpressionClass.getExpressionClass().setOperation(op);
                        ExpressionClass.getExpressionClass().isNextOperation = false;
                        ExpressionClass.getExpressionClass().setLastButton("op");
                        return ExpressionClass.getExpressionClass().getDTO();
                    } else {
                        ExpressionClass.getExpressionClass().setExpressionList("");
                        ExpressionClass.getExpressionClass().isSave = false;
                    }
                    ExpressionClass.getExpressionClass().setSecondVal("");
                }
                ExpressionClass.getExpressionClass().isFirstVal = true;
                ExpressionClass.getExpressionClass().setOperation(op);
                ExpressionClass.getExpressionClass().setExpressionList(
                        ExpressionClass.getExpressionClass().getFirstVal() + " " + op, false
                );
            } else {
                ExpressionClass.getExpressionClass().setOperation(op);
                if(ExpressionClass.getExpressionClass().isFirstVal){
                    ExpressionClass.getExpressionClass().setExpressionList(op, true);
                } else {
                    ExpressionClass.getExpressionClass().setFirstVal("0");
                    ExpressionClass.getExpressionClass().isFirstVal = true;
                    ExpressionClass.getExpressionClass().setExpressionList("0 " + op, false);
                }
            }
        }
        ExpressionClass.getExpressionClass().isNextOperation = false;
        ExpressionClass.getExpressionClass().setLastButton("op");
        return ExpressionClass.getExpressionClass().getDTO();
    }

    private boolean isDouble(String num){
        try{Double.parseDouble(num);}
        catch (NumberFormatException ex){
            return false;
        }
        return true;
    }

    private boolean isOperation(String op){
        String operators = "+-/*";
        return operators.contains(op);
    }

    @Data
    static class ExpressionClass {
        private static ExpressionClass expressionClass = new ExpressionClass();
        private String firstVal = "";
        private String secondVal = "";
        private String expressionList = "";
        private String operation = "";
        private String result = "0";
        private String lastButton = "";
        private boolean isSave = false;
        private boolean isFirstVal = false;
        private boolean isNextNum = true;
        private boolean isNextOperation = false;

        public static ExpressionClass getExpressionClass() {
            return expressionClass;
        }

        public void setExpressionList(String val, boolean change) {
            if(change) { expressionList = expressionList.substring(expressionList.length() - 1) + val; }
            else {this.expressionList += val;}
        }

        public void setSecondVal(String num) {
            this.secondVal += num;
        }

        public void setSecondVal(String num, boolean change) {
            if (change) {
                this.secondVal = num;
            } else {
                setSecondVal(num);
            }
        }

        public void setFirstVal(String num) {
            this.firstVal += num;
        }

        public void setFirstVal(String num, boolean change) {
            if (change) {
                this.firstVal = num;
            } else {
                setFirstVal(num);
            }
        }

        public static void refreshClass(){
            expressionClass = new ExpressionClass();
        }

        public ExpressionDTO getDTO(){
            ExpressionDTO expressionDTO = new ExpressionDTO();
            expressionDTO.setExpressionList(getExpressionList());
            expressionDTO.setResult(getResult());
            expressionDTO.setFirstValue(getFirstVal());
            expressionDTO.setSecondValue(getSecondVal());
            expressionDTO.setOperation(getOperation());

            return expressionDTO;
        }

    }

}
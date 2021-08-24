package org.example.controller;

import org.example.dao.ExpressionDAO;
import org.example.model.Expression;
import org.example.service.CalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CalcController {

    CalculationService calculateService;
    ExpressionDAO expressionDAO;

    public CalcController(CalculationService calculate, ExpressionDAO expressionDAO) {
        this.calculateService = calculate;
        this.expressionDAO = expressionDAO;
    }

    @GetMapping("/")
    public String indexGet(){
        return "index";
    }

    @GetMapping("/calc")
    public String calcGet(Model model) {
        model.addAttribute("list", expressionDAO.getListExpressions());
        return "calc2";
    }

    @GetMapping("/expressions")
    public @ResponseBody List<Expression> expressionListGet() {
        return expressionDAO.getListExpressions();
    }

    @PostMapping("/expressions")
    public @ResponseBody Expression expressionSave(Expression model) {
        expressionDAO.putExpression(model);
        return model;
    }

    @PostMapping("/subtotal")
    public @ResponseBody String processRequest(@RequestParam("firstVar") String firstVar,
                                                               @RequestParam("secondVar") String secondVar,
                                                               @RequestParam("operation") String operation) {
        if(secondVar.equals("0")){return "error";}
        return calculateService.calculate(firstVar,secondVar, operation);
    }

}

package org.example.controller;

import org.example.dao.ExpressionDAO;
import org.example.model.Expression;
import org.example.service.CalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/calc")
public class CalcController {

    CalculationService calculateService;
    ExpressionDAO expressionDAO;

    public CalcController(CalculationService calculate, ExpressionDAO expressionDAO) {
        this.calculateService = calculate;
        this.expressionDAO = expressionDAO;
    }

    @GetMapping()
    public String calcGet() {
        return "calc";
    }

    @GetMapping("/expressions")
    public @ResponseBody ResponseEntity<List<Expression>> expressionListGet() {
        return new ResponseEntity<>(expressionDAO.getListExpressions(10), HttpStatus.OK);
    }

    @PostMapping("/expressions")
    public @ResponseBody ResponseEntity<Expression> expressionSave(Expression model) {
        expressionDAO.putExpression(model);
        return new ResponseEntity<>(model,HttpStatus.OK);
    }

    @PostMapping("/subtotal")
    public @ResponseBody ResponseEntity<String> processRequest(@RequestParam("firstVar") String firstVar,
                                                               @RequestParam("secondVar") String secondVar,
                                                               @RequestParam("operation") String operation) {
        return new ResponseEntity<>(calculateService.calculate(firstVar,secondVar, operation), HttpStatus.OK);
    }

}

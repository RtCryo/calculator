package org.example.controller;

import org.example.model.Expression;
import org.example.service.CalculationService;
import org.example.service.ExpressionDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/calc")
@PreAuthorize("hasAuthority('user:read')")
public class CalcController {

    private final CalculationService calculateService;
    private final ExpressionDaoService expressionDaoService;

    public CalcController(CalculationService calculateService, ExpressionDaoService expressionDaoService) {
        this.calculateService = calculateService;
        this.expressionDaoService = expressionDaoService;
    }

    @GetMapping()
    public String calcGet() {
        return "calc";
    }

    @GetMapping("/expressions")
    public @ResponseBody ResponseEntity<List<Expression>> expressionListGet() {
        return new ResponseEntity<>(expressionDaoService.listToView(10), HttpStatus.OK);
    }

    @PostMapping("/expressions")
    public @ResponseBody ResponseEntity<Expression> expressionSave(Expression expression) {
        expressionDaoService.expressionToSave(expression);
        return new ResponseEntity<>(expression,HttpStatus.OK);
    }

    @PostMapping("/subtotal")
    public @ResponseBody ResponseEntity<String> processRequest(@RequestParam("firstVar") String firstVar,
                                                               @RequestParam("secondVar") String secondVar,
                                                               @RequestParam("operation") String operation) {
        return new ResponseEntity<>(calculateService.calculate(firstVar,secondVar, operation), HttpStatus.OK);
    }

}

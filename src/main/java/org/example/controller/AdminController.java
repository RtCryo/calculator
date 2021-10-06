package org.example.controller;

import org.example.model.Expression;
import org.example.service.ExpressionDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ExpressionDaoService expressionDaoService;

    public AdminController(ExpressionDaoService expressionDaoService) {
        this.expressionDaoService = expressionDaoService;
    }

    @GetMapping()
    public String adminIndex() {
        return "admin";
    }

    @GetMapping("/expressionsList")
    public @ResponseBody ResponseEntity<List<Expression>> adminGetList() {
        return new ResponseEntity<>(expressionDaoService.listToView(0), HttpStatus.OK);
    }

    @PostMapping("/expressionsToDelete")
    public @ResponseBody ResponseEntity<List<Expression>> expressionsToDelete(@RequestBody List<Expression> list) {
        expressionDaoService.listToDelete(list);
        return new ResponseEntity<>(expressionDaoService.listToView(0), HttpStatus.OK);
    }

}

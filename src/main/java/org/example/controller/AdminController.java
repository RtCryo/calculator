package org.example.controller;

import org.example.dao.ExpressionDAO;
import org.example.model.Expression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    ExpressionDAO expressionDAO;

    public AdminController(ExpressionDAO expressionDAO) {
        this.expressionDAO = expressionDAO;
    }

    @GetMapping()
    public String adminIndex() {
        return "admin";
    }

    @GetMapping("/expressionsList")
    public @ResponseBody ResponseEntity<List<Expression>> adminGetList() {
        return new ResponseEntity<>(expressionDAO.getListExpressions(), HttpStatus.OK);
    }

    @PostMapping("/expressionsToDelete")
    public @ResponseBody ResponseEntity<List<Expression>> expressionsToDelete(@RequestBody List<Expression> list) { //todo DOA -> service
        for (Expression ex : list) {
            expressionDAO.deleteExpression(ex);
        }
        return new ResponseEntity<>(expressionDAO.getListExpressions(), HttpStatus.OK);
    }

}

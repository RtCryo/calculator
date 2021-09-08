package org.example.controller;

import org.example.model.Expression;
import org.example.service.DaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DaoService daoService;

    public AdminController(DaoService daoService) {
        this.daoService = daoService;
    }

    @GetMapping()
    public String adminIndex() {
        return "admin";
    }

    @GetMapping("/expressionsList")
    public @ResponseBody ResponseEntity<List<Expression>> adminGetList() {
        return new ResponseEntity<>(daoService.listToView(0), HttpStatus.OK);
    }

    @PostMapping("/expressionsToDelete")
    public @ResponseBody ResponseEntity<List<Expression>> expressionsToDelete(@RequestBody List<Expression> list) {
        daoService.listToDelete(list);
        return new ResponseEntity<>(daoService.listToView(0), HttpStatus.OK);
    }

}

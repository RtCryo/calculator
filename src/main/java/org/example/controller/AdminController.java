package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Expression;
import org.example.service.ExpressionDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('admin:read')")
public class AdminController {

    private final ExpressionDaoService expressionDaoService;

    @GetMapping()
    public String adminIndex() {
        return "admin";
    }

    @GetMapping("/expressionsList")
    public @ResponseBody ResponseEntity<List<Expression>> adminGetList() {
        return new ResponseEntity<>(expressionDaoService.listToView(0), HttpStatus.OK);
    }

    @PostMapping("/expressionsToDelete")
    @PreAuthorize("hasAuthority('admin:write')")
    public @ResponseBody ResponseEntity<List<Expression>> expressionsToDelete(@RequestBody List<Expression> list) {
        expressionDaoService.listToDelete(list);
        return new ResponseEntity<>(expressionDaoService.listToView(0), HttpStatus.OK);
    }

}

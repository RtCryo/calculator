package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Expression;
import org.example.service.ExpressionDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('developers:read')")
public class AdminController {

    private final ExpressionDaoService expressionDaoService;

    @GetMapping()
    public @ResponseBody ResponseEntity<List<Expression>> adminGetList() {
        return new ResponseEntity<>(expressionDaoService.listToView(0), HttpStatus.OK);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('admin:write')")
    public @ResponseBody ResponseEntity<List<Expression>> expressionsToDelete(@RequestBody List<Expression> list) {
        expressionDaoService.listToDelete(list);
        return new ResponseEntity<>(expressionDaoService.listToView(0), HttpStatus.OK);
    }

}

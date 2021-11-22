package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ExpressionDTO;
import org.example.model.Expression;
import org.example.service.CalculationService;
import org.example.service.ExpressionDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calc")
@PreAuthorize("hasAuthority('user:read')")
public class CalcController {

    private final CalculationService calculateService;
    private final ExpressionDaoService expressionDaoService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<Expression>> expressionListGet() {
        return new ResponseEntity<>(expressionDaoService.listToView(10), HttpStatus.OK);
    }

    @PutMapping("/expression/add")
    public @ResponseBody ResponseEntity<ExpressionDTO> expressionSave(ExpressionDTO expressionDTO) {
        expressionDaoService.expressionToSave(expressionDTO);
        return new ResponseEntity<>(expressionDTO, HttpStatus.CREATED);
    }

    @PostMapping("/expression/subtotal")
    public @ResponseBody ResponseEntity<ExpressionDTO> processRequest(ExpressionDTO expressionDTO) {
        expressionDTO.setResult(calculateService.calculate(
                expressionDTO.getFirstValue(),
                expressionDTO.getSecondValue(),
                expressionDTO.getOperation()));
        return new ResponseEntity<>(expressionDTO, HttpStatus.OK);
    }

}

package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ExpressionDTO;
import org.example.model.Expression;
import org.example.service.CalculationService;
import org.example.service.ExpressionDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calc")
public class CalcController {

    private final CalculationService calculateService;
    private final ExpressionDaoService expressionDaoService;

    @GetMapping
    public @ResponseBody ResponseEntity<List<Expression>> expressionListGet() {
        return new ResponseEntity<>(expressionDaoService.listToView(10), HttpStatus.OK);
    }

    @PutMapping("/expression/add")
    public @ResponseBody ResponseEntity<ExpressionDTO> expressionSave(@RequestBody ExpressionDTO expressionDTO) {
        expressionDaoService.expressionToSave(expressionDTO);
        return new ResponseEntity<>(expressionDTO, HttpStatus.CREATED);
    }

    @PostMapping("/expression/calculate")
    public @ResponseBody ResponseEntity<String> expressionCalculate(@RequestBody ExpressionDTO expressionDTO){
        String result = calculateService.calculate(expressionDTO.getFirstValue(), expressionDTO.getSecondValue(), expressionDTO.getOperation());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

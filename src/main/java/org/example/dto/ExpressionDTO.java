package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionDTO {

    private String expressionList;
    private String result;
    private LocalDateTime date;
    private String firstValue;
    private String secondValue;
    private String operation;

}

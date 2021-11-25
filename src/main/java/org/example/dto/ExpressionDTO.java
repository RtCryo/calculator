package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionDTO implements Serializable {

    private Long id;
    private String expressionList;
    private String result;
    private LocalDateTime date;
    private String firstValue;
    private String secondValue;
    private String operation;

}

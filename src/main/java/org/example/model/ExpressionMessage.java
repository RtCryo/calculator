package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpressionMessage {

    private MessageType type;
    private Expression expression;

    public enum MessageType {
        ADD,
        DELETE,
        REFRESH
    }
}

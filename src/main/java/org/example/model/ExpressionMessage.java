package org.example.model;

public class ExpressionMessage {

    private MessageType type;
    private Expression expression;

    public enum MessageType {
        ADD,
        DELETE,
        REFRESH
    }

    public ExpressionMessage(MessageType type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}

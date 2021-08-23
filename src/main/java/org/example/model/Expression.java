package org.example.model;

public class Expression {
    private int id;
    private String expressionList;
    private String result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(String expressionList) {
        this.expressionList = expressionList;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "id=" + id +
                ", expressionList='" + expressionList + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

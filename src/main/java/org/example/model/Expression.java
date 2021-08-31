package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Expression {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;

    private int id;
    private String expressionList;
    private String result;


    public Expression() {
    }

    public Expression(int id, String expressionList, String result, Date date) {
        this.id = id;
        this.expressionList = expressionList;
        this.result = result;
        this.date = date;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "id=" + id +
                ", expressionList='" + expressionList + '\'' +
                ", result='" + result + '\'' +
                ", date=" + date +
                '}';
    }
}

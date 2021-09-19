package org.example.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expressionstable")
public class Expression {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String expressionList;
    private String result;
    private LocalDateTime date;

    public Expression() {
    }

    public Expression(long id, String expressionList, String result, LocalDateTime date) {
        this.id = id;
        this.expressionList = expressionList;
        this.result = result;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

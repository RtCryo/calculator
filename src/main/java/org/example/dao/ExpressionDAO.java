package org.example.dao;

import org.example.model.Expression;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ExpressionDAO {
    private List<Expression> listExpressions;
    private static int EXPRESSION_ID;

    public ExpressionDAO() {
        this.listExpressions = new LinkedList<>();
    }

    public List<Expression> getListExpressions() {
        return listExpressions;
    }

    public void putExpression(Expression a) {
        a.setId(EXPRESSION_ID++);
        listExpressions.add(a);
    }

    public Expression getExpression(int id){
        for (Expression a : listExpressions) {
            if (a.getId() == id) {
                return a;
            }
        }
        return new Expression();
    }

}

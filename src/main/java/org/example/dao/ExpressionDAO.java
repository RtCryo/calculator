package org.example.dao;

import org.example.model.Expression;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExpressionDAO {
    private List<Expression> listExpressions;
    private static AtomicInteger counter;

    public ExpressionDAO() {
        this.listExpressions = new LinkedList<>();
        counter = new AtomicInteger(0);
    }

    public List<Expression> getListExpressions() {
        return listExpressions;
    }

    public void putExpression(Expression a) {
        a.setId(counter.incrementAndGet());
        listExpressions.add(a);
    }

}

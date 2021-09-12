package org.example.service;

import org.example.dao.ExpressionDAO;
import org.example.model.Expression;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DaoService {

    ExpressionDAO expressionDAO;

    public DaoService(ExpressionDAO expressionDAO) {
        this.expressionDAO = expressionDAO;
    }

    public void listToDelete (List<Expression> list) {
        for (Expression ex : list) {
            expressionDAO.deleteExpression(ex);
        }
    }

    public List<Expression> listToView(int count) {
        if (count > 0) {
            return expressionDAO.getListExpressions(count);
        } else {
            return expressionDAO.getListExpressions();
        }
    }
    public void expressionToSave (Expression expression) {
        expressionDAO.putExpression(expression);
    }
}

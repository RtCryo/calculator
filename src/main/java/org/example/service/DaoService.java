package org.example.service;

import org.example.dao.ExpressionDAO;
import org.example.model.Expression;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DaoService {

    ExpressionDAO expressionDAO;
    WebSocketService webSocketService;

    public DaoService(ExpressionDAO expressionDAO, WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
        this.expressionDAO = expressionDAO;
    }

    public void listToDelete (List<Expression> list) {
        if (list.size() > 1) {
            StringBuilder sqlStatement = new StringBuilder("delete from expressionstable where id in (");
            for(int i = 0; i < list.size() - 1; i++){
                sqlStatement.append(list.get(i).getId()).append(" ,");
            }
            sqlStatement.append(list.get(list.size() - 1).getId()).append(")");
            expressionDAO.deleteListExpressions(sqlStatement.toString());
            webSocketService.sendToRefresh();
        } else {
            expressionDAO.deleteExpression(list.get(0));
            webSocketService.sendToDelete(list.get(0));
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
        webSocketService.sendToAll(expressionDAO.getListExpressions(1).get(0));
    }
}

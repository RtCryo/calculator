package org.example.service;

import org.example.dao.ExpressionRepository;
import org.example.model.Expression;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpressionDaoService {

    WebSocketService webSocketService;
    ExpressionRepository repository;

    public ExpressionDaoService(WebSocketService webSocketService, ExpressionRepository repository) {
        this.webSocketService = webSocketService;
        this.repository = repository;
    }

    public void listToDelete (List<Expression> list){
        if (list.size() > 1) {
            repository.deleteAll(list);
            webSocketService.sendToRefresh();
        } else {
            repository.deleteById(list.get(0).getId());
            webSocketService.sendToDelete(list.get(0));
        }
    }

    public List<Expression> listToView(int count) {
        if (count > 1) { return (List<Expression>) repository.findLast(count);}
        return (List<Expression>) repository.findAll();
    }

    public void expressionToSave (Expression expression) {
        expression.setDate(LocalDateTime.now());
        repository.save(expression);
        webSocketService.sendToAll(((List<Expression>)repository.findLast(1)).get(0));
    }
}

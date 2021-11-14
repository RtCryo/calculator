package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.ExpressionDTO;
import org.example.dao.ExpressionRepository;
import org.example.model.Expression;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExpressionDaoService {

    WebSocketService webSocketService;
    ExpressionRepository repository;

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
        return repository.findAllByOrderByDateAsc();
    }

    public void expressionToSave (ExpressionDTO expressionDTO) {
        Expression expression = new Expression();
        expression.setExpressionList(expressionDTO.getExpressionList());
        expression.setResult(expressionDTO.getResult());
        expression.setDate(LocalDateTime.now());
        repository.save(expression);
        webSocketService.sendToAll(((List<Expression>)repository.findLast(1)).get(0));
    }
}

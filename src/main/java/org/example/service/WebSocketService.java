package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Expression;
import org.example.model.ExpressionMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {
    private final SimpMessageSendingOperations messagingTemplate;

    @Value("${my.webSocket.destination}")
    private String destination;

    public void sendToAll(Expression expression){
        messagingTemplate.convertAndSend(destination, new ExpressionMessage(ExpressionMessage.MessageType.ADD,expression));
    }

    public void sendToDelete(Expression expression) {
        messagingTemplate.convertAndSend(destination, new ExpressionMessage(ExpressionMessage.MessageType.DELETE, expression));
    }

    public void sendToRefresh() {
        messagingTemplate.convertAndSend(destination, new ExpressionMessage(ExpressionMessage.MessageType.REFRESH, null));
    }
}

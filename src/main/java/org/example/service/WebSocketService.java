package org.example.service;

import org.example.model.Expression;
import org.example.model.ExpressionMessage;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendToAll(Expression expression){
        messagingTemplate.convertAndSend("/topic/public", new ExpressionMessage(ExpressionMessage.MessageType.ADD,expression));
    }

    public void sendToDelete(Expression expression) {
        messagingTemplate.convertAndSend("/topic/public", new ExpressionMessage(ExpressionMessage.MessageType.DELETE, expression));
    }

    public void sendToRefresh() {
        messagingTemplate.convertAndSend("/topic/public", new ExpressionMessage(ExpressionMessage.MessageType.REFRESH, null));
    }
}

package org.example.service;

import org.example.controller.IndexController;
import org.example.model.Expression;
import org.example.model.ExpressionMessage;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final IndexController indexController;
    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketService(IndexController indexController, SimpMessageSendingOperations messagingTemplate) {
        this.indexController = indexController;
        this.messagingTemplate = messagingTemplate;
    }

    public void sendToAll(Expression expression){
        messagingTemplate.convertAndSend("/topic/public", new ExpressionMessage(ExpressionMessage.MessageType.ADD,expression));
    }
}

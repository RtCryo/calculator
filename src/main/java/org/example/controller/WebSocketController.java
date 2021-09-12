package org.example.controller;

import org.springframework.stereotype.Controller;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Controller
@ServerEndpoint("/calc/message")
public class WebSocketController {

    @OnOpen
    public void onOpen(Session session) {
        System.out.printf("Session opened, id: %s%n", session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            for(Session s: session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendText("message");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.printf("Session closed with id: %s%n", session.getId());
    }

}

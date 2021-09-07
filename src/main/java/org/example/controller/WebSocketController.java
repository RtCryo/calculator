package org.example.controller;

import org.springframework.stereotype.Controller;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@ServerEndpoint("/calc/message")
public class WebSocketController {

    private static final List<Session> webSockets = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        webSockets.add(session);
        System.out.printf("Session opened, id: %s%n", session.getId());
    }

    @OnMessage
    public void onMessage(String message) {
        try {
            for(Session s : webSockets) {
                s.getBasicRemote().sendText("message");
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

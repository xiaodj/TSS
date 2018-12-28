package com.tssweb.websocket;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaodj on 2018/11/27.
 */

public class MessageHandler implements WebSocketHandler {
    public static final String USER_KEY = "current_user";
    private static final Map<Integer, WebSocketSession> userMap;  //保存用户ID 对应 session
    static {
        userMap = new ConcurrentHashMap<Integer, WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.print("连接成功");
    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        String msg = message.toString();
//        String userId = this.getUserId(session);
//        System.err.println("该"+userId+"用户发送的消息是："+msg);
//        message = new TextMessage("服务端已经接收到消息，msg="+msg);
//        session.sendMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
        WebSocketMessage<String> message = new TextMessage("异常信息："+e.getMessage());
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.print("连接关闭");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToUser(Integer uid, String msg) {
        WebSocketSession session = userMap.get(uid);
        if(session !=null && session.isOpen()) {
            try {
                TextMessage message = new TextMessage(msg);
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getUserId(WebSocketSession session){
        try {
            String userId = (String)session.getAttributes().get(USER_KEY);
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

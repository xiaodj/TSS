package com.tssweb.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by xiaodj on 2018/11/27.
 */
@Component
public class WebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor{
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("Before Handshake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute("SESSION_USERNAME");
                if (userName==null) {
                    userName="default-system";
                }
                attributes.put("WEBSOCKET_USERNAME",userName);
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}

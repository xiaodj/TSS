package com.tssweb.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by xiaodj on 2018/11/27.
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(msgHandler(),"/webSocketServer").addInterceptors(new WebSocketInterceptor());
        registry.addHandler(msgHandler(), "/sockjs/webSocketServer").addInterceptors(new WebSocketInterceptor()).withSockJS();
    }
    @Bean
    public MessageHandler msgHandler(){
        return new MessageHandler();
    }
}

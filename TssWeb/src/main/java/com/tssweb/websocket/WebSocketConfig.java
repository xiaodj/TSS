package com.tssweb.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by xiaodj on 2018/11/27.
 */

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(),"/websocket/socketServer.do").addInterceptors(new WebSocketHandlerInterceptor());
        registry.addHandler(webSocketHandler(), "/sockjs/socketServer.do").addInterceptors(new WebSocketHandlerInterceptor()).withSockJS();
    }
    @Bean
    public TextWebSocketHandler webSocketHandler(){
        return new WebSocketHandler();
    }
}

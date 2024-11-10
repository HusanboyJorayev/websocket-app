package org.example.bsocket.client;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class WebSocketClientConnector implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(WebSocketClientConnector.class);
    private static final String URL = "ws://localhost:1111/ws"; // URL manzilini o‘zgartiring
    private StompSession stompSession;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        connect();
    }

    private void connect() {
        try {
            log.info("WebSocket mijoziga ulanmoqda...");
            WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
            stompClient.setMessageConverter(new MappingJackson2MessageConverter());

            stompClient.connect(URL, new StompSessionHandlerAdapter() {
                @Override
                public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                    stompSession = session;
                    stompSession.subscribe("/topic/commands", new MyStompFrameHandler());
                    log.info("WebSocket Server B bilan muvaffaqiyatli ulandi.");
                }
            });
        } catch (Exception e) {
            log.error("WebSocket ulanishida xatolik yuz berdi: ", e);
        }
    }
}



package org.example.bsocket.client;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

public class MyStompFrameHandler implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        // Bu metod kelayotgan xabar turini belgilaydi
        return String.class; // Bizning xabar turi CommandsDto
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        // Xabar kelganda ushbu metod ishga tushadi va xabarni qayta ishlaydi
        String commandsDto = (String) payload;
        System.out.println("Received message: " + commandsDto);
    }
}

package org.example.asocket.server;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final SimpMessagingTemplate template;

    public MessageService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(String message) {
        template.convertAndSend("/topic/commands", message);
    }
}


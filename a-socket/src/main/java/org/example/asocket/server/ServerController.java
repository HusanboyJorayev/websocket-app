package org.example.asocket.server;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class ServerController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;


    @PostMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        // WebSocket orqali xabar yuborish
        messagingTemplate.convertAndSend("/topic/commands", message);
        return "Message sent to WebSocket!";
    }

    @PostMapping("/send2/{message}")
    public String sendMessage2(@PathVariable String message) {
        // WebSocket orqali xabar yuborish
        this.messageService.sendMessage(message);
        return "Message sent to WebSocket!";
    }
}

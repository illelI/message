package com.ilelli.message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.UUID;

@Controller
public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(MessageDto messageDto, Principal principal) {
        UUID userid = UUID.fromString(principal.getName());
        Message message = MessageMapper.toMessage(userid, messageDto);
        messageService.saveMessage(message);

        messagingTemplate.convertAndSendToUser(
                message.getKey().getReceiver().toString(),
                "/private/user",
                message.getContent()
        );
    }

}

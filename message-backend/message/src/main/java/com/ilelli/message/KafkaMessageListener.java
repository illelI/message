package com.ilelli.message;

import com.ilelli.message.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    public KafkaMessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "user-messages", groupId = "websocket-consumers")
    public void consume(Message message) {
        String receiverId = message.getKey().getReceiver().toString();
        messagingTemplate.convertAndSendToUser(
                receiverId,
                "/private/user",
                message.getContent()
        );
    }
}

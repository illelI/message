package com.ilelli.message;

import com.ilelli.message.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository repository;
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public MessageService(MessageRepository repository, KafkaTemplate<String, Message> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void saveAndSendToReceiverMessage(Message message) {
        repository.save(message);
        kafkaTemplate.send("user-messages", message.getKey().getReceiver().toString(), message);
    }
}

package com.ilelli.message.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @EmbeddedId
    private MessagePK key;
    private LocalDateTime date;
    private MessageContent content;
}

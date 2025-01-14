package com.ilelli.message;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class MessagePK implements Serializable {
    private UUID sender;
    private UUID receiver;
    private UUID messageNO;
}

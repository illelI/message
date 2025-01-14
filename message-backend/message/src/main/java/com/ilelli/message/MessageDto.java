package com.ilelli.message;

import jakarta.persistence.Lob;
import lombok.Data;

import java.util.UUID;

@Data
public class MessageDto {
    private UUID receiver;
    private UUID messageNO;
    private String text;
    @Lob
    private byte[] image;
}

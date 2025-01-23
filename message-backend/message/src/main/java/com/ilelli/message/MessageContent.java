package com.ilelli.message;

import com.ilelli.message.utils.CorrectMessage;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Data;

@Embeddable
@Data
@CorrectMessage
public class MessageContent {
    private String text;
    @Lob
    private byte[] image;
}

package com.ilelli.message.utils;

import com.ilelli.message.model.Message;
import com.ilelli.message.model.MessageContent;
import com.ilelli.message.model.MessageDto;
import com.ilelli.message.model.MessagePK;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageMapper {

    private MessageMapper() {}

    public static Message toMessage(UUID userId, MessageDto dto) {
        MessagePK pk = new MessagePK();
        MessageContent content = new MessageContent();
        Message message = new Message();

        pk.setSender(userId);
        pk.setReceiver(dto.getReceiver());
        pk.setMessageNO(dto.getMessageNO());

        content.setText(dto.getText());
        content.setImage(dto.getImage());

        message.setKey(pk);
        message.setDate(LocalDateTime.now());
        message.setContent(content);

        return message;
    }

}

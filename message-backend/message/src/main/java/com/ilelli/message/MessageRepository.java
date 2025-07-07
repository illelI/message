package com.ilelli.message;

import com.ilelli.message.model.Message;
import com.ilelli.message.model.MessagePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, MessagePK> {
}

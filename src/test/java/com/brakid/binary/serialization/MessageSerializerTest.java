package com.brakid.binary.serialization;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.brakid.binary.types.Message;
import com.brakid.binary.types.User;

public class MessageSerializerTest {
    @Test
    public void givenMessage_whenSerialze_thenCanBeDeserialized() {
        Message message = new Message(987654321l, "A meaningful message", new User(1l, "Sender"), new User(2l, "Receiver"), ZonedDateTime.now().toEpochSecond() * 1000);
        Serializer<Message> messageSerializer = new MessageSerializer();
        assertEquals(message, messageSerializer.deserialize(messageSerializer.serialize(message)));
    }
}

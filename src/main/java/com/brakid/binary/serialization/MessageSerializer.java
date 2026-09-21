package com.brakid.binary.serialization;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brakid.binary.types.Message;
import com.brakid.binary.types.User;

public class MessageSerializer implements Serializer<Message>{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSerializer.class);
    private static final Serializer<User> USER_SERIALIZER = new UserSerializer();

    @Override
    public byte[] serialize(Message message) {
        final byte[] messageBytes = message.message().getBytes();
        final byte[] senderBytes = USER_SERIALIZER.serialize(message.sender());
        final byte[] receiverBytes = USER_SERIALIZER.serialize(message.receiver());
        return ByteBuffer.allocate(
                        Long.BYTES + Integer.BYTES + messageBytes.length + Integer.BYTES + 
                        senderBytes.length + Integer.BYTES + receiverBytes.length + Long.BYTES)
                .putLong(message.id())
                .putInt(messageBytes.length)
                .put(messageBytes)
                .putInt(senderBytes.length)
                .put(senderBytes)
                .putInt(receiverBytes.length)
                .put(receiverBytes)
                .putLong(message.epochMillis())
                .array();
    }

    @Override
    public Message deserialize(byte[] value) {
        final ByteBuffer byteBuffer = ByteBuffer.wrap(value);
        
        final long messageId = byteBuffer.getLong();
        final int messageLength = byteBuffer.getInt();
        final byte[] messageBytes = new byte[messageLength];
        byteBuffer.get(messageBytes, 0, messageLength);
        LOGGER.info("Name bytes: {}: {}", messageLength, messageBytes);
        final String message = new String(messageBytes);
        final int senderLength = byteBuffer.getInt();
        final byte[] senderBytes = new byte[senderLength];
        byteBuffer.get(senderBytes, 0, senderLength);
        LOGGER.info("Sender bytes: {}: {}", senderLength, senderBytes);
        final User sender = USER_SERIALIZER.deserialize(senderBytes);
        final int receiverLength = byteBuffer.getInt();
        final byte[] receiverBytes = new byte[receiverLength];
        byteBuffer.get(receiverBytes, 0, receiverLength);
        LOGGER.info("Receiver bytes: {}: {}", receiverLength, receiverBytes);
        final User receiver = USER_SERIALIZER.deserialize(receiverBytes);
        final long epochMillis = byteBuffer.getLong();
        
        return new Message(messageId, message, sender, receiver, epochMillis);
    }
}

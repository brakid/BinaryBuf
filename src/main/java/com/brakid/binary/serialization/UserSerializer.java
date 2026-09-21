package com.brakid.binary.serialization;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brakid.binary.types.User;
import static com.google.common.base.Preconditions.checkState;

public class UserSerializer implements Serializer<User>{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSerializer.class);

    @Override
    public byte[] serialize(User user) {
        final int nameLength = user.name().length();
        final ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES + nameLength).putLong(user.id()).put(user.name().getBytes());
        final byte[] bytes = byteBuffer.array();
        checkState(bytes.length == Long.BYTES + nameLength, "Unexpected bytes length: %d vs %d", bytes.length, Long.BYTES + nameLength);
        LOGGER.info("Bytes from Long ({}) + String ({})", Long.BYTES, nameLength);
        return bytes;
    }

    @Override
    public User deserialize(byte[] value) {
        final ByteBuffer byteBuffer = ByteBuffer.wrap(value);
        checkState(byteBuffer.limit() > Long.BYTES, "Byte buffer needs to contain at least a long value");
        
        final long userId = byteBuffer.getLong();
        final int nameLength = byteBuffer.remaining();
        final byte[] nameBytes = new byte[nameLength];
        byteBuffer.get(nameBytes, 0, nameLength);
        LOGGER.info("Name bytes: {}: {}", nameLength, nameBytes);
        final String name = new String(nameBytes);
        checkState(name.length() == nameLength, "Unexpected name length: %d vs %d", name.length(), nameLength);
        
        return new User(userId, name);
    }
}

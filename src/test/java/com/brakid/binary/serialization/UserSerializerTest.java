package com.brakid.binary.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.brakid.binary.types.User;

public class UserSerializerTest {
    @Test
    public void givenUser_whenSerialze_thenCanBeDeserialized() {
        User user = new User(1234567890l, "Dummy Name");
        Serializer<User> userSerializer = new UserSerializer();
        assertEquals(user, userSerializer.deserialize(userSerializer.serialize(user)));
    }
}

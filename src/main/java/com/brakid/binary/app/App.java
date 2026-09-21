package com.brakid.binary.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brakid.binary.serialization.UserSerializer;
import com.brakid.binary.types.User;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        User user = new User(123l, "Test");
        UserSerializer userSerializer = new UserSerializer();
        byte[] bytes = userSerializer.serialize(user);
        LOGGER.info("Bytes: {}", bytes);
        User user1 = userSerializer.deserialize(bytes);
        LOGGER.info("User: {}", user1);
    }
}

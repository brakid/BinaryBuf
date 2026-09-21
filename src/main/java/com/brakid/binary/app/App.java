package com.brakid.binary.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brakid.binary.serialization.MessageSerializer;
import com.brakid.binary.serialization.Serializer;
import com.brakid.binary.types.Message;
import com.brakid.binary.types.User;

import tools.jackson.databind.json.JsonMapper;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        User sender = new User(Long.MAX_VALUE, "Sender");
        User receiver = new User(Long.MIN_VALUE, "receiver");
        Message message = new Message(Long.MAX_VALUE, "Some message with a bit of text", sender, receiver, Long.MAX_VALUE);
        Serializer<Message> messageSerializer = new MessageSerializer();
        byte[] bytes = messageSerializer.serialize(message);
        LOGGER.info("Bytes: {}, length: {}", bytes, bytes.length);
    
        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(message);
        LOGGER.info("Json: {}, length: {}", json, json.length());
    }
}

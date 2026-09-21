package com.brakid.binary.types;

public record Message(long id, String message, User sender, User receiver, long epochMillis) {}

package com.brakid.binary.serialization;

public interface Serializer<T> {
    public byte[] serialize(T value);
    public T deserialize(byte[] value);
}

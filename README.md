# Binary Buffer serialization
Saving on bytes when serializing objects of a known type instead of using general purpose JSON that requires more data to be transferred due to the human-readable format.

Similar to the principle behind [Protocol Buffers - protobuf](https://protobuf.dev/) but with a custom serializer.
### Running Java
```java -p binarybuf-service-1.0-SNAPSHOT.jar:lib -m com.brakid.binary/com.brakid.binary.app.App```

### Create custom JRE - stripping unused modules of the Java Runtime
```jlink --module-path binarybuf-service-1.0-SNAPSHOT.jar:lib --add-modules com.brakid.binary --strip-debug --no-man-pages --no-header-files --compress=zip-9 --output jre```

Run using the custom stripped JVM:
```./jre/bin/java -p binarybuf-service-1.0-SNAPSHOT.jar:lib -m com.brakid.binary/com.brakid.binary.app.App```
package com.github.tmquotebot.searchspider.client;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.tmquotebot.searchspider.exception.WebException;

import java.io.IOException;

public class ErrorCodeDeserializer<T> extends StdDeserializer<T> {

    private final Class<T> clazz;
    private final ObjectMapper objectMapper;

    public ErrorCodeDeserializer(Class<T> vc, ObjectMapper objectMapper) {
        super(vc);
        this.clazz = vc;
        this.objectMapper = objectMapper;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper codecMapper = (ObjectMapper) p.getCodec();
        ObjectNode object = codecMapper.readTree(p);
        if (object.has("error_code")) {
            throw new WebException(object.get("message").asText("favqs client error"));
        }
        return objectMapper.treeToValue(object, clazz);
    }


}
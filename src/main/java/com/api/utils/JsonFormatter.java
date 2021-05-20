package com.api.utils;

import com.api.dtos.AbstractJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatter extends AbstractJson {
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
    }


    public static String format(Object o) throws JsonProcessingException {
        return MAPPER.writeValueAsString(o);
    }

    public static <T extends AbstractJson> T parse(String json, Class<T> valueType) throws JsonProcessingException {
        return MAPPER.readValue(json, valueType);
    }
}

package org.trading.ticker.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.trading.ticker.utils.exception.JsonException;


public class JsonUtil {

    public static String toJsonString(Object o) {
        try {
            return getMapper().writeValueAsString(o);
        } catch (Exception e) {
            throw new JsonException("Could not map object to string", e);
        }
    }

    public static <T> T toObject(String jsonStr, Class<T> type) {
        try {
            return getMapper().readValue(jsonStr, type);
        } catch (Exception e) {
            throw new JsonException("Could not convert json string to type", e);
        }
    }

    public static <T> T toObject(String jsonStr, TypeReference<T> typeReference) {
        try {
            return getMapper().readValue(jsonStr, typeReference);
        } catch (Exception e) {
            throw new JsonException("Could not convert json string to type", e);
        }
    }

    private static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}

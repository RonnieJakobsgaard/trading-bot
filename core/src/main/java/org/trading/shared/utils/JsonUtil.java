package org.trading.shared.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.trading.shared.utils.exception.JsonException;


public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static String toJsonString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new JsonException("Could not map object to string", e);
        }
    }

    public static <T> T toObject(String jsonStr, Class<T> type) {
        try {
            return mapper.readValue(jsonStr, type);
        } catch (Exception e) {
            throw new JsonException("Could not convert json string to type", e);
        }
    }

    public static <T> T toObject(String jsonStr, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(jsonStr, typeReference);
        } catch (Exception e) {
            throw new JsonException("Could not convert json string to type", e);
        }
    }

    public static <T> T toObject(byte[] jsonBytes, Class<T> type) {
        try {
            return mapper.readValue(jsonBytes, type);
        } catch (Exception e) {
            throw new JsonException("Could not convert json bytes to type", e);
        }
    }
}

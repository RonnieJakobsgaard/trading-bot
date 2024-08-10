package org.trading.shared.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.trading.shared.utils.JsonUtil;


import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class JsonUtilTest {

    @Test
    public void testObjectToJsonString() {
        // Arrange
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");

        // Act
        String actual = JsonUtil.toJsonString(map);

        // Assert
        String expected = "{\"a\":\"1\"}";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testJsonStringToObject() {
        // Arrange
        String data = "{\"a\":\"1\"}";
        Map<String, String> expected = new HashMap<>();
        expected.put("a", "1");

        // Act
        Map<String, String> actual = JsonUtil.toObject(data, new TypeReference<>() {});

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testJsonStringToObjectClass() {
        // Arrange
        String data = "{\"a\":\"1\"}";
        SimpleObject expected = new SimpleObject("1");

        // Act
        SimpleObject actual = JsonUtil.toObject(data, SimpleObject.class);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}

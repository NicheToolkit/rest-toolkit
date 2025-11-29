package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.error.supply.JsonDeserializeException;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.JsonNode;

import java.util.*;

public class DeserializeHelper {

    public static Map<String, JsonNode> deserializerBean(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerBean(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    public static Map<String, JsonNode> deserializerBean(JsonNode jsonNode) {
        Map<String, JsonNode> beanMap = new HashMap<>();
        if (!jsonNode.isArray()) {
            buildBeanMap(jsonNode, beanMap);
        }
        return beanMap;
    }

    public static List<JsonNode> deserializerList(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerList(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    public static List<JsonNode> deserializerList(JsonNode jsonNode) {
        List<JsonNode> resultList = new ArrayList<>();
        if (jsonNode.isArray()) {
            for (JsonNode objectNode : jsonNode) {
                resultList.add(objectNode);
            }
        } else {
            resultList.add(jsonNode);
        }
        return resultList;
    }

    public static List<Map<String, JsonNode>> deserializerBeanList(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerBeanList(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    public static List<Map<String, JsonNode>> deserializerBeanList(JsonNode jsonNode) {
        List<Map<String, JsonNode>> resultList = new ArrayList<>();
        if (jsonNode.isArray()) {
            for (JsonNode objectNode : jsonNode) {
                Map<String, JsonNode> beanMap = new HashMap<>();
                buildBeanMap(objectNode, beanMap);
                resultList.add(beanMap);
            }
        } else {
            Map<String, JsonNode> beanMap = new HashMap<>();
            buildBeanMap(jsonNode, beanMap);
            resultList.add(beanMap);
        }
        return resultList;
    }

    public static Map<String, JsonNode> deserializerMap(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerMap(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }

    }

    public static Map<String, JsonNode> deserializerMap(JsonNode jsonNode) {
        Map<String, JsonNode> resultMap = new HashMap<>();
        if (!jsonNode.isArray()) {
            Set<Map.Entry<String, JsonNode>> entrySet = jsonNode.properties();
            for (Map.Entry<String, JsonNode> entry : entrySet) {
                JsonNode valueNode = entry.getValue();
                resultMap.put(entry.getKey(), valueNode);
            }
        }
        return resultMap;
    }

    public static Map<String, Map<String, JsonNode>> deserializerBeanMap(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerBeanMap(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    public static Map<String, Map<String, JsonNode>> deserializerBeanMap(JsonNode jsonNode) {
        Map<String, Map<String, JsonNode>> dataMap = new HashMap<>();
        if (!jsonNode.isArray()) {
            Set<Map.Entry<String, JsonNode>> entrySet = jsonNode.properties();
            for (Map.Entry<String, JsonNode> entry : entrySet) {
                Map<String, JsonNode> beanMap = new HashMap<>();
                JsonNode valueNode = entry.getValue();
                buildBeanMap(valueNode, beanMap);
                dataMap.put(entry.getKey(), beanMap);
            }
        }
        return dataMap;
    }

    public static void buildBeanMap(JsonNode jsonNode, Map<String, JsonNode> beanMap) {
        Set<Map.Entry<String, JsonNode>> entrySet = jsonNode.properties();
        for (Map.Entry<String, JsonNode> entry : entrySet) {
            JsonNode valueNode = entry.getValue();
            if (valueNode.isNull()) {
                continue;
            }
            beanMap.put(entry.getKey(), valueNode);
        }
    }
}

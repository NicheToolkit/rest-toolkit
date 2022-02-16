package io.github.nichetoolkit.rest.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.rest.error.supply.JsonDeserializeException;

import java.io.IOException;
import java.util.*;

/**
 * <p>DeserializeHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DeserializeHelper {

    public static Map<String, Object> deserializerBean(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            Map<String, Object> beanMap = new HashMap<>();
            if (!jsonNode.isArray()) {
                buildBeanMap(jsonNode, beanMap);
            }
            return beanMap;
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }

    }

    public static List<Object> deserializerList(JsonParser parser) throws JsonDeserializeException {
        try {
            List<Object> resultList = new ArrayList<>();
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            if (jsonNode.isArray()) {
                for (JsonNode objectNode : jsonNode) {
                    resultList.add(objectNode);
                }
            }
            return resultList;
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }

    }

    public static List<Map<String, Object>> deserializerBeanList(JsonParser parser) throws JsonDeserializeException {
        try {
            List<Map<String, Object>> resultList = new ArrayList<>();
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            if (jsonNode.isArray()) {
                for (JsonNode objectNode : jsonNode) {
                    Map<String, Object> beanMap = new HashMap<>();
                    buildBeanMap(objectNode, beanMap);
                    resultList.add(beanMap);
                }
            }
            return resultList;
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }

    }

    public static Map<String, Object> deserializerMap(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            Map<String, Object> resultMap = new HashMap<>();
            if (!jsonNode.isArray()) {
                for (Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields(); iter.hasNext(); ) {
                    Map.Entry<String, JsonNode> entry = iter.next();
                    JsonNode valueNode = entry.getValue();
                    resultMap.put(entry.getKey(), valueNode);
                }
            }
            return resultMap;
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }

    }

    public static Map<String, Map<String, Object>> deserializerBeanMap(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            Map<String, Map<String, Object>> dataMap = new HashMap<>();
            if (!jsonNode.isArray()) {
                for (Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields(); iter.hasNext(); ) {
                    Map.Entry<String, JsonNode> entry = iter.next();
                    Map<String, Object> beanMap = new HashMap<>();
                    JsonNode valueNode = entry.getValue();
                    buildBeanMap(valueNode, beanMap);
                    dataMap.put(entry.getKey(), beanMap);
                }
            }
            return dataMap;
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }

    }

    public static void buildBeanMap(JsonNode jsonNode, Map<String, Object> beanMap) {
        for (Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields(); iter.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = iter.next();
            JsonNode valueNode = entry.getValue();
            if (valueNode.isNull()) {
                continue;
            }
            if (valueNode.isTextual()) {
                beanMap.put(entry.getKey(), valueNode.asText());
            } else if (valueNode.isFloat() || valueNode.isDouble()) {
                beanMap.put(entry.getKey(), valueNode.asDouble());
            } else if (valueNode.isInt()) {
                beanMap.put(entry.getKey(), valueNode.asInt());
            } else if (valueNode.isLong()) {
                beanMap.put(entry.getKey(), valueNode.asLong());
            } else if (valueNode.isBoolean()) {
                beanMap.put(entry.getKey(), valueNode.asBoolean());
            } else {
                beanMap.put(entry.getKey(), valueNode);
            }
        }
    }
}

package io.github.nichetoolkit.rest.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.rest.error.supply.JsonDeserializeException;

import java.io.IOException;
import java.util.*;

/**
 * <code>DeserializeHelper</code>
 * <p>The deserialize helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class DeserializeHelper {

    /**
     * <code>deserializerBean</code>
     * <p>The deserializer bean method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonParser
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     * @return  {@link java.util.Map} <p>The deserializer bean return object is <code>Map</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     */
    public static Map<String, JsonNode> deserializerBean(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            return deserializerBean(jsonNode);
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    /**
     * <code>deserializerBean</code>
     * <p>The deserializer bean method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  java.util.Map
     * @return  {@link java.util.Map} <p>The deserializer bean return object is <code>Map</code> type.</p>
     */
    public static Map<String, JsonNode> deserializerBean(JsonNode jsonNode) {
        Map<String, JsonNode> beanMap = new HashMap<>();
        if (!jsonNode.isArray()) {
            buildBeanMap(jsonNode, beanMap);
        }
        return beanMap;
    }

    /**
     * <code>deserializerList</code>
     * <p>The deserializer list method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonParser
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     * @return  {@link java.util.List} <p>The deserializer list return object is <code>List</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     */
    public static List<JsonNode> deserializerList(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            return deserializerList(jsonNode);
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    /**
     * <code>deserializerList</code>
     * <p>The deserializer list method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The deserializer list return object is <code>List</code> type.</p>
     */
    public static List<JsonNode> deserializerList(JsonNode jsonNode) {
        List<JsonNode> resultList = new ArrayList<>();
        if (jsonNode.isArray()) {
            for (JsonNode objectNode : jsonNode) {
                resultList.add(objectNode);
            }
        }
        return resultList;
    }

    /**
     * <code>deserializerBeanList</code>
     * <p>The deserializer bean list method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonParser
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     * @return  {@link java.util.List} <p>The deserializer bean list return object is <code>List</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     */
    public static List<Map<String, JsonNode>> deserializerBeanList(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            return deserializerBeanList(jsonNode);
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    /**
     * <code>deserializerBeanList</code>
     * <p>The deserializer bean list method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The deserializer bean list return object is <code>List</code> type.</p>
     */
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

    /**
     * <code>deserializerMap</code>
     * <p>The deserializer map method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonParser
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     * @return  {@link java.util.Map} <p>The deserializer map return object is <code>Map</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     */
    public static Map<String, JsonNode> deserializerMap(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            return deserializerMap(jsonNode);
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }

    }

    /**
     * <code>deserializerMap</code>
     * <p>The deserializer map method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  java.util.Map
     * @return  {@link java.util.Map} <p>The deserializer map return object is <code>Map</code> type.</p>
     */
    public static Map<String, JsonNode> deserializerMap(JsonNode jsonNode) {
        Map<String, JsonNode> resultMap = new HashMap<>();
        if (!jsonNode.isArray()) {
            for (Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields(); iter.hasNext(); ) {
                Map.Entry<String, JsonNode> entry = iter.next();
                JsonNode valueNode = entry.getValue();
                resultMap.put(entry.getKey(), valueNode);
            }
        }
        return resultMap;
    }

    /**
     * <code>deserializerBeanMap</code>
     * <p>The deserializer bean map method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonParser
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     * @return  {@link java.util.Map} <p>The deserializer bean map return object is <code>Map</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     */
    public static Map<String, Map<String, JsonNode>> deserializerBeanMap(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.getCodec().readTree(parser);
            return deserializerBeanMap(jsonNode);
        } catch (IOException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    /**
     * <code>deserializerBeanMap</code>
     * <p>The deserializer bean map method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  java.util.Map
     * @return  {@link java.util.Map} <p>The deserializer bean map return object is <code>Map</code> type.</p>
     */
    public static Map<String, Map<String, JsonNode>> deserializerBeanMap(JsonNode jsonNode) {
        Map<String, Map<String, JsonNode>> dataMap = new HashMap<>();
        if (!jsonNode.isArray()) {
            for (Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields(); iter.hasNext(); ) {
                Map.Entry<String, JsonNode> entry = iter.next();
                Map<String, JsonNode> beanMap = new HashMap<>();
                JsonNode valueNode = entry.getValue();
                buildBeanMap(valueNode, beanMap);
                dataMap.put(entry.getKey(), beanMap);
            }
        }
        return dataMap;
    }

    /**
     * <code>buildBeanMap</code>
     * <p>The build bean map method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @param beanMap {@link java.util.Map} <p>The bean map parameter is <code>Map</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  java.util.Map
     */
    public static void buildBeanMap(JsonNode jsonNode, Map<String, JsonNode> beanMap) {
        for (Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields(); iter.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = iter.next();
            JsonNode valueNode = entry.getValue();
            if (valueNode.isNull()) {
                continue;
            }
            beanMap.put(entry.getKey(), valueNode);
        }
    }
}

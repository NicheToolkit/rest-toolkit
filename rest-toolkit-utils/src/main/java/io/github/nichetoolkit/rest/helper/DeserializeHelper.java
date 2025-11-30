package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.error.supply.JsonDeserializeException;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.JsonNode;

import java.util.*;

/**
 * <code>DeserializeHelper</code>
 * <p>The deserialize helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk17
 */
public class DeserializeHelper {

    /**
     * <code>deserializerBean</code>
     * <p>The deserializer bean method.</p>
     * @param parser {@link tools.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer bean return object is <code>Map</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     * @see tools.jackson.core.JsonParser
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     */
    public static Map<String, JsonNode> deserializerBean(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerBean(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    /**
     * <code>deserializerBean</code>
     * <p>The deserializer bean method.</p>
     * @param jsonNode {@link tools.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer bean return object is <code>Map</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see java.util.Map
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
     * @param parser {@link tools.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.List} <p>The deserializer list return object is <code>List</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     * @see tools.jackson.core.JsonParser
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     */
    public static List<JsonNode> deserializerList(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerList(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    /**
     * <code>deserializerList</code>
     * <p>The deserializer list method.</p>
     * @param jsonNode {@link tools.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.List} <p>The deserializer list return object is <code>List</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see java.util.List
     */
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

    /**
     * <code>deserializerBeanList</code>
     * <p>The deserializer bean list method.</p>
     * @param parser {@link tools.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.List} <p>The deserializer bean list return object is <code>List</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     * @see tools.jackson.core.JsonParser
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     */
    public static List<Map<String, JsonNode>> deserializerBeanList(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerBeanList(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    /**
     * <code>deserializerBeanList</code>
     * <p>The deserializer bean list method.</p>
     * @param jsonNode {@link tools.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.List} <p>The deserializer bean list return object is <code>List</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see java.util.List
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
     * @param parser {@link tools.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer map return object is <code>Map</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     * @see tools.jackson.core.JsonParser
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     */
    public static Map<String, JsonNode> deserializerMap(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerMap(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }

    }

    /**
     * <code>deserializerMap</code>
     * <p>The deserializer map method.</p>
     * @param jsonNode {@link tools.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer map return object is <code>Map</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see java.util.Map
     */
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

    /**
     * <code>deserializerBeanMap</code>
     * <p>The deserializer bean map method.</p>
     * @param parser {@link tools.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer bean map return object is <code>Map</code> type.</p>
     * @throws JsonDeserializeException {@link io.github.nichetoolkit.rest.error.supply.JsonDeserializeException} <p>The json deserialize exception is <code>JsonDeserializeException</code> type.</p>
     * @see tools.jackson.core.JsonParser
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.supply.JsonDeserializeException
     */
    public static Map<String, Map<String, JsonNode>> deserializerBeanMap(JsonParser parser) throws JsonDeserializeException {
        try {
            JsonNode jsonNode = parser.readValueAsTree();
            return deserializerBeanMap(jsonNode);
        } catch (JacksonException exception) {
            throw new JsonDeserializeException(exception.getMessage());
        }
    }

    /**
     * <code>deserializerBeanMap</code>
     * <p>The deserializer bean map method.</p>
     * @param jsonNode {@link tools.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer bean map return object is <code>Map</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see java.util.Map
     */
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

    /**
     * <code>buildBeanMap</code>
     * <p>The build bean map method.</p>
     * @param jsonNode {@link tools.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @param beanMap  {@link java.util.Map} <p>The bean map parameter is <code>Map</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see java.util.Map
     */
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

package io.github.nichetoolkit.rest.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.rest.error.supply.JsonDeserializeException;
import io.github.nichetoolkit.rest.helper.DeserializeHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <code>DeserializeUtils</code>
 * <p>The deserialize utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk17
 */
@Slf4j
public class DeserializeUtils {

    /**
     * <code>deserializerBean</code>
     * <p>The deserializer bean method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer bean return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.Map
     */
    public static Map<String, JsonNode> deserializerBean(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerBean(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map of bean fields!  {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyMap();
    }

    /**
     * <code>deserializerBean</code>
     * <p>The deserializer bean method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer bean return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.databind.JsonNode
     * @see java.util.Map
     */
    public static Map<String, JsonNode> deserializerBean(JsonNode jsonNode) {
        return DeserializeHelper.deserializerBean(jsonNode);
    }

    /**
     * <code>deserializerList</code>
     * <p>The deserializer list method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.List} <p>The deserializer list return object is <code>List</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.List
     */
    public static List<JsonNode> deserializerList(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerList(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as list of bean! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyList();
    }

    /**
     * <code>deserializerList</code>
     * <p>The deserializer list method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.List} <p>The deserializer list return object is <code>List</code> type.</p>
     * @see com.fasterxml.jackson.databind.JsonNode
     * @see java.util.List
     */
    public static List<JsonNode> deserializerList(JsonNode jsonNode) {
        return DeserializeHelper.deserializerList(jsonNode);
    }

    /**
     * <code>deserializerBeanList</code>
     * <p>The deserializer bean list method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.List} <p>The deserializer bean list return object is <code>List</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.List
     */
    public static List<Map<String, JsonNode>> deserializerBeanList(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerBeanList(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as list map of bean fields! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyList();
    }

    /**
     * <code>deserializerBeanList</code>
     * <p>The deserializer bean list method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.List} <p>The deserializer bean list return object is <code>List</code> type.</p>
     * @see com.fasterxml.jackson.databind.JsonNode
     * @see java.util.List
     */
    public static List<Map<String, JsonNode>> deserializerBeanList(JsonNode jsonNode) {
        return DeserializeHelper.deserializerBeanList(jsonNode);
    }

    /**
     * <code>deserializerMap</code>
     * <p>The deserializer map method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer map return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.Map
     */
    public static Map<String, JsonNode> deserializerMap(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerMap(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map of bean! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyMap();
    }

    /**
     * <code>deserializerMap</code>
     * <p>The deserializer map method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer map return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.databind.JsonNode
     * @see java.util.Map
     */
    public static Map<String, JsonNode> deserializerMap(JsonNode jsonNode) {
        return DeserializeHelper.deserializerMap(jsonNode);
    }


    /**
     * <code>deserializerBeanMap</code>
     * <p>The deserializer bean map method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer bean map return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.Map
     */
    public static Map<String,Map<String, JsonNode>> deserializerBeanMap(JsonParser parser){
        try {
            return DeserializeHelper.deserializerBeanMap(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map map of bean fields! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyMap();
    }

    /**
     * <code>deserializerBeanMap</code>
     * <p>The deserializer bean map method.</p>
     * @param jsonNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The json node parameter is <code>JsonNode</code> type.</p>
     * @return {@link java.util.Map} <p>The deserializer bean map return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.databind.JsonNode
     * @see java.util.Map
     */
    public static Map<String,Map<String, JsonNode>> deserializerBeanMap(JsonNode jsonNode) {
        return DeserializeHelper.deserializerBeanMap(jsonNode);
    }
}

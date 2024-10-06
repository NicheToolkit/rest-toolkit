package io.github.nichetoolkit.rest.util;

import com.fasterxml.jackson.core.JsonParser;
import io.github.nichetoolkit.rest.error.supply.JsonDeserializeException;
import io.github.nichetoolkit.rest.helper.DeserializeHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <code>DeserializeUtils</code>
 * <p>The type deserialize utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class DeserializeUtils {

    /**
     * <code>deserializerBean</code>
     * <p>The bean method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The bean return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.Map
     */
    public static Map<String, Object> deserializerBean(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerBean(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map of bean fields!  {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyMap();
    }

    /**
     * <code>deserializerList</code>
     * <p>The list method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.List} <p>The list return object is <code>List</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.List
     */
    public static List<Object> deserializerList(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerList(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as list of bean! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyList();
    }

    /**
     * <code>deserializerBeanList</code>
     * <p>The bean list method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.List} <p>The bean list return object is <code>List</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.List
     */
    public static List<Map<String, Object>> deserializerBeanList(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerBeanList(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as list map of bean fields! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyList();

    }


    /**
     * <code>deserializerMap</code>
     * <p>The map method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The map return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.Map
     */
    public static Map<String, Object> deserializerMap(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerMap(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map of bean! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyMap();
    }


    /**
     * <code>deserializerBeanMap</code>
     * <p>The bean map method.</p>
     * @param parser {@link com.fasterxml.jackson.core.JsonParser} <p>The parser parameter is <code>JsonParser</code> type.</p>
     * @return {@link java.util.Map} <p>The bean map return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.core.JsonParser
     * @see java.util.Map
     */
    public static Map<String,Map<String, Object>> deserializerBeanMap(JsonParser parser){
        try {
            return DeserializeHelper.deserializerBeanMap(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map map of bean fields! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return Collections.emptyMap();
    }
}

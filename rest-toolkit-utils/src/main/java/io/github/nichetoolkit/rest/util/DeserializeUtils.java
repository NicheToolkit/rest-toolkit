package io.github.nichetoolkit.rest.util;

import com.fasterxml.jackson.core.JsonParser;
import io.github.nichetoolkit.rest.error.supply.JsonDeserializeException;
import io.github.nichetoolkit.rest.helper.DeserializeHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>DeserializeUtils</p>
 *
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class DeserializeUtils {

    /**
     * JsonParser 反序列化Map方法
     * @param parser JsonParser对象
     * @return Map<String, Object>
     */
    public static Map<String, Object> deserializerBean(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerBean(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map of bean fields!",exception);
            exception.printStackTrace();
        }
        return Collections.emptyMap();
    }

    /**
     * JsonParser 反序列化List方法
     * @param parser JsonParser对象
     * @return List<Map<String, Object>>
     */
    public static List<Object> deserializerList(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerList(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as list of bean!",exception);
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * JsonParser 反序列化BeanList方法
     * @param parser JsonParser对象
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> deserializerBeanList(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerBeanList(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as list map of bean fields!",exception);
            exception.printStackTrace();
        }
        return Collections.emptyList();

    }


    /**
     * JsonParser 反序列化Map方法
     * @param parser JsonParser对象
     * @return List<Map<String, Object>>
     */
    public static Map<String, Object> deserializerMap(JsonParser parser) {
        try {
            return DeserializeHelper.deserializerMap(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map of bean!",exception);
            exception.printStackTrace();
        }
        return Collections.emptyMap();
    }


    /**
     * JsonParser 反序列化BeanMap方法
     * @param parser JsonParser对象
     * @return List<Map<String, Object>>
     */
    public static Map<String,Map<String, Object>> deserializerBeanMap(JsonParser parser){
        try {
            return DeserializeHelper.deserializerBeanMap(parser);
        } catch (JsonDeserializeException exception) {
            log.error("It is failed during json to deserialize as map map of bean fields!",exception);
            exception.printStackTrace();
        }
        return Collections.emptyMap();
    }
}

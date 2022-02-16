package io.github.nichetoolkit.rest.holder;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>ObjectMapperHolder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class ObjectMapperHolder {

    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired(required = false)
    public ObjectMapperHolder(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
    }

    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }
}

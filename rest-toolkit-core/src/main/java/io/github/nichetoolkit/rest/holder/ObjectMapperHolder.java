package io.github.nichetoolkit.rest.holder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>ObjectMapperHolder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ObjectMapperHolder {

    private static ObjectMapper OBJECT_MAPPER;

    static {
        JsonFactory jsonFactory = new JsonFactoryBuilder().build();
        OBJECT_MAPPER = new ObjectMapper(jsonFactory);
    }

    @Autowired
    public ObjectMapperHolder(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
    }

    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }
}

package io.github.nichetoolkit.rest.holder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <code>ObjectMapperHolder</code>
 * <p>The type object mapper holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ObjectMapperHolder {

    /**
     * <code>OBJECT_MAPPER</code>
     * {@link com.fasterxml.jackson.databind.ObjectMapper} <p>the constant <code>OBJECT_MAPPER</code> field.</p>
     * @see com.fasterxml.jackson.databind.ObjectMapper
     */
    private static ObjectMapper OBJECT_MAPPER;

    static {
        JsonFactory jsonFactory = new JsonFactoryBuilder().build();
        OBJECT_MAPPER = new ObjectMapper(jsonFactory);
    }

    /**
     * <code>ObjectMapperHolder</code>
     * Instantiates a new object mapper holder.
     * @param objectMapper {@link com.fasterxml.jackson.databind.ObjectMapper} <p>the object mapper parameter is <code>ObjectMapper</code> type.</p>
     * @see com.fasterxml.jackson.databind.ObjectMapper
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public ObjectMapperHolder(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
    }

    /**
     * <code>objectMapper</code>
     * <p>the mapper method.</p>
     * @return {@link com.fasterxml.jackson.databind.ObjectMapper} <p>the mapper return object is <code>ObjectMapper</code> type.</p>
     * @see com.fasterxml.jackson.databind.ObjectMapper
     */
    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }
}

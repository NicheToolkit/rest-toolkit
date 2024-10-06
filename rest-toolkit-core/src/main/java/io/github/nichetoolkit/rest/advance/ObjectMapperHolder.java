package io.github.nichetoolkit.rest.advance;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <code>ObjectMapperHolder</code>
 * <p>The type object mapper holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class ObjectMapperHolder {

    /**
     * <code>OBJECT_MAPPER</code>
     * {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The constant <code>OBJECT_MAPPER</code> field.</p>
     * @see com.fasterxml.jackson.databind.ObjectMapper
     */
    private static ObjectMapper OBJECT_MAPPER;

    static {
        JsonFactory jsonFactory = new JsonFactoryBuilder().build();
        OBJECT_MAPPER = new ObjectMapper(jsonFactory);
    }

    /**
     * <code>ObjectMapperHolder</code>
     * <p>Instantiates a new object mapper holder.</p>
     * @param objectMapper {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The object mapper parameter is <code>ObjectMapper</code> type.</p>
     * @see com.fasterxml.jackson.databind.ObjectMapper
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public ObjectMapperHolder(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
        log.debug("The object mapper holder has be initiated");
    }

    /**
     * <code>objectMapper</code>
     * <p>The mapper method.</p>
     * @return {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The mapper return object is <code>ObjectMapper</code> type.</p>
     * @see com.fasterxml.jackson.databind.ObjectMapper
     */
    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }
}

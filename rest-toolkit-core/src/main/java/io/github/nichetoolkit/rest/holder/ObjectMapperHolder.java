package io.github.nichetoolkit.rest.holder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <code>ObjectMapperHolder</code>
 * <p>The object mapper holder class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class ObjectMapperHolder {

    /**
     * <code>OBJECT_MAPPER</code>
     * {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The constant <code>OBJECT_MAPPER</code> field.</p>
     * @see  com.fasterxml.jackson.databind.ObjectMapper
     */
    private static ObjectMapper OBJECT_MAPPER;

    /**
     * <code>PURITY_MAPPER</code>
     * {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The constant <code>PURITY_MAPPER</code> field.</p>
     * @see  com.fasterxml.jackson.databind.ObjectMapper
     */
    private static final ObjectMapper PURITY_MAPPER;

    static {
        PURITY_MAPPER = ofPurityMapper();
        OBJECT_MAPPER = PURITY_MAPPER;
    }

    /**
     * <code>purityMapper</code>
     * <p>The purity mapper method.</p>
     * @return  {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The purity mapper return object is <code>ObjectMapper</code> type.</p>
     * @see  com.fasterxml.jackson.databind.ObjectMapper
     */
    public static ObjectMapper purityMapper() {
        return PURITY_MAPPER;
    }

    /**
     * <code>ofPurityMapper</code>
     * <p>The of purity mapper method.</p>
     * @return  {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The of purity mapper return object is <code>ObjectMapper</code> type.</p>
     * @see  com.fasterxml.jackson.databind.ObjectMapper
     */
    public static ObjectMapper ofPurityMapper() {
        JsonFactory jsonFactory = new JsonFactoryBuilder().build();
        return new ObjectMapper(jsonFactory);
    }

    public ObjectMapperHolder() {
    }

    /**
     * <code>ObjectMapperHolder</code>
     * <p>Instantiates a new object mapper holder.</p>
     * @param objectMapper {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The object mapper parameter is <code>ObjectMapper</code> type.</p>
     * @see  com.fasterxml.jackson.databind.ObjectMapper
     * @see  org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public ObjectMapperHolder(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
        log.debug("The object mapper holder has be initiated");
    }

    /**
     * <code>objectMapper</code>
     * <p>The object mapper method.</p>
     * @return  {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The object mapper return object is <code>ObjectMapper</code> type.</p>
     * @see  com.fasterxml.jackson.databind.ObjectMapper
     */
    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }


}

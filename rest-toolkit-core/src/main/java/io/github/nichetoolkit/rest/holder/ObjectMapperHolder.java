package io.github.nichetoolkit.rest.holder;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.type.TypeFactory;

/**
 * <code>ObjectMapperHolder</code>
 * <p>The object mapper holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk17
 */
@Slf4j
public class ObjectMapperHolder {

    /**
     * <code>OBJECT_MAPPER</code>
     * {@link tools.jackson.databind.ObjectMapper} <p>The constant <code>OBJECT_MAPPER</code> field.</p>
     * @see tools.jackson.databind.ObjectMapper
     */
    private static ObjectMapper OBJECT_MAPPER;

    /**
     * <code>JACKSON_MAPPER</code>
     * {@link tools.jackson.databind.json.JsonMapper} <p>The constant <code>JACKSON_MAPPER</code> field.</p>
     * @see tools.jackson.databind.json.JsonMapper
     */
    private static final JsonMapper JACKSON_MAPPER;

    /**
     * <code>TYPE_FACTORY</code>
     * {@link tools.jackson.databind.type.TypeFactory} <p>The constant <code>TYPE_FACTORY</code> field.</p>
     * @see tools.jackson.databind.type.TypeFactory
     */
    private static final TypeFactory TYPE_FACTORY;

    static {
        TYPE_FACTORY = TypeFactory.createDefaultInstance();
        JACKSON_MAPPER = ofJacksonMapper();
        OBJECT_MAPPER = JACKSON_MAPPER;
    }

    /**
     * <code>jacksonMapper</code>
     * <p>The jackson mapper method.</p>
     * @return {@link tools.jackson.databind.json.JsonMapper} <p>The jackson mapper return object is <code>JsonMapper</code> type.</p>
     * @see tools.jackson.databind.json.JsonMapper
     */
    public static JsonMapper jacksonMapper() {
        return JACKSON_MAPPER;
    }

    /**
     * <code>typeFactory</code>
     * <p>The type factory method.</p>
     * @return {@link tools.jackson.databind.type.TypeFactory} <p>The type factory return object is <code>TypeFactory</code> type.</p>
     * @see tools.jackson.databind.type.TypeFactory
     */
    public static TypeFactory typeFactory() {
        return TYPE_FACTORY;
    }

    /**
     * <code>ofJsonBuilder</code>
     * <p>The of json builder method.</p>
     * @return {@link tools.jackson.databind.json.JsonMapper.Builder} <p>The of json builder return object is <code>Builder</code> type.</p>
     * @see tools.jackson.databind.json.JsonMapper.Builder
     */
    public static JsonMapper.Builder ofJsonBuilder() {
        return JsonMapper.builder();
    }

    /**
     * <code>ofJacksonMapper</code>
     * <p>The of jackson mapper method.</p>
     * @return {@link tools.jackson.databind.json.JsonMapper} <p>The of jackson mapper return object is <code>JsonMapper</code> type.</p>
     * @see tools.jackson.databind.json.JsonMapper
     */
    public static JsonMapper ofJacksonMapper() {
        JsonMapper.Builder builder = JsonMapper.builder();
        builder.changeDefaultPropertyInclusion((value) -> {
            value.withValueInclusion(JsonInclude.Include.NON_NULL);
            return value.withContentInclusion(JsonInclude.Include.NON_NULL);
        });
        return builder.build();
    }

    /**
     * <code>ObjectMapperHolder</code>
     * <p>Instantiates a new object mapper holder.</p>
     */
    public ObjectMapperHolder() {
    }

    /**
     * <code>ObjectMapperHolder</code>
     * <p>Instantiates a new object mapper holder.</p>
     * @param objectMapper {@link tools.jackson.databind.ObjectMapper} <p>The object mapper parameter is <code>ObjectMapper</code> type.</p>
     * @see tools.jackson.databind.ObjectMapper
     */
    public ObjectMapperHolder(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
        log.debug("The object mapper holder has be initiated");
    }

    /**
     * <code>objectMapper</code>
     * <p>The object mapper method.</p>
     * @return {@link tools.jackson.databind.ObjectMapper} <p>The object mapper return object is <code>ObjectMapper</code> type.</p>
     * @see tools.jackson.databind.ObjectMapper
     */
    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }


}

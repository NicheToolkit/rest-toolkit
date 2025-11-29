package io.github.nichetoolkit.rest.serialize;


import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;


/**
 * <code>RestKeySerializer</code>
 * <p>The rest key serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueSerializer
 * @since Jdk17
 */
public class RestKeySerializer extends ValueSerializer<RestKey<?>> {
    @Override
    public void serialize(RestKey<?> restKey, JsonGenerator jsonGenerator, SerializationContext serializerProvider) throws
            JacksonException {
        if (GeneralUtils.isNotEmpty(restKey)) {
            jsonGenerator.writePOJO(restKey.getKey());
        }
    }
}

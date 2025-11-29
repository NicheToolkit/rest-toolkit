package io.github.nichetoolkit.rest.serialize;

import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

/**
 * <code>RestValueSerializer</code>
 * <p>The rest value serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueSerializer
 * @since Jdk17
 */
public class RestValueSerializer extends ValueSerializer<RestValue<?,?>> {
    @Override
    public void serialize(RestValue<?,?> restValue, JsonGenerator jsonGenerator, SerializationContext serializerProvider) throws
            JacksonException {
        if (GeneralUtils.isNotEmpty(restValue)) {
            jsonGenerator.writePOJO(restValue.getValue());
        }
    }
}

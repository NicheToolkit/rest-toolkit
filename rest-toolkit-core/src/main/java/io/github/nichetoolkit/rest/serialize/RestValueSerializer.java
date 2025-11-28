package io.github.nichetoolkit.rest.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.io.IOException;

/**
 * <code>RestValueSerializer</code>
 * <p>The rest value serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see com.fasterxml.jackson.databind.JsonSerializer
 * @since Jdk17
 */
public class RestValueSerializer extends JsonSerializer<RestValue<?,?>> {
    @Override
    public void serialize(RestValue<?,?> restValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws
    IOException {
        if (GeneralUtils.isNotEmpty(restValue)) {
            jsonGenerator.writeObject(restValue.getValue());
        }
    }
}

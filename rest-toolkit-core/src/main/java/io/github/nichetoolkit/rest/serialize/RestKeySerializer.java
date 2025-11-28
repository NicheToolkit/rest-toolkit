package io.github.nichetoolkit.rest.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.io.IOException;

/**
 * <code>RestKeySerializer</code>
 * <p>The rest key serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see com.fasterxml.jackson.databind.JsonSerializer
 * @since Jdk17
 */
public class RestKeySerializer extends JsonSerializer<RestKey<?>> {
    @Override
    public void serialize(RestKey<?> restKey, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws
    IOException {
        if (GeneralUtils.isNotEmpty(restKey)) {
            jsonGenerator.writeObject(restKey.getKey());
        }
    }
}

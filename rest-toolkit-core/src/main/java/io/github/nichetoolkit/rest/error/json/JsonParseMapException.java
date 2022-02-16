package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JsonParseMapException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JsonParseMapException extends JsonParseErrorException {
    public JsonParseMapException() {
        super(RestErrorStatus.JSON_PARSE_MAP);
    }

    public JsonParseMapException(String resource) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource);
    }

    public JsonParseMapException(RestStatus status) {
        super(status);
    }

    public JsonParseMapException(String resource, Object value) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource, value);
    }

    public JsonParseMapException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource, message);
    }

    public JsonParseMapException(String resource, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource, value, message);
    }

    @Override
    public JsonParseMapException get() {
        return new JsonParseMapException();
    }
}

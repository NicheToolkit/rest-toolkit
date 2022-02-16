package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JsonParseSetException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JsonParseSetException extends JsonParseErrorException {
    public JsonParseSetException() {
        super(RestErrorStatus.JSON_PARSE_SET);
    }

    public JsonParseSetException(String resource) {
        super(RestErrorStatus.JSON_PARSE_SET, resource);
    }

    public JsonParseSetException(RestStatus status) {
        super(status);
    }

    public JsonParseSetException(String resource, Object value) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, value);
    }

    public JsonParseSetException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, message);
    }

    public JsonParseSetException(String resource, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, value, message);
    }

    @Override
    public JsonParseSetException get() {
        return new JsonParseSetException();
    }
}

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

    public JsonParseSetException(RestStatus status) {
        super(status);
    }

    public JsonParseSetException(String message) {
        super(RestErrorStatus.JSON_PARSE_SET, message);
    }

    public JsonParseSetException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, message);
    }

    public JsonParseSetException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, field, message);
    }

    public JsonParseSetException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, field, value, message);
    }

    @Override
    public JsonParseSetException get() {
        return new JsonParseSetException();
    }
}

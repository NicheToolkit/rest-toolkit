package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JsonParseBeanException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JsonParseResultException extends JsonParseErrorException {
    public JsonParseResultException() {
        super(RestErrorStatus.JSON_PARSE_RESULT);
    }

    public JsonParseResultException(RestStatus status) {
        super(status);
    }

    public JsonParseResultException(String resource) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource);
    }

    public JsonParseResultException(String resource, Object value) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, value);
    }

    public JsonParseResultException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, message);
    }

    public JsonParseResultException(String resource, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, value, message);
    }

    @Override
    public JsonParseResultException get() {
        return new JsonParseResultException();
    }
}

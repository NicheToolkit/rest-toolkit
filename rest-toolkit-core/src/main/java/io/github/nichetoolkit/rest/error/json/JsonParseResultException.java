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

    public JsonParseResultException(String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, message);
    }

    public JsonParseResultException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, message);
    }

    public JsonParseResultException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, field, message);
    }

    public JsonParseResultException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, field, value, message);
    }

    @Override
    public JsonParseResultException get() {
        return new JsonParseResultException();
    }
}

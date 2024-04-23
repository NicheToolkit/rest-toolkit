package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JsonParseListException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JsonParseListException extends JsonParseErrorException {
    public JsonParseListException() {
        super(RestErrorStatus.JSON_PARSE_LIST);
    }

    public JsonParseListException(RestStatus status) {
        super(status);
    }

    public JsonParseListException(String message) {
        super(RestErrorStatus.JSON_PARSE_LIST, message);
    }

    public JsonParseListException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_LIST, resource, message);
    }

    public JsonParseListException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_LIST, resource, field, message);
    }

    public JsonParseListException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_LIST, resource, field, value, message);
    }

    @Override
    public JsonParseListException get() {
        return new JsonParseListException();
    }
}

package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JsonParseBeanException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JsonParseBeanException extends JsonParseErrorException {
    public JsonParseBeanException() {
        super(RestErrorStatus.JSON_PARSE_BEAN);
    }

    public JsonParseBeanException(RestStatus status) {
        super(status);
    }

    public JsonParseBeanException(String message) {
        super(RestErrorStatus.JSON_PARSE_BEAN, message);
    }

    public JsonParseBeanException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_BEAN, resource, message);
    }

    public JsonParseBeanException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_BEAN, resource, field, message);
    }

    public JsonParseBeanException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_BEAN, resource, field, value, message);
    }

    @Override
    public JsonParseBeanException get() {
        return new JsonParseBeanException();
    }
}

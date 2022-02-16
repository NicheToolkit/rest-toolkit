package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ParseErrorException;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JsonParseErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JsonParseErrorException extends ParseErrorException {

    public JsonParseErrorException() {
        super(RestErrorStatus.JSON_PARSE_ERROR);
    }

    public JsonParseErrorException(RestErrorStatus status) {
        super(status);
    }

    public JsonParseErrorException(String error) {
        super(RestErrorStatus.JSON_PARSE_ERROR, RestError.error(RestErrorStatus.JSON_PARSE_ERROR, error));
    }

    public JsonParseErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public JsonParseErrorException(RestStatus status, String resource) {
        super(status, RestError.error(resource, status));
    }

    public JsonParseErrorException(RestStatus status, String resource, Object value) {
        super(status, RestError.error(resource, value, status));
    }

    public JsonParseErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public JsonParseErrorException(RestStatus status, String resource, Object value, String message) {
        super(status, RestError.error(resource, value, status, message));
    }

    @Override
    public JsonParseErrorException get() {
        return new JsonParseErrorException();
    }
}

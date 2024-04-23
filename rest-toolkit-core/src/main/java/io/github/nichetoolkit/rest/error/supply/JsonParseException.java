package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JsonParseException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JsonParseException extends RestErrorException {
    public JsonParseException() {
        super(RestErrorStatus.JSON_PARSE_ERROR);
    }

    public JsonParseException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public JsonParseException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_ERROR, RestError.error(resource, RestErrorStatus.JSON_PARSE_ERROR,message));
    }

    public JsonParseException(String resource, String className, String message) {
        super(RestErrorStatus.JSON_PARSE_ERROR, RestError.error(resource, className, RestErrorStatus.JSON_PARSE_ERROR, message));
    }

    public JsonParseException(String resource, String className, String json, String message) {
        super(RestErrorStatus.JSON_PARSE_ERROR, RestError.error(resource, className, json, RestErrorStatus.JSON_PARSE_ERROR, message));
    }

    public JsonParseException(String resource, String className, RestStatus status, String message) {
        super(status, RestError.error(resource, className, status, message));
    }

    public JsonParseException(String resource, String className, String json, RestStatus status, String message) {
        super(status, RestError.error(resource,  className, json, status, message));
    }

    @Override
    public JsonParseException get() {
        return new JsonParseException();
    }
}

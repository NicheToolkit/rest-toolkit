package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ParseErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ParseErrorException extends RestErrorException {

    public ParseErrorException() {
        super(RestErrorStatus.PARSE_ERROR);
    }

    public ParseErrorException(RestErrorStatus status) {
        super(status);
    }

    public ParseErrorException(String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ParseErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ParseErrorException(RestStatus status, String error) {
        super(status,RestError.error(status, error));
    }

    public ParseErrorException(String property, String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(property, RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(String property, Object parser, String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(property, parser, RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(String property, RestStatus status) {
        super(status, RestError.error(property, status));
    }

    public ParseErrorException(String property, RestStatus status, String error) {
        super(status, RestError.error(property, status, error));
    }

    public ParseErrorException(String property, Object parser, RestStatus status) {
        super(status, RestError.error(property, parser, status));
    }

    public ParseErrorException(String property, Object parser, RestStatus status, String error) {
        super(status, RestError.error(property, parser, status, error));
    }

    public ParseErrorException(String resource, String field, String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(resource, field, RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(String resource, String field, RestStatus status) {
        super(status, RestError.error(resource, field, status));
    }

    public ParseErrorException(String resource, String field, RestStatus status, String error) {
        super(status, RestError.error(resource, field, status, error));
    }

    public ParseErrorException(String resource, String field, Object parser, String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(resource, field, parser, RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(String resource, String field, Object parser, RestStatus status) {
        super(status, RestError.error(resource, field, parser, status));
    }

    public ParseErrorException(String resource, String field, Object parser, RestStatus status, String error) {
        super(status, RestError.error(resource, field, parser, status, error));
    }

    @Override
    public ParseErrorException get() {
        return new ParseErrorException();
    }
}

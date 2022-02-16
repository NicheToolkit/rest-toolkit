package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>RepeatNameException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class NameRepeatException extends RestErrorException {
    public NameRepeatException() {
        super(RestErrorStatus.NAME_REPEATED);
    }

    public NameRepeatException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public NameRepeatException(Object value) {
        super(RestErrorStatus.NAME_REPEATED, RestError.error("name", value, RestErrorStatus.NAME_REPEATED));
    }

    public NameRepeatException(String resource, Object value) {
        super(RestErrorStatus.NAME_REPEATED, RestError.error(resource,"name", value, RestErrorStatus.NAME_REPEATED));
    }

    public NameRepeatException(String resource, Object value, String message) {
        super(RestErrorStatus.NAME_REPEATED, RestError.error(resource, "name", value, RestErrorStatus.NAME_REPEATED,message));
    }

    @Override
    public NameRepeatException get() {
        return new NameRepeatException();
    }
}

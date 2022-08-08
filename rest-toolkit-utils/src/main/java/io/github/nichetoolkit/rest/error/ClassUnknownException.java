package io.github.nichetoolkit.rest.error;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ClassErrorException;
import io.github.nichetoolkit.rest.error.natives.ConvertErrorException;

/**
 * <p>ClassUnknownException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ClassUnknownException extends ClassErrorException {
    public ClassUnknownException() {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN);
    }

    public ClassUnknownException(String message) {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN,message);
    }

    public ClassUnknownException(String resource, Object value) {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN, resource, value);
    }

    public ClassUnknownException(String resource, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN, resource, message);
    }

    public ClassUnknownException(String resource, Object value, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNKNOWN, resource, value, message);
    }

    @Override
    public ClassUnknownException get() {
        return new ClassUnknownException();
    }
}

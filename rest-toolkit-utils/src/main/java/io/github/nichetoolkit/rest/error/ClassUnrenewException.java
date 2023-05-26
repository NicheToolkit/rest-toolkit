package io.github.nichetoolkit.rest.error;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ClassErrorException;
import io.github.nichetoolkit.rest.error.natives.ConvertErrorException;

/**
 * <p>ClassUnknownException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ClassUnrenewException extends ClassErrorException {
    public ClassUnrenewException() {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW);
    }

    public ClassUnrenewException(String message) {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW,message);
    }

    public ClassUnrenewException(String resource, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW, resource, message);
    }

    public ClassUnrenewException(String resource, String field, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW, resource, field, message);
    }

    public ClassUnrenewException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.CLASS_TYPE_UNRENEW, resource,field, value, message);
    }

    @Override
    public ClassUnrenewException get() {
        return new ClassUnrenewException();
    }
}

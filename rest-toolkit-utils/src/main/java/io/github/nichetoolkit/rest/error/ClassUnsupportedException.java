package io.github.nichetoolkit.rest.error;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ConvertErrorException;

/**
 * <p>ClassUnsupportedException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ClassUnsupportedException extends ConvertErrorException {
    public ClassUnsupportedException() {
        super(RestErrorStatus.CONVERT_TYPE_UNSUPPORTED);
    }

    public ClassUnsupportedException(String message) {
        super(RestErrorStatus.CONVERT_TYPE_UNSUPPORTED,message);
    }

    public ClassUnsupportedException(String resource, Object value) {
        super(RestErrorStatus.CONVERT_TYPE_UNSUPPORTED, resource, value);
    }

    public ClassUnsupportedException(String resource, String message) {
        super(RestErrorStatus.CONVERT_TYPE_UNSUPPORTED, resource, message);
    }

    public ClassUnsupportedException(String resource, Object value, String message) {
        super(RestErrorStatus.CONVERT_TYPE_UNSUPPORTED, resource, value, message);
    }

    @Override
    public ClassUnsupportedException get() {
        return new ClassUnsupportedException();
    }
}
